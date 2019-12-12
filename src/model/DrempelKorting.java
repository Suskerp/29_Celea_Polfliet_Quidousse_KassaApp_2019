package model;

/**
 * @author Jef Quidousse
 */

import model.Discount.KortingStrategy;

import java.util.ArrayList;

public class DrempelKorting implements KortingStrategy {
    private double drempelWaarde;
    private double procent;
    private ArrayList<Artikel> artikels;

    public DrempelKorting(double procent,double drempelWaarde){
        setDrempelWaarde(drempelWaarde);
        setProcent(procent);
        setArtikels(artikels);
    }

    private void setProcent(double p){
        this.procent = p;
    }

    private void setDrempelWaarde(double x){
        this.drempelWaarde = x;
    }

    private void setArtikels(ArrayList artikels){
        this.artikels = artikels;
    }


    @Override
    public double getKorting(ArrayList<Artikel> artikels) {
        double r = 0;
        double p = procent/100;
        for (Artikel a : artikels){
            r += a.getVerkoopprijs();
        }
        if(r > drempelWaarde){
            return r * p;
        }
        return 0;
    }
}
