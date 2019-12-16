package model.Decorator;

import model.Discount.KortingEnum;

import java.util.ArrayList;
import java.util.List;

/**
 * @auther Rafael Polfliet
 */

public enum BillEnum {

    FOOTER_GENERAL("General footer","model.Decorator.FooterGeneral"),
    FOOTER_KORTING("Excl Korting footer","model.Decorator.FooterExclKorting"),
   FOOTER_BTW("Btw footer","model.Decorator.FooterExlBTW"),
    HEADER_DATETIME("Header date time","model.Decorator.HeaderDateTime"),
    HEADER_GENERAL("Header general","model.Decorator.HeaderGeneral");

    private final String omschrijving;
    private final String Klassenaam;

    BillEnum(String omschrijving, String klassenaam){
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
