package database;

import model.Artikel;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
/**
 * @author Luca Celea
 */
public class ArtikelTekstLoadSave extends TekstLoadSaveTemplate {
    private String[] tekst;
    private File file;
    private ArrayList<Artikel> scannedItems = new ArrayList<>();

    public ArtikelTekstLoadSave(String fileNaam){
        this.file =  new File(fileNaam);
    }

    public File getFile() {
        return file;
    }


    @Override
    public ArrayList<Artikel> load() {
        try {
            ArrayList<Artikel> artikels = new ArrayList<>();
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                tekst = scanner.nextLine().split(";");
                artikels.add(new Artikel(tekst[0], tekst[1], tekst[2], Double.parseDouble(tekst[3]), Integer.parseInt(tekst[4])));
            }
            Collections.sort(artikels);
            return artikels;
        }catch (FileNotFoundException e){
            throw new DatabaseException(e);
        }
    }

    @Override
    public Artikel scan(String id) {
        ArrayList<Artikel> artikels = load();

        for (Artikel artikel:artikels){
            if (artikel.getCode().equalsIgnoreCase(id)){
                scannedItems.add(artikel);
                return artikel;
            }
        }
        throw new DatabaseException("This code is not available");
    }
    @Override
    public ArrayList<Artikel> getScanItems() {
        return scannedItems;
    }

}