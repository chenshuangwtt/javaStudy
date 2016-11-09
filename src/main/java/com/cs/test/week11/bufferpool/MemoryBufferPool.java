package com.cs.test.week11.bufferpool;

import java.nio.ByteBuffer;
import java.util.Iterator;
import java.util.TreeSet;
import java.util.concurrent.atomic.AtomicBoolean;

public class MemoryBufferPool {
	private static int defaultSize = 1024;
	private static int byte_size = 1024 * 1024 * 1;
	private static int dilatancy_size = 1024 * 1024 * 1;
	/**
	 * 是否VM托管
	 */
	private static boolean isDirect = false;
	private ByteBuffer byteBuffer = null;
	private TreeSet<MemoryBuffer> bufferSet = new TreeSet<MemoryBuffer>(new MemoryBufferCommpositor());
	public MemoryBufferPool() {
		this(byte_size, defaultSize, dilatancy_size, isDirect);
	}

	public MemoryBufferPool(boolean isDirect) {
		this(byte_size, defaultSize, dilatancy_size, isDirect);
	}

	public MemoryBufferPool(int byteSize) {
		this(byteSize, defaultSize, dilatancy_size, isDirect);
	}

	public MemoryBufferPool(int byteSize, boolean isDirect) {
		this(byteSize, defaultSize, dilatancy_size, isDirect);
	}

	public MemoryBufferPool(int byteSize, int defaultSize, int dilatancySize, boolean isDirect) {
		byte_size = byteSize;
		this.defaultSize = defaultSize;
		dilatancy_size = dilatancySize;
		this.isDirect = isDirect;
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
			this.getBufferSet().add(new MemoryBuffer(slicebuf, this.byteBuffer.position(), this.byteBuffer.limit()));
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
