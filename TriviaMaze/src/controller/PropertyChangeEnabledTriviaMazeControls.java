package Controller;

import java.beans.PropertyChangeListener;
/**
 * Defines behaviors allowing PropertyChangeListeners to be added or removed from a
 * TriviaMaze object.
 * Defines a set of Properties that may be listened too. Implementing class may further define
 * more Properties.
 *
 * @author Matthew Uzunoe-Chin, Dustin Feldt, Elias Arriolas
 * @version Spring 2024
 *
 */
public interface PropertyChangeEnabledTriviaMazeControls extends TriviaMazeControls {

    /**
     * A property name for the player.
     */
    String PROPERTY_PLAYER = "player";

    /**
     * Add a PropertyChangeListener to the listener list. The listener is registered for
     * all properties.
     *
     * @param theListener The PropertyChangeListener to be added
     */
    void addPropertyChangeListener(final PropertyChangeListener theListener);

}