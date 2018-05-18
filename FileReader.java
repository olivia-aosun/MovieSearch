package hw5;

import java.io.FileNotFoundException;
/**
 * This interface class defines behaviors of a file reader.
 * @author olivia_aosun
 *
 */
public interface FileReader {
	
	/**
	 * Reads specific file and parse data into a database 
	 * @param filename of the file to be read
	 * @param db database
	 * @throws FileNotFoundException if file passed in cannot be found/opened 
	 */
	public void readFile(String filename, Database db) throws FileNotFoundException;
	
}
