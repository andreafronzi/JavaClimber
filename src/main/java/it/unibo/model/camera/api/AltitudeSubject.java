package it.unibo.model.camera.api;

public interface AltitudeSubject {
    void addObserver(AltitudeObserver observer);
    void removeObserver(AltitudeObserver observer);
    void notifyObserver(double delta);
}
