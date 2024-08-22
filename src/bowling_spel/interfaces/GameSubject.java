package bowling_spel.interfaces;

//Interface for defining a subject in the game, to be observed by GameObservers
public interface GameSubject {

	/**
     * Registers an observer to be notified of changes.
     *
     * @param observer the GameObserver to be registered
     */
    void registerObserver(GameObserver observer);

    /**
     * Removes an observer from the notification list.
     *
     * @param observer the GameObserver to be removed
     */
    void removeObserver(GameObserver observer);

    /**
     * Notifies all registered observers of an event.
     *
     * @param event the event that occurred, represented as a String
     */
    void notifyObservers(String event);
}
