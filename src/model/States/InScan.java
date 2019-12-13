package model.States;

import model.Verkoop;

public class InScan implements VerkoopState {
    private Verkoop verkoop;

    public InScan(Verkoop newVerkoop){ verkoop = newVerkoop;}

    @Override
    public void afgesloten(){
        verkoop.setVerkoopState(verkoop.getAfsluitbareState());
    }

    @Override
    public void hold(){verkoop.setVerkoopState(verkoop.getHoldState());}

}
