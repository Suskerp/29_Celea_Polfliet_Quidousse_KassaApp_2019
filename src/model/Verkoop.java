package model;

import database.DatabaseException;
import database.Factory.DBInMemoryFactory;
import database.PropertiesLoadWrite;
import database.Strategy.ArtikelDBStrategy;
import model.Discount.KortingStrategy;
import model.States.*;

import java.util.*;

/**
 * @author Rafael Polfliet - Jef Quidousse
 */

public class Verkoop {
    private VerkoopState verkoopState;
    private HashMap<Integer,Artikel> artikels;
    private ArrayList<Artikel> scannedItems;
    private LinkedHashMap<Artikel,Integer> klantMap;
    private ArtikelDBStrategy artikelDBStrategy;
    private KortingStrategy kortingStrategy;

    public Verkoop() {

        verkoopState = new InScan(this);
        artikelDBStrategy = PropertiesLoadWrite.getInstance().readDBContext();
        this.artikels = artikelDBStrategy.load();
        kortingStrategy = PropertiesLoadWrite.getInstance().readKorting();
        this.scannedItems = new ArrayList<>();
        this.klantMap = new LinkedHashMap<>();

    }

    public void setVerkoopState(VerkoopState verkoopState){
        this.verkoopState = verkoopState;
    }

    public VerkoopState getVerkoopState(){
        return this.verkoopState;
    }

    public HashMap<Integer, Artikel> getArtikels() {
        return artikels;
    }

    public void addToScannedItems(Artikel artikel){
        scannedItems.add(artikel);
    }

    public ArrayList<Artikel> getScannedItems() {
        ArrayList<Artikel> out = new ArrayList<>();
        out.addAll(scannedItems);
        return out;
    }

    public LinkedHashMap<Artikel,Integer> getMapOfScannedItems(){
        for (Artikel artikel:scannedItems){
            klantMap.put(artikel, Collections.frequency(scannedItems,artikel));
        }
        return klantMap;
    }

    public KortingStrategy getKortingStrategy() {
        return kortingStrategy;
    }


    public void removeFromScanned(Artikel artikel){
        scannedItems.remove(artikel);
        klantMap.clear();
    }

    public Double getSum(){
        double total = 0;

        for (Artikel artikel: getScannedItems()){
            total += artikel.getVerkoopprijs();
        }

        return total;
    }

    public ArtikelDBStrategy getArtikelDBStrategy() {
        return artikelDBStrategy;
    }

    public void setArtikels() {
        artikels = PropertiesLoadWrite.getInstance().readDBContext().load();
    }

}
