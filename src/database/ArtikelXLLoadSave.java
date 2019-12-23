package database;

import database.Strategy.LoadSaveStrategy;
import excel.ExcelPlugin;
import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import model.Artikel;
import model.SortByName;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

/**
 * @author Rafael Polfliet
 */
public class ArtikelXLLoadSave implements LoadSaveStrategy {
    private ExcelPlugin excelPlugin = new ExcelPlugin();
    private File file;

    public ArtikelXLLoadSave(String fileName) {
        this.file = new File(fileName);
    }

    @Override
    public HashMap<Integer,Artikel> load() {
        try {
            HashMap<Integer,Artikel> output = new HashMap<>();
            ArrayList<ArrayList<String>> excelOutput = excelPlugin.read(file);

            for (ArrayList<String> line:excelOutput){
                Artikel artikel = new Artikel(Integer.parseInt(line.get(0)),line.get(1),line.get(2),Double.parseDouble(line.get(3)),Integer.parseInt(line.get(4)));
                output.put(artikel.getCode(),artikel);
            }
            return output;
        }catch (IOException | BiffException e){
            throw new DatabaseException(e + " "+file.getPath());
        }

    }

    @Override
    public void save(ArrayList<Object> objects) {
        try {
        ArrayList<ArrayList<String>> output = new ArrayList<>();
        for (Object object:objects){
            Artikel artikel = (Artikel) object;
            ArrayList<String> line =  new ArrayList<String>();
            line.add(artikel.getCode()+"");
            line.add(artikel.getNaam());
            line.add(artikel.getOmschrijving());
            line.add("" +artikel.getVerkoopprijs());
            line.add("" +artikel.getStock());
            output.add(line);
        }

            excelPlugin.write(file,output);
        }catch (IOException | WriteException | BiffException e){
            throw new DatabaseException(e);
        }

    }

}
