package database;

import database.Enum.ArtikelDBEnum;
import database.Factory.ArtikelDBFactory;
import database.Factory.DBInMemoryFactory;
import database.Strategy.ArtikelDBStrategy;
import model.Discount.KortingFactory;
import model.Discount.KortingStrategy;
import model.ModelException;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * @author Rafael Polfliet
 */
public class PropertiesLoadWrite {
    public static ArtikelDBStrategy readDBContext(){
        Properties properties = new Properties();

        try {
            properties.load(new FileInputStream("src\\bestanden\\config.properties"));
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


    public static KortingStrategy readKorting() {
        Properties properties = new Properties();

        try {
            properties.load(new FileInputStream("src\\bestanden\\config.properties"));
            String korting = properties.getProperty("Korting");
            String percentage = properties.getProperty("Percentage");
            String kortingVar = properties.getProperty("KortingVar");


            if (korting != null && !korting.trim().isEmpty() && !korting.equalsIgnoreCase("Geen korting")) {
                KortingStrategy kortingStrategy;

                try {
                    kortingStrategy = KortingFactory.kortingStrategy(korting,Double.parseDouble(percentage),kortingVar);

                } catch (IllegalArgumentException e) {
                    throw new ModelException("Wrong context, not defined in Enum - KortingEnum");
                }

                return kortingStrategy;
            }else return null;

            }catch(IOException e){
                    throw new ModelException(e);
            }

    }


    public static void write(String artikelDBContext,String loadSave,String file,String korting,int percentage,String extra){
            if (artikelDBContext == "ARTIKEL_DB_MYSQL") throw new DatabaseException("This is not available yet");
            if (loadSave == null || loadSave.trim().isEmpty() ||file == null|| file.trim().isEmpty()) throw new DatabaseException("Please select an option in each menu");

            Properties properties = new Properties();
            try {
                properties.setProperty("Kassa", artikelDBContext);
                properties.setProperty("LoadSave", loadSave);
                properties.setProperty("File", file);
                properties.setProperty("Korting",korting);
                properties.setProperty("Percentage",percentage+"");
                properties.setProperty("KortingVar",extra);


                properties.store(new FileOutputStream("src\\bestanden\\config.properties"), null);

            } catch (IOException e) {
                throw new DatabaseException(e);
            }


    }
    public static void write(String artikelDBContext,String korting,int percentage,String extra){
        if (artikelDBContext == "ARTIKEL_DB_MYSQL") throw new DatabaseException("This is not available yet");

        Properties properties = new Properties();
        try {
            properties.setProperty("Kassa",artikelDBContext);
            properties.setProperty("LoadSave","");
            properties.setProperty("File","");
            properties.setProperty("Korting",korting);
            properties.setProperty("Percentage",percentage+"");
            properties.setProperty("KortingVar",extra);


            properties.store(new FileOutputStream("src\\bestanden\\config.properties"),null);

        }catch (IOException e) {
            throw new DatabaseException(e);
        }


    }

}
