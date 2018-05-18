package hw5;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map.Entry;
import java.util.TreeMap;

/**
 * This class represents a controller that manipulates data in the database
 * It is the controller part of the 3-tier architectural pattern.
 * @author olivia_aosun
 *
 */
public class DataController {
	private Database db;
	
	public DataController() {
		db = new Database();
	}
	
	/**
	 * @return the db
	 */
	public Database getDb() {
		return db;
	}

	/**
	 * This method gets the a certain number (defined by user's numOfNeighbors) of neighbors of given user
	 * and stores users with highest similarities in a HashMap
	 * 
	 * Neighbors are defined by those who
	 * 1) have rated Movie m 
	 * 2) have high similarity to User u
	 * @param u the user to generate neighbors of
	 * @param m the movie being rated
	 * @return a HashMap of Users and their corresponding similarity score 
	 */
	public HashMap<User, Double> getNeighbors(User u, Movie m) {
		TreeMap<Double, User> similarUsers = new TreeMap<>();
		HashMap<User, Double> neighbors = new HashMap<>();
		for (User o : db.getUsers().values()) {
			if (o.getMoviesRated().containsKey(m)) {
				double similarity = u.getSimilarity(o);
				similarUsers.put(similarity, o);
			}		
		}
		int num = 0;
		if (similarUsers.size() >= u.getNumOfNeighbors()) {
			num = u.getNumOfNeighbors();
		} else {
			num = similarUsers.size();
		}
		for (int i = 0; i < num; i++) {
			Entry<Double, User> e = similarUsers.pollLastEntry();
			neighbors.put(e.getValue(), e.getKey());
		}		
		return neighbors;
	}
	
	/**
	 * Predicts a user's likely rating of a given movie 
	 * @param u the user
	 * @param m the movie
	 * @return predicted rating 
	 */
	public double getPrediction(User u, Movie m) {
		double base = u.getAvgRating();
		HashMap<User, Double> neighbors = getNeighbors(u, m);
		double sumOfSim = 0;
		double sumOfSimTimesMd = 0;
		for (User o : neighbors.keySet()) {
			double mean = o.getAvgRating();
			double ratingOfM = o.getRating(m);
			double md = ratingOfM - mean;
			double similarity = neighbors.get(o);
			sumOfSim += Math.abs(similarity);
			sumOfSimTimesMd += similarity * md;
		}
		double predictedRating = base + sumOfSimTimesMd/sumOfSim;
		return predictedRating;
	}
	
	/**
	 * Generates a list of movies (in forms of id number) a user is likely to rate high on 
	 * @param u the user 
	 * @param threshold number of recommendations to generate
	 * @return a list of movie ids 
	 */
	public ArrayList<Integer> getTopRecs(User u, int threshold) {
		ArrayList<Integer> recs = new ArrayList<>();
		HashSet<Movie> unwatchedMovies = new HashSet<>(db.getMovies().values());
		unwatchedMovies.removeAll(u.getMoviesRated().keySet());
		TreeMap<Double, Integer> predictions = new TreeMap<>();
		for (Movie m : unwatchedMovies) {
			double predictedRating = getPrediction(u, m);
			if (!Double.isNaN(predictedRating)) {
				predictions.put(predictedRating, m.getId());
			}
		}
		int listLength = 0;
		if (predictions.size() > threshold) {
			listLength = threshold;
		} else {
			listLength = predictions.size();
		}
		for (int i = 0; i < listLength; i++) {
			Entry<Double, Integer> e = predictions.pollLastEntry();
			int r = e.getValue();
			recs.add(r);
		}
		return recs;	
	}
}
