package prog;

import graph.Graph;
import helper.IdGetter;
import helper.RecommendSongsDFS;

import java.util.Scanner;

import read.Read;
import datatypes.Recommendation;
import datatypes.Songs;
import Stopwatch.Stopwatch;

public class Test {
	public static void main(String[] args) {
		
		String filename = "train_triplets.txt";
		int lines = 1000000; //number of lines to read
		
		System.out.println("Reading songs...");
		Songs songs = Read.readSongs();
		
		Graph g = new Graph(filename, lines); //creates data structure
		System.out.println(g.V());
		IdGetter getter = new IdGetter(songs, g);
		
		System.out.println("Done\n");
		Scanner s = new Scanner(System.in);
	
		//Test query
		while(true){
		
			//s = new Scanner(System.in);
			System.out.println("Enter Song Name: ");
			String songName = s.nextLine();
			if(songName.compareTo("s") == 0) break;
			System.out.println("Enter artist: ");
			String artist = s.nextLine();
			
			
			String songId = getter.getId(songName,artist);
			
			//GET SONGID METTHOD GIVEN NAME
			
			

			/*String songName = s.nextLine();
				if(songName.compareTo("s") == 0) break;
				songId = songs.getByName(songName).getSongId();	*/		


			Stopwatch t = new Stopwatch();
			double start = t.elapsedTime();
			try
			{
				RecommendSongsDFS r = new RecommendSongsDFS(g, songId);
				
				double end = t.elapsedTime();

				System.out.println("Input: "+ songs.getById(songId)+"\n" );
				for(Recommendation i : r.getTopN(10))
					System.out.println(songs.getById(i.name()) + " || Count:" + i.count());
				System.out.println( "(" + (end - start) + "s) " + "\n");
			
			}
			catch(Exception e){
				System.out.println(songName + ", " + songId);
				System.out.println("Song not found in user data");
				continue;
			}
		}
		s.close();
	}	

		
}
