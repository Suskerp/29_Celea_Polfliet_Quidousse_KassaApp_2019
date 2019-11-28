package database;

import model.Artikel;

import java.util.*;
/**
 * @author Luca Celea
 */
public class ArtikelDB {
    private HashMap<String, Artikel> artikelHashMap;

    public ArtikelDB(){
        artikelHashMap = new HashMap<>();
    }

    public ArtikelDB(ArrayList<Artikel> artikels){
        this.artikelHashMap = new HashMap<>();
        for (Artikel a: artikels
        ) {
            this.artikelHashMap.put(a.getCode(),a);
        }
    }


    public HashMap<String, Artikel> getArtikelHashMap() {
        return artikelHashMap;
    }

    public void setArtikelHashMap(HashMap<String, Artikel> artikelHashMap) {
        if(artikelHashMap != null) {
            this.artikelHashMap = artikelHashMap;
        } else throw new IllegalArgumentException("Hashmap cannot be empty");
    }

    public void addArtikel(Artikel artikel){
        this.artikelHashMap.put(artikel.getCode(),artikel);
    }

    public ArrayList<Artikel> getGesorteerdeLijst(){
        ArrayList<Artikel> list = new ArrayList<>(artikelHashMap.values());
        Collections.sort(list);
        return list;
    }

    public void load(String filename){
        this.artikelHashMap.clear();
        ArtikelTekstLoadSave artikelTekstLoadSave = new ArtikelTekstLoadSave(filename);
        for (Artikel a: artikelTekstLoadSave.load()
             ) {
            this.artikelHashMap.put(a.getCode(),a);
        }
    }
}
