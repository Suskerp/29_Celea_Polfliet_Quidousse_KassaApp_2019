package database.Strategy;

import model.Artikel;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author Rafael Polfliet
 */
public interface LoadSaveStrategy {
    HashMap<Integer,Artikel> load();
    void save(ArrayList<Object> objects);
}
