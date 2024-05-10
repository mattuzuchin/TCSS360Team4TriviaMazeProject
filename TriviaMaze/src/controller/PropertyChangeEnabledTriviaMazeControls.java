package controller;

import java.beans.PropertyChangeListener;

public interface PropertyChangeEnabledTriviaMazeControls extends TriviaMazeControls {

    String PROPERTY_QUESTION= "question";

    String PROPERTY_EXIT = "end";
    String PROPERTY_START = "start";
    String PROPERTY_ANSWER = "answer";
    String PROPERTY_MAZE = "maze";


    void addPropertyChangeListener(PropertyChangeListener theListener);

    void addPropertyChangeListener(String thePropertyName, PropertyChangeListener theListener);

    void removePropertyChangeListener(PropertyChangeListener theListener);

    void removePropertyChangeListener(String thePropertyName,
                                      PropertyChangeListener theListener);
}
