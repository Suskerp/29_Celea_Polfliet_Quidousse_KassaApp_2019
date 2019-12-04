package database;

import model.Artikel;

import java.util.ArrayList;

public class test {
    public static void main(String[] args) {

        ArtikelDBInMemory artikelDBInMemory = new ArtikelDBInMemory();
        artikelDBInMemory.setLoadSaveStrategy(DBInMemoryFactory.createStrategy("TEKST","src\\bestanden\\artikel.txt"));

       ArrayList<Artikel> artikels = artikelDBInMemory.load();
        for (Artikel artikel: artikelDBInMemory.load()) {
            System.out.println(artikel.toString());
        }

    }
}
