package controller;

import database.*;
import database.Enum.ArtikelDBEnum;
import database.Enum.LoadSaveEnum;
import database.Strategy.ArtikelDBStrategy;
import javafx.collections.FXCollections;
import javafx.collections.ObservableMap;
import model.Artikel;
import view.Observer;


import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * @author Rafael Polfliet
 */

public class KassaController implements Subject{
    private ArtikelDBStrategy artikelDBStrategy;
    private ArrayList<Artikel> artikels;
    private ArrayList<Artikel> scannedItems;
    private ArrayList<Observer> observers;
    private LinkedHashMap<Artikel,Integer> klantMap;
    private ArrayList<Artikel> hold;


    public KassaController() {
        this.artikelDBStrategy = PropertiesLoadWrite.read();
        artikels = artikelDBStrategy.load();
        scannedItems = new ArrayList<>();
        observers = new ArrayList<>();
        klantMap = new LinkedHashMap<>();
        hold = new ArrayList<>();
    }

    public ArtikelDBStrategy getArtikelDBStrategy() {
        return artikelDBStrategy;
    }

    public static List<String> getContexts() {
        List<String> contextLijst = new ArrayList<>();

        for (database.Enum.ArtikelDBEnum ArtikelDBEnum: ArtikelDBEnum.values()){
            contextLijst.add(ArtikelDBEnum.toString());
        }

        return contextLijst;
    }
    public static List<String> getContextsInMem() {
        List<String> contextLijst = new ArrayList<>();

        for (LoadSaveEnum loadSaveEnum:LoadSaveEnum.values()){
            contextLijst.add(loadSaveEnum.toString());
        }

        return contextLijst;
    }

    public ArrayList<Artikel> getArtikels() {
        return artikels;
    }

    public Artikel scan(String id) {
        for (Artikel artikel:artikels){
            if (artikel.getCode().equalsIgnoreCase(id)){
                scannedItems.add(artikel);
                return artikel;
            }
        }
        throw new DatabaseException("This item id doesn't exist");
    }

    public ArrayList<Artikel> getScannedItems() {
        return scannedItems;
    }

    public void verwijderFromScannedItems(String id) {
        Artikel artikelTBRemoved = null;
        for (Artikel artikel:scannedItems){
            if (artikel.getCode().equalsIgnoreCase(id))  artikelTBRemoved = artikel;
        }
        if (artikelTBRemoved !=null) {
            scannedItems.remove(artikelTBRemoved);
            klantMap.clear();
            notifyObservers();
        }
    }

    private LinkedHashMap<Artikel,Integer> getScannedForKlant(){
        for (Artikel artikel:scannedItems){
            klantMap.put(artikel, Collections.frequency(scannedItems,artikel));
        }
        return klantMap;
    }


    private ArrayList<Artikel> load() {
        return artikelDBStrategy.load();
    }


    private void save(ArrayList<Object> artikels) {
        artikelDBStrategy.save(artikels);
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
            observer.update(getScannedForKlant());
        }
    }


    public void placeOnHold(){
        if (scannedItems.size() == 0) throw new ControllerException("Can't place an empty cart on hold");
        if (hold.size() != 0) throw new ControllerException("Already a cart on hold");
        hold.addAll(scannedItems);
        scannedItems.clear();
        klantMap.clear();
        notifyObservers();
    }

    public void returnFromHold(){
        if (scannedItems.size() != 0) throw new ControllerException("Current shopping cart has to be empty before returning cart on hold");
        if (hold.size() == 0) throw new ControllerException("There is no cart on hold that can be returned");
        scannedItems.addAll(hold);
        hold.clear();
        notifyObservers();
    }
}
