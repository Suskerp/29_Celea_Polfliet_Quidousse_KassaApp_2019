package model.Discount;

/**
 * @author Jef Quidousse
 */

import model.Artikel;
import model.Discount.KortingStrategy;

import java.util.ArrayList;

public class DuursteKorting implements KortingStrategy {
    private double procent;

    public DuursteKorting(double procent){
        setProcent(procent);
    }

    private void setProcent(double x){
        this.procent = x;
    }


    @Override
    public double getKorting(ArrayList<Artikel> artikels) {
        double r = procent/100;

        Artikel a = artikels.get(0);
        for (Artikel b : artikels){
            if (b.getVerkoopprijs() > a.getVerkoopprijs()){
                a = b;
            }
        }
        return a.getVerkoopprijs()*r;
    }
}
