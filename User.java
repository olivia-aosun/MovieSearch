package hw5;

import java.util.HashMap;
import java.util.HashSet;

/**
 * This class represents a User object
 * @author olivia_aosun
 *
 */
public class User {
	private final int numOfNeighbors = 20;
	private int id;
	private HashMap<Movie, Double> moviesRated;
	private double weightedAvgRating;
	private int count;
	private double total;
	
	public User (int id) {
		this.id = id;
		moviesRated = new HashMap<>();
		weightedAvgRating = 0;
		count = 0;
		total = 0;
	}
		
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * Adds a rating of specific movie
	 * @param m movie to be rated 
	 * @param rating
	 */
	public void rateMovie(Movie m, double rating) {
		moviesRated.put(m, rating);
		total += rating;
		count += 1;
		weightedAvgRating = total/count;
	}
	
	/**
	 * Gets user's rating of specified movie
	 * @param m the movie
	 * @return rating
	 */
	public double getRating(Movie m) {
		return moviesRated.get(m);
	}
	
	/**
	 * Gets user's weighted average rating 
	 * of all movies this user has rated 
	 * @return weightedAvgRating
	 */
	public double getAvgRating() {
		return weightedAvgRating;
	}

	/**
	 * @return the moviesRated
	 */
	public HashMap<Movie, Double> getMoviesRated() {
		return moviesRated;
	}

	/**
	 * @return the numOfNeighbors
	 */
	public int getNumOfNeighbors() {
		return numOfNeighbors;
	}

	/**
	 * Computes the similarity between this user and another user passed in as parameter
	 * @param u the user to compare with
	 * @return similarity 
	 */
	public double getSimilarity(User u) {
		HashSet<Movie> moviesInCommon = new HashSet<>(this.getMoviesRated().keySet());
		moviesInCommon.retainAll(u.getMoviesRated().keySet());
		double cov = 0;
		double sumOfSd1 = 0; 
		double sumOfSd2 = 0;
		for (Movie m: moviesInCommon) {
			double rating1 = this.getMoviesRated().get(m);
			double rating2 = u.getMoviesRated().get(m);
			double diff1 = rating1 - this.getAvgRating();
			double diff2 = rating2 - u.getAvgRating();
			sumOfSd1 += Math.pow(diff1, 2);
			sumOfSd2 += Math.pow(diff2, 2);
			cov = cov + diff1*diff2;
		}
		double similarity = cov/(Math.sqrt(sumOfSd1)*(Math.sqrt(sumOfSd2)));
		if (Double.isNaN(similarity)) {
			similarity = 0;
		}
		return similarity;
	}
	
}
