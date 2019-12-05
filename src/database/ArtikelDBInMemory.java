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



    @Override
    public ArrayList<Artikel> load() {
        return loadSaveStrategy.load();
    }

    @Override
    public void save(ArrayList<Object> artikels) {
        loadSaveStrategy.save(artikels);
    }



}