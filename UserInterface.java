package hw5;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * This class represents a user interface 
 * that has all the functionality interfacing with a user (who is currently running the program)
 * @author olivia_aosun
 *
 */
public class UserInterface implements Observable{
	private List<Observer> observers = new ArrayList<Observer>();
	private ReaderFactory rf;
	private FileReader mr;
	private FileReader rr;
	private DataController dc;
	private String input;
	
	public UserInterface() {
		
	}
	
	/**
	 * Sets up all backend data structures 
	 * @throws FileNotFoundException if reading file fails
	 */
	private void setUp() throws FileNotFoundException {
		System.out.println("Setting up, please wait...");
		rf = new ReaderFactory();
		mr = rf.getReader("movie");
		rr = rf.getReader("rating");
		dc = new DataController();
		mr.readFile("movies.dat", dc.getDb());
		rr.readFile("ratings.dat", dc.getDb());
		Scanner sc = new Scanner(System.in);
		System.out.println("Please specify a path to write the log. Example format: example.txt");
		String s = sc.next();
		Logger.getInstance().setPath(s);
	}
	
	/**
	 * Prints welcome message to user 
	 * serves as the entry point 
	 * @throws FileNotFoundException if setUp() encounters read file failure
	 */
	public void welcomeScreen() throws FileNotFoundException {
		System.out.println("Welcome to the movie search program.");
		System.out.println("====================================");
		setUp();
		boolean quit = false;
		while (!quit) {
			System.out.println("Menu: ");
			System.out.println("Option 1: Give us a movie id and a user id to get the system’s prediction for the user’s likely preference of that movie");
			System.out.println("Option 2: Give us a user id and a threshold n to get n-highest predicted preferences for that user");
			System.out.println("Option 3: Quit");
			System.out.println("Please enter 1, 2 or 3.");
			System.out.println("====================================");
			Scanner sc = new Scanner(System.in);
			boolean valid = false;	
			int option = 0;	
			while (!valid) {
				try {
					String s = sc.next();
					option = Integer.parseInt(s);
					if (option == 1 || option == 2) {
						valid = true;
					} else if (option == 3) {
						System.out.println("You have exited the program.");
						quit = true;
						return;
					} else {
						System.out.println("The input is invalid, please try again.");
						continue;
					}
				} catch (Exception e) {
					System.out.println("The input is invalid, please try again.");
				}
			}
			runProgram(option);
		}	
	}
	
	/**
	 * Runs the actually program depending on user's option 
	 * @param option
	 */
	private void runProgram(int option) {
		Scanner sc = new Scanner(System.in);
		boolean valid = false;
		while (!valid) {
			if (option == 1) {
				System.out.println("====================================");
				int userId = Integer.MAX_VALUE;
				int movieId = Integer.MAX_VALUE;
				System.out.println("Please enter a user id or enter 'menu' to return to the menu");
				while (userId == Integer.MAX_VALUE) {
					try {
						input = sc.next();
						updateInput();
						if (input.equalsIgnoreCase("menu")) {
							return;
						}
						userId = Integer.parseInt(input);
					} catch (Exception e) {
						System.out.println("Wrong format, please try again.");
					}			
				}
				System.out.println("Please enter a movie id or enter 'menu' to return to the menu");
				while (movieId == Integer.MAX_VALUE) {
					try {
						input = sc.next();
						updateInput();
						if (input.equalsIgnoreCase("menu")) {
							return;
						}
						movieId = Integer.parseInt(input);
					} catch (Exception e) {
						System.out.println("Wrong format, please try again.");
					}
				}
				User u = dc.getDb().getUsers().get(userId);
				Movie m = dc.getDb().getMovies().get(movieId);
				if (u == null) {
					System.out.println("User not found. Please try again.");
					continue;
				} else if (m == null) {
					System.out.println("Movie not found. Please try again.");
					continue;
				} else {
					valid = true;
				}
				System.out.println("Calculating, please wait...");
				System.out.println("====================================");
				double prediction = dc.getPrediction(u, m);
				if (Double.isNaN(prediction)) {
					System.out.println("Sorry, we cannot generate a prediction for the user-movie combination you entered.");
				} else {
					System.out.println("Predicted rating for movie " + movieId + " is " + prediction);
				}
				System.out.println("====================================");
			} else if (option == 2) {
				System.out.println("====================================");
				int userId = Integer.MAX_VALUE;
				int threshold = Integer.MAX_VALUE;
				System.out.println("Please enter a user id or enter 'menu' to return to the menu");
				while (userId == Integer.MAX_VALUE) {
					try {
						input = sc.next();
						updateInput();
						if (input.equalsIgnoreCase("menu")) {
							return;
						}
						userId = Integer.parseInt(input);
					} catch (Exception e) {
						System.out.println("Wrong format, please try again.");
					}			
				}
				System.out.println("Please enter how many reccomendations you would like or enter 'menu' to return to the menu"); 
				while (threshold == Integer.MAX_VALUE) {
					try {
						input = sc.next();
						updateInput();
						if (input.equalsIgnoreCase("menu")) {
							return;
						}
						threshold = Integer.parseInt(input);
					} catch (Exception e) {
						System.out.println("Wrong format, please try again.");
					}			
				}
				User u = dc.getDb().getUsers().get(userId);
				if (u == null) {
					System.out.println("User not found. Please try again.");
					continue;
				} else {
					valid = true;
				}
				System.out.println("Calculating, please wait...");
				ArrayList<Integer> recs = new ArrayList<>(dc.getTopRecs(u, threshold));
				System.out.println("====================================");
				System.out.println("Recommended movies are:");
				for (Integer i: recs) {
					System.out.println("Movie Id: " + i);
				}
				System.out.println("====================================");
			}
		}		
	}
	
	/**
	 * Registers given observer
	 * @param ob observer to be registered
	 */
	@Override
	public void register(Observer ob) {
		if(ob == null) {
			throw new NullPointerException("Null Observer");
		}
		if(!observers.contains(ob)) {
			observers.add(ob);
		}		
	}

	/**
	 * Unregisters given observer
	 * @param ob observer to be unregistered
	 */
	@Override
	public void unregister(Observer ob) {
		observers.remove(ob);	
	}
	
	/**
	 * Notifies observers of change
	 * @throws IOException if an input or output operation is failed
	 */
	@Override
	public void notifyObservers() throws IOException {
		 for (Observer observer : observers) {
	         observer.update();
	      }		
	}
	
	/**
	 * Gets updates from subject
	 * @param ob the observer to get update
	 * @return the update as a generic Object
	 */
	@Override
	public Object getUpdate(Observer ob) {
		return (input==null? "": this.input);		
	}
	
	/**
	 * update with observers whenever the input is updated
	 * @throws IOException
	 */
	public void updateInput() throws IOException{
		notifyObservers();
	}
}

