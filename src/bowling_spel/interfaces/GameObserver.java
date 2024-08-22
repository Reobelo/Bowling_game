package bowling_spel.interfaces;

//Interface for defining an observer for the game
public interface GameObserver {
	/**
     * Updates the observer based on the event that occurred.
     *
     * @param event the event that occurred, represented as a String
     */
	void update(String event);
}
