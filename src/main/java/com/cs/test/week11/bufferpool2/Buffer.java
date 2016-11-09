package com.cs.test.week11.bufferpool2;

import java.io.IOException;
import java.io.RandomAccessFile;
/**
 *  Buffers to be used in the BufferPool.
 *
 *  @author David Nguyen (dnguy06)
 *  @author Nicholas Crowder (crowdern)
 *  @version Oct 29, 2013
 */
public class Buffer {
    //variables each buffer will hold
    private BufferPool pool;
    private byte[] data;
    private RandomAccessFile file;
    private boolean dirty;
    private boolean info;
    private int offset;
    private int size;

    /**
     * Creates a new Buffer object.
     * @param p BufferPool that buffer is in
     * @param f The file that backs this buffer.
     * @param dist how far away buffer is from front of file
     * @param sz size of buffer
     */
    public Buffer(BufferPool p, RandomAccessFile f, int dist, int sz, int id) {
        pool = p;
        file = f;
        size = sz;
        offset = dist;
        //when first created data not inserted and has not been altered
        dirty = false;
        info = false;
    }

    /**
     * Remove data from the Buffer, must be written to disk if dirty
     */
    public void flush() {
        //if data is dirty, must be written to disk
        if (dirty) {
            try {
                PrintOutput.diskWrites++; //increase number of disk writes
                file.seek(offset); //look for correct spot in the file
                file.write(data); //write data into the file
            }
            catch (IOException e) {
                e.printStackTrace();
            }
            dirty = false; //has been written out, so not dirty anymore
        }
        //data and is now null and info is now false
        data = null;
        info = false;
    }

    /**
     * Try to read from the pool, if not in pool, will read from disk
     * @return returns the array
     */
    public byte[] readBlock() {
        pool.mark(this); //tell pool this was read/written to
        //if info not set, then there is a cache miss and must be read from disk
        if (!info) {
            PrintOutput.cacheMisses++;
            data = new byte[size];
            try {
                PrintOutput.diskReads++; //increase number of disk reads
                file.seek(offset); //looks for info by how far from front info is
                file.read(data); //read the data into pool
            }
            catch (IOException e) {
                e.printStackTrace();
            }
            //there is information in buffer, but it has not been changed
            info = true;
            dirty = false;
        }
        //otherwise it is a hit
        else {
            PrintOutput.cacheHits++;
        }
        //return data
        return data;
    }

    /**
     * Writes the information passed in into the buffer
     * @param d new data to be inserted into the buffer
     */
    public void writeBlock(byte[] d) {
        pool.mark(this); //tell pool this was read/written to
        data = d; //data is now the bytes passed into method
        info = true; //info is in the buffer
        dirty = true; //has been written to so is dirty
    }
}
