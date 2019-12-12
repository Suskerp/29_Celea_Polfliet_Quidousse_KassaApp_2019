package model.Discount;

/**
 * @author Jef Quidousse
 */

import model.Artikel;
import model.Discount.KortingStrategy;

import java.util.ArrayList;

public class GroepKorting implements KortingStrategy {
    private double procent;
    private String groep;


    public GroepKorting(double procent, String groep){
        setProcent(procent);
        setGroep(groep);
    }

    private void setGroep(String groep){
        this.groep = groep;
    }

    private void setProcent(double p){
        this.procent = p;
    }

    @Override
    public double getKorting(ArrayList<Artikel> artikels) {
        double r = procent/100;
        double totaal = 0;
        for (Artikel a : artikels){
            if(a.getOmschrijving().equalsIgnoreCase(groep)){
                totaal += a.getVerkoopprijs()*r;
            }
        }

        return totaal;
    }
}
