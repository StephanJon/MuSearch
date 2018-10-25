package prog;

import java.util.Scanner;

import datatypes.Recommendation;
import graph.Graph;
import helper.RecommendSongsDFS;

public class SmallTest {
	public static void main(String[] args) {
		String filename = "Presentation_Example.txt";
		int line = 27;
		int top = 5;
		
		Graph g = new Graph(filename, line);
		Scanner s;
		while (true)
		{
			s = new Scanner(System.in);
			System.out.println("Enter song:");
			String id = s.nextLine();
			if(id.compareTo("s") == 0) break;
			try
			{
				RecommendSongsDFS R = new RecommendSongsDFS(g, id);
				for (Recommendation r : R.getTopN(top))
					System.out.println(r);
			}
			catch (ArrayIndexOutOfBoundsException e){
				System.out.printf("%s not found\n", id);
			}
		}
		s.close();
	}
}
