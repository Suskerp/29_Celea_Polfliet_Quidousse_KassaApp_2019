package database;

import model.Artikel;

import java.util.ArrayList;
import java.util.List;

public class ArtikelDBInMemory implements ArtikelDBStrategy {

    private LoadSaveStrategy loadSaveStrategy;

    @Override
    public ArrayList<Artikel> load() {
        return loadSaveStrategy.load();
    }

    @Override
    public void save(ArrayList<Object> artikels) {
        loadSaveStrategy.save(artikels);
    }

    @Override
    public void setLoadSaveStrategy(LoadSaveStrategy loadSaveStrategy) {
        this.loadSaveStrategy = loadSaveStrategy;
    }

    @Override
    public List<String> getContexts() {
        List<String> contextLijst = new ArrayList<>();

        for (LoadSaveEnum loadSaveEnum:LoadSaveEnum.values()){
            contextLijst.add(loadSaveEnum.toString());
        }

        return contextLijst;
    }
}
