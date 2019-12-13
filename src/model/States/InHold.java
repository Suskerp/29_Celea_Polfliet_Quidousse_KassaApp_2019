package model.States;

import model.Verkoop;

public class InHold implements  VerkoopState{
    Verkoop verkoop;

    public InHold(Verkoop newVerkoop){
        verkoop = newVerkoop;
    }

    @Override
    public void scan(){
        verkoop.setVerkoopState(verkoop.getScanbareState());
    }

    @Override
    public void annuleer(boolean genoegGeld){verkoop.setVerkoopState(verkoop.getAnnuleerbareState());}
}
