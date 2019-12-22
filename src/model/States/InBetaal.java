package model.States;

import model.Artikel;
import model.Verkoop;

import java.util.ArrayList;
import java.util.Map;

public class InBetaal implements VerkoopState {
    Verkoop verkoop;

    public InBetaal(Verkoop newVerkoop){
        verkoop = newVerkoop;
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
    public void save() {
        for (Map.Entry<Artikel,Integer> entry:verkoop.getMapOfScannedItems().entrySet()){
            verkoop.getArtikels().get(verkoop.getArtikels().indexOf(entry.getKey())).setStock(entry.getKey().getStock()-entry.getValue());
        }
        ArrayList<Object> out = new ArrayList<>(verkoop.getArtikels());
        verkoop.getArtikelDBStrategy().save(out);
    }
}
