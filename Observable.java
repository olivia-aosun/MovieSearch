package hw5;

import java.io.IOException;
/**
 * This interface class defines the behavior of objects of Observable type.
 * @author olivia_aosun
 *
 */
public interface Observable {
	
	/**
	 * Registers given observer
	 * @param ob observer to be registered
	 */
	public void register(Observer ob);
	
	/**
	 * Unregisters given observer
	 * @param ob observer to be unregistered
	 */
	public void unregister(Observer ob);
	
	/**
	 * Notifies observers of change
	 * @throws IOException if an input or output operation is failed
	 */
	public void notifyObservers() throws IOException;
	
	/**
	 * Gets updates from subject
	 * @param ob the observer to get update
	 * @return the update as a generic Object
	 */
	public Object getUpdate(Observer ob);
}
