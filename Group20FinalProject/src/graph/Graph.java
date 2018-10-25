package graph;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import Stopwatch.Stopwatch;
import containers.Bag;
import read.Read;
import symbolTables.TST;


/**
 * Symbol Graph data structure with customized constructor
 * referenced from princeton.cs website
 * by Robert Sedgewick and Kevin Wayne
 */

public class Graph {
	
	//private ArrayST st;
	private TST<Integer> st;
	private String[] keys;
    private final int V;
    private int E;
    private Bag<Integer>[] adj;
    
    /**
     * Graph constructor
     * @param filename Name of user data
     * @param lines Number of lines in the file
     */
    public Graph(String filename, int lines) {
    	st = new TST<Integer>();
    	Stopwatch watch = new Stopwatch();
		double start,end;
    	
    	try {
			System.out.print("Reading user data...");start = watch.elapsedTime();
    		
			BufferedReader bf = new BufferedReader(new FileReader(filename));
			//init st
			for(int i = 0; i < lines; i++){
				String[] line = bf.readLine().split("\t");
				if(!st.contains(line[0]))
					st.put(line[0],st.size());
				if(!st.contains(line[1]))
					st.put(line[1], st.size());
			}
			bf.close();
			end = watch.elapsedTime();
			System.out.printf("data read (%.2fs)\n", (end-start));
			
			//Constructing graph 
			
			System.out.print("Constructing graph..."); start = watch.elapsedTime();
			
			adj = (Bag<Integer>[]) new Bag[st.size()];
			for (int v = 0; v < st.size(); v++) 
				adj[v] = new Bag<Integer>();
			keys = new String[st.size()];
			
			
			for(String s : st.keys()){
				keys[st.get(s)] = s;
			}
			
			
			
			bf = new BufferedReader(new FileReader(filename));
			
			
			for(int i = 0; i < lines; i++){
				String[] line = bf.readLine().split("\t");
				addEdge(st.get(line[0]),st.get(line[1]));
			}
			bf.close();
			end = watch.elapsedTime();
			System.out.printf("graph constructed. (%.2fs)\n", end - start);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	this.V = st.size();
    	this.E = lines;
    }
    
    public int V() {
        return V;
    }

    public int E() {
        return E;
    }
    
    public String nameOf(int i){
    	return keys[i];
    }
    
    public int indexOf(String v){ //return index of songs from superArray
    	return st.get(v);
    	//return st.indexOf(v);
    }
    public void addEdge(int v, int w) {
        E++;
        adj[v].add(w);
        adj[w].add(v);
    }
    public Iterable<Integer> adj(int v) {
        return adj[v]; // returns bag (adjacency list)
    }
    public int degree(String v, int n) {
        return adj[this.indexOf(v)].size();
    }
    
    public boolean contains(String id){
    	return st.contains(id);
    }

    public static void main(String[] args) {
		Graph g = new Graph("train_triplets.txt", 1000000);
		System.out.println(g.contains("SODGVGW12AC9075A8D"));
	}
}

