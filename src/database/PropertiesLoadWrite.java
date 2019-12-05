package database;

import model.Kassa;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * @author Rafael Polfliet
 */
public class PropertiesLoadWrite {
    public static ArtikelDBStrategy read(){
        Properties properties = new Properties();

        try {
            properties.load(new FileInputStream("src\\bestanden\\config.propreties"));
            String artikelDBContext= properties.getProperty("Kassa");
            String loadSave= properties.getProperty("LoadSave");
            String file = properties.getProperty("File");

            ArtikelDBStrategy artikelDBStrategy;

            try {
                artikelDBStrategy =  ArtikelDBFactory.artikelDBStrategy(artikelDBContext);

            }catch (IllegalArgumentException e){
                throw new DatabaseException("Wrong context, not defined in Enum - ArtikelDBEnum");
            }

            if (artikelDBContext.equals(ArtikelDBEnum.ARTIKEL_DB_MEM.toString())){
                if (loadSave.trim().isEmpty() || file.trim().isEmpty()) throw new DatabaseException("Error in configfile trying to create DBINMEM without file or loadsave strategy");
                ArtikelDBInMemory artikelDBInMemory = new ArtikelDBInMemory();

                try {
                    artikelDBInMemory.setLoadSaveStrategy(DBInMemoryFactory.createStrategy(loadSave,file));
                }catch (IllegalArgumentException e){
                    throw new DatabaseException("Wrong context, not defined in Enum - LoadSaveEnum");
                }

                return artikelDBInMemory;
            }

            return artikelDBStrategy;
        }catch (IOException e){
            throw new DatabaseException(e);
        }

    }

    public static void write(String artikelDBContext,String loadSave,String file){
            if (artikelDBContext == "ARTIKEL_DB_MYSQL") throw new DatabaseException("This is not available yet");
            if (loadSave == null || loadSave.trim().isEmpty() ||file == null|| file.trim().isEmpty()) throw new DatabaseException("Please select an option in each menu");

            Properties properties = new Properties();
            try {
                properties.setProperty("Kassa", artikelDBContext);
                properties.setProperty("LoadSave", loadSave);
                properties.setProperty("File", file);

                properties.store(new FileOutputStream("src\\bestanden\\config.propreties"), null);

            } catch (IOException e) {
                throw new DatabaseException(e);
            }


    }
    public static void write(String artikelDBContext){
        if (artikelDBContext == "ARTIKEL_DB_MYSQL") throw new DatabaseException("This is not available yet");

        Properties properties = new Properties();
        try {
            properties.setProperty("Kassa",artikelDBContext);
            properties.setProperty("LoadSave","");
            properties.setProperty("File","");

            properties.store(new FileOutputStream("src\\bestanden\\config.propreties"),null);

        }catch (IOException e) {
            throw new DatabaseException(e);
        }


    }

}
