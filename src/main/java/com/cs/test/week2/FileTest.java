package com.cs.test.week2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

public class FileTest {
	public static void main(String[] args) throws Exception {
		BufferedReader reader = null;
		BufferedWriter writer  = null;
		try {
			File file = new File("D:", "salary.txt");
//			file.createNewFile(); // 创建文件
//			FileOutputStream out = new FileOutputStream(file);
//			//设置输出文件编码格式
//			writer = new BufferedWriter(new OutputStreamWriter(out, "UTF-8"));
//			//生成数据
//			for (int i = 0; i < 10000000; i++) {
//				writer.write(new Salary().write()+"\n");
//			}
//			writer.close();
			
			//开始读取数据并分组  Map并不是最好的 键的个数有可能特别多
			long start = System.currentTimeMillis();
			FileInputStream inputStream = new FileInputStream(file);
			InputStreamReader isr = new InputStreamReader(inputStream,"UTF-8");
			reader = new BufferedReader(isr);
			String temp;
			Map<String, Item> resultMap = new TreeMap<String, Item>();
			int lines =0;
			while ((temp = reader.readLine())!= null) {
				lines++;
				String[] tempArr = temp.split(",");
				if (resultMap.containsKey(tempArr[0].substring(0, 2))) {
					Item item = resultMap.get(tempArr[0].substring(0, 2));
					item.setSum(item.getSum() + Integer.parseInt(tempArr[1])*13+Integer.parseInt(tempArr[2]));  //计算年薪
					item.setCount(item.getCount() + 1);
				} else {
					Item item = new Item(Integer.parseInt(tempArr[1])*13+Integer.parseInt(tempArr[2]),1);//初始化一个item 
					resultMap.put(tempArr[0].substring(0, 2), item);
				}
			}
			
			// 这里将map.entrySet()转换成list
			List<Map.Entry<String, Item>> list = new ArrayList<Map.Entry<String, Item>>(resultMap.entrySet());
			// 然后通过比较器来实现排序
			Collections.sort(list, new Comparator<Map.Entry<String, Item>>() {
				// 升序排序
				public int compare(Entry<String, Item> o1, Entry<String, Item> o2) {
					return o1.getValue().getSum() > o2.getValue().getSum() ? -1 : 1;
				}
			});
			int  count = 0;
			for (Map.Entry<String, Item> mapping : list) {
				System.out.println(mapping.getKey() + ":" + mapping.getValue().toString());
				count++;
				if(count == 10){
					break;
				}
			}
			System.err.println(count);
			System.err.println("共耗时："+(System.currentTimeMillis()- start));
			System.out.println("line count is "+lines);
		} finally {
			reader.close();
		}
	}
}
