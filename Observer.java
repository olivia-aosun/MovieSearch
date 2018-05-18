package hw5;

import java.io.IOException;

/**
 * This interface class defines behaviors of an observer
 * @author olivia_aosun
 *
 */
public interface Observer {
	
	/**
	 * Updates the observer 
	 * @throws IOException if an input or output operation is failed
	 */
	public void update() throws IOException;
	
	
	/**
	 * Attaches which object to observe
	 * @param sub of Observable type to be observed
	 */
	public void setSubject(Observable sub);
}
