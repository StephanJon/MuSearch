package containers;

/**
 * Bag implementation by Robert Sedgewick and Kevin Wayne 
 */
import java.util.Iterator;
import java.util.NoSuchElementException;


public class Bag<Item> implements Iterable<Item> {
    private Node<Item> first;    // beginning of bag
    private int n;               // number of elements in bag

    // helper linked list class
    public static class Node<Item> {
        private Item item;
        private Node<Item> next;
    }

    /**
     * Initializes an empty bag.
     */
    public Bag() {
        first = null;
        n = 0;
    }

    /**
     * Returns true if this bag is empty.
     *
     * @return {@code true} if this bag is empty;
     *         {@code false} otherwise
     */
    public boolean isEmpty() {
        return first == null;
    }

    /**
     * Returns the number of items in this bag.
     *
     * @return the number of items in this bag
     */
    public int size() {
        return n;
    }

    /**
     * Adds the item to this bag.
     *
     * @param  item the item to add to this bag
     */
    public void add(Item item) {
        Node<Item> oldfirst = first;
        first = new Node<Item>();
        first.item = item;
        first.next = oldfirst;
        n++;
    }
    
    public boolean contains(Item item){
    	for(Node<Item> i = first; i != null; i = i.next)
    		if (item == i.item) return true;
    	return false;
    }
    
    
    private void remove(Item item){
    	
    	if(first.item == item) {
    		first = first.next;
    		n--;
    		return;
    	}
    	Node<Item> p = new Node<Item>();
    	for(Node<Item> i = first; i != null; p = i, i = i.next){
    		
    		if (item == i.item) {
    			p.next = i.next;
    			n--;
    			return;
    		}
    	}
    }


    /**
     * Returns an iterator that iterates over the items in this bag in arbitrary order.
     *
     * @return an iterator that iterates over the items in this bag in arbitrary order
     */
    public Iterator<Item> iterator()  {
        return new ListIterator<Item>(first);  
    }

    // an iterator, doesn't implement remove() since it's optional
    private class ListIterator<Item> implements Iterator<Item> {
        private Node<Item> current;

        public ListIterator(Node<Item> first) {
            current = first;
        }

        public boolean hasNext()  { return current != null;                     }
        public void remove()      { throw new UnsupportedOperationException();  }

        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            Item item = current.item;
            current = current.next; 
            return item;
        }
    }
    public String toString(){
    	String s = first.item + "";
    	
    	for (Node<Item> n = first.next; n != null; n = n.next ){
    		s += "\n" + n.item;
    	}
    	return s;
    }
    
    public static void main(String[] args) {
		Bag<Integer> b = new Bag<Integer>();
		b.add(1);
		b.add(2);
		b.add(3);
		b.add(4);
		b.add(5);
		b.remove(0);
		b.remove(0);
		System.out.println(b);
		System.out.println(b.size());
	}
}
