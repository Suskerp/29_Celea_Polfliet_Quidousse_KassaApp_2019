package model.States;

import database.DatabaseException;
import model.Artikel;
import model.Verkoop;

public class InScan implements VerkoopState {
    private Verkoop verkoop;

    public InScan(Verkoop newVerkoop){ verkoop = newVerkoop;}

    @Override
    public void scan(){
        boolean found = false;
        for (Artikel artikel : verkoop.getArtikels()) {
            if (artikel.getCode().equalsIgnoreCase(verkoop.getCurrentScannedItem())) {
                verkoop.addToScannedItems(artikel);
                found = true;
                break;
            }
        }
        if (!found) throw new DatabaseException("This item id doesn't exist");
    }

    @Override
    public void hold(){
        verkoop.setArtikels();
        verkoop.setVerkoopState(new InHold(verkoop));
    }

    @Override
    public void afgesloten(){
        verkoop.setVerkoopState(new InAfsluit(verkoop));
    }

    @Override
    public void annuleer(){
        verkoop.setVerkoopState(new InAnnulering(verkoop));
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
