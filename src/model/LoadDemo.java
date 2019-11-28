package model;

public class LoadDemo {
    public static void main(String[] args) {
        ArtikelTekstLoadSave artikelTekstLoadSave = new ArtikelTekstLoadSave("src\\bestanden\\artikel.txt");
        for (Artikel a: artikelTekstLoadSave.load()
             ) {
            System.out.println(a.toString());
        }
    }
}
