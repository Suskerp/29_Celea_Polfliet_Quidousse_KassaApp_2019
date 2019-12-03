package database;

public class DBInMemoryFactory {
    public static LoadSaveStrategy createStrategy(String strategyCode){
        LoadSaveEnum loadSaveEnum = LoadSaveEnum.valueOf(strategyCode);
        String klasseNaam = loadSaveEnum.getKlassenaam();
        LoadSaveStrategy loadSaveStrategy = null;
        try{
            Class dbClass = Class.forName(klasseNaam);
            Object dbObject = dbClass.newInstance();
            loadSaveStrategy = (LoadSaveStrategy) dbObject;
        }catch (Exception e){
            System.out.println(e);
        }
        return loadSaveStrategy;
    }
}
