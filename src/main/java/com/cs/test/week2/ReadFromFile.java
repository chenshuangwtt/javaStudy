package com.cs.test.week2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

public class ReadFromFile {
	/**
	 * 以字节为单位读取文件，常用于读二进制文件，如图片、声音、影像等文件。
	 */
	public static void readFileByByte(String fileName) {
		long start = System.currentTimeMillis();
		File file = new File(fileName);
		InputStream in = null;
		System.out.println("以字节为单位读取文件内容，一次读一个字节：");
		try {
			// 一次读一个字节
			in = new FileInputStream(file);
			int tempbyte;
			while ((tempbyte = in.read()) != -1) {
				// System.out.write(tempbyte);
			}
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
			return;
		} finally {
			try {
				in.close();
			} catch (IOException e1) {
					e1.printStackTrace();
			}
		}
		System.err.println("单字节 read time cost:" +
							(System.currentTimeMillis() - start));
	}

	public static void readFileByBytes(String fileName) {
		long start = System.currentTimeMillis();
		File file = new File(fileName);
		InputStream in = null;
		System.out.println("以字节为单位读取文件内容，一次读多个字节：");
		try {
			// 一次读多个字节
			byte[] tempbytes = new byte[100];
			int byteread = 0;
			in = new FileInputStream(file);
			ReadFromFile.showAvailableBytes(in);
			// 读入多个字节到字节数组中，byteread为一次读入的字节数
			while ((byteread = in.read(tempbytes)) != -1) {
				// System.out.write(tempbytes, 0, byteread);
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		} finally {
			try {
				in.close();
			} catch (IOException e1) {
					e1.printStackTrace();
			}
		}
		System.err.println(" 多字节 read time cost:" +
							(System.currentTimeMillis() - start));
	}

	/**
	 * 以字符为单位读取文件，常用于读文本，数字等类型的文件
	 */
	public static void readFileByChar(String fileName) {
		long start = System.currentTimeMillis();
		File file = new File(fileName);
		Reader reader = null;
		System.out.println("以字符为单位读取文件内容，一次读一个字符：");
		try {
			// 一次读一个字符
			reader = new InputStreamReader(new FileInputStream(file));
			int tempchar;
			while ((tempchar = reader.read()) != -1) {
				// 对于windows下，\r\n这两个字符在一起时，表示一个换行。
				// 但如果这两个字符分开显示时，会换两次行。
				// 因此，屏蔽掉\r，或者屏蔽\n。否则，将会多出很多空行。
				if (((char) tempchar) != '\r') {
					// System.out.print((char) tempchar);
				}
			}
			reader.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		System.err.println("单字符 read time cost:" + 
							(System.currentTimeMillis() - start));
	}

	// 多字符读取
	public static void readFileByChars(String fileName) throws IOException {
		long start = System.currentTimeMillis();
		File file = new File(fileName);
		Reader reader = null;
		System.out.println("以字符为单位读取文件内容，一次读多个字符：");
		try {
			// 一次读多个字符
			char[] tempchars = new char[30];
			int charread = 0;
			reader = new InputStreamReader(new FileInputStream(file));
			// 读入多个字符到字符数组中，charread为一次读取字符数
			while ((charread = reader.read(tempchars)) != -1) {
				// 同样屏蔽掉\r不显示
				if ((charread == tempchars.length) 
						&& (tempchars[tempchars.length - 1] != '\r')) {
					// System.out.print(tempchars);
				} else {
					for (int i = 0; i < charread; i++) {
						if (tempchars[i] == '\r') {
							continue;
						} else {
							// System.out.print(tempchars[i]);
						}
					}
				}
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		} finally {
			reader.close();
		}
		System.err.println("多字符 read time cost:" + 
							(System.currentTimeMillis() - start));
	}

	/**
	 * 以行为单位读取文件，常用于读面向行的格式化文件
	 */
	public static void readFileByLines(String fileName) throws IOException {
		long start = System.currentTimeMillis();
		File file = new File(fileName);
		BufferedReader reader = null;
		System.out.println("以行为单位读取文件内容，一次读一整行：");
		try {
			reader = new BufferedReader(new FileReader(file));
			String tempString = null;
			int line = 0;
			// 一次读入一行，直到读入null为文件结束
			while ((tempString = reader.readLine()) != null) {
				line++;
			}
			reader.close();
			System.err.println("read line cost:" + 
								(System.currentTimeMillis() - start));
			System.err.println("line count :" + line);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			reader.close();
		}
	}

	/**
	 * 显示输入流中还剩的字节数
	 */
	private static void showAvailableBytes(InputStream in) {
		try {
			System.out.println("当前字节输入流中的字节数为:" + in.available());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws IOException {
		String fileName = "D:\\salary.txt";
		ReadFromFile.readFileByByte(fileName);
		ReadFromFile.readFileByBytes(fileName);
		ReadFromFile.readFileByChar(fileName);
		ReadFromFile.readFileByChars(fileName);
		ReadFromFile.readFileByLines(fileName);
	}
}