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
        verkoop.save();
    }

    @Override
    public void annuleer(){
        verkoop.setVerkoopState(new InAnnulering(verkoop));
    }

    @Override
    public double korting() {
        return verkoop.getKortingStrategy().getKorting(verkoop.getScannedItems());
    }

    @Override
    public double finalSum() {
        return verkoop.getSum() - korting();
    }

    @Override
    public void verwijder(int id) {
        Artikel artikelTBRemoved = null;
        for (Artikel artikel : verkoop.getScannedItems()) {
            if (artikel.getCode() == id) {
                artikelTBRemoved = artikel;
                break;
            }
        }
        if (artikelTBRemoved != null) {
            verkoop.removeFromScanned(artikelTBRemoved);
        }
    }
}
