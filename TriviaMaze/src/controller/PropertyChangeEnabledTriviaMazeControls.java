package controller;

import java.beans.PropertyChangeListener;

public interface PropertyChangeEnabledTriviaMazeControls extends TriviaMazeControls {

    String PROPERTY_DOOR= "door";

    String PROPERTY_EXIT = "end";
    String PROPERTY_START = "start";

    String PROPERTY_GRID = "the maze grid";
    String PROPERTY_PLAYER = "player";


    void addPropertyChangeListener(final PropertyChangeListener theListener);

    void addPropertyChangeListener(final String thePropertyName, final PropertyChangeListener theListener);

    void removePropertyChangeListener(final PropertyChangeListener theListener);

    void removePropertyChangeListener(final String thePropertyName,
                                      final PropertyChangeListener theListener);
}

