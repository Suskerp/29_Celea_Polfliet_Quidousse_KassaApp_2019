package model;

/**
 * @author Jef Quidousse
 */

import model.Discount.KortingInterface;

import java.util.ArrayList;

public class DrempelKorting implements KortingInterface {
    private double drempelWaarde;
    private int procent;
    private ArrayList<Artikel> artikels;

    public DrempelKorting(double drempelWaarde, int procent, ArrayList<Artikel> artikels){
        setDrempelWaarde(drempelWaarde);
        setProcent(procent);
        this.artikels = artikels;
    }

    private void setProcent(int p){
        this.procent = p;
    }

    private void setDrempelWaarde(double x){
        this.drempelWaarde = x;
    }


    @Override
    public double getKorting() {
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
