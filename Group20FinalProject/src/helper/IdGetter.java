package helper;

import graph.Graph;

import java.util.Scanner;

import containers.Queue;
import datatypes.Song;
import datatypes.Songs;

/**
 * UI to that assists with obtaining song Id from song name and artist
 */
public class IdGetter{
	Songs songs;
	Graph g;

	/**
	 * Constructor for IdGetter
	 * @param songs songs which contains data about all songs
	 * @param g graph constructed using user data
	 */
	public IdGetter(Songs songs, Graph g){

		this.songs = songs;
		this.g = g;	
		
		
	}
	
	/**
	 * Get id of song given song name and artist. Uses console to display all matching
	 * results where user can select the correct song
	 * @param songName name of song
	 * @param artist Artist
	 * @return Song Id
	 */
	public String getId(String songName, String artist){
		if (songName.length() <= 1 && artist.length() <= 1) return "";
		
		Queue<Song> q = new Queue<Song>();
		addSame(q,songName,artist);
//		return q;
		if (q.size() == 0) return "";
		
		int counter = 1;
		for (Song song : q){
			System.out.printf("%d. \"%s\", %s, %s\n", counter++, song.getName(), song.getArtist(), song.getAlbum());
		}
		System.out.println("Choose song (Enter number). 0 to cancel");
		Scanner s = new Scanner(System.in);
		int n = s.nextInt();
		if (n <= 0) return "";
		for(int i = 0; i < n-1; i++)
			q.dequeue();
		return q.dequeue().getSongId();
	}
	
	/**
	 * Helper class that adds songs into a queue that match the search fields
	 * @param q empty 
	 * @param songName song name field
	 * @param artist song artist field
	 */
	private void addSame(Queue<Song> q, String songName, String artist){
		//boolean marked = false;
		for(Song s : songs){
			if (s.getName().toLowerCase().contains(songName.toLowerCase())){
				if(s.getArtist().toLowerCase().contains(artist.toLowerCase()))
					if (g.contains(s.getSongId()))
						q.enqueue(s);
			}
		}
	}
	
	

}
