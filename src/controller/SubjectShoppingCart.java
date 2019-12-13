package controller;

import view.ObserverShoppingCart;

public interface SubjectShoppingCart {
    void registerObserverShoppingCart(ObserverShoppingCart e);
    void removeObserverShoppingCart(ObserverShoppingCart e);
    void notifyObserversShoppingCart();
}
