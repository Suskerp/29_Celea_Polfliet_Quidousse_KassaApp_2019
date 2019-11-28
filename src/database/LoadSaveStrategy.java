package database;

import model.Artikel;

import java.util.ArrayList;
import java.util.Objects;

public interface LoadSaveStrategy {
    ArrayList<Artikel> load();
    void save(ArrayList<Objects> Objects);
}
