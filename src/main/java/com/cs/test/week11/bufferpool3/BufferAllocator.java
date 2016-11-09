package com.cs.test.week11.bufferpool3;

/**
 * 分配器对BufferHolder提供ByteBuffer
 * 的分配和释放功能。 
 */
public interface  BufferAllocator {
	/**
	 * <tt>BufferHolder</tt>申请持有分配器的分配的<tt>ByteBuffer</tt>对象。 
     * <tt>BufferEntry</tt>对象使用默认的容量。 
	 */
	void allocate(BufferHolder holder);  
	
	/** 
     * <tt>BufferHolder</tt>申请持有分配器的分配的<tt>ByteBuffer</tt>对象。 
     * <tt>BufferEntry</tt>对象使用指定的容量。 
     *  
     * @param holder 
     * @param bufferSize 
     */  
    void allocate(BufferHolder holder, int bufferSize);  
  
    /** 
     * <tt>BufferHolder</tt>申请释放其持有的<tt>ByteBuffer</tt>对象. 
     * @param holder 
     */  
    void release(BufferHolder holder);  
}
