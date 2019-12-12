package model;

public class InBetaal implements  VerkoopState {
    Kassa kassa;

    public InBetaal(Kassa newKassa){
        kassa = newKassa;
    }

    @Override
    public void scan(){
        kassa.setVerkoopState(kassa.getScanbareState());
    }
}
