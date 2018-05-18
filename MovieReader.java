package hw5;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * This class represents a movie reader 
 * that reads in a movie file 
 * parse the data and stores each movie as a Movie object
 * @author olivia_aosun
 *
 */
public class MovieReader implements FileReader {

	/**
	 * Reads specific file and parse data into a database 
	 * @param filename of the file to be read
	 * @param db database
	 * @throws FileNotFoundException if file passed in cannot be found/opened 
	 */
	@Override
	public void readFile(String filename, Database ds) throws FileNotFoundException {
		File file = new File(filename);
		Scanner sc = new Scanner(file);
		
		while (sc.hasNextLine()) {
			String line = sc.nextLine();
			String[] tokens = line.split("::");
			int id = Integer.parseInt(tokens[0]);
			String title = tokens[1].replaceAll("[^A-Za-z ]+", "").trim();
			if (title.length() > 3 && title.substring(title.length()-3, title.length()-1).equalsIgnoreCase("the")) {
				title = "The" + title.substring(0, title.length()-3);
			}
			Movie m = new Movie(id, title);
			ds.addMovie(m);
		}
	}

}
