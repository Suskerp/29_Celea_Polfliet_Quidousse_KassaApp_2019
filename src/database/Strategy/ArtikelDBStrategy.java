package database.Strategy;

import model.Artikel;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author Rafael Polfliet
 */

public interface ArtikelDBStrategy {
    HashMap<Integer,Artikel> load();
    void save(ArrayList<Object> artikels);
}
