package Controller;

public interface TriviaMazeControls {

    void advanceNorth();
    void advanceEast();
    void advanceSouth();
    void advanceWest();
    void lockDoor();
    void end();

    void start();

    void reset();

    int getHeight();


    int getWidth();
}
