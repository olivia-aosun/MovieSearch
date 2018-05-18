package hw5;

/**
 * This class represents a Movie object
 * @author olivia_aosun
 *
 */
public class Movie{
	private int id;
	private String title;
	private double avgRating;
	private int count;
	
	public Movie (int id, String title) {
		this.id = id;
		this.title = title;
		avgRating = 0;
		count = 0;
	}
	
	/**
	 * Adds a rating to this movie 
	 * @param rating
	 */
	public void addRating(double rating) {
		avgRating = ((avgRating*count)+rating)/(count+1); 
		count += 1;
	}

	/**
	 * @return the avgRating
	 */
	public double getAvgRating() {
		return avgRating;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}
	
}
