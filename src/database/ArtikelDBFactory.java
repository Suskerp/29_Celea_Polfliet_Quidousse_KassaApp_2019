package database;

public class ArtikelDBFactory {
    public static ArtikelDBStrategy artikelDBStrategy(String strategyCode){
        ArtikelDBEnum artikelDBEnum = ArtikelDBEnum.valueOf(strategyCode);
        String klasseNaam = artikelDBEnum.getKlassenaam();
        ArtikelDBStrategy artikelDBStrategy = null;
        try{
            Class dbClass = Class.forName(klasseNaam);
            Object dbObject = dbClass.newInstance();
            artikelDBStrategy = (ArtikelDBStrategy) dbObject;
        }catch (Exception e){
        }

        return artikelDBStrategy;
    }
}
