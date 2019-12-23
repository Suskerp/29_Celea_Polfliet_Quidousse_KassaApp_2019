package model.States;

import database.DatabaseException;
import model.Artikel;
import model.Verkoop;

public class InScan implements VerkoopState {
    private Verkoop verkoop;

    public InScan(Verkoop newVerkoop){ verkoop = newVerkoop;}

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

    @Override
    public void scanItem(int id) {
        Artikel artikel = verkoop.getArtikels().get(id);
        if (artikel != null){
            verkoop.addToScannedItems(artikel);
        }else{
            throw new DatabaseException("Id not found");
        }
    }
}
