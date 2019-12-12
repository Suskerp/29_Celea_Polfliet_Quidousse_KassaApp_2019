package model;
/**
 * @author Jef Quidousse
 */

import java.io.FileNotFoundException;

public class InAfsluit implements VerkoopState {
    Kassa kassa;

    public InAfsluit(Kassa newKassa){
        kassa = newKassa;
    }

    @Override
    public void betaald(boolean genoegGeld) {
        if(genoegGeld) {
            kassa.setVerkoopState(kassa.getBetaalbareState());
        }
        else{
            kassa.setVerkoopState(kassa.getAfsluitbareState());
        }
    }

    @Override
    public void annuleer(boolean genoegGeld){
        if(!genoegGeld){
            kassa.setVerkoopState(kassa.getAnnuleerbareState());
        }
        else{
            kassa.setVerkoopState(kassa.getBetaalbareState());
        }
    }


}
