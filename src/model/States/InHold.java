package model.States;

import model.Verkoop;

public class InHold implements  VerkoopState{
    Verkoop verkoop;

    public InHold(Verkoop newVerkoop){
        verkoop = newVerkoop;
    }

    @Override
    public void scan(){
        verkoop.setVerkoopState(new InScan(verkoop));
    }

    @Override
    public void annuleer(){
        verkoop.setVerkoopState(new InAnnulering(verkoop));
    }
}
