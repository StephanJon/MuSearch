package helper;

import graph.Graph;
import graph.NeighborsDFS;
import containers.Bag;
import containers.Queue;
import datatypes.Recommendation;
import symbolTables.LinearHashStrInt;

/**
 * Class used to look for similar patterns between users and the graph using NeighborsDFS 
 * and allows access to top recommended song(s) based on the graph constructed using user data 
 */
public class RecommendSongsDFS {
	private final int DEPTH = 2;
	private NeighborsDFS neigbors2;
	private Graph g;
	private String song;
	
	/**
	 * Recommendation based on given Graph and input song
	 * @param g Graph like data structure
	 * @param song Input song within the graph
	 */
	public RecommendSongsDFS(Graph g,String song){
		
		this.song = song;
		neigbors2 = new NeighborsDFS(g, g.indexOf(song), DEPTH);
		this.g = g;
		
	}
	
	/**
	 * Returns top 1 recommended (most common user count)
	 * @return Top 1 Recommendation object contains data about song Id and common user count. 
	 */
	public Recommendation getTop1(){
		return ((Queue<Recommendation>)getTopN(1)).dequeue();
		
		
	}
	/**
	 * Get iterable of top n songs
	 * @param n
	 * @return Iterable of Recommendation objects representing top recommended songs
	 */
	public Iterable<Recommendation> getTopN(int N){
		
		Queue<Recommendation> top = new Queue<Recommendation>();
		LinearHashStrInt st = new LinearHashStrInt(); 
		
		for (int i : neigbors2.neighbors()) st.put(g.nameOf(i), 0);
		//insertion
		for (int i : neigbors2.neighbors()){
			String currentSong = g.nameOf(i);		
			st.put(currentSong, st.get(currentSong) + 1);
		}
		//finding the max count for repeated songs
		
		for(int i = 0; i < N; i++){
			int max = 0;
			int maxCount = 0;
			String maxSong = null;
			for (String currentSong : st.keys()){
				if (currentSong.compareTo(song) != 0 && st.get(currentSong) > max ){ 	
					maxSong = currentSong;
					max = st.get(maxSong);
					maxCount = st.get(currentSong);	
				}
			}
			if(!st.isEmpty())
			{
				top.enqueue(new Recommendation(maxSong, maxCount));
				st.delete(maxSong);
			}
			
		}
		return top;
		
	}

}
