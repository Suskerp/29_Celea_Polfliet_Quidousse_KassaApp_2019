package database;

import database.Strategy.ArtikelDBStrategy;
import database.Strategy.LoadSaveStrategy;
import model.Artikel;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author Rafael Polfliet
 */

public class ArtikelDBInMemory implements ArtikelDBStrategy {


    private LoadSaveStrategy loadSaveStrategy;


    public void setLoadSaveStrategy(LoadSaveStrategy loadSaveStrategy) {
        this.loadSaveStrategy = loadSaveStrategy;
    }



    @Override
    public HashMap<Integer, Artikel> load() {
        return loadSaveStrategy.load();
    }

    @Override
    public void save(ArrayList<Object> artikels) {
        loadSaveStrategy.save(artikels);
    }



}