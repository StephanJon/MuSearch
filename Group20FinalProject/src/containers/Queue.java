package containers;

import java.util.Iterator;


/**
 * FIFO Queue implementation referenced from Algorithms and Data Structures textbook page 151 and 155.
 */
public class Queue<Item> implements Iterable<Item> {
    private Node<Item> first;    // beginning of queue
    private Node<Item> last;     // end of queue
    private int n;               // number of elements on queue


    private class Node<Item> {
        private Item item;
        private Node<Item> next;
    }

    /**
     * Construct empty queue
     */
    public Queue() {
        first = null;
        last  = null;
        n = 0;
    }

    /**
     * is queue empty?
     * @return true if empty, false otherwise.
     */
    public boolean isEmpty() {
        return first == null;
    }

    /**
     * size of queue
     * @return size of queue
     */
    public int size() {
        return n;
    }

    /**
     * Insert item into the FIFO queue
     * @param item item to be inserted
     */
    public void enqueue(Item item) {
        Node<Item> oldlast = last;
        last = new Node<Item>();
        last.item = item;
        last.next = null;
        if (isEmpty()) first = last;
        else oldlast.next = last;
        n++;
    }

   /**
    * removes and returns item from FIFO queue
    * @return item at back. (FIFO)
    */
    public Item dequeue() {
        Item item = first.item;
        first = first.next;
        n--;
        if (isEmpty()) last = null;   // to avoid loitering
        return item;
    }
    
   
    /**
     * iterator for queue
     */
    public Iterator<Item> iterator()  {
        return new ListIterator<Item>(first);  
    }

    private class ListIterator<Item> implements Iterator<Item> {
        private Node<Item> current;

        public ListIterator(Node<Item> first) {
            current = first;
        }

        public boolean hasNext() { return current != null; }
        public void remove() { throw new UnsupportedOperationException(); }

        public Item next() {
            Item item = current.item;
            current = current.next; 
            return item;
        }
    }
    
    public String toString(){
    	String s = first.item + "";
    	
    	for (Queue<Item>.Node<Item> n = first.next; n != null; n = n.next ){
    		s += "\n" + n.item;
    	}
    	return s;
    }
}