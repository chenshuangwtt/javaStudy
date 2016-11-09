package com.cs.test.week11.bufferpool2;

/**
 *  Interface for BufferPool.
 *
 *  @author David Nguyen (dnguy06)
 *  @author Nicholas Crowder (crowdern)
 *  @version Oct 28, 2013
 */
public interface BufferPoolADT {
    /**
     * Relates a block to a buffer, returns a pointer to a buffer object.
     * @param block block to be set
     * @return returns pointer to buffer
     */
    public Buffer acquireBuffer(int block);
}
