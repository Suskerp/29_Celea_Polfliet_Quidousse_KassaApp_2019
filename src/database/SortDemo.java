package database;

import model.Artikel;

import java.util.ArrayList;

public class SortDemo {
    public static void main(String[] args) {
        ArrayList<Artikel> artikels = new ArrayList<>();
        Artikel artikel1 = new Artikel("1","e","gr1",12.5,10);
        Artikel artikel2 = new Artikel("2","c","gr1",12.5,10);
        Artikel artikel3 = new Artikel("3","b","gr1",12.5,10);
        Artikel artikel4 = new Artikel("4","d","gr1",12.5,10);
        Artikel artikel5 = new Artikel("5","a","gr1",12.5,10);
        artikels.add(artikel1);
        artikels.add(artikel2);
        artikels.add(artikel3);
        artikels.add(artikel4);
        artikels.add(artikel5);

        ArtikelDB artikelDB = new ArtikelDB(artikels);

        for (Artikel a: artikelDB.getGesorteerdeLijst()
             ) {
            System.out.println(a.toString());
        }

    }
}

