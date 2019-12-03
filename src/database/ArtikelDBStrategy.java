package database;

import model.Artikel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public interface ArtikelDBStrategy {
    LoadSaveStrategy loadSaveStrategy = null;

    public void setLoadSaveStrategy(LoadSaveStrategy loadSaveStrategy);

    public ArrayList<Artikel> load();
    public void save(ArrayList<Object> artikels);

    public List<String> getContexts();
}
