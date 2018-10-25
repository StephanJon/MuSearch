package graph;

import containers.Bag;


/**
 * Search for all neighbors of a specific depth. prob change name 
 * @author Edward
 *
 */
public class NeighborsDFS {
	private final int s;
	private Bag<Integer> neighbors;
	
	/**
	 * Constructor
	 * @param G Graph
	 * @param s source node
	 * @param depth depth of search
	 */
	public NeighborsDFS(Graph G, int s, int depth){
		this.s = s;
		this.neighbors = new Bag<Integer>();
		dfs(G,s, depth);
	}
	
	private void dfs(Graph g, int v, int depth){
		dfs(g, v, 0, depth);
		
	}
	private void dfs(Graph g, int v, int start, int stop){
		if (start ==  stop && s!= v ) {			
			neighbors.add(v);
			return;
		}
		if (start == stop) return;
		
		for(int w: g.adj(v))
			dfs(g, w, start + 1, stop);
	}
	
	/**
	 * amount of neighbors of the specified depth
	 * @return number of neighbors of the specified depth
	 */
	public int size(){return neighbors.size(); }
	
	/**
	 * Neighbors of specified depth 
	 * @return Iterable of neighbors of specified depth 
	 * @return 
	 */
	public Iterable<Integer> neighbors(){
		return neighbors;
	}

}
