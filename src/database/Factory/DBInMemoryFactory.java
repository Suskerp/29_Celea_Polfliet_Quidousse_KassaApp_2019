package database.Factory;

import database.DatabaseException;
import database.Enum.LoadSaveEnum;
import database.Strategy.LoadSaveStrategy;

/**
 * @author Rafael Polfliet
 */
public class DBInMemoryFactory {
    private static DBInMemoryFactory INSTANCE;
    private DBInMemoryFactory() {
    }

    public LoadSaveStrategy createStrategy(String strategyCode, String bestand){
        LoadSaveEnum loadSaveEnum = LoadSaveEnum.valueOf(strategyCode);
        String klasseNaam = loadSaveEnum.getKlassenaam();
        LoadSaveStrategy loadSaveStrategy = null;
        try{
            Class dbClass = Class.forName(klasseNaam);
            Object dbObject = dbClass.getConstructor(String.class).newInstance(bestand);
            loadSaveStrategy = (LoadSaveStrategy) dbObject;
        }catch (Exception e){
            throw new DatabaseException(e);
        }
        return loadSaveStrategy;
    }

    public static DBInMemoryFactory getInstance(){
        if (INSTANCE == null){
            INSTANCE = new DBInMemoryFactory();
        }
        return INSTANCE;
    }
}
