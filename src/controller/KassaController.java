package controller;

import model.Artikel;
import model.Verkoop;
import view.KassaView;
import view.KlantView;
import view.Observer;


import java.util.ArrayList;

/**
 * @author Rafael Polfliet
 */

public class KassaController implements Subject{

    private KlantView klantView;
    private KassaView kassaView;
    private Verkoop verkoop;

    private ArrayList<Observer> observers;



    public KassaController() {

        verkoop = new Verkoop();

        kassaView = new KassaView(this);
        klantView = new KlantView();

        observers = new ArrayList<>();


        registerObserver(klantView);
    }

    public double getKorting(){
        return verkoop.getKorting();
    }
    public void scanItem(String id){
        verkoop.scan(id);
        notifyObservers();
    }

    public void verwijder(String id){
        verkoop.verwijderFromScannedItems(id);
        notifyObservers();
    }

    public ArrayList<Artikel> getScannedItems(){
        return verkoop.getScannedItems();
    }

    public void placeOnHold(){
        verkoop.placeOnHold();
        notifyObservers();
    }

    public void returnFromHold(){
        verkoop.returnFromHold();
        notifyObservers();
    }

    public Double getSum(){
        return verkoop.getSum();
    }

    public ArrayList<Artikel> getArtikels(){
       return verkoop.getArtikels();
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
            observer.update(verkoop.getScannedForKlant(), verkoop.getSum());
        }
    }

    public double getFinalSum(){
        return verkoop.getFinalSum();
    }
}
