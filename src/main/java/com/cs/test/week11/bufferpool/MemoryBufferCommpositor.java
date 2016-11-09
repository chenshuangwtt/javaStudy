package com.cs.test.week11.bufferpool;

import java.util.Comparator;

public class MemoryBufferCommpositor implements Comparator<MemoryBuffer> {

	public int compare(MemoryBuffer o1, MemoryBuffer o2) {
		int position_1 = o1.getPosition();
		int position_2 = o2.getPosition();

		if (position_1 > position_2) {
			return 1;
		}
		if (position_1 < position_2) {
			return -1;
		}
		return 0;
	}

}
