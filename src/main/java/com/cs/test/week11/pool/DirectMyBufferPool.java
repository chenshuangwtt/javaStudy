package com.cs.test.week11.pool;

import java.util.Iterator;
import java.util.TreeSet;

public class DirectMyBufferPool {
	private static TreeSet<MyBufferSet> bufferSets = new TreeSet<MyBufferSet>();
	private static final int _4K = 4 * 1024;
	private static final int _16K = 4 * _4K;
	private static final int _256K = 16 * _16K;
	private static final int _64M = 256 * _256K;
	
	public static MyBuffer allocation(int size) throws Exception{
		MyBuffer myBuffer = null;
		if (size<_4K) {
			size =_4K;
		}else if(size<_16K){
			size = _16K;
		}else if(size<_256K){
			 size = _256K;
		}else if (size<_64M) {
			size = _64M;
		}else {
			throw new Exception("分配内存大小过大,出错");
		}
		if (!bufferSets.isEmpty()) {
			Iterator<MyBufferSet> iterator = bufferSets.iterator();
			while (iterator.hasNext()) {
				MyBufferSet myBufferSet = iterator.next();
				if (myBufferSet.getCapacity()==size) {
					return myBufferSet.getBuffer();
				}
			}
		}
		if (myBuffer ==null) {
			MyBufferSet myBufferSet = new MyBufferSet();
			myBuffer = new MyBuffer(size);
			myBufferSet.add(myBuffer);
			bufferSets.add(myBufferSet);
		}
		return myBuffer;
	}
	
	
	public static boolean removeBuffer(MyBuffer myBuffer){
		Iterator<MyBufferSet> iterator = bufferSets.iterator();
		while(iterator.hasNext()){
			MyBufferSet  myBufferSet = iterator.next();
			if (myBufferSet.getCapacity()==myBuffer.getSize()) {
				myBufferSet.removeBuffer(myBuffer);
				return true;
			}
		}
		return false;
	}
}
