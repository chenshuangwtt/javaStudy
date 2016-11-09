package com.cs.test.week11.bufferpool2;

/**
 *  A Max heap implementation. Influenced by the MaxHeap implementation used
 *  on project 1 and 2.
 *
 *  @author David Nguyen (dnguy06)
 *  @author Nicholas Crowder (crowdern)
 *  @version Oct 30, 2013
 */
public class MaxHeap {
    private RecordHolder rH;
    private int size;

    /**
     * Create a new MaxHeap object. Will heapify the array when this MaxHeap is
     * instantiated.
     * @param array as the recordholder's array to be parsed
     */
    public MaxHeap(RecordHolder array) {
        rH = array;
        size = rH.getSize();
        this.buildHeap();
    }

    /**
     * Checks if the position is a leaf.
     * @param pos as the position to be checked.
     * @return true if the position is a leaf or false if not.
     */
    public boolean isLeaf(int pos) {
        return ((pos >= size() / 2) && (pos < size()));
    }

    /**
     * Retrieves the left child of the position's node.
     * @param pos as the position to be check for a left child.
     * @return the position of the left child of the initial position.
     */
    public int leftChild(int pos) {
        assert (pos < size() / 2): "Position has no left child";
        return (2 * pos + 1);
    }

    /**
     * Retrieves the right child of the position's node.
     * @param pos as the position to be checked for a right child.
     * @return the position of the right child of the initial position.
     */
    public int rightChild(int pos)  {
        assert(pos < (size() - 1) / 2): "Position has no right child";
        return (2 * pos + 2);
    }

    /**
     * Retrieves the position for the parent of the position's node.
     * @param pos as the index of the child
     * @return the position of the child's parent
     */
    public int parent(int pos) {
        assert (pos > 0): "Position has no parent";
        return (pos -1) / 2;
    }

    /**
     * Builds the heap by using siftdown, influenced by the MaxHeap's methods
     * from project 1 and 2.
     */
    public void buildHeap() {
        for (int i = (size / 2) - 1; i >= 0; i--) {
            siftDown(i);
        }
    }

    /**
     *Places elements within the MaxHeap in their correct positions.
     */
    private void siftDown(int pos) {
        assert ((pos >= 0) && (pos < size)): "Illegal heap position";
        while (!isLeaf(pos)) {
            //Retrieve the left Child
            int child = leftChild(pos);
            //Instead of using compareTo(), we compare the left and right child
            //and select the larger of the two
            if ((child < (size - 1))
                && (rH.getKey(child) < rH.getKey(child + 1))) {
                child++;// child is now index of child with greater value
            }
            //Compare the child to it's parent
            if (rH.getKey(pos) >= rH.getKey(child)) {
                return;
            }
            rH.swap(pos, child);
            pos = child;
        }
    }
    /**
     *Remove the max element int he heap.
     */
    public void removeMax() {
        assert (size > 0): "Removing from empty heap";
        //Decrease the size of the heap
        size--;
        //Swap the maximum with the last element
        rH.swap(0, size);
        //We need to put the new root in the correct place
        if (size != 0)
        {
            siftDown(0);
        }
    }

    /**
     * Retrieves the number of records inside the MaxHeap.
     * @return the exact number of records inside the heap at that time.
     */
    public int size() {
        return size;
    }

    /**
     * Sorts the item in the heap by removing the max element in the heap.
     */
    public void sort() {
        for (int i = 0; i < rH.getSize(); i++) {
            removeMax(); //sort the heap by removing max
        }
    }
}
