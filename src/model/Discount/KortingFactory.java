package model.Discount;

/**
 * @author Jef Quidousse
 */

public class KortingFactory {
    private static KortingFactory INSTANCE;

    private KortingFactory(){}

    public KortingStrategy kortingStrategy(String kortingCode, double percentage, String extra){
        KortingEnum kortingEnum = KortingEnum.valueOf(kortingCode);
        KortingStrategy kortingStrategy = null;
        if (kortingEnum.equals(KortingEnum.Korting_Duurste)){
            kortingStrategy = new DuursteKorting(percentage);
        }else if(kortingEnum.equals(KortingEnum.Korting_Drempel)){
            kortingStrategy = new DrempelKorting(percentage,Double.parseDouble(extra));
        }else if (kortingEnum.equals(KortingEnum.Korting_Groep)){
            kortingStrategy = new GroepKorting(percentage,extra);
        }

        return kortingStrategy;
    }

    public static KortingFactory getInstance(){
        if (INSTANCE == null){
            INSTANCE = new KortingFactory();
        }
        return INSTANCE;
    }
}
