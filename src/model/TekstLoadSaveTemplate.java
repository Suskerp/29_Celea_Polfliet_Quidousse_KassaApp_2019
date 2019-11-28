package model;

import java.util.ArrayList;
/**
 * @author Luca Celea
 */
public abstract class TekstLoadSaveTemplate {
    abstract ArrayList<Artikel> load();
    abstract void save(ArrayList<Artikel> artikels);
}
