package model;

public class InScan implements VerkoopState{
    Kassa kassa;

    public InScan(Kassa newKassa){ kassa = newKassa;}

    @Override
    public void afgesloten(){
        kassa.setVerkoopState(kassa.getAfsluitbareState());
    }

}
