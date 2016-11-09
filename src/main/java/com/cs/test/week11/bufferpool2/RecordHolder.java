package com.cs.test.week11.bufferpool2;

import java.nio.ByteBuffer;
import java.io.File;
/**
 *  An array implementation that always users to access the data within. The
 *  is parsed though and held within the array.
 *
 *  @author David Nguyen (dnguy06)
 *  @author Nicholas Crowder (crowdern)
 *  @version Oct 30, 2013
 */
public class RecordHolder {
    private BufferPool pool;
    //record size
    private final static int RECORDS = 4;
    //block byte size
    private final static int BLOCK_BYTE_SIZE = 4096;
    /**
     * Create a new RecordHolder object.
     * @param name as the name of the specified file
     * @param numBuff as the maximum number of buffers that the array can
     * have.
     */
    public RecordHolder(String name, int numBuff) {
        File file = new File(name);
        pool = new BufferPool(file,numBuff, BLOCK_BYTE_SIZE);
    }

    /**
     * Retrieves the buffer pool
     * @return the buffer pool within the array
     */
    public BufferPool getPool() {
        return pool;
    }

    /**
     * Returns the number of records within the array.
     * @return the number of records since it is a multiple of 1024
     */
    public int getSize() {
        return (pool.getSize() * (BLOCK_BYTE_SIZE / RECORDS));
    }

    /**
     * Retrieves the value for the record within the array
     * @param pos as the position of the record to be read
     * @return the value for the record
     */
    public short getVal(int pos) {
        //retrieves the block position
        int block = (pos * RECORDS) / BLOCK_BYTE_SIZE;
        //retrieves the offset for the specified block
        int offset = (pos * RECORDS) % BLOCK_BYTE_SIZE;
        //creates a new byte array from the data in the block
        byte[] buf = pool.acquireBuffer(block).readBlock();
        //creates a short wrapper from the bytebuffer
        short value = ByteBuffer.wrap(buf).getShort(offset + 2);
        //returns the value
        return value;
    }

    /**
     * Finds and retrieves the key for a record within the array.
     * @param pos as the position of the record
     * @return the generated key for the record
     */
    public short getKey(int pos) {
        //retrieves the block position
        int block = (pos * RECORDS) / BLOCK_BYTE_SIZE;
        //retrieves the offset for the specified block
        int offset = (pos * RECORDS) % BLOCK_BYTE_SIZE;
        //creates a new byte array from the data in the block
        byte[] buf = pool.acquireBuffer(block).readBlock();
        //creates a short wrapper from the bytebuffer
        short shortVal = ByteBuffer.wrap(buf).getShort(offset);
        assert ((shortVal > 0) && (shortVal <= 30000)): "Invalid Key.";
        return shortVal;
    }

    /**
     * Swaps two records within the array
     * @param pos1 the position of the first array to be swapped
     * @param pos2 the position of the second array to be swapped
     */
    public void swap(int pos1, int pos2) {
        //retrieve the first and second blocks
        int first_block = (pos1 * RECORDS) / BLOCK_BYTE_SIZE;
        int second_block = (pos2 * RECORDS) / BLOCK_BYTE_SIZE;

        //calculate the offset for both
        int first_offset = (pos1 * RECORDS) % BLOCK_BYTE_SIZE;
        int second_offset = (pos2 * RECORDS) % BLOCK_BYTE_SIZE;

        //create new byte arrays with the correct size
        byte[] f_record = new byte[RECORDS];
        byte[] s_record = new byte[RECORDS];

        //create new buffer objects from the blocks within the pool
        Buffer f_buf = pool.acquireBuffer(first_block);
        Buffer s_buf = pool.acquireBuffer(second_block);

        //Retrieves the first record and stores it into a temp byte array
        byte[] tempBuff = f_buf.readBlock();
        System.arraycopy(tempBuff, first_offset, f_record, 0, RECORDS);

        //Retrieves the second record and stores it into a temp byte array
        tempBuff = s_buf.readBlock();
        System.arraycopy(tempBuff, second_offset, s_record, 0, RECORDS);

        //Places the first record in the buffer that used to store the second
        //record
        System.arraycopy(f_record, 0, tempBuff, second_offset, RECORDS);
        s_buf.writeBlock(tempBuff);

        //Places the second record in the buffer that used to store the first
        //record
        tempBuff = f_buf.readBlock();
        System.arraycopy(s_record, 0, tempBuff, first_offset, RECORDS);
        f_buf.writeBlock(tempBuff);
    }
}
