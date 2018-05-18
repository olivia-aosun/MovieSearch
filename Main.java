package hw5;

import java.io.FileNotFoundException;

/**
 * This is the main class 
 * It is the entrance point of the program 
 * @author olivia_aosun
 *
 */
public class Main {

	public static void main(String[] args) throws FileNotFoundException {
		UserInterface ui = new UserInterface();
		Observer ob = new InputObserver();
		ob.setSubject(ui);
		ui.register(ob);
		ui.welcomeScreen();
		ui.unregister(ob);
	}

}
