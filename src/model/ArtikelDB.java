package model;

import java.util.*;
/**
 * @author Luca Celea
 */
public class ArtikelDB {
    private HashMap<String,Artikel> artikelHashMap;

    public ArtikelDB(){
        artikelHashMap = new HashMap<>();
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
}
