package database;

/**
 * @author Rafael Polfliet
 */
public class DBInMemoryFactory {
    public static LoadSaveStrategy createStrategy(String strategyCode){
        LoadSaveEnum loadSaveEnum = LoadSaveEnum.valueOf(strategyCode);
        String klasseNaam = loadSaveEnum.getKlassenaam();
        LoadSaveStrategy loadSaveStrategy = null;
        try{
            Class dbClass = Class.forName(klasseNaam);
            Object dbObject = dbClass.getConstructor(String.class).newInstance("src\\bestanden\\artikel.txt");
            loadSaveStrategy = (LoadSaveStrategy) dbObject;
        }catch (Exception e){
            System.out.println(e);
        }
        return loadSaveStrategy;
    }
}
