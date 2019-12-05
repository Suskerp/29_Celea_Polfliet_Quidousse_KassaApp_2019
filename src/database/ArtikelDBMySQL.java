package database;

import model.Artikel;

import java.util.ArrayList;

/**
 * @author Rafael Polfliet
 */
public class ArtikelDBMySQL implements ArtikelDBStrategy{

    @Override
    public ArrayList<Artikel> load() {
        return null;
    }

    @Override
    public void save(ArrayList<Object> artikels) {

    }

    @Override
    public ArrayList<Artikel> search(String id) {
        return null;
    }

    @Override
    public ArrayList<Artikel> getSearchItems() {
        return null;
    }
}
