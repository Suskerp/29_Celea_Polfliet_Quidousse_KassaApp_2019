package model;

/**
 * @author Jef Quidousse
 */

import model.Discount.KortingInterface;

import java.util.ArrayList;

public class GroepKorting implements KortingInterface {
    private ArrayList<Artikel> artikels;
    private int procent;
    private String groep;


    public GroepKorting(int procent, ArrayList<Artikel> artikels, String groep){
        this.artikels = artikels;
        setProcent(procent);
        setGroep(groep);
    }

    private void setGroep(String groep){
        this.groep = groep;
    }

    private void setProcent(int p){
        this.procent = p;
    }

    @Override
    public double getKorting() {
        double r = procent/100;
        double totaal = 0;
        for (Artikel a : artikels){
            if(a.getOmschrijving() == this.groep){
                totaal += a.getVerkoopprijs()*r;
            }
        }

        return totaal;
    }
}
