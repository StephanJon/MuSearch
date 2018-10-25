package read;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;



import datatypes.Song;
import datatypes.Songs;
import Stopwatch.Stopwatch;

public abstract class Read{
	
	/**
	 * Reads Song data set with 3 columns being userId, songId, and listenCount respectively.
	 * @param filename Name of data file
	 * @param lines number of lines to read (can be less than the entire file)
	 * @return Sorted String array of every data entry in the first 2 columns only (usersId and songId)
	 */
	public static String[] readData(String filename, int lines){
		
		String[] s = new String[2*lines] ;
		int i = 0;
		Stopwatch t = new Stopwatch();
		System.out.print("Reading...");
		
		try {
			double start = t.elapsedTime();
			//Scanner scanner = new Scanner(new File(filename));
			BufferedReader bf = new BufferedReader(new FileReader(filename));
			for (int j = 0; j<lines;j++ ){
				String[] line = bf.readLine().split("\t");
				s[i++] = line[0];
				s[i++] = line[1];
			}
			bf.close();
			
			
			double end = t.elapsedTime();
			System.out.printf("Read: %.2fs\n", end - start);
			
			
		}catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return s;
	}
	
	public static Songs readSongs(){
		int lines = 999056;
		Song[] songs = new Song[lines];
		
		try {
			BufferedReader bf = new BufferedReader(new FileReader("song_data.csv"));
			bf.readLine();
			for(int i = 0; i < lines; i++ ){
				String l = bf.readLine();
				String[] line = l.split(",");
				//System.out.println(l);
				//System.out.println(line.length);
				//System.out.println(line[0]);
				songs[i] = new Song(
						line[0],
						line[1].replace("\"", ""),
						line[2].replace("\"", ""),
						line[3].replace("\"", ""), 		
						line[4]);
			}
			bf.close();
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new Songs(songs);
	}
	public static void main(String[] args) {
		Songs s = readSongs();
		//System.out.println(s.getByName("Behind The Sea [Live In Chicago]").getSongId());
		//System.out.println(s.getById("SOQMMHC12AB0180CB8"));
	}
}
