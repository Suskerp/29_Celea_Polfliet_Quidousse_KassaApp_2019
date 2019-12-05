package model;

import java.util.ArrayList;

public class DuursteKorting implements KortingInterface {
    private int procent;
    private ArrayList<Artikel> artikels;

    public DuursteKorting(int procent, ArrayList<Artikel> artikels){
        setProcent(procent);
        this.artikels = artikels;
    }

    private void setProcent(int x){
        this.procent = x;
    }


    @Override
    public double getKorting() {
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
