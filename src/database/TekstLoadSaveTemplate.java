package database;

import database.Strategy.LoadSaveStrategy;
import model.Artikel;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * @author Luca Celea
 */
public abstract class TekstLoadSaveTemplate implements LoadSaveStrategy {
    private String fileNaam;

    public abstract ArrayList<Artikel> load();
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