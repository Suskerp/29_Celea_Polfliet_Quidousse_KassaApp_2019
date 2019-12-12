package model.Discount;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Jef Quidousse
 */

public enum KortingEnum {

    Korting_Drempel("DrempelKorting","model.DrempelKorting"),
    Korting_Duurste("DuursteKorting","model.DuursteKorting"),
    Korting_Groep("GroepsKorting","model.GroepKorting");

    private final String omschrijving;
    private final String Klassenaam;

    KortingEnum(String omschrijving, String klassenaam){
        this.omschrijving = omschrijving;
        Klassenaam = klassenaam;
    }

    public static List<String> valuesToString(){
        List<String> out = new ArrayList<>();
        for (KortingEnum kortingDBEnum:KortingEnum.values()){
            out.add(kortingDBEnum.toString());
        }
        return out;
    }

    public String getOmschrijving(){
        return omschrijving;
    }

    public String getKlassenaam(){
        return Klassenaam;
    }
}
