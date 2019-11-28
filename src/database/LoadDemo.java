package database;

import database.ArtikelTekstLoadSave;
import model.Artikel;

/**
 * @author Luca Celea
 */
public class LoadDemo {
    public static void main(String[] args) {
        ArtikelTekstLoadSave artikelTekstLoadSave = new ArtikelTekstLoadSave("src\\bestanden\\artikel.txt");
        for (Artikel a: artikelTekstLoadSave.load()
             ) {
            System.out.println(a.toString());
        }
    }
}
