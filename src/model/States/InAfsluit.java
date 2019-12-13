package model.States;

import model.Verkoop;

/**
 * @author Jef Quidousse
 */

public class InAfsluit implements VerkoopState {
    Verkoop verkoop;

    public InAfsluit(Verkoop newVerkoop){
        verkoop = newVerkoop;
    }

    @Override
    public void betaald() {
        verkoop.setVerkoopState(verkoop.getBetaalbareState());
    }

    @Override
    public void annuleer(){
        verkoop.setVerkoopState(verkoop.getAnnuleerbareState());

    }


}
