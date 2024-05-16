package Controller;

import java.beans.PropertyChangeListener;

public interface PropertyChangeEnabledTriviaMazeControls extends TriviaMazeControls {

    String PROPERTY_PLAYER = "player";


    void addPropertyChangeListener(final PropertyChangeListener theListener);

}

