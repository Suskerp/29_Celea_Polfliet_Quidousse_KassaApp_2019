package model;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class ArtikelTekstLoadSave extends TekstLoadSaveTemplate {
    private Scanner scanner;
    private String tekst;

    public ArtikelTekstLoadSave(String fileNaam){
        try{
            scanner = new Scanner(new File(fileNaam));
        }
        catch (FileNotFoundException e){
            tekst= "??";
        }
    }

    @Override
    ArrayList<Artikel> load() {

        return null;
    }

    @Override
    void save(ArrayList<Artikel> artikels) {

    }
}
