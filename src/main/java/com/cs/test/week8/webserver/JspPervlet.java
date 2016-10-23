package com.cs.test.week8.webserver;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Map;

import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;

public class JspPervlet implements pervlet {
	@Override
	public Response handlerRequest(Request request, Response response) {
		JavaCompiler javaCompiler = ToolProvider.getSystemJavaCompiler();
		String filepath = HttpUtil.UrlPage(request.getUri());
		try {
			File file = new File(HttpServer.WEB_ROOT+filepath);
			System.err.println("文件的上次更新时间:"+HttpServer.lastModifiedMap.get(file.getName()));
			System.err.println("文件的更新时间："+file.lastModified());
			System.err.println(file.getName());
			//检测文件最后修改时间是否变更
			String classFile = filepath.substring(0, filepath.lastIndexOf(".")) + ".class";
			File  tempFile = new File(HttpServer.WEB_ROOT + classFile);
			if (file.lastModified() != HttpServer.lastModifiedMap.get(file.getName()) 
					|| !tempFile.exists()) {
				HttpServer.lastModifiedMap.put(file.getName(), file.lastModified());
				String fileName = HttpServer.WEB_ROOT + filepath;
				BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(fileName), "UTF-8"));
				
				String javaFile = filepath.substring(0, filepath.lastIndexOf(".")) + ".java";
				BufferedWriter bw = new BufferedWriter(
						new OutputStreamWriter(new FileOutputStream(HttpServer.WEB_ROOT + javaFile), "UTf-8"));
				String line = "";
				while ((line = br.readLine()) != null) {
					bw.write(line);
				}
				br.close();
				bw.flush();
				bw.close();
				int flag = javaCompiler.run(null, null, null, HttpServer.WEB_ROOT + javaFile);
				System.out.println(flag == 0 ? "编译成功" : "编译失败");
			}
			String result = "";
			URL[] urls = new URL[] { new URL("file:/" + HttpServer.WEB_ROOT + "/") };
			URLClassLoader loader = new URLClassLoader(urls);
			// 通过反射调用此类
			Class clazz = loader.loadClass("login");
			Map<String, String> paramMap = HttpUtil.URLRequest(request.getUri());
			Method m = clazz.getMethod("loginAction", new Class[] { String.class, String.class });
			result = (String) m.invoke(clazz.newInstance(),
					new Object[] { paramMap.get("name"), paramMap.get("password") });
			response.output.write(result.getBytes());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}

}
