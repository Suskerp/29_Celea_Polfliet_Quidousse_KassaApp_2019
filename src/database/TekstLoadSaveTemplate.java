package database;

import database.Strategy.LoadSaveStrategy;
import model.Artikel;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author Luca Celea
 */
public abstract class TekstLoadSaveTemplate implements LoadSaveStrategy {
    private String fileNaam;


    public TekstLoadSaveTemplate(String fileNaam) {
        this.fileNaam = fileNaam;
    }

    public abstract HashMap<Integer,Artikel> load();
    public void save(ArrayList<Object> objects){
        try {
            FileWriter fileWriter = new FileWriter(fileNaam);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            for (Object a: objects
            ) {
                bufferedWriter.write(a.toString());
                bufferedWriter.newLine();
            }
            bufferedWriter.close();
        } catch (IOException e) {
            System.out.println("Kan geen bestand aanmaken");
        }
    }

}