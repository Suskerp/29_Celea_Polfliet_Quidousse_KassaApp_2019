package database;

import model.Artikel;

public class test {
    public static void main(String[] args) {

        ArtikelXLLoadSave artikelXLLoadSave = new ArtikelXLLoadSave("src\\bestanden\\artikel.xls");

        for (Artikel artikel: artikelXLLoadSave.load()){
            System.out.println(artikel.toString());
        }

    }
}
