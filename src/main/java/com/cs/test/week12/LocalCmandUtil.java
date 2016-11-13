package com.cs.test.week12;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class LocalCmandUtil {
	public static String callCmdAndgetResult(String cmd) {
		StringBuilder result = new StringBuilder();
		try {
			ProcessBuilder processBuilder = new ProcessBuilder(cmd.split("\\s")); // 创建进程管理实例
			Process process = processBuilder.start(); // 启动线程
			InputStream inputStream = process.getInputStream(); // 获取输入流
			InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "GBK");
			BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
			String line;
			while ((line = bufferedReader.readLine()) != null) { // 循环读取数据
				result.append(line);
			}
			inputStream.close();
			inputStreamReader.close();
			bufferedReader.close();
			process.waitFor();
		} catch (Exception e) {
			result.append(e.toString());
		}
		return result.toString();
	}
}
