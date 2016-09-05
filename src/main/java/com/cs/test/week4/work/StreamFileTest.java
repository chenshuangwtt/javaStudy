package com.cs.test.week4.work;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.HashMap;
import java.util.IntSummaryStatistics;
import java.util.Map;
import java.util.Map.Entry;

public class StreamFileTest {
	public static void main(String[] args) throws Exception {
		BufferedReader reader = null;
		BufferedWriter writer = null;
		try {
			// File file = new File("D:", "salary.txt");
			// file.createNewFile(); // 创建文件
			// FileOutputStream out = new FileOutputStream(file);
			// // 设置输出文件编码格式
			// writer = new BufferedWriter(new OutputStreamWriter(out,
			// "UTF-8"));
			// // 生成数据
			// for (int i = 0; i < 10000000; i++) {
			// writer.write(new Salary().write() + "\n");
			// }
			// writer.close();

			long start = System.currentTimeMillis();
			HashMap<String, IntSummaryStatistics> resultMap = Files.lines(Paths.get("D:", "salary.txt"))
					.collect(new SalaryCollector());
			resultMap.entrySet().stream().sorted(new IntSummaryStatisticsComparator()).limit(10)
			.forEach(s-> System.out.println(s.getKey()+":"+s.getValue().toString()));
			System.err.println("共耗时：" + (System.currentTimeMillis() - start));
		} finally {
			// reader.close();
		}
	}
}
