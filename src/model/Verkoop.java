package model;

import database.DatabaseException;
import database.Factory.DBInMemoryFactory;
import database.PropertiesLoadWrite;
import database.Strategy.ArtikelDBStrategy;
import model.Discount.KortingStrategy;
import model.States.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author Rafael Polfliet - Jef Quidousse
 */

public class Verkoop {
    private VerkoopState verkoopState;
    private VerkoopState scan;
    private VerkoopState afgesloten;
    private VerkoopState betaald;
    private VerkoopState annuleer;
    private VerkoopState hold;
    private ArrayList<Artikel> artikels;
    private ArrayList<Artikel> scannedItems;
    private LinkedHashMap<Artikel,Integer> klantMap;
    private ArtikelDBStrategy artikelDBStrategy;
    private KortingStrategy kortingStrategy;

    public Verkoop() {
        scan = new InScan(this);
        afgesloten = new InAfsluit(this);
        betaald = new InBetaal(this);
        annuleer = new InAnnulering(this);
        hold = new InHold(this);

        verkoopState = scan;
        artikelDBStrategy = PropertiesLoadWrite.readDBContext();
        this.artikels = artikelDBStrategy.load();
        kortingStrategy = PropertiesLoadWrite.readKorting();
        this.scannedItems = new ArrayList<>();
        this.klantMap = new LinkedHashMap<>();

    }

    public void setVerkoopState(VerkoopState verkoopState){
        this.verkoopState = verkoopState;
    }

    public void scannen(String id){
        if (verkoopState == scan) {
            Boolean found = false;
            for (Artikel artikel : artikels) {
                if (artikel.getCode().equalsIgnoreCase(id)) {
                    scannedItems.add(artikel);
                    found = true;
                }
            }
            if (!found) throw new DatabaseException("This item id doesn't exist");
            verkoopState = scan;
        }else throw new StateException("Kan nu geen artikel meer toevoegen");
    }

    public VerkoopState getVerkoopState(){
        return this.verkoopState;
    }

    public void afsluiten(){
        if (verkoopState != scan) throw new StateException("Verkoop al afgesloten");
        verkoopState = afgesloten;
    }

    public void betalen(){
        verkoopState = betaald;
        save();
    }
    public void placeOnHold(){
        if (verkoopState == scan){
            this.verkoopState = hold;
        }else throw new StateException("Kan de winkelwagen kan niet meer op hold zetten");

    }

    public void returnFromHold(){
        this.verkoopState = scan;
    }

    public void annuleren(){
        verkoopState = annuleer;
    }
    public ArrayList<Artikel> getArtikels() {
        return artikels;
    }



    public ArrayList<Artikel> getScannedItems() {
        ArrayList<Artikel> out = new ArrayList<>();
        out.addAll(scannedItems);
        return out;
    }

    public void verwijderFromScannedItems(String id) {
        if (verkoopState == scan || verkoopState == afgesloten) {
            Artikel artikelTBRemoved = null;
            for (Artikel artikel : scannedItems) {
                if (artikel.getCode().equalsIgnoreCase(id)) artikelTBRemoved = artikel;
            }
            if (artikelTBRemoved != null) {
                scannedItems.remove(artikelTBRemoved);
                klantMap.clear();
            }
        }else throw new StateException("Kan nu geen artikel meer verwijderen");
    }

    public LinkedHashMap<Artikel,Integer> getScannedForKlant(){
        for (Artikel artikel:scannedItems){
            klantMap.put(artikel, Collections.frequency(scannedItems,artikel));
        }
        return klantMap;
    }

    public Double getKorting(){
        if (verkoopState != afgesloten) throw new StateException("Kan korting niet berekening terwijl er nog gescand wordt");
        return kortingStrategy.getKorting(getScannedItems());
    }


    public Double getSum(){
        double total = 0;

        for (Artikel artikel: getScannedItems()){
            total += artikel.getVerkoopprijs();
        }

        return total;
    }

    public Double getFinalSum(){
        if (verkoopState == afgesloten) {
            return getSum() - getKorting();
        }else throw new StateException("Kan eindsom niet berekenen terwijl er nog gescand wordt");
    }


    private void save(){
        if (this.verkoopState != betaald) throw new StateException("Kan niet saven als de lijst nog niet betaald is");
        for (Map.Entry<Artikel,Integer> entry:getScannedForKlant().entrySet()){
            artikels.get(artikels.indexOf(entry.getKey())).setStock(entry.getKey().getStock()-entry.getValue());
        }
        ArrayList<Object> out = new ArrayList<>();
        out.addAll(artikels);
        artikelDBStrategy.save(out);
    }


    public VerkoopState getScanbareState(){ return scan;}
    public VerkoopState getAfsluitbareState(){ return afgesloten;}
    public VerkoopState getBetaalbareState(){return betaald;}
    public VerkoopState getAnnuleerbareState(){return annuleer;}
    public VerkoopState getHoldState(){return hold;}
}
