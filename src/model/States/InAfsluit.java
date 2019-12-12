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
    public void betaald(boolean genoegGeld) {
        if(genoegGeld) {
            verkoop.setVerkoopState(verkoop.getBetaalbareState());
        }
        else{
            verkoop.setVerkoopState(verkoop.getAfsluitbareState());
        }
    }

    @Override
    public void annuleer(boolean genoegGeld){
        if(!genoegGeld){
            verkoop.setVerkoopState(verkoop.getAnnuleerbareState());
        }
        else{
            verkoop.setVerkoopState(verkoop.getBetaalbareState());
        }
    }


}
