package model.Discount;

import model.ModelException;

/**
 * @author Jef Quidousse
 */

public class KoritngDBFactory {
    private KoritngDBFactory(){}

    public static KortingInterface koritngInterface(String kortingCode){
        KortingEnum kortingEnum = KortingEnum.valueOf(kortingCode);
        String klasseNaam = kortingEnum.getKlassenaam();
        KortingInterface kortingInterface = null;
        try{
            Class dbClass = Class.forName(klasseNaam);
            Object dbObject = dbClass.newInstance();
            kortingInterface = (KortingInterface) dbObject;
        }catch (Exception e){
            throw new ModelException(e);
        }

        return kortingInterface;
    }
}
