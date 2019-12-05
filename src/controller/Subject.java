package controller;

import view.Observer;

public interface Subject {
    void registerObserver(Observer e);
    void removeObserver(Observer e);
    void notifyObservers();
}
