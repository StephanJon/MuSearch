package datatypes;

/**
 * Recommendation ADT containing song and "count" (amount of similar users)
 */
public class Recommendation {
	private int count;
	private String name;
	
	/**
	 * Constructor for Recommendation
	 * @param name ID of song
	 * @param count amount of shared listeners (Higher count = stronger recommendation)
	 */
	public Recommendation(String name, int count){
		this.count = count;
		this.name = name;
	}
	
	/**
	 * Getter for name
	 * @return Name
	 */
	public String name(){ return name; }
	
	/**
	 * Getter for count
	 * @return Count
	 */
	public int count(){return count; }
	
	/**
	 * String representation
	 */
	public String toString(){
		return name +", "+ count;
	}
}
