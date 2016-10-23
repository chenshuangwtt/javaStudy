package com.cs.test.week8.http;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class MyServer {
	public static void main(String[] args) {
		try {
			ServerSocket serverSocket = new ServerSocket(80);
			while (true) {
				Socket socket = serverSocket.accept();
				System.out.println("Request:" + socket.toString() + " connected");
				LineNumberReader lineNumberReader = new LineNumberReader(
						new InputStreamReader(socket.getInputStream()));
				String lineInput;
				String requestPage = null;
				String userInfo = null;
				while ((lineInput = lineNumberReader.readLine()) != null) {
					if (lineNumberReader.getLineNumber() == 1) {
						requestPage = lineInput.substring(lineInput.indexOf('/') + 1, lineInput.lastIndexOf(' '));
						System.out.println("request page:" + requestPage);
					} else {
						if (lineInput.startsWith("Cookie: ")) {
							userInfo = lineInput;
							System.out.println("new User" + lineInput);
						} else if (lineInput.isEmpty()) {
							System.out.println("header finished");
							doResponseGet(userInfo, requestPage, socket);
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void doResponseGet(String userInfo, String requestPage, Socket socket) throws IOException {
		final String WEB_ROOT = System.getProperty("user.dir") + File.separator + "webroot";
		File theFile = new File(WEB_ROOT, requestPage);
		OutputStream outputStream = socket.getOutputStream();
		if (theFile.exists()) {
			InputStream inputStream = new FileInputStream(theFile);
			byte[] buf = new byte[inputStream.available()];
			inputStream.read(buf);
			inputStream.close();
			outputStream.write(buf);
			outputStream.flush();
			socket.close();
			System.out.println("request complete.");
		} else {
			String msg = "I can't find bao zang....cry..\r\n";
			String response = "HTTP/1.1 200 OK\r\n";
			response += "Server:My Server/0.1\r\n";
			if (userInfo == null) {
				response += genCookieHeader();
			}
			response += "Content-Length:" + (msg.length() - 4) + "\r\n";
			response += "\r\n";
			response += msg;
			outputStream.write(response.getBytes());
			outputStream.flush();
		}
	}

	private static String genCookieHeader() {
		String header = "Set-Cookie: jsessionid=" + System.currentTimeMillis() + ".basara;domian=localhost" + "\r\n";
		header += "Set-Cookie: autologin=true;domain=localhost" + "\r\n";
		return header;
	}

}
