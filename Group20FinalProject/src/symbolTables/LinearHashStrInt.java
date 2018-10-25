package symbolTables;

import containers.Bag;



//References princetons LinearProbingHashST.java
/**
 * References core hash methods from Princeton's LinearProbingHashST and
 * Robert Sedgewick's "Algorithms and Data Structures"
 * Specifically uses String,Integer as key, value.
 *
 */
public class LinearHashStrInt {


    private int n;           // number of key-value pairs in the symbol table
    private int m;           // size of linear probing table
    private String[] keys;      // the keys
    private Integer[] vals;    // the values
    
    public String getKey(int i){
    	return keys[i];
    }
    
    
    /**
     * Initialize default capacity of 4
     */
    public LinearHashStrInt(){
    	m = 4;
    	n = 0;
    	keys = new String[m];
        vals = new Integer[m];
    }
    
    
    
    /**
     * Initializes an empty symbol table with the specified initial capacity.
     *
     * @param capacity the initial capacity
     */
    public LinearHashStrInt(int capacity) {
        m = capacity;
        n = 0;
        keys = new String[m];
        vals = new Integer[m];
    }

    /**
     * Returns the number of key-value pairs in this symbol table.
     *
     * @return the number of key-value pairs in this symbol table
     */
    public int size() {
        return n;
    }
    
    public int M(){
    	return m;
    }

    /**
     * Returns true if this symbol table is empty.
     *
     * @return {@code true} if this symbol table is empty;
     *         {@code false} otherwise
     */
    public boolean isEmpty() {
        return size() == 0;
    }

    /**
     * Returns true if this symbol table contains the specified key.
     *
     * @param  key the key
     * @return {@code true} if this symbol table contains {@code key};
     *         {@code false} otherwise
     * @throws IllegalArgumentException if {@code key} is {@code null}
     */
    public boolean contains(String key) {
        if (key == null) throw new IllegalArgumentException("argument to contains() is null");
        return get(key) != null;
    }

    // hash function for keys - returns value between 0 and M-1
    public int hash(String key) {
    	int hash = 0;
    	for (int i = 0; i < key.length(); i++)
    		hash = (31*hash+key.charAt(i)) % m;
    	return hash;
    }

    /**
     * Inserts the specified key-value pair into the symbol table, overwriting the old 
     * value with the new value if the symbol table already contains the specified key.
     * Deletes the specified key (and its associated value) from this symbol table
     * if the specified value is {@code null}.
     *
     * @param  key the key
     * @param  val the value
     * @throws IllegalArgumentException if {@code key} is {@code null}
     */
    public void put(String key, Integer val) {
        if (key == null) throw new IllegalArgumentException("first argument to put() is null");

        if (val == null) {
            delete(key);
            return;
        }

        // double table size if 50% full
        if (n >= m/2) resize(2*m);

        int i;
        for (i = hash(key); keys[i] != null; i = (i + 1) % m) {
            if (keys[i].compareTo(key)==0) { //checks same
                vals[i] = val;
                return;
            }
        }
        keys[i] = key;
        vals[i] = val;
        n++;
    }

    /**
     * Returns the value associated with the specified key.
     * @param key the key
     * @return the value associated with {@code key};
     *         {@code null} if no such value
     * @throws IllegalArgumentException if {@code key} is {@code null}
     */
    public Integer get(String key) {
        if (key == null) throw new IllegalArgumentException("argument to get() is null");
        for (int i = hash(key); keys[i] != null; i = (i + 1) % m)
            if (keys[i].compareTo(key) == 0)
                return vals[i];
        return null;
    }

    /**
     * Removes the specified key and its associated value from this symbol table     
     * (if the key is in this symbol table).    
     *
     * @param  key the key
     * @throws IllegalArgumentException if {@code key} is {@code null}
     */
    public void delete(String key) {
        if (key == null) throw new IllegalArgumentException("argument to delete() is null");
        if (!contains(key)) return;

        // find position i of key
        int i = hash(key);
        while (key.compareTo(keys[i])!=0) {
            i = (i + 1) % m;
        }

        // delete key and associated value
        keys[i] = null;
        vals[i] = null;

        // rehash all keys in same cluster
        i = (i + 1) % m;
        while (keys[i] != null) {
            // delete keys[i] an vals[i] and reinsert
            String   keyToRehash = keys[i];
            Integer valToRehash = vals[i];
            keys[i] = null;
            vals[i] = null;
            n--;
            put(keyToRehash, valToRehash);
            i = (i + 1) % m;
        }

        n--;

        // halves size of array if it's 12.5% full or less
        if (n > 0 && n <= m/8) resize(m/2);

      
    }
    
    private void resize(int capacity) {
        LinearHashStrInt temp = new LinearHashStrInt(capacity);
        for (int i = 0; i < m; i++) {
            if (keys[i] != null) {
                temp.put(keys[i], vals[i]);
            }
        }
        keys = temp.keys;
        vals = temp.vals;
        m    = temp.m;
    }
    
    public Iterable<String> keys() {
        Bag<String> bag = new Bag<String>();
        for (int i = 0; i < m; i++)
            if (keys[i] != null) bag.add(keys[i]);
        return bag;
    }
}
