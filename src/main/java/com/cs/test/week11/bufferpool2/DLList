/**
 *  This double linked list will be implemented in the Least Recently Used state
 *  Such as keeping track of top priority objects, and removing the least used
 *  one.
 *
 *  Prior to filling up the list, we will insert the element and maintain it's
 *  position at the front until some other element is used. the list will always
 *  return the element in the last position (tail's previous)
 *
 *  Used the head and tail implementation of the linked list so it would be
 *  easier to keep track of the most recently used (head.next) and least used
 *  (tail.prev).
 *
 *  @author David Nguyen (dnguy06)
 *  @author Nicholas Crowder (crowdern)
 *  @version Oct 30, 2013
 *  @param <E> can store objects of type E
 */
public class DLList<E> {
    //the head and the tail link to keep track of the position of the other
    //links.
    @SuppressWarnings("rawtypes")
    private DLink head;
    @SuppressWarnings("rawtypes")
    private DLink tail;
    /**
     * Instantiates the Linked List with the input size.
     * @param maxSize
     */
    @SuppressWarnings("unchecked")
    public DLList(int maxSize) {
        //create the head and the tail links
        head = new DLink<E>(null);
        tail = new DLink<E>(null);
        //set head's next and tail's previous to each other
        head.setNext(tail);
        //Create null Links and fills up the DLList with them until max size is
        //reached
        for (int i = 0; i < maxSize; i++) {
            DLink<E> nullLink = new DLink<E>(null);
            nullLink.setNext(head.next());
            head.setNext(nullLink);
        }
    }
     /**
     * Insert a newly created Link to the front (Head's next link)
     * Removes the last DLink afterwards
     * @param element the element to be added to the list
     * @return the element that was removed.
     */
    @SuppressWarnings("unchecked")
    public E insert(E element) {
        //Creates a new DLink and inserts to the front as head's next
        DLink<E> newLink = new DLink<E>(element);
        newLink.setNext(head.next());
        head.setNext(newLink);
        //To maintain the LRU state, we remove the last DLink (tail's prev)
        DLink<E> removeLink = tail.prev();
        removeLink.prev().setNext(tail);
        return removeLink.getElement();
    }
    /**
     * Removes the desired element from the DLList. If the item does not exist
     * within the DLList, remove will do nothing.
     * @param element as the desired element to be removed.
     */
    @SuppressWarnings("unchecked")
    public void remove(E element) {
        DLink<E> temp = head;
        //while((temp != tail) && (temp != null))
        while ((temp = temp.next()) != tail) {
            if(element.equals(temp.getElement())) {
                //remove the current Link
                removeLink(temp);
                //Insert a null element
                //as the last element in the list
                DLink<E> nullLink = new DLink<E>(null);
                tail.prev().setNext(nullLink);
                nullLink.setNext(tail);
                return;
            }
        }
    }
    /**
     * Remove the Link from the DDList
     * @param toBeRemove as the Link to be removed
     */
    public void removeLink(DLink<E> toBeRemove) {
        //Sets the prev of the current Link's next to it's previous
        //Sets the next of the current's previous to it's next
        //removes from list
        toBeRemove.prev().setNext(toBeRemove.next());
    }
    /**
     * Sets the input Link to the front of the DDList. Correctly sets the
     * pointers of the head,current link, and head's next link.
     * @param link the link to be set to the front of the lsit
     */
    @SuppressWarnings("unchecked")
    public void recentlyUsed(DLink<E> link) {
        removeLink(link);
        link.setNext(head.next());
        head.setNext(link);
    }
    /**
     * Checks to see if the Link should be inserted (null list) or be set to
     * the front and the last link will be removed.
     * @param link as the link to be inserted
     * @return null or the last element;
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public E checkRecent(E link) {
        //If the link is null (constructor) we assert insert
        if (link == null) {
            return this.insert(link);
        }
        DLink temp = head;
        while ((temp != tail)) {
            if(link.equals(temp.getElement())) {
                recentlyUsed(temp);
                return null;
            }
            temp = temp.next();
        }
        return insert(link);
    }
    /**
     * Simple method which returns the head link used for testing.
     * @return the head Link
     */
    @SuppressWarnings("rawtypes")
    public DLink getHead() {
        return head;
    }
    /**
     * Simple method which returns the tail link used for testing.
     * @return the head Link
     */
    @SuppressWarnings("rawtypes")
    public DLink getTail() {
        return tail;
    }
}
