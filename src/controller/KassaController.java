package controller;

import model.Artikel;
import model.States.*;
import model.Verkoop;
import view.*;


import javax.swing.*;
import java.util.ArrayList;

/**
 * @author Rafael Polfliet
 */

public class KassaController implements SubjectShoppingCart, SubjectInventory {

    private KlantView klantView;
    private KassaView kassaView;
    private ProductOverviewPane productOverviewPane;
    private ArrayList<Verkoop> verkopen;
    private int huidigeVerkoop;
    private ArrayList<ObserverShoppingCart> observerShoppingCarts;
    private ArrayList<ObserverInventory> observerInventories;



    public KassaController() {

        verkopen = new ArrayList<>();

        verkopen.add(new Verkoop());
        huidigeVerkoop = 0;

        productOverviewPane = new ProductOverviewPane(this);
        kassaView = new KassaView(this);
        klantView = new KlantView();

        observerShoppingCarts = new ArrayList<>();
        observerInventories = new ArrayList<>();

        registerObserverShoppingCart(klantView);
        registerObserverInventory(productOverviewPane);
    }

    private Verkoop getHuidigeVerkoop(){
        return verkopen.get(huidigeVerkoop);
    }

    public VerkoopState getHuidigeVerkoopState(){
        return getHuidigeVerkoop().getVerkoopState();
    }
    public double getKorting(){
        return getHuidigeVerkoop().getKorting();
    }
    public void scanItem(String id){
        try {
            getHuidigeVerkoop().scannen(id);
            notifyObserversShoppingCart();
        }catch (StateException e){
            JOptionPane.showMessageDialog(null, e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void verwijder(String id){
        getHuidigeVerkoop().verwijderFromScannedItems(id);
        notifyObserversShoppingCart();
    }
    public void annuleren(){
        getHuidigeVerkoop().annuleren();
        holdCheck();
        notifyObserversShoppingCart();
    }
    public void betalen(){
       getHuidigeVerkoop().betalen();
       holdCheck();
       notifyObserversShoppingCart();
       notifyObserversInventory();
    }

    private void holdCheck() {
        int holdIndex = getHoldIndex();
        int huidigeIndex = verkopen.size()-1;
        if (holdIndex >= 0) {
            if (huidigeIndex - holdIndex >= 3) {
                verkopen.get(holdIndex).annuleren();
                verkopen.add(new Verkoop());
                huidigeVerkoop = verkopen.size()-1;
            } else {
                verkopen.get(holdIndex).returnFromHold();
                huidigeVerkoop = holdIndex;
            }
        } else {
            verkopen.add(new Verkoop());
            huidigeVerkoop = verkopen.size()-1;
        }
    }
    public void afsluiten(){
        getHuidigeVerkoop().afsluiten();
    }


    public ArrayList<Artikel> getScannedItems(){
        return getHuidigeVerkoop().getScannedItems();
    }

    private int getHoldIndex(){
        for (Verkoop verkoop:verkopen){
            if (verkoop.getVerkoopState() == verkoop.getHoldState()){
                return verkopen.indexOf(verkoop);
            }
        }
        return -1;
    }

    public void placeOnHold(){
        if (getHoldIndex() < 0) {
            getHuidigeVerkoop().placeOnHold();
            verkopen.add(new Verkoop());
            huidigeVerkoop = verkopen.size()-1;
            notifyObserversShoppingCart();
        }else throw new StateException("Al een verkoop op hold");
    }


    public Double getSum(){
        return getHuidigeVerkoop().getSum();
    }

    public ArrayList<Artikel> getArtikels(){
       return getHuidigeVerkoop().getArtikels();
    }

    @Override
    public void registerObserverShoppingCart(ObserverShoppingCart e) {
        observerShoppingCarts.add(e);
    }

    @Override
    public void removeObserverShoppingCart(ObserverShoppingCart e) {
        observerShoppingCarts.remove(e);
    }

    @Override
    public void notifyObserversShoppingCart() {
        for (ObserverShoppingCart observerShoppingCart : observerShoppingCarts){
            observerShoppingCart.update(getHuidigeVerkoop().getScannedForKlant(), getHuidigeVerkoop().getSum());
        }
    }

    public double getFinalSum(){
        return getHuidigeVerkoop().getFinalSum();
    }


    @Override
    public void registerObserverInventory(ObserverInventory e) {
        observerInventories.add(e);
    }

    @Override
    public void removeObserverInventory(ObserverInventory e) {
        observerInventories.remove(e);
    }

    @Override
    public void notifyObserversInventory() {
        for (ObserverInventory observerInventory:observerInventories){
            observerInventory.update(getHuidigeVerkoop().getArtikels());
        }
    }

    public ProductOverviewPane getProductOverviewPane() {
        return productOverviewPane;
    }
}
