package hw5;

import java.util.HashMap;

/**
 * This class represents a database that stores all the user and movie information.
 * @author olivia_aosun
 *
 */
public class Database {
	private HashMap<Integer, User> users;
	private HashMap<Integer, Movie> movies;
	private HashMap<User, HashMap<Movie,Double>> userToMovie;
	
	public Database () {
		users = new HashMap<>();
		movies = new HashMap<>();
		userToMovie = new HashMap<>();
	}
	
	/**
	 * Add a user to the database 
	 * @param u the user to be added
	 */
	public void addUser(User u) {
		users.put(u.getId(), u);		
	}
	
	/** 
	 * Update movies rated by certain user in the userToMovie map 
	 * @param u the user to be updated
	 */
	public void updateList(User u) {
		userToMovie.put(u, u.getMoviesRated());
	}
	
	/**
	 * Add a movie to the database 
	 * @param m the movie to be added
	 */
	public void addMovie(Movie m) {
		movies.put(m.getId(), m);
	}

	/**
	 * @return the users
	 */
	public HashMap<Integer, User> getUsers() {
		return users;
	}

	/**
	 * @return the movies
	 */
	public HashMap<Integer, Movie> getMovies() {
		return movies;
	}

	/**
	 * @return the userToMovie
	 */
	public HashMap<User, HashMap<Movie, Double>> getUserToMovie() {
		return userToMovie;
	}
	
	
}
