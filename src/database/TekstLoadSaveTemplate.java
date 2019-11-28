package database;

import model.Artikel;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

/**
 * @author Luca Celea
 */
public abstract class TekstLoadSaveTemplate implements LoadSaveStrategy {
    private String fileNaam;

    public abstract ArrayList<Artikel> load();
    public void save(ArrayList<Objects> objects){
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