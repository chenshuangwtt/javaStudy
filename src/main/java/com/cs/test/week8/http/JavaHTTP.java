package com.cs.test.week8.http;

import java.net.*;
import java.io.*;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class JavaHTTP extends Thread {
	// 文件所在目录
	private File documentRootDirectory;
	// 默认的文件名
	private String indexFileName = "index.html";
	private ServerSocket server;

	public JavaHTTP(File documentRootDirectory, int port, String indexFileName) 
							throws IOException {
		if (!documentRootDirectory.isDirectory()) {
			throw new IOException(documentRootDirectory + " does not exist as a directory");
		}
		this.documentRootDirectory = documentRootDirectory;
		this.indexFileName = indexFileName;
		this.server = new ServerSocket(port);
	}

	public JavaHTTP(File documentRootDirectory, int port) 
				throws IOException {
		this(documentRootDirectory, port, "index.html");
	}

	public JavaHTTP(File documentRootDirectory) 
				throws IOException {
		this(documentRootDirectory, 80, "index.html");
	}

	public void run() {
		// 建立一个Http Request线程池
		ThreadPoolExecutor threadPool = new ThreadPoolExecutor(2, 4, 3, TimeUnit.SECONDS,
				new ArrayBlockingQueue<Runnable>(3), new ThreadPoolExecutor.AbortPolicy());

		System.out.println("Accepting connections on port " + server.getLocalPort());
		System.out.println("Document Root: " + documentRootDirectory);
		while (true) {
			try {
				// 使用服务器端socket，接受http请求
				Socket request = server.accept();
				// 用线程池处理http请求
				threadPool.execute(new ThreadPoolTask(documentRootDirectory, indexFileName, request));
			} catch (IOException ex) {
			}
		}
	}
	
	public static void main(String[] args) {
		File docroot;
		try {
			docroot = new File(System.getProperty("user.dir") 
					+ File.separator + "webroot");
		} catch (ArrayIndexOutOfBoundsException ex) {
			System.out.println("Usage: java JavaHTTP docroot port indexfile");
			return;
		}
		int port;
		try {
			port = Integer.parseInt(args[1]);
			if (port < 0 || port > 65535)
				port = 80;
		} catch (Exception ex) {
			port = 80;
		}

		try {
			JavaHTTP webserver = new JavaHTTP(docroot, port);
			webserver.start();
		} catch (IOException ex) {
			System.out.println("Server could not start because of an " + ex.getClass());
			System.out.println(ex);
		}
	}
}
