package database;

import model.Artikel;
import model.SortByName;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Scanner;
/**
 * @author Luca Celea
 */
public class ArtikelTekstLoadSave extends TekstLoadSaveTemplate {
    private String[] tekst;
    private File file;

    public ArtikelTekstLoadSave(String fileNaam){
        super(fileNaam);
        this.file = new File(fileNaam);
    }

    @Override
    public HashMap<Integer,Artikel> load(){
        try {
            HashMap<Integer,Artikel> artikels = new HashMap<>();
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                tekst = scanner.nextLine().split(";");
                Artikel artikel = new Artikel(Integer.parseInt(tekst[0]), tekst[1], tekst[2], Double.parseDouble(tekst[3]), Integer.parseInt(tekst[4]));
                artikels.put(artikel.getCode(),artikel);
            }
            return artikels;
        }catch (FileNotFoundException e){
            throw new DatabaseException(e);
        }
    }



}