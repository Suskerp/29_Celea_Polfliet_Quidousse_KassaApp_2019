package model;

import database.DatabaseException;
import view.Observer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;

/**
 * @author Rafael
 */

public class Kassa {
    private ArrayList<Artikel> artikels;
    private ArrayList<Artikel> scannedItems;
    private LinkedHashMap<Artikel,Integer> klantMap;
    private ArrayList<Artikel> hold;

    public Kassa(ArrayList<Artikel> artikels) {
        this.artikels = artikels;
        this.scannedItems = new ArrayList<>();
        this.klantMap = new LinkedHashMap<>();
        this.hold = new ArrayList<>();
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




    public void placeOnHold(){
        if (scannedItems.size() == 0) throw new ModelException("Can't place an empty cart on hold");
        if (hold.size() != 0) throw new ModelException("Already a cart on hold");
        hold.addAll(scannedItems);
        scannedItems.clear();
        klantMap.clear();
    }

    public void returnFromHold(){
        if (scannedItems.size() != 0) throw new ModelException("Current shopping cart has to be empty before returning cart on hold");
        if (hold.size() == 0) throw new ModelException("There is no cart on hold that can be returned");
        scannedItems.addAll(hold);
        hold.clear();
    }


    public String getSum(){
        double total = 0;

        for (Artikel artikel: getScannedItems()){
            total += artikel.getVerkoopprijs();
        }

        return "Total: €"+String.format("%.2f", total);
    }

}
