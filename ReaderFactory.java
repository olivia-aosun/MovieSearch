package hw5;

/**
 * This class represents a concrete factory 
 * that generates different type of file readers
 * @author olivia_aosun
 *
 */
public class ReaderFactory {
	
	/**
	 * Generates specific file reader
	 * @param type file type
	 * @return MovieReader or RatingReader according to input file type
	 * null if input file type is unrecognized 
	 */
	public FileReader getReader(String type) {
		if (type.equalsIgnoreCase("movie")) {
			return new MovieReader();
		} else if (type.equalsIgnoreCase("rating")) {
			return new RatingReader();
		} else {
			return null;
		}
	}
}
