package application;

import com.sun.xml.internal.ws.util.StringUtils;
import database.ArtikelDBInMemory;
import database.ArtikelTekstLoadSave;
import model.Artikel;
/**
    @author Luca Celea
 */
public class Appeke {
    public static void main(String[] args) {
        ArtikelDBInMemory artikelDBInMemory = new ArtikelDBInMemory();
        artikelDBInMemory.setLoadSaveStrategy(new ArtikelTekstLoadSave("src//bestanden//artikel.txt"));
        Double prijs = 0.0;
        String string = "";

        for (int i = 0; i < 55; i++) {
            string = string.concat("*");
        }

        System.out.printf("%-36s%-14s%-22s\n","Omschrijving","Aantal","Prijs");
        System.out.println(string);
        for (Artikel artikel: artikelDBInMemory.load()
             ) {
            System.out.printf("%-40s%-10s%5s\n",artikel.getNaam(),artikel.getCode(),artikel.getVerkoopprijs());
            prijs += artikel.getVerkoopprijs();
        }

        System.out.println(string);
        System.out.println("Betaald (inclusief korting) : " + String.format("%.2f", prijs) + " Euro");


    }
}
