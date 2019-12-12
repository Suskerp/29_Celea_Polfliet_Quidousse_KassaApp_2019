package model.Discount;

import model.Artikel;

import java.util.ArrayList;

/**
 * @author Jef Quidousse
 */

public interface KortingStrategy {
    double getKorting(ArrayList<Artikel> a);
}
