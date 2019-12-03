package database;

import model.Artikel;

public class test {
    public static void main(String[] args) {
        ArtikelDBContext artikelDBContext = new ArtikelDBContext();

        artikelDBContext.setArtikelDBStrategy(ArtikelDBFactory.artikelDBStrategy("ARTIKEL_DB_MEM"));

        ArtikelDBInMemory artikelDBInMemory = (ArtikelDBInMemory) artikelDBContext.getArtikelDBStrategy();
        artikelDBInMemory.setLoadSaveStrategy(DBInMemoryFactory.createStrategy("TEKST"));


        for (Artikel artikel: artikelDBInMemory.load()) {
            System.out.println(artikel.toString());
        }

    }
}
