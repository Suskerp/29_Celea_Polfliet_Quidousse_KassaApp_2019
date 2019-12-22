package model.States;

import model.Artikel;
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
        verkoop.setVerkoopState(new InBetaal(verkoop));
        verkoop.getVerkoopState().save();
    }

    @Override
    public void annuleer(){
        verkoop.setVerkoopState(new InAnnulering(verkoop));
    }

    @Override
    public double korting() {
        return verkoop.getKorting();
    }

    @Override
    public double finalSum() {
        return verkoop.getSum() - verkoop.getKorting();
    }

    @Override
    public void verwijder(String id) {
        Artikel artikelTBRemoved = null;
        for (Artikel artikel : verkoop.getScannedItems()) {
            if (artikel.getCode().equalsIgnoreCase(id)) {
                artikelTBRemoved = artikel;
                break;
            }
        }
        if (artikelTBRemoved != null) {
            verkoop.removeFromScanned(artikelTBRemoved);
        }
    }
}
