package search;

import read.Read;

/**
 * Recursive binary search 
 */
public class Search {
	
	/**
	 * Recursive Binary search
	 * @param arr Array to be searched
	 * @param lo lower bound
	 * @param hi Higher bound
	 * @param x Search value
	 * @return Index of value, -2 if not found
	 */
	public static int binarySearchIndex(Comparable [] arr, int lo, int hi, Comparable x ){
		if (hi>=lo)
        {
            int mid = lo + (hi - lo)/2;
            if (arr[mid].compareTo(x) == 0)
               return mid;
            if (arr[mid].compareTo(x) > 0)
               return binarySearchIndex(arr, lo, mid-1, x);
            return binarySearchIndex(arr, mid+1, hi, x);
        }
 
        // x not found 
		//System.out.println(x);
        return -2;
	}
	
}
