package model;

import java.util.HashMap;

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
}
