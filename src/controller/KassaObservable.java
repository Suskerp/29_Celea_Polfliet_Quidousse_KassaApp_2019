package controller;

import view.panes.KassaObserver;

public interface KassaObservable {
    void registerObserverInventory(KassaObserver e);
    void removeObserverInventory(KassaObserver e);
    void notifyObserversInventory();

    void registerObserverKlant(KassaObserver e);
    void removeObserverKlant(KassaObserver e);
    void notifyObserversKlant();

    void registerObserverLog(KassaObserver e);
    void removeObserverLog(KassaObserver e);
    void notifyObserversLog();
    
    
}
