package model.States;

import model.Verkoop;

public class InAnnulering implements VerkoopState {
    Verkoop verkoop;

    public InAnnulering(Verkoop newVerkoop){
        verkoop = newVerkoop;
    }

    @Override
    public void scan(){
        verkoop.setVerkoopState(verkoop.getScanbareState());
    }
}
