package controller;

import view.ObserverInventory;
import view.ObserverShoppingCart;

public interface SubjectInventory {
    void registerObserverInventory(ObserverInventory e);
    void removeObserverInventory(ObserverInventory e);
    void notifyObserversInventory();
}
