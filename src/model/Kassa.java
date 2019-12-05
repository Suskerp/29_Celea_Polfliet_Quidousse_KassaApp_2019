package model;

import database.*;


import java.util.ArrayList;
import java.util.List;

public class Kassa {
    private ArtikelDBStrategy artikelDBStrategy;
    private ArrayList<Artikel> artikels;
    private ArrayList<Artikel> scannedItems;

    public Kassa() {
        this.artikelDBStrategy = PropertiesLoadWrite.read();
        artikels = artikelDBStrategy.load();
        scannedItems = new ArrayList<>();
    }

    public ArtikelDBStrategy getArtikelDBStrategy() {
        return artikelDBStrategy;
    }

    public static List<String> getContexts() {
        List<String> contextLijst = new ArrayList<>();

        for (database.ArtikelDBEnum ArtikelDBEnum:ArtikelDBEnum.values()){
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

    public void verwijder(String id) {
        artikels.remove(scan(id));
    }


    private ArrayList<Artikel> load() {
        return artikelDBStrategy.load();
    }


    public void save(ArrayList<Object> artikels) {
        artikelDBStrategy.save(artikels);
    }
}
