package database;

import database.Enum.ArtikelDBEnum;
import database.Factory.ArtikelDBFactory;
import database.Factory.DBInMemoryFactory;
import database.Strategy.ArtikelDBStrategy;
import model.Decorator.BillEnum;
import model.Decorator.BillFactory;
import model.Decorator.BillPrinter;
import model.Discount.KortingFactory;
import model.Discount.KortingStrategy;


import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Properties;

/**
 * @author Rafael Polfliet
 */
public class PropertiesLoadWrite {
    private static PropertiesLoadWrite INSTANCE;
    private Properties properties = new Properties();


    private PropertiesLoadWrite() {};

    public ArtikelDBStrategy readDBContext(){

        try {
            properties.load(new FileInputStream("src\\bestanden\\config.properties"));
            String artikelDBContext= properties.getProperty("Kassa");
            String loadSave= properties.getProperty("LoadSave");
            String file = properties.getProperty("File");

            ArtikelDBStrategy artikelDBStrategy;

            try {
                artikelDBStrategy =  ArtikelDBFactory.getInstance().artikelDBStrategy(artikelDBContext);

            }catch (IllegalArgumentException e){
                throw new DatabaseException("Wrong context, not defined in Enum - ArtikelDBEnum");
            }

            if (artikelDBContext.equals(ArtikelDBEnum.ARTIKEL_DB_MEM.toString())){
                if (loadSave.trim().isEmpty() || file.trim().isEmpty()) throw new DatabaseException("Error in configfile trying to create DBINMEM without file or loadsave strategy");
                ArtikelDBInMemory artikelDBInMemory = new ArtikelDBInMemory();

                try {
                    artikelDBInMemory.setLoadSaveStrategy(DBInMemoryFactory.getInstance().createStrategy(loadSave,file));
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


    public KortingStrategy readKorting() {
        try {
            properties.load(new FileInputStream("src\\bestanden\\config.properties"));
            String korting = properties.getProperty("Korting");
            String percentage = properties.getProperty("Percentage");
            String kortingVar = properties.getProperty("KortingVar");


            if (korting != null && !korting.trim().isEmpty() && !korting.equalsIgnoreCase("Geen korting")) {
                KortingStrategy kortingStrategy;

                try {
                    kortingStrategy = KortingFactory.getInstance().kortingStrategy(korting,Double.parseDouble(percentage),kortingVar);

                } catch (IllegalArgumentException e) {
                    throw new DatabaseException("Wrong context, not defined in Enum - KortingEnum");
                }

                return kortingStrategy;
            }else return null;

            }catch(IOException e){
                    throw new DatabaseException(e);
            }

    }
    
    public BillPrinter readBill(){
        HashMap<BillEnum,Boolean> map = new HashMap<>();
        try {
            properties.load(new FileInputStream("src\\bestanden\\config.properties"));
            boolean headerGeneral = Boolean.parseBoolean(properties.getProperty("headerGeneral"));
            String headerGeneralValue = properties.getProperty("headerGeneralValue");
            boolean headerDateTime = Boolean.parseBoolean(properties.getProperty("headerDateTime"));
            boolean footerExclKorting = Boolean.parseBoolean(properties.getProperty("footerExclKorting"));
            boolean footerExclBTW = Boolean.parseBoolean(properties.getProperty("footerExclBTW"));
            boolean footerGeneral = Boolean.parseBoolean(properties.getProperty("footerGeneral"));

            map.put(BillEnum.HEADER_GENERAL,headerGeneral);
            map.put(BillEnum.HEADER_DATETIME,headerDateTime);
            map.put(BillEnum.FOOTER_KORTING,footerExclKorting);
            map.put(BillEnum.FOOTER_BTW,footerExclBTW);
            map.put(BillEnum.FOOTER_GENERAL,footerGeneral);

            return BillFactory.getInstance().createBill(map,headerGeneralValue);

        }catch (IOException e){
            throw new DatabaseException(e);
        }
    }


    public void writeDBContext(String artikelDBContext,String loadSave,String file){
            if (artikelDBContext == "ARTIKEL_DB_MYSQL") throw new DatabaseException("This is not available yet");
            if (loadSave == null || loadSave.trim().isEmpty() ||file == null|| file.trim().isEmpty()) throw new DatabaseException("Please select an option in each menu");

                properties.setProperty("Kassa", artikelDBContext);
                properties.setProperty("LoadSave", loadSave);
                properties.setProperty("File", file);

    }
    public void writeDBContext(String artikelDBContext){
            properties.setProperty("Kassa",artikelDBContext);
            properties.setProperty("LoadSave","");
            properties.setProperty("File","");

    }

    public void writeKorting(String korting,String percentage,String extra){

            properties.setProperty("Korting",korting);
            properties.setProperty("Percentage",percentage+"");
            properties.setProperty("KortingVar",extra);
    }

    public void writeBillProperties(boolean headerGeneral,String headerGeneralValue,boolean headerDateTime, boolean footerExclKorting,boolean footerExclBTW, boolean footerGeneral){
            properties.setProperty("headerGeneral", String.valueOf(headerGeneral));
            properties.setProperty("headerGeneralValue",headerGeneralValue);
            properties.setProperty("headerDateTime", String.valueOf(headerDateTime));
            properties.setProperty("footerExclKorting", String.valueOf(footerExclKorting));
            properties.setProperty("footerExclBTW", String.valueOf(footerExclBTW));
            properties.setProperty("footerGeneral", String.valueOf(footerGeneral));

    }


    public void saveProperties(){
        try {
            properties.store(new FileOutputStream("src\\bestanden\\config.properties"), null);
        }catch (IOException e){
            throw new DatabaseException("There was a problem saving the properties file" + e);
        }
    }


    public static PropertiesLoadWrite getInstance() {
        if (INSTANCE == null){
            INSTANCE = new PropertiesLoadWrite();
        }

        return INSTANCE;
    }
}
