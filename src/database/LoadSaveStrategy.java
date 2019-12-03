package database;

import model.Artikel;

import java.util.ArrayList;

/**
 * @author Rafael Polfliet
 */
public interface LoadSaveStrategy {
    ArrayList<Artikel> load();
    void save(ArrayList<Object> Objects);

}
