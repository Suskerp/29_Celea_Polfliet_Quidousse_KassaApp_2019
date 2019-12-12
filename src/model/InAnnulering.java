package model;

public class InAnnulering implements VerkoopState{
    Kassa kassa;

    public InAnnulering(Kassa newKassa){
        kassa = newKassa;
    }

    @Override
    public void scan(){
        kassa.setVerkoopState(kassa.getScanbareState());
    }
}
