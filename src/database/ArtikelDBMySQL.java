package database;

import database.Strategy.ArtikelDBStrategy;
import model.Artikel;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author Rafael Polfliet
 */
public class ArtikelDBMySQL implements ArtikelDBStrategy {

    @Override
    public HashMap<Integer,Artikel> load() {
        return null;
    }

    @Override
    public void save(ArrayList<Object> artikels) {

    }


}
