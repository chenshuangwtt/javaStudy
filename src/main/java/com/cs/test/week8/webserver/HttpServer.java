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
import java.util.Map;

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
	public static final String WEB_ROOT = System.getProperty("user.dir") + File.separator + "webroot";

	// 关闭服务命令
	private static final String SHUTDOWN_COMMAND = "/SHUTDOWN";

	public static void main(String[] args) {
		HttpServer server = new HttpServer();
		// 等待连接请求
		server.await();
		// System.out.println(System.getProperty("user.dir") + File.separator +
		// "webroot");
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
				// 检查是否是关闭服务命令
				if (request.getUri().equals(SHUTDOWN_COMMAND)) {
					break;
				}
				System.err.println(request.getUri());
				String filepath = UrlPage(request.getUri());
				Map<String, String> paramMap = URLRequest(request.getUri());
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
					// GroovyScriptEngine groovyScriptEngine = new
					// GroovyScriptEngine(HttpServer.WEB_ROOT);
					// Binding binding = new Binding();
					// for (String key : paramMap.keySet()) {
					// binding.setVariable(key, paramMap.get(key));
					// }
					// groovyScriptEngine.run(filepath, binding);
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
							String result = (String) invocable.invokeFunction("login", new Object[] { paramMap.get("name"), paramMap.get("password")});
							Response response = new Response(output);
							response.setRequest(request);
							response.output.write(result.getBytes());
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				}else if(filepath.endsWith(".mspjava")){ 
					JavaCompiler javaCompiler =ToolProvider.getSystemJavaCompiler();
				
				
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

	/**
	 * 解析出url参数中的键值对 如 "index.jsp?Action=del&id=123"，解析出Action:del,id:123存入map中
	 * 
	 * @param URL
	 *            url地址
	 * @return url请求参数部分
	 */
	public static Map<String, String> URLRequest(String URL) {
		Map<String, String> mapRequest = new HashMap<String, String>();
		String[] arrSplit = null;
		String strUrlParam = TruncateUrlPage(URL);
		if (strUrlParam == null) {
			return mapRequest;
		}
		// 每个键值为一组
		arrSplit = strUrlParam.split("[&]");
		for (String strSplit : arrSplit) {
			String[] arrSplitEqual = null;
			arrSplitEqual = strSplit.split("[=]");

			// 解析出键值
			if (arrSplitEqual.length > 1) {
				// 正确解析
				mapRequest.put(arrSplitEqual[0], arrSplitEqual[1]);
			} else {
				if (arrSplitEqual[0] != "") {
					// 只有参数没有值，不加入
					mapRequest.put(arrSplitEqual[0], "");
				}
			}
		}
		return mapRequest;
	}

	private static String TruncateUrlPage(String strURL) {
		String strAllParam = null;
		String[] arrSplit = null;
		strURL = strURL.trim().toLowerCase();
		arrSplit = strURL.split("[?]");
		if (strURL.length() > 1) {
			if (arrSplit.length > 1) {
				if (arrSplit[1] != null) {
					strAllParam = arrSplit[1];
				}
			}
		}
		return strAllParam;
	}

	public static String UrlPage(String strURL) {
		String strPage = null;
		String[] arrSplit = null;
		strURL = strURL.trim().toLowerCase();
		arrSplit = strURL.split("[?]");
		if (strURL.length() > 0) {
			if (arrSplit.length > 1) {
				if (arrSplit[0] != null) {
					strPage = arrSplit[0];
				}
			}
		}
		return strPage;
	}
}