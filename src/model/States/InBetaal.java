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
        return verkoop.getKortingStrategy().getKorting(verkoop.getScannedItems());
    }

    @Override
    public double finalSum() {
        return verkoop.getSum() - korting();
    }

    @Override
    public void save() {
        for (Map.Entry<Artikel,Integer> entry:verkoop.getMapOfScannedItems().entrySet()){
            Artikel artikel = verkoop.getArtikels().get(entry.getKey().getCode());
            artikel.setStock(artikel.getStock()-entry.getValue());
            verkoop.getArtikels().put(artikel.getCode(),artikel);
        }
        ArrayList<Object> out = new ArrayList<>(verkoop.getArtikels().values());
        verkoop.getArtikelDBStrategy().save(out);
    }
}
