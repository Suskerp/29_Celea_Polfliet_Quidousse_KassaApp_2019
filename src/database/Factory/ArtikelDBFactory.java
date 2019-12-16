package database.Factory;

import com.sun.org.apache.bcel.internal.generic.IF_ACMPEQ;
import database.Enum.ArtikelDBEnum;
import database.DatabaseException;
import database.Strategy.ArtikelDBStrategy;
import model.Discount.KortingFactory;

/**
 * @author Rafael Polfliet
 */
public class ArtikelDBFactory {
    private static ArtikelDBFactory INSTANCE;
    private ArtikelDBFactory() {
    }

    public  ArtikelDBStrategy artikelDBStrategy(String strategyCode){
        ArtikelDBEnum artikelDBEnum = ArtikelDBEnum.valueOf(strategyCode);
        String klasseNaam = artikelDBEnum.getKlassenaam();
        ArtikelDBStrategy artikelDBStrategy = null;
        try{
            Class dbClass = Class.forName(klasseNaam);
            Object dbObject = dbClass.newInstance();
            artikelDBStrategy = (ArtikelDBStrategy) dbObject;
        }catch (Exception e){
            throw new DatabaseException(e);
        }

        return artikelDBStrategy;
    }

    public static ArtikelDBFactory getInstance(){
        if (INSTANCE == null){
            INSTANCE = new ArtikelDBFactory();
        }
        return INSTANCE;
    }
}
