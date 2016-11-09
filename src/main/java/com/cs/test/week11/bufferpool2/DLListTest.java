package com.cs.test.week11.bufferpool2;

/**
 *  Tests the methods and classes within the DLList class.
 *
 *  @author David Nguyen (dnguy06)
 *  @author Nicholas Crowder (crowdern)
 *  @version Oct 31, 2013
 */
public class DLListTest
    extends junit.framework.TestCase
{
    //private objects to be tested
    private int testL1;
    private int testL2;
    private String testL3;
    private String testL4;
    @SuppressWarnings("rawtypes")
    private DLList testList;
    /**
     * This method is automatically called once before each test case method,
     * so that all the variables are cleanly initialized for each test.
     */
    @SuppressWarnings("rawtypes")
    public void setUp()
    {
      testL1 = 5;
      testL2 = 10;
      testL3 = new String("wow");
      testL4 = new String("ham");
      //instantiates a new Double linked list with a max capcity of four.
      testList = new DLList(4);
    }
    /**
     *  tests the insert method to ensure the correct functionality of
     *  LRU. The last element that is added should be the head's next
     *  which is the front of the list. The method uses removeLink method, so
     *  if the tests succeeds, that method is tested as well.
     */
    @SuppressWarnings("unchecked")
    public void testInsert()
    {
        //tests the first insertion. the dlist still maintians it's head and
        //tail elements
        testList.insert(testL1);
        //checks to see if the first element in the list is the correct one.
        assertEquals(testL1, testList.getHead().next().getElement());
        //checks the list from the tail (since the maximum size is 4)
        //the element should be the previous x 4 from the tail.
        assertEquals(testList.getTail().prev().prev()
            .prev().prev().getElement(), testL1);
        //inserts the second element, ensure that it's in the front of the list
        testList.insert(testL2);
        //checks to see if the first element in the list is the correct one.
        assertEquals(testL2, testList.getHead().next().getElement());
        //checks the second element
        //checks to see if the second element in the list is the correct one.
        // [head] <-> [test2] <-> [test1] <-> [null] <-> [null] <-> [tail]
        assertEquals(testL1, testList.getHead().next().next().getElement());
        //insert Third
        testList.insert(testL3);
        testList.insert(testL4);
        //check for full list, checks first element was the last one added
        assertEquals(testL4, testList.getHead().next().getElement());
        //checks the end of the list to see the most least used one is in
        //the correct position (which wa sthe first element that was added.
        assertEquals(testL1, testList.getTail().prev().getElement());
        //tests when the list is full, correctly returns the tail's prev link
        assertEquals(testL1, testList.insert(testL4));
    }
    /**
     * Tests the recentlyUsedMethod and it's functionality.
     * The test was to compare the list's order after it's filled up and one
     * element is marked as the most recently used.
     */
    @SuppressWarnings("unchecked")
    public void testRecentlyUsed()
    {
        testList.checkRecent(testL1);
        testList.checkRecent(testL2);
        testList.checkRecent(testL3);
        testList.checkRecent(testL4);
        testList.checkRecent(testL3);
        // [head] <-> [test3] <-> [test4] <-> [test2] <-> [test1] <-> [tail]
        assertEquals(testL3, testList.getHead().next().getElement());
        //if we add a null, we return the last element in the list.
        assertEquals(testL1, testList.checkRecent(null));
    }
    /**
     * Tests the remove method and it's functionality. Also tests remove link
     * method.
     */
    @SuppressWarnings("unchecked")
    public void testRemove()
    {
        testList.checkRecent(testL1);
        testList.checkRecent(testL2);
        testList.checkRecent(testL3);
        testList.checkRecent(testL4);
        // [head] <-> [test4] <-> [test3] <-> [test2] <-> [test1] <-> [tail]
        testList.remove(testL2);
        //checks to see if the head of the lsit is unchanged
        assertEquals(testL4, testList.getHead().next().getElement());
        //checks to see if the element is actually removed
        assertEquals(testL1, testList.getHead().next().next().next().getElement());
    }
}
