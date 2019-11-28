package database;

import model.Artikel;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
/**
 * @author Luca Celea
 */
public class ArtikelTekstLoadSave extends TekstLoadSaveTemplate {
    private Scanner scanner;
    private String[] tekst;
    private String fileNaam;

    public ArtikelTekstLoadSave(String fileNaam){
        try{
            this.scanner = new Scanner(new File(fileNaam));
        }
        catch (FileNotFoundException e){
            System.out.println("bestand niet gevonden");
        }

        this.fileNaam = fileNaam;
    }

    @Override
    public ArrayList<Artikel> load() {
        ArrayList<Artikel> artikels = new ArrayList<>();

        while (scanner.hasNextLine()){
            tekst = scanner.nextLine().split(",");
            artikels.add(new Artikel(tekst[0],tekst[1],tekst[2],Double.parseDouble(tekst[3]),Integer.parseInt(tekst[4])));
        }
        return artikels;
    }

    @Override
    public void save(ArrayList<Artikel> artikels) {
        try {
            FileWriter fileWriter = new FileWriter(fileNaam);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            for (Artikel a: artikels
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

