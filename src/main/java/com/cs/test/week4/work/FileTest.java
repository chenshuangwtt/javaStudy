package com.cs.test.week4.work;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class FileTest {
	public static void main(String[] args) throws Exception {
		BufferedReader reader = null;
		BufferedWriter writer = null;
		try {
			File file = new File("D:", "salary.txt");
//			file.createNewFile(); // 创建文件
//			FileOutputStream out = new FileOutputStream(file);
//			// 设置输出文件编码格式
//			writer = new BufferedWriter(new OutputStreamWriter(out, "UTF-8"));
//			// 生成数据
//			for (int i = 0; i < 10000000; i++) {
//				writer.write(new Salary().write() + "\n");
//			}
//			writer.close();
			
			long start = System.currentTimeMillis();
			FileInputStream inputStream = new FileInputStream(file);
			InputStreamReader isr = new InputStreamReader(inputStream, "UTF-8");
			reader = new BufferedReader(isr);
			String temp;
			String[] tempArr = null;
			List<Salary> list = new ArrayList<>();
			while ((temp = reader.readLine()) != null) {
				tempArr = temp.split(",");
				Salary salary = new Salary(tempArr[0].substring(0, 2), Integer.parseInt(tempArr[1]),
						Integer.parseInt(tempArr[2]));
				if(salary.totalSalary() > 10){
					list.add(salary);
				}
			}
			// 根据姓名进行分组，
			Map<String, Long> map = list.parallelStream()
					.collect(Collectors.groupingBy(Salary::getName, Collectors.summingLong(Salary::totalSalary)));
			// 姓名 计数
			Map<String, Long> mapCount = list.parallelStream()
					.collect(Collectors.groupingBy(Salary::getName, Collectors.counting()));
			
			List<Map.Entry<String, Long>> mapList = new ArrayList<Map.Entry<String, Long>>(map.entrySet());
			
			Collections.sort(mapList, (o1, o2) -> (int) (o2.getValue() - o1.getValue()));
			for (int i = 0; i < 10; i++) {
				Long count = mapCount.get(mapList.get(i).getKey());
				System.out.println(mapList.get(i).getKey() + "," + mapList.get(i).getValue() + "," + count);
			}
			System.err.println("共耗时：" + (System.currentTimeMillis() - start));
		} finally {
			reader.close();
		}
	}
}
