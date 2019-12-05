package database.Strategy;

import model.Artikel;

import java.io.File;
import java.util.ArrayList;

/**
 * @author Rafael Polfliet
 */
public interface LoadSaveStrategy {
    ArrayList<Artikel> load();
    void save(ArrayList<Object> objects);
}
