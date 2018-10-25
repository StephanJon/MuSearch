package sort;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;




/**
 * Quick sort implementation obtained from algorithm and data structures textbook
 * by Robert Sedgewick and Kevin Wayne.
 */


public class Sort {

    public static void sort(Comparable[] a) {
        shuffle(a);
        sort(a, 0, a.length - 1);
    }
    
    
    private static void sort(Comparable[] a, int lo, int hi) { 
        if (hi <= lo) return;
        int j = partition(a, lo, hi);
        sort(a, lo, j-1);
        sort(a, j+1, hi); 
    }
    

    private static int partition(Comparable[] a, int lo, int hi) {
        int i = lo;
        int j = hi + 1;
        Comparable v = a[lo];
        while (true) { 

            while (less(a[++i], v)) {
                if (i == hi) break;
            }

            while (less(v, a[--j])) {
                if (j == lo) break;   
            }
            if (i >= j) break;

            exch(a, i, j);
        }
        exch(a, lo, j);

        return j;
    }
    

    
    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }
    
        

    private static void exch(Comparable[] a, int i, int j) {
        Comparable temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }



    private static void shuffle(Comparable[] ar){
		Random rnd = ThreadLocalRandom.current();
		for (int i = ar.length - 1; i > 0; i--){
			int index = rnd.nextInt(i + 1);
			// Simple swap
			Comparable a = ar[index];
			ar[index] = ar[i];
			ar[i] = a;
	    } 
	}	

}
