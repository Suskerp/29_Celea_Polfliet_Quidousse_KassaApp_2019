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
}
