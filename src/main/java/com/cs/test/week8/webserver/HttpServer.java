package com.cs.test.week8.webserver;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;

import groovy.lang.GroovyClassLoader;
import groovy.lang.GroovyObject;

public class HttpServer {
	/**
	 * WEB_ROOT是HTML和其它文件存放的目录. 这里的WEB_ROOT为工作目录下的webroot目录
	 */
	public static final String WEB_ROOT = System.getProperty("user.dir") 
										+ File.separator + "webroot";

	public static final  ConcurrentHashMap<String, Long> 
								lastModifiedMap = new ConcurrentHashMap<>();
	
	public static void main(String[] args) {
		HttpServer server = new HttpServer();
		File dir = new File(WEB_ROOT);
		if (dir.isDirectory()) {
			File []  files =dir.listFiles();
			for(int i=0;i<files.length;i++){
				lastModifiedMap.put(files[i].getName(), 
								files[i].lastModified());
			}
		}
		// 等待连接请求
		server.await();
	}

	public void await() {
		ServerSocket serverSocket = null;
		int port = 8080;
		try {
			// 服务器套接字对象
			serverSocket = new ServerSocket(port, 1, InetAddress.getByName("127.0.0.1"));
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
		// 循环等待一个请求
		while (true) {
			Socket socket = null;
			InputStream input = null;
			OutputStream output = null;
			try {
				// 等待连接，连接成功后，返回一个Socket对象
				socket = serverSocket.accept();
				input = socket.getInputStream();
				output = socket.getOutputStream();

				// 创建Request对象并解析
				Request request = new Request(input);
				request.parse();
				System.err.println(request.getUri());
				String filepath = HttpUtil.UrlPage(request.getUri());
				Map<String, String> paramMap = HttpUtil.URLRequest(request.getUri());
				System.out.println(filepath);
				System.out.println(paramMap);
				// 请求是msp的
				if (filepath.endsWith(".msp")) {
					ClassLoader parent = ClassLoader.getSystemClassLoader();
					GroovyClassLoader loader = new GroovyClassLoader(parent);
					Class groovyClass = loader.parseClass(new File(HttpServer.WEB_ROOT, filepath));
					GroovyObject groovyObject = (GroovyObject) groovyClass.newInstance();
					String res = (String) groovyObject.invokeMethod("loginAction",
							new String[] { paramMap.get("name"), paramMap.get("password") });
					System.out.println("res=" + res);
					// 创建 Response 对象
					Response response = new Response(output);
					response.setRequest(request);
					response.output.write(res.getBytes());
				} else if (filepath.endsWith(".mspjs")) {
					ScriptEngineManager scriptEngineManager = new ScriptEngineManager();
					ScriptEngine nashorn = scriptEngineManager.getEngineByName("nashorn");
					try {
						nashorn.eval(new FileReader(HttpServer.WEB_ROOT+filepath));
						if (nashorn instanceof Invocable) {
							// 调用JS方法
							Invocable invocable = (Invocable) nashorn;
							String result = (String) invocable.invokeFunction("login", new Object[] { 
										paramMap.get("name"), paramMap.get("password")});
							Response response = new Response(output);
							response.setRequest(request);
							response.output.write(result.getBytes());
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				}else if(filepath.endsWith(".mspjava")){ 
					JspPervlet jspPervlet = new JspPervlet();
					Response response = new Response(output);
					jspPervlet.handlerRequest(request, response);
				}else{
					// 创建 Response 对象
					Response response = new Response(output);
					response.setRequest(request);
					response.sendStaticResource();
				}
				// 关闭 socket 对象
				socket.close();
			} catch (Exception e) {
				e.printStackTrace();
				continue;
			}
		}
	}
}