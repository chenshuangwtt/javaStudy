package com.cs.test.week11.bufferpool2;

/**
 * Double linked Node
 * Used to store the data for the Double linked list
 *
 * @author David Nguyen (dnguy06)
 * @author Nicholas Crowder (crowdern)
 * @version Oct 30, 2013
 * @param <E> Objects of type e can be stored
 */
public class DLink<E>
{
    private E element;
    private DLink<E> next;
    private DLink<E> prev;
    /**
     *Instaniates a new Double Linked object.
     *@param data as the data to be stored.
     */
    public DLink(E data)
    {
        this.element = data;
    }
    /**
     * Retrieves the element within this DLink
     * @return the element
     */
    public E getElement()
    {
        return element;
    }
    /**
     * Sets the next field of the DLink to the input.
     * And set's the input's prev to the current Link.
     * @param link as the element to be set.
     */
    public void setNext(DLink<E> link)
    {
        this.next = link;
        this.next().setPrev(this);
    }
    /**
     * Retrieves the Dlink's next DLink.
     * @return the next field
     */
    public DLink<E> next()
    {
        return next;
    }
    /**
     * Sets the previous field of the Dlink to the input.
     * @param link as the element to be set.
     */
    public void setPrev(DLink<E> link)
    {
        this.prev = link;
    }
    /**
     * Retrieves the DLink's previous DLink element.
     * @return the previous element.
     */
    public DLink<E> prev()
    {
        return prev;
    }
}
