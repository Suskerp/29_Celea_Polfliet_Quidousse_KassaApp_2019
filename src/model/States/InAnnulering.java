package model.States;

import model.Verkoop;

public class InAnnulering implements VerkoopState {
    private Verkoop verkoop;

    public InAnnulering(Verkoop newVerkoop){
        verkoop = newVerkoop;
    }

}
