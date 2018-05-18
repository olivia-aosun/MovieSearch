package hw5;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Timestamp;

import static java.nio.file.StandardOpenOption.*;
import java.nio.file.*;
import java.io.*;

/**
 * This class represents a Logger Singleton
 * @author yiwang, Olivia  Sun
 *
 */
public class Logger {

	private static Logger logger = new Logger(); 
	private String filename;

	//Create a private constructor so that no other class can instantiate it 
	private Logger() {

	}
	/**
	 * This returns the only instance of the logger 
	 * @return the logger instance 
	 */
	public static Logger getInstance() {
		if(logger == null) {
			logger = new Logger();
		}
		return logger;
	}
	
	/**
	 * Sets the path to which the log is stored
	 * @param path relative path in the source folder 
	 */
	public void setPath(String path) {
		filename = path;
	}
	
	/**
	 * This method appends the new input into the log 
	 * @param input user input string 
	 * @throws IOException 
	 */
	public void writeLog(String input) throws IOException {
		Path p = Paths.get(filename);
		byte data[] = input.getBytes();
		OutputStream out = new BufferedOutputStream(Files.newOutputStream(p, CREATE, APPEND));	
		try {
			byte[] timestamp = new Timestamp(System.currentTimeMillis()).toString().getBytes();
			out.write(timestamp, 0, timestamp.length);
			out.write("  ".getBytes());
			out.write(data, 0, data.length);
			out.write("\r\n".getBytes());
		} catch (IOException x) {
			System.err.println(x);
		} finally {
			out.close();
		}
	}
	
}

