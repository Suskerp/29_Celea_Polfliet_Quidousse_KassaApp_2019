package database;

import model.Artikel;

import java.util.ArrayList;

public interface LoadSaveStrategy {
    ArrayList<Artikel> load();
    void save(ArrayList<Artikel> artikels);
}
