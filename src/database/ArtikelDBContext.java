package database;

import model.Artikel;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Rafael Polfliet
 */

public class ArtikelDBContext implements ArtikelDBStrategy{
    private ArtikelDBStrategy artikelDBStrategy;


    public void setArtikelDBStrategy(ArtikelDBStrategy artikelDBStrategy) {
        this.artikelDBStrategy = artikelDBStrategy;
    }

    public ArtikelDBStrategy getArtikelDBStrategy() {
        return artikelDBStrategy;
    }

    public static List<String> getContexts() {
        List<String> contextLijst = new ArrayList<>();

        for (ArtikelDBEnum ArtikelDBEnum:ArtikelDBEnum.values()){
            contextLijst.add(ArtikelDBEnum.toString());
        }

        return contextLijst;
    }

    @Override
    public Artikel search(String id) {
        return artikelDBStrategy.search(id);
    }

    @Override
    public ArrayList<Artikel> load() {
        return artikelDBStrategy.load();
    }

    @Override
    public void save(ArrayList<Object> artikels) {
        artikelDBStrategy.save(artikels);
    }
}
