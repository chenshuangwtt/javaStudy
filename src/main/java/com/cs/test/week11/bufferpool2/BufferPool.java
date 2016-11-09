package com.cs.test.week11.bufferpool2;

import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.RandomAccessFile;
import java.io.File;

/**
 *  The BufferPool holds all of the Buffers.
 *
 *  @author David Nguyen (dnguy06)
 *  @author Nicholas Crowder (crowdern)
 *  @version Oct 28, 2013
 */
public class BufferPool implements BufferPoolADT {
    private Buffer[] pool;
    private RandomAccessFile file;
    private DLList<Buffer> list;
    private int maxBuff;
    private int blockSize;

    /**
     * Create a new BufferPool object.
     * @param f file to be processed
     * @param numBuff number of buffers allowed
     * @param size size of the buffer
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public BufferPool(File f, int numBuff, int size) {
        blockSize = size;
        list = new DLList(numBuff);
        //create file, allow read and write
        try {
            file = new RandomAccessFile(f, "rw");
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        maxBuff = 0;
        pool = new Buffer[maxBuff];
    }

    /**
     * If block was written to or read, the block is flushed.
     * @param buff buffer that was written to or read
     */
    public void mark(Buffer buff) {
        Buffer remove = list.checkRecent(buff);

        if(remove != null) {
            remove.flush();
        }
    }

    /**
     * Relates a block to a buffer, returns a pointer to a buffer object.
     * @param block block to be set
     * @return returns pointer to buffer
     */
    @Override
    public Buffer acquireBuffer(int block) {
        if (block >= maxBuff) {
            Buffer[] p = new Buffer[block + 1];
            System.arraycopy(pool, 0, p, 0, maxBuff);
            maxBuff = block + 1;
            pool = p;
        }

        if (pool[block] == null) {
            pool[block] = makeNewBuffer(block);
        }

        return pool[block];
    }

    /**
     * Creates and returns a new Buffer
     * @param block block of the buffer
     * @return returns new buffer
     */
    private Buffer makeNewBuffer(int block) {
        assert(block < maxBuff);
        return new Buffer(this, file, block * blockSize, blockSize, block);
    }

    /**
     * Returns the size of the pool.
     * @return size of pool
     */
    public int getSize() {
        return maxBuff;
    }

    /**
     * Return blockSize of buffers
     * @return returns the block size of the buffers
     */
    public int bufferSize() {
        return blockSize;
    }

    /**
     * Flushes all data out of the pool
     */
    public void flushPool() {
        for (int i = 0; i < maxBuff; i++) {
            pool[i].flush();
        }
    }
}
