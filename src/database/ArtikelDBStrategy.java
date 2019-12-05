package database;

import model.Artikel;

import java.util.ArrayList;
/**
 * @author Rafael Polfliet
 */

public interface ArtikelDBStrategy {
    ArrayList<Artikel> load();
    void save(ArrayList<Object> artikels);
    Artikel search(String id);
}
