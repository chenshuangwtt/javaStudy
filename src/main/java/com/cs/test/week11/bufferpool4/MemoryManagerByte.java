package com.cs.test.week11.bufferpool4;

import java.nio.ByteBuffer;
import java.util.Iterator;
import java.util.TreeSet;

/**
 * 该内存管理对象主要是在当你需要长时间的new一快内存的时候使用，
 * 主要作用是为了不让GC对这些内存不停的释放分配而消耗性能，而且每次获取的内存大小可以是自己指定的大小）
 * 本内存管理对象主要通过预先分配一个大的ByteBuffer然后每次从这个ByteBuffer中获取一小块内存出来进行使用，
 * 具体获取内存的规则是在打的内存中获取一段连续的内存出来使用，如果中间有一小段内存(例如三个字节)未可以使用，
 * 但如果你获取的内存都币这三内存大的话，则永远获取不到该内存（这里就会产生一小块的内存碎片）， 当然只要该小段内存的前后被释放后将又可以获取使用
 * 主要是通过ByteBuffer的子序列来做到的，如果当预先分配的内存不足的时候，将重新分配另快大的内存
 * (该该重新分配的内存也是有初始化的时候用使用者设置，重新分配的内存将由本内存管理对象中的子内存管理对象所拥有,
 * 主要算法是通过链表的形式来实现，理论上当总内存不可用的时候可以无限分配新的内存)
 */
public class MemoryManagerByte {
	private static int defaultSize = 1024;
	private static int byte_size = 1024 * 1024 * 1;
	private static int dilatancy_size = 1024 * 1024 * 1;
	private static boolean isDirect = false;
	private ByteBuffer byteBuffer = null;
	private TreeSet<MemoryBuffer> bufferSet = new TreeSet<MemoryBuffer>(new MemoryBufferCommpositor());
	public MemoryManagerByte() {
		this(byte_size, defaultSize, dilatancy_size, isDirect);
	}
	public MemoryManagerByte(boolean isDirect) {
		this(byte_size, defaultSize, dilatancy_size, isDirect);
	}
	public MemoryManagerByte(int byteSize) {
		this(byteSize, defaultSize, dilatancy_size, isDirect);
	}
	public MemoryManagerByte(int byteSize, boolean isDirect) {
		this(byteSize, defaultSize, dilatancy_size, isDirect);
	}

	public MemoryManagerByte(int byteSize, int defaultSize,
			int dilatancySize, boolean isDirect) {
		byte_size = byteSize;
		defaultSize = defaultSize;
		dilatancy_size = dilatancySize;
		isDirect = isDirect;
		if (isDirect) {
			byteBuffer = ByteBuffer.allocateDirect(byte_size);
		} else {
			byteBuffer = ByteBuffer.allocate(byte_size);
		}
	}

	public ByteBuffer allocate() {  
        return this.allocate(defaultSize);  
    }  
	
    public ByteBuffer allocate(int size) {  
    //先从总内存中获取  
        ByteBuffer byteBuffer = gain(size);  
        return byteBuffer;  
    }  
    
    //从byteBuffer中获取一块内存出来使用
    private ByteBuffer gain(int size) { 
    	boolean bor = false; 
    	//如果还没有获取过内存就直接从第一个位置开始获取  
        if(bufferSet == null || bufferSet.size()<=0){  
            this.byteBuffer.position(0);  
            this.byteBuffer.limit(size);  
            bor = true;  
        }else{
        	//如果之前获取过
        	synchronized (this.bufferSet) {
        		//遍历之前获取的内存对象 拿到它的索引值 根据索引值来接着后面的位置获取  
        		Iterator<MemoryBuffer> iterator = bufferSet.iterator();
        		int  position = 0;
        		while(iterator.hasNext()){
        			MemoryBuffer memoryBuffer = iterator.next();
        			if (memoryBuffer.getPosition() - position >= size) {
						this.byteBuffer.position(position);
						this.byteBuffer.limit(memoryBuffer.getPosition());
						bor = true;
					}
        		}
        		if ((byte_size -position) >= size) {
        			this.byteBuffer.position(position);  
                    this.byteBuffer.limit(position + size);  
                    bor = true; 
				}
			}
        }
        ByteBuffer slicebuf = null;
        if (bor) {
			slicebuf = this.byteBuffer.slice();
			this.getBufferSet().add(new MemoryBuffer(slicebuf, 
						this.byteBuffer.position(), this.byteBuffer.limit()));
		}
        this.byteBuffer.clear();
		return slicebuf;
    }
    
    private ByteBuffer getByteBuffer() {  
        return byteBuffer;  
    }  
    
    /** 
     * 返回正在使用的ByteBuffer队列，用来标示有哪些区间已经在使用了 
     */  
    private TreeSet<MemoryBuffer> getBufferSet() {  
        return bufferSet;  
    }  
    
    /**
     * 释放Bytebuffer    
     * @param buf
     * @throws Exception
     */
    public void free(ByteBuffer buf) throws Exception {  
    	synchronized (this.bufferSet) {
    		Iterator<MemoryBuffer> iterator = bufferSet.iterator();
    		while(iterator.hasNext()){
				if (!getBufferSet().contains(iterator.next())) {
					this.getBufferSet().remove(iterator.next());
				}
			}
		}
    }  
}
