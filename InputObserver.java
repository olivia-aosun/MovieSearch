package hw5;

import java.io.IOException;

/**
 * This class represents an input observer 
 * that updates whenever a new input string is read 
 * @author olivia_aosun
 *
 */
public class InputObserver implements Observer{
	private Observable subject;
	
	/**
	 * Updates the observer 
	 * @throws IOException if an input or output operation is failed
	 */
	@Override
	public void update() throws IOException {
		String input = null;
		try {
			input = (String) subject.getUpdate(this);
		} catch (Exception e) {
			e.printStackTrace();
		}
		Logger.getInstance().writeLog(input);
	}

	/**
	 * Attaches which object to observe
	 * @param sub of Observable type to be observed
	 */
	@Override
	public void setSubject(Observable sub) {
		this.subject = sub;
	}
	

}
