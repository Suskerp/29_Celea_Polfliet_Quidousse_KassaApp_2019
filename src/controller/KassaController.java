package controller;

import database.*;
import database.Enum.ArtikelDBEnum;
import database.Enum.LoadSaveEnum;
import database.Strategy.ArtikelDBStrategy;
import javafx.collections.FXCollections;
import javafx.collections.ObservableMap;
import model.Artikel;
import model.Kassa;
import view.KassaView;
import view.KlantView;
import view.Observer;


import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * @author Rafael Polfliet
 */

public class KassaController implements Subject{

    private KlantView klantView;
    private KassaView kassaView;
    private Kassa kassa;

    private ArrayList<Observer> observers;



    public KassaController() {

        kassa = new Kassa();

        kassaView = new KassaView(this);
        klantView = new KlantView();

        observers = new ArrayList<>();


        registerObserver(klantView);
    }

    public double getKorting(){
        return kassa.getKorting();
    }
    public void scanItem(String id){
        kassa.scan(id);
        notifyObservers();
    }

    public void verwijder(String id){
        kassa.verwijderFromScannedItems(id);
        notifyObservers();
    }

    public ArrayList<Artikel> getScannedItems(){
        return kassa.getScannedItems();
    }

    public void placeOnHold(){
        kassa.placeOnHold();
        notifyObservers();
    }

    public void returnFromHold(){
        kassa.returnFromHold();
        notifyObservers();
    }

    public Double getSum(){
        return kassa.getSum();
    }

    public ArrayList<Artikel> getArtikels(){
       return kassa.getArtikels();
    }

    @Override
    public void registerObserver(Observer e) {
        observers.add(e);
    }

    @Override
    public void removeObserver(Observer e) {
        observers.remove(e);
    }

    @Override
    public void notifyObservers() {
        for (Observer observer:observers){
            observer.update(kassa.getScannedForKlant(),kassa.getSum());
        }
    }

    public double getFinalSum(){
        return kassa.getFinalSum();
    }
}
