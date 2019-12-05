package database;

import model.Artikel;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Rafael
 */

public class ArtikelDBInMemory implements ArtikelDBStrategy {


    private LoadSaveStrategy loadSaveStrategy;


    public void setLoadSaveStrategy(LoadSaveStrategy loadSaveStrategy) {
        this.loadSaveStrategy = loadSaveStrategy;
    }

    public static List<String> getContexts() {
        List<String> contextLijst = new ArrayList<>();

        for (LoadSaveEnum loadSaveEnum:LoadSaveEnum.values()){
            contextLijst.add(loadSaveEnum.toString());
        }

        return contextLijst;
    }

    @Override
    public Artikel scan(String id) {
        return loadSaveStrategy.scan(id);
    }

    @Override
    public ArrayList<Artikel> getScanItems() {
        return loadSaveStrategy.getScanItems();
    }

    @Override
    public ArrayList<Artikel> load() {
        return loadSaveStrategy.load();
    }

    @Override
    public void save(ArrayList<Object> artikels) {
        loadSaveStrategy.save(artikels);
    }

}