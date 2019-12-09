package database.Enum;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Rafael Polfliet
 */
public enum LoadSaveEnum {
    TEKST("src\\bestanden\\artikel.txt","database.ArtikelTekstLoadSave"),
    EXCEL("src\\bestanden\\artikel.xls","database.ArtikelXLLoadSave");

    private final String omschrijving;
    private final String Klassenaam;

    LoadSaveEnum(String omschrijving, String klassenaam) {
        this.omschrijving = omschrijving;
        Klassenaam = klassenaam;
    }
    public static List<String> valuesToString(){
        List<String> out = new ArrayList<>();
        for (LoadSaveEnum loadSaveEnum:LoadSaveEnum.values()){
            out.add(loadSaveEnum.toString());
        }
        return out;
    }
    public String getOmschrijving() {
        return omschrijving;
    }

    public String getKlassenaam() {
        return Klassenaam;
    }
}
