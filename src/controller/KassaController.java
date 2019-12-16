package controller;

import database.PropertiesLoadWrite;
import model.Artikel;
import model.States.*;
import model.Verkoop;
import view.*;


import javax.swing.*;
import java.util.ArrayList;

/**
 * @author Rafael Polfliet
 */

public class KassaController implements KassaObservable {

    private KlantView klantView;
    private KassaView kassaView;
    private ProductOverviewPane productOverviewPane;
    private LogPane logPane;
    private ArrayList<Verkoop> verkopen;
    private int huidigeVerkoop;
    private ArrayList<KassaObserver> observersKlant;
    private ArrayList<KassaObserver> observersInventories;
    private ArrayList<KassaObserver> observersLog;



    public KassaController() {

        verkopen = new ArrayList<>();

        verkopen.add(new Verkoop());
        huidigeVerkoop = 0;



        productOverviewPane = new ProductOverviewPane(this);
        logPane = new LogPane();
        kassaView = new KassaView(this);
        klantView = new KlantView();

        observersKlant = new ArrayList<>();
        observersInventories = new ArrayList<>();
        observersLog = new ArrayList<>();

        registerObserverKlant(klantView);
        registerObserverInventory(productOverviewPane);
        registerObserverLog(logPane);
    }

    private Verkoop getHuidigeVerkoop(){
        return verkopen.get(huidigeVerkoop);
    }

    public VerkoopState getHuidigeVerkoopState(){
        return getHuidigeVerkoop().getVerkoopState();
    }
    public double getKorting(){
        return getHuidigeVerkoop().getKorting();
    }
    public void scanItem(String id){
        try {
            getHuidigeVerkoop().scannen(id);
            notifyObserversKlant();
        }catch (StateException e){
            JOptionPane.showMessageDialog(null, e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void verwijder(String id){
        getHuidigeVerkoop().verwijderFromScannedItems(id);
        notifyObserversKlant();
    }
    public void annuleren(){
        if(getHuidigeVerkoop().getScannedItems().size()!=0) {
            getHuidigeVerkoop().annuleren();
            holdCheck();
            notifyObserversKlant();
        }
    }
    public void betalen(){
        if(getHuidigeVerkoop().getScannedItems().size()!=0) {
                PropertiesLoadWrite.getInstance().readBill().print(getHuidigeVerkoop());
                getHuidigeVerkoop().betalen();
                notifyObserversInventory();
                notifyObserversLog();
                holdCheck();
                notifyObserversKlant();
        }
    }

    private void holdCheck() {
        int holdIndex = getHoldIndex();
        int huidigeIndex = verkopen.size()-1;
        if (holdIndex >= 0) {
            if (huidigeIndex - holdIndex >= 3) {
                verkopen.get(holdIndex).annuleren();
                verkopen.add(new Verkoop());
                huidigeVerkoop = verkopen.size()-1;
            } else {
                verkopen.get(holdIndex).returnFromHold();
                huidigeVerkoop = holdIndex;
            }
        } else {
            verkopen.add(new Verkoop());
            huidigeVerkoop = verkopen.size()-1;
        }
    }
    public void afsluiten(){
        if(getHuidigeVerkoop().getScannedItems().size()!=0) {
            getHuidigeVerkoop().afsluiten();
        }
    }


    public ArrayList<Artikel> getScannedItems(){
        return getHuidigeVerkoop().getScannedItems();
    }

    private int getHoldIndex(){
        for (Verkoop verkoop:verkopen){
            if (verkoop.getVerkoopState() == verkoop.getHoldState()){
                return verkopen.indexOf(verkoop);
            }
        }
        return -1;
    }

    public void placeOnHold(){
        if(getHuidigeVerkoop().getScannedItems().size()!=0) {
            if (getHoldIndex() < 0) {
                getHuidigeVerkoop().placeOnHold();
                verkopen.add(new Verkoop());
                huidigeVerkoop = verkopen.size() - 1;
                notifyObserversKlant();
            } else throw new StateException("Al een verkoop op hold");
        }
    }


    public Double getSum(){
        return getHuidigeVerkoop().getSum();
    }

    public ArrayList<Artikel> getArtikels(){
       return getHuidigeVerkoop().getArtikels();
    }

    public double getFinalSum(){
        return getHuidigeVerkoop().getFinalSum();
    }


    @Override
    public void registerObserverInventory(KassaObserver e) {
        observersInventories.add(e);
    }

    @Override
    public void removeObserverInventory(KassaObserver e) {
        observersInventories.remove(e);
    }

    @Override
    public void notifyObserversInventory() {
        for (KassaObserver kassaObserver :observersInventories){
            kassaObserver.update(getHuidigeVerkoop().getArtikels());
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

    public ProductOverviewPane getProductOverviewPane() {
        return productOverviewPane;
    }

    public LogPane getLogPane() {
        return logPane;
    }
}
