package model.Decorator;


import model.Artikel;
import model.Verkoop;

import java.security.PublicKey;
import java.util.ArrayList;
import java.util.Map;

/**
 * Luca Celea & Rafael Polfliet
 */

public class Bill implements BillPrinter {



   public void print(Verkoop verkoop){
       String string = "";

       for (int i = 0; i < 55; i++) {
           string = string.concat("*");
       }

       System.out.printf("%-36s%-14s%-22s\n","Omschrijving","Aantal","Prijs");
       System.out.println(string);
       for (Map.Entry<Artikel,Integer> entry:verkoop.getMapOfScannedItems().entrySet()){
           System.out.printf("%-40s%-10s%5s\n",entry.getKey().getNaam(),entry.getValue(),entry.getKey().getVerkoopprijs());
       }

       System.out.println(string);
       System.out.println("Betaald (inclusief korting) : " + String.format("%.2f", verkoop.getVerkoopState().finalSum()) + " Euro\n");
   }
}
