package model.States;

import model.Verkoop;

public class InBetaal implements VerkoopState {
    Verkoop verkoop;

    public InBetaal(Verkoop newVerkoop){
        verkoop = newVerkoop;
    }

    @Override
    public void scan(){
        verkoop.setVerkoopState(verkoop.getScanbareState());
    }
}
