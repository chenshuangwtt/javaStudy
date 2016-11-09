package com.cs.test.week11.bufferpool2;


/**
 *  Test class to tests the methods and functionality of the DLink class.
 *
 *  @author David Nguyen (dnguy06)
 *  @author Nicholas Crowder (crowdern)
 *  @version Oct 31, 2013
 */
public class DLinkTest
    extends junit.framework.TestCase
{
    @SuppressWarnings("rawtypes")
    private DLink testL1;
    @SuppressWarnings("rawtypes")
    private DLink testL2;
    @SuppressWarnings("rawtypes")
    private DLink testL3;

    /**
     * This method is automatically called once before each test case method,
     * so that all the variables are cleanly initialized for each test.
     */
    public void setUp()
    {
      testL1 = new DLink<Integer>(5);
      testL2 = new DLink<Integer>(10);
      testL3 = new DLink<String>("wow");
    }

    /**
     * Tests the constructor, tests the next and prev pointers if null
     * that means the instaniation was a success.
     */
    public void testLink()
    {
        assertEquals(5, testL1.getElement());
        assertNull(testL1.next());
        assertNull(testL1.prev());
    }

    /**
     * Tests the functionality of setNext to assure it set's the link's next
     * as well as it's next's prev to the current link. The setNext invokes
     * the setPrev, so if the test passes it essentially tests both methods.
     */
    @SuppressWarnings("unchecked")
    public void testNextAndPrev()
    {
        testL1.setNext(testL2);
        assertEquals(testL1, testL2.prev());
        assertEquals(testL2, testL1.next());
        testL2.setNext(testL3);

        //tests links with different element types.
        assertEquals("wow", testL2.next().getElement());
    }
}
