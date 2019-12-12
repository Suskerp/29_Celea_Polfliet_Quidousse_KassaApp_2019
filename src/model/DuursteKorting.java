package model;

/**
 * @author Jef Quidousse
 */

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

        Artikel a = new Artikel("r", "r", "r", 0.0, 0);
        for (Artikel b : artikels){
            if (b.getVerkoopprijs() > a.getVerkoopprijs()){
                a = b;
            }
        }
        return a.getVerkoopprijs()*r;
    }
}
