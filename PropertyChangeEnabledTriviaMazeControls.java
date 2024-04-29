package Controller;

import java.beans.PropertyChangeListener;

public interface PropertyChangeEnabledTriviaMazeControls extends TriviaMazeControls {

    String PROPERTY_DOOR= "door";

    String PROPERTY_EXIT = "end";
    String PROPERTY_START = "start";

    String PROPERTY_GRID = "the maze grid";


    void addPropertyChangeListener(PropertyChangeListener theListener);

    void addPropertyChangeListener(String thePropertyName, PropertyChangeListener theListener);

    void removePropertyChangeListener(PropertyChangeListener theListener);

    void removePropertyChangeListener(String thePropertyName,
                                      PropertyChangeListener theListener);
}

