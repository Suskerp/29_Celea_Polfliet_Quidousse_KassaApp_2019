package model.Discount;

import model.Artikel;

import java.util.ArrayList;

/**
 * @author Jef Quidousse
 */

public interface KortingInterface {
    double getKorting(ArrayList<Artikel> a);
}
