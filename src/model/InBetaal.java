package model;

/**
 * @author Jef Quidousse
 */

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
