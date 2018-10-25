package datatypes;

import java.util.Iterator;

import search.Search;
import sort.Sort;

/**
 * Wrapper class for list of songs allowing for quick access to songs by id.
 */
public class Songs implements Iterable<Song> {
	
	private Song[] songs; //sorted by ids
	private int n;
	
	/**
	 * Constructor takes a array of Song objects from song_data.csv
	 * @param songs array of songs
	 */
	public Songs(Song[] songs ){
		n = songs.length;
		
		//this.songNames = names;
		this.songs = songs;
		
		Sort.sort(songs);
	}
	
	/**
	 * Obtain Song from songId
	 * @param id Song id
	 * @return Song with matching song id
	 */
	public Song getById(String id){
		int i = Search.binarySearchIndex(songs, 0, n, new Song(id, null, null, null, null));
		return songs[i];
	}
	
	/**
	 * Iterates all Song objects in the collection
	 */
	public Iterator<Song> iterator()  {
        return new ListIterator<Song>(0);  
    }

    private class ListIterator<Song> implements Iterator<Song> {
        private int i;

        public ListIterator(int first) {
            i = first;
        }

        public boolean hasNext() { return i != n; }
        public void remove() { throw new UnsupportedOperationException(); }

        public Song next() {
            Song item = (Song)songs[i];
            i++;
            return (Song)item;
        }
    }	
}
