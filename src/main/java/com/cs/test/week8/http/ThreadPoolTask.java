package com.cs.test.week8.http;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Serializable;
import java.io.Writer;
import java.net.Socket;
import java.util.Date;
import java.util.StringTokenizer;

public class ThreadPoolTask implements Runnable, Serializable {

	private static final long serialVersionUID = 0;
	private File documentRootDirectory;
	private String indexFileName = "index.html";
	private Object threadPoolTaskData;

	ThreadPoolTask(File documentRootDirectory, String indexFileName, Object tasks) {
		if (documentRootDirectory.isFile()) {
			throw new IllegalArgumentException("documentRootDirectory"
					+ " must be a directory, not a file");
		}
		this.documentRootDirectory = documentRootDirectory;
		try {
			this.documentRootDirectory = documentRootDirectory.getCanonicalFile();
		} catch (IOException ex) {
		}
		if (indexFileName != null)
			this.indexFileName = indexFileName;
		this.threadPoolTaskData = tasks;
	}

	public void run() {
		String root = documentRootDirectory.getPath();
		// 建立socket connection对象
		Socket connection;
		connection = (Socket) threadPoolTaskData;
		try {
			String filename;
			String contentType;
			OutputStream raw = new BufferedOutputStream(connection.getOutputStream());
			Writer out = new OutputStreamWriter(raw);
			Reader in = new InputStreamReader(new BufferedInputStream(
								connection.getInputStream()), "ASCII");
			StringBuffer requestLine = new StringBuffer();

			// 读取输入流，这里只读第一行
			int c;
			while (true) {
				c = in.read();
				if (c == '\r' || c == '\n')
					break;
				requestLine.append((char) c);
			}

			String get = requestLine.toString();
			// log the request
			System.out.println("请求的数据是："+get);
			StringTokenizer st = new StringTokenizer(get);
			String method = st.nextToken();
			String version = "";
			if (method.equals("GET")) {
				filename = st.nextToken();
				if (filename.endsWith("/"))
					filename += indexFileName;
				contentType = guessContentTypeFromName(filename);
				if (st.hasMoreTokens()) {
					version = st.nextToken();
				}
				File theFile = new File(documentRootDirectory, 
								filename.substring(1, filename.length()));
				if (theFile.canRead()
						&& theFile.getCanonicalPath().startsWith(root)) {
					DataInputStream fis = new DataInputStream(new BufferedInputStream(
														new FileInputStream(theFile)));
					byte[] theData = new byte[(int) theFile.length()];
					fis.readFully(theData);
					fis.close();
					if (version.startsWith("HTTP ")) { // send a MIME header
						out.write("HTTP/1.0 200 OK\r\n");
						Date now = new Date();
						out.write("Date: " + now + "\r\n");
						out.write("Server: javaHTTP/1.0\r\n");
						out.write("Content-length: " + theData.length + "\r\n");
						out.write("Content-type: " + contentType + "\r\n\r\n");
						out.flush();
					} // end if
					raw.write(theData);
					raw.flush();
				} // end if

				// 如果文件不存在，返回404
				else { // can't find the file
					if (version.startsWith("HTTP ")) { // send a MIME header
						out.write("HTTP/1.0 404 File Not Found\r\n");
						Date now = new Date();
						out.write("Date: " + now + "\r\n");
						out.write("Server: JavaHTTP/1.1\r\n");
						out.write("Content-type: text/html\r\n\r\n");
					}
					out.write("<HTML>\r\n");
					out.write("<HEAD><TITLE>File Not Found</TITLE>\r\n");
					out.write("</HEAD>\r\n");
					out.write("<BODY>");
					out.write("<H1>HTTP Error 404: File Not Found</H1>\r\n");
					out.write("</BODY></HTML>\r\n");
					out.flush();
				}
			}else { // method does not equal "GET"
				if (version.startsWith("HTTP ")) { // send a MIME header
					out.write("HTTP/1.0 501 Not Implemented\r\n");
					Date now = new Date();
					out.write("Date: " + now + "\r\n");
					out.write("Server: JavaHTTP 1.1\r\n");
					out.write("Content-type: text/html\r\n\r\n");
				}
				out.write("<HTML>\r\n");
				out.write("<HEAD><TITLE>Not Implemented</TITLE>\r\n");
				out.write("</HEAD>\r\n");
				out.write("<BODY>");
				out.write("<H1>HTTP Error 501: Not Implemented</H1>\r\n");
				out.write("</BODY></HTML>\r\n");
				out.flush();
			}
		} catch (IOException ex) {
		} finally {
			try {
				connection.close();
			} catch (IOException ex) {
			}
		}
	}

	public static String guessContentTypeFromName(String name) {
		if (name.endsWith(".html") || name.endsWith(".htm")) {
			return "text/html";
		} else if (name.endsWith(".txt") || name.endsWith(".java")) {
			return "text/plain";
		} else if (name.endsWith(".gif")) {
			return "image/gif";
		} else if (name.endsWith(".class")) {
			return "application/octet-stream";
		} else if (name.endsWith(".jpg") || name.endsWith(".jpeg")) {
			return "image/jpeg";
		} else
			return "text/plain";
	}
}