package model;

import java.util.Comparator;

public class SortByName implements Comparator<Artikel> {
    @Override
    public int compare(Artikel o1, Artikel o2) {
        return o1.getNaam().compareTo(o2.getNaam());
    }
}
