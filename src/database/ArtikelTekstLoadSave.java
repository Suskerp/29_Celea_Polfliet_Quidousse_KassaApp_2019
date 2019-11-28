package database;

import model.Artikel;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
/**
 * @author Luca Celea
 */
public class ArtikelTekstLoadSave extends TekstLoadSaveTemplate {
    private String[] tekst;
    private String fileNaam;

    public ArtikelTekstLoadSave(String fileNaam){
        this.fileNaam = fileNaam;
    }

    public String getFileNaam() {
        return fileNaam;
    }

    @Override
    public ArrayList<Artikel> load() {
        ArrayList<Artikel> artikels = new ArrayList<>();
        Scanner scanner = new Scanner(this.getFileNaam());
        while (scanner.hasNextLine()){
            tekst = scanner.nextLine().split(",");
            artikels.add(new Artikel(tekst[0],tekst[1],tekst[2],Double.parseDouble(tekst[3]),Integer.parseInt(tekst[4])));
        }
        return artikels;
    }
}