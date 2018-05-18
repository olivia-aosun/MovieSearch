package hw5;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * This class represents a rating reader 
 * that reads in a ratings file 
 * parse the data and stores each User as a User object
 * @author olivia_aosun
 *
 */
public class RatingReader implements FileReader {

	/**
	 * Reads specific file and parse data into a database 
	 * @param filename of the file to be read
	 * @param db database
	 * @throws FileNotFoundException if file passed in cannot be found/opened 
	 */
	@Override
	public void readFile(String filename, Database db) throws FileNotFoundException {
		File file = new File(filename);
		Scanner sc = new Scanner(file);
		
		while (sc.hasNextLine()) {
			String line = sc.nextLine();
			String[] tokens = line.split("::");
			int userId = Integer.parseInt(tokens[0]);
			int movieId = Integer.parseInt(tokens[1]);
			double rating = Double.parseDouble(tokens[2]);
					
			if (db.getUsers().containsKey(userId)) {
				User u = db.getUsers().get(userId);
				Movie m = db.getMovies().get(movieId);
				u.rateMovie(m, rating);
				db.updateList(u);
			} else {
				User u = new User(userId);
				Movie m = db.getMovies().get(movieId);
				u.rateMovie(m, rating);
				db.addUser(u);
				db.updateList(u);
			}
			
		}
	}

}
