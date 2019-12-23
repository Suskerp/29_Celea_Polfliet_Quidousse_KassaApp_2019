package controller;

import database.PropertiesLoadWrite;
import model.Artikel;
import model.SortByName;
import model.States.*;
import model.Verkoop;
import view.panes.KassaObserver;


import javax.swing.*;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author Rafael Polfliet
 */

public class KassaController implements KassaObservable {
    private ArrayList<Verkoop> verkopen;
    private int huidigeVerkoop;
    private ArrayList<KassaObserver> observersKlant;
    private ArrayList<KassaObserver> observersInventories;
    private ArrayList<KassaObserver> observersLog;

    public KassaController() {
        this.verkopen = new ArrayList<>();
        this.verkopen.add(new Verkoop());

        this.huidigeVerkoop = 0;

        this.observersKlant = new ArrayList<>();
        this.observersInventories = new ArrayList<>();
        this.observersLog = new ArrayList<>();
    }

    private Verkoop getHuidigeVerkoop(){
        return verkopen.get(huidigeVerkoop);
    }

    public VerkoopState getHuidigeVerkoopState(){
        return getHuidigeVerkoop().getVerkoopState();
    }
    public double getKorting(){
        return getHuidigeVerkoop().korting();
    }
    public void scanItem(int id){
        try {
            getHuidigeVerkoop().scanItem(id);
            notifyObserversKlant();
        }catch (StateException e){
            JOptionPane.showMessageDialog(null, e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void verwijder(int id){
        getHuidigeVerkoop().verwijder(id);
        notifyObserversKlant();
    }

    public void annuleren(){
        if(getHuidigeVerkoop().getScannedItems().size()!=0) {
            getHuidigeVerkoop().annuleer();
            holdCheck();
            notifyObserversKlant();
        }
    }

    public void betalen(){
        if(getHuidigeVerkoop().getScannedItems().size()!=0) {
                PropertiesLoadWrite.getInstance().readBill().print(getHuidigeVerkoop());
                getHuidigeVerkoop().betaald();
                notifyObserversInventory();
                notifyObserversLog();
                holdCheck();
                notifyObserversKlant();
        }
    }

    private void holdCheck() {
        int holdIndex = getHoldIndex();
        if (holdIndex >= 0) {
            int huidigeIndex = verkopen.size()-1;
            if (huidigeIndex - holdIndex >= 3) {
                verkopen.get(holdIndex).annuleer();
                verkopen.add(new Verkoop());
                huidigeVerkoop = verkopen.size()-1;
            } else {
                verkopen.get(holdIndex).scan();
                huidigeVerkoop = holdIndex;
            }
        } else {
            verkopen.add(new Verkoop());
            huidigeVerkoop = verkopen.size()-1;
        }
    }

    public void afsluiten(){
        if(getHuidigeVerkoop().getScannedItems().size()!=0) {
            getHuidigeVerkoop().afgesloten();
        }
    }

    public ArrayList<Artikel> getScannedItems(){
        return getHuidigeVerkoop().getScannedItems();
    }

    private int getHoldIndex(){
        for (Verkoop verkoop:verkopen){
            if (verkoop.getVerkoopState() instanceof InHold){
                return verkopen.indexOf(verkoop);
            }
        }
        return -1;
    }

    public void placeOnHold(){
        if(getHuidigeVerkoop().getScannedItems().size()!=0) {
            if (getHoldIndex() < 0) {
                getHuidigeVerkoop().hold();
                verkopen.add(new Verkoop());
                huidigeVerkoop = verkopen.size() - 1;
                notifyObserversKlant();
            } else throw new StateException("Al een verkoop op hold");
        }
    }

    public Double getSum(){
        return getHuidigeVerkoop().getSum();
    }

    public HashMap<Integer, Artikel> getArtikels(){
       return getHuidigeVerkoop().getArtikels();
    }

    public double getFinalSum(){
        return getHuidigeVerkoop().finalSum();
    }

    @Override
    public void registerObserverInventory(KassaObserver e) {
        observersInventories.add(e);
        notifyObserversInventory();
    }

    @Override
    public void removeObserverInventory(KassaObserver e) {
        observersInventories.remove(e);
    }

    @Override
    public void notifyObserversInventory() {
        ArrayList<Artikel> artikels = new ArrayList<>(getHuidigeVerkoop().getArtikels().values());
        artikels.sort(new SortByName());
        for (KassaObserver kassaObserver :observersInventories){
            kassaObserver.update(artikels);
        }
    }

    @Override
    public void registerObserverKlant(KassaObserver e) {
        observersKlant.add(e);
    }

    @Override
    public void removeObserverKlant(KassaObserver e) {
        observersKlant.remove(e);
    }

    @Override
    public void notifyObserversKlant() {
        for (KassaObserver kassaObserver : observersKlant){
            kassaObserver.update(getHuidigeVerkoop().getScannedItems());
        }
    }

    @Override
    public void registerObserverLog(KassaObserver e) {
        observersLog.add(e);
    }

    @Override
    public void removeObserverLog(KassaObserver e) {
        observersLog.remove(e);
    }

    @Override
    public void notifyObserversLog() {
        for (KassaObserver kassaObserver: observersLog){
            kassaObserver.update(verkopen);
        }
    }

}
