package com.cs.test.week11.bufferpool3;

import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.LinkedList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FixedDirectBufferPool implements BufferAllocator {

	protected final static Logger LOG = LoggerFactory  
            .getLogger(FixedDirectBufferPool.class);
	private final LinkedList<ByteBuffer> readyQueue;  
    private final HashMap<BufferHolder, ByteBuffer> dirtyQueue;  
    private final int poolSize;  
    private final int bufferSize;
	
    public FixedDirectBufferPool(int bufferSize, int poolSize) {  
        this.poolSize = poolSize;  
        this.bufferSize = bufferSize;  
        this.readyQueue = new LinkedList<>();  
        this.dirtyQueue = new HashMap<>();  
        for (int i = 0; i < poolSize; i++) {  
            this.readyQueue.add(ByteBuffer.allocateDirect(bufferSize));  
        }  
        LOG.info("Initialize the Direct Buffer Pool, size:{}",  
                this.readyQueue.size());  
    }  
    
	@Override
	public void allocate(BufferHolder holder) {
		 allocate(holder, bufferSize);  
	}

	@Override
	public synchronized void allocate(BufferHolder holder, int bufferSize) {
		if (bufferSize<=this.bufferSize &&!this.readyQueue.isEmpty()) {
			if (this.dirtyQueue.containsKey(holder)) {  
                LOG.info( "the holder({}) has already hold a buffer, so do nothing",  
                        holder);  
                return;  
            }  
			// poll an element  
            ByteBuffer buffer = readyQueue.poll();  
            // hold the buffer  
            holder.hold(buffer);  
            // register the holder  
            this.dirtyQueue.put(holder, buffer);  
  
            if (LOG.isDebugEnabled()) {  
                LOG.debug(  
                        "[ALLOCATE SUCCESS] - holder:{}, pool:[ready:{}, dirty:{}]",  
                        holder, readyQueue.size(), dirtyQueue.size());  
            }  
		}else{
			 LOG.warn(  
	                    "[ALLOCATE FAILED] - ready:{}, dirty:{}, need bufferSize:{}",  
	                    readyQueue.size(), dirtyQueue.size(), bufferSize);  
	            holder.hold((ByteBuffer.allocate(bufferSize)));  
	            if (LOG.isDebugEnabled()) {  
	                LOG.debug("allocate no-direct buffer, holder:{}", holder);  
	            }  
		}
		
	}

	@Override
	public synchronized void release(BufferHolder holder) {
		if (holder != null && dirtyQueue.containsKey(holder)) {
			ByteBuffer buffer = this.dirtyQueue.remove(holder);  
            this.readyQueue.add(buffer);  
            buffer.clear();
            if (LOG.isDebugEnabled()) {  
                LOG.debug(  
                        "[RELEASE SUCCESS] - holder:{}, pool:[ready:{}, dirty:{}]",  
                        holder, readyQueue.size(), dirtyQueue.size());  
            }  
		}
		holder.free();  
	}
	
	public int getPoolSize() {  
        return poolSize;  
    }  
	
}
