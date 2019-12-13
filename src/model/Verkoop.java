package model;

import database.DatabaseException;
import database.PropertiesLoadWrite;
import model.Discount.KortingStrategy;
import model.States.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;

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

    private KortingStrategy kortingStrategy;

    public Verkoop() {
        scan = new InScan(this);
        afgesloten = new InAfsluit(this);
        betaald = new InBetaal(this);
        annuleer = new InAnnulering(this);
        hold = new InHold(this);

        verkoopState = scan;

        this.artikels = PropertiesLoadWrite.readDBContext().load();
        kortingStrategy = PropertiesLoadWrite.readKorting();
        this.scannedItems = new ArrayList<>();
        this.klantMap = new LinkedHashMap<>();

    }

    public void setVerkoopState(VerkoopState verkoopState){
        this.verkoopState = verkoopState;
    }

    public void scannen(){
        verkoopState.scan();
    }

    public VerkoopState getVerkoopState(){
        return this.verkoopState;
    }

    public void afsluiten(){
        verkoopState.afgesloten();
    }

    public void betalen(boolean genoegGeld){
        verkoopState.betaald(genoegGeld);
    }

    public void annuleren(boolean genoegGeld){
        verkoopState.annuleer(genoegGeld);
    }
    public ArrayList<Artikel> getArtikels() {
        return artikels;
    }

    public void scan(String id) {
        Boolean found = false;
        for (Artikel artikel:artikels){
            if (artikel.getCode().equalsIgnoreCase(id)){
                scannedItems.add(artikel);
                found = true;
            }
        }
        if (!found) throw new DatabaseException("This item id doesn't exist");
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
        }
    }

    public LinkedHashMap<Artikel,Integer> getScannedForKlant(){
        for (Artikel artikel:scannedItems){
            klantMap.put(artikel, Collections.frequency(scannedItems,artikel));
        }
        return klantMap;
    }

    public Double getKorting(){
        return kortingStrategy.getKorting(getScannedItems());
    }


   public void placeOnHold(){
        /*if (scannedItems.size() == 0) throw new ModelException("Can't place an empty cart on hold");
        if (hold.size() != 0) throw new ModelException("Already a cart on hold");
        hold.addAll(scannedItems);
        scannedItems.clear();
        klantMap.clear();*/
    }

    public void returnFromHold(){
        /*if (scannedItems.size() != 0) throw new ModelException("Current shopping cart has to be empty before returning cart on hold");
        if (hold.size() == 0) throw new ModelException("There is no cart on hold that can be returned");
        scannedItems.addAll(hold);
        hold.clear();*/
    }


    public Double getSum(){
        double total = 0;

        for (Artikel artikel: getScannedItems()){
            total += artikel.getVerkoopprijs();
        }

        return total;
    }

    public Double getFinalSum(){
        return getSum() - getKorting();
    }


    public VerkoopState getScanbareState(){ return scan;}
    public VerkoopState getAfsluitbareState(){ return afgesloten;}
    public VerkoopState getBetaalbareState(){return betaald;}
    public VerkoopState getAnnuleerbareState(){return annuleer;}
    public VerkoopState getHoldState(){return hold;}
}
