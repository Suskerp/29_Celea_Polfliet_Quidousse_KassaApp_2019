package database;

import model.Artikel;

import java.util.ArrayList;
import java.util.List;

public class ArtikelDBContext implements ArtikelDBStrategy{
    private ArtikelDBStrategy artikelDBStrategy;




    public void setArtikelDBStrategy(ArtikelDBStrategy artikelDBStrategy) {
        this.artikelDBStrategy = artikelDBStrategy;
    }

    public ArtikelDBStrategy getArtikelDBStrategy() {
        return artikelDBStrategy;
    }

    public List<String> getContexts() {
        List<String> contextLijst = new ArrayList<>();

        for (ArtikelDBEnum ArtikelDBEnum:ArtikelDBEnum.values()){
            contextLijst.add(ArtikelDBEnum.toString());
        }

        return contextLijst;
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
