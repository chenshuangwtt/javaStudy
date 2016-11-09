package com.cs.test.week11.bufferpool2;

/**
 *  Runs the Heapsort and outputs all the necessary data fields
 *
 *  @author David Nguyen (dnguy06)
 *  @author Nicholas Crowder (crowdern)
 *  @version Nov 3, 2013
 */
public class PrintOutput {
    //name of file
    private static String fileName;
    //number of buffers allowed
    private static int numBuff;
    //amount of time to sort
    private static long sortTime = 0;
    //holds records
    private static RecordHolder rH;

    //public so that buffers can increase the count of each
    /**
     * number of cache hits
     */
    public static int cacheHits = 0;
    /**
     * number of cache misses
     */
    public static int cacheMisses = 0;
    /**
     * number of disk reads
     */
    public static int diskReads = 0;
    /**
     * number of disk writes
     */
    public static int diskWrites = 0;

    /**
     * Create a new PrintOutput object and runs the Heapsort on the input file.
     * @param name as the filename
     * @param maxBuff as the maximum number of buffers allowed in the Bufferpool
     */
    public PrintOutput(String name, int maxBuff) {
        fileName = name;//get the name of the file
        numBuff = maxBuff; //parse the max number of buffers
        rH = new RecordHolder(fileName, numBuff); //create a new record holder

        //start the timer when we sort the Heap
        long timer = System.currentTimeMillis(); //Begin the timer
        //build heap
        MaxHeap heap = new MaxHeap(rH);
        heap.sort();

        //Flush the pool obtained by the record holder
        rH.getPool().flushPool();

        //calculates the time it took to sort
        sortTime = System.currentTimeMillis() - timer;
    }

    /**
     * Outputs all of the necessary data fields and the sorted file.
     */
    public void printOut() {
        //prints the filename
        System.out.println("Sorting " + fileName);
        //prints the # of cache hits
        System.out.println("Number of cache hits: " + cacheHits);
        //prints the # of cache misses
        System.out.println("Number of cache misses: " + cacheMisses);
        //prints the # of disk reads
        System.out.println("Number of disk reads: " + diskReads);
        //prints the # of disk writes
        System.out.println("Number of disk writes: " + diskWrites);
        //prints the time it took to execute the sort method
        System.out.println("The time to execute the heapsort " + sortTime);

        int records = 1024;
        for (int i = 0; i < (rH.getSize() / records); i++) {
            System.out.printf("  %5d  %5d", rH.getKey(i * records),
                rH.getVal(i * records));
            if (((i + 1) % 8 == 0)) {
                System.out.println();
            }
        }
        System.out.println();
        //prints the # of records that were processed (the RecordHolder size)
        System.out.println(rH.getSize() + " records processed");
    }
}
