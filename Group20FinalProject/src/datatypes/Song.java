package datatypes;

/**
 * Song ADT containing information about a song. Used when importing all songs from song_data.csv.
 * Song ordering is imposed by song Ids.
 */
public class Song implements Comparable<Song> {
	protected String songId;
	protected String name;
	protected String artist;
	protected String album;
	protected String date;
	
	/**
	 * Constructor for Song
	 * @param songId Unique id for song
	 * @param name Common name of song
	 * @param albulm Album name
	 * @param artist Artist name
	 * @param date Date released
	 */
	public Song(String songId, String name, String albulm, String artist, String date){
		this.songId = songId;
		this.name = name;
		this.artist = artist;
		this.album = albulm;
		this.date = date;
	}

	/**
	 * Getter for song Id
	 * @return Song Id
	 */
	public String getSongId() {
		return songId;
	}

	/**
	 * Getter for song name
	 * @return Song name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Getter for artist name
	 * @return Artist
	 */
	public String getArtist() {
		return artist;
	}

	/**
	 * Getter for song album
	 * @return Album
	 */
	public String getAlbum() {
		return album;
	}

	/**
	 * Getter for Date
	 * @return Date released
	 */
	public String getDate() {
		return date;
	}
	
	/**
	 * String representiation 
	 */
	public String toString() {
		return String.format("%s, %s, %s, %s", name,artist,album,date);
	}
	
	/**
	 * Songs are compared based on song Id
	 */
	public int compareTo(Song o) {
		return this.songId.compareTo(o.songId);	
	}
}