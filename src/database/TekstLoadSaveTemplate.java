package database;

import model.Artikel;

import java.util.ArrayList;
/**
 * @author Luca Celea
 */
public abstract class TekstLoadSaveTemplate implements LoadSaveStrategy {
    public abstract ArrayList<Artikel> load();
    public abstract void save(ArrayList<Artikel> artikels);
}
