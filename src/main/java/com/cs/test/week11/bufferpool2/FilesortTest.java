package com.cs.test.week11.bufferpool2;

import java.io.IOException;
/**
 *  Generates a file used for testing.
 *  Uses SortCheck to ensure the file has been sorted properly
 *
 *  @author David H. Nguyen (dnguy06)
 *  @author Nicholas Crowder (crowdern)
 *  @version Nov 4, 2013
 */
public class FilesortTest
    extends junit.framework.TestCase
{
    /**
     * Tests the following classes: Buffer, BufferPool, RecordArray, DLList,
     * DLLink, MaxHeap, and Filesort
     * @throws IOException
     */
    @SuppressWarnings("static-access")
    public void testMain() throws IOException
    {
        //generate a new file to test
        Genfile2 create = new Genfile2();
        String[] genArg = new String[3];
        genArg[0] = "-a";
        genArg[1] = "test1.txt";
        genArg[2] = "5";

        //run the main of Genfile to create the test file
        create.main(genArg);

        //create new filesort object to test the main method
        Filesort test = new Filesort();

        //create a new string array to hold the arguments to test with.
        String[] argString = new String[2];
        argString[0] = "test1.txt";
        argString[1] = "5";

        //start the main method
        test.main(argString);

        assertEquals(PrintOutput.cacheMisses, PrintOutput.diskReads);

        //create a new SortCheck object tp check the sortness of the file
        SortCheck sC = new SortCheck();

        String[] argSort = new String[1];
        argSort[0] = "test1.txt";
        sC.main(argSort);
    }

    /**
     * Tests out the different methods in the heap
     * @throws IOException
     */
    @SuppressWarnings("static-access")
    public void testHeap() throws IOException {
        //generate a new file to test
        Genfile2 create = new Genfile2();
        String[] genArg = new String[3];
        genArg[0] = "-a";
        genArg[1] = "test2.txt";
        genArg[2] = "1";

        //run the main of Genfile to create the test file
        create.main(genArg);

        //create new RecordHolder and put it into the heap
        RecordHolder rH = new RecordHolder("test2.txt", 5);
        MaxHeap heap = new MaxHeap(rH);

        //test out methods in heap
        assertTrue(heap.isLeaf(1023));
        assertEquals(511, heap.parent(1023));
        assertFalse(heap.isLeaf(5));
        assertEquals(11, heap.leftChild(5));
        assertEquals(12, heap.rightChild(5));
    }
}
