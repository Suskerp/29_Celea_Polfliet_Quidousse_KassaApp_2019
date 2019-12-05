package database;

import excel.ExcelPlugin;
import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;
import model.Artikel;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * @author Rafael Polfliet
 */
public class ArtikelXLLoadSave implements LoadSaveStrategy {
    private ExcelPlugin excelPlugin = new ExcelPlugin();
    private File file;
    private ArrayList<Artikel> scannedItems = new ArrayList<>();

    public ArtikelXLLoadSave(String fileName) {
        this.file = new File(fileName);
    }

    @Override
    public ArrayList<Artikel> load() {
        try {
            ArrayList<Artikel> output = new ArrayList<>();
            ArrayList<ArrayList<String>> excelOutput = excelPlugin.read(file);

            for (ArrayList<String> line:excelOutput){
                Artikel artikel = new Artikel(line.get(0),line.get(1),line.get(2),Double.parseDouble(line.get(3)),Integer.parseInt(line.get(4)));
                output.add(artikel);
            }
            return output;
        }catch (IOException | BiffException e){
            throw new DatabaseException(e);
        }

    }

    @Override
    public void save(ArrayList<Object> objects) {
        try {
        ArrayList<ArrayList<String>> output = new ArrayList<>();
        for (Object object:objects){
            Artikel artikel = (Artikel) object;
            ArrayList<String> line =  new ArrayList<String>();
            line.add(artikel.getCode());
            line.add(artikel.getNaam());
            line.add("" +artikel.getVerkoopprijs());
            line.add("" +artikel.getStock());
            output.add(line);
        }

            excelPlugin.write(file,output);
        }catch (IOException | WriteException | BiffException e){
            throw new DatabaseException(e);
        }

    }

    @Override
    public Artikel scan(String id) {
        ArrayList<Artikel> artikels = load();

        for (Artikel artikel:artikels){
            if (artikel.getCode().equalsIgnoreCase(id)) {
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

    @Override
    public void verwijder() {

    }
}
