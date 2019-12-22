package database.Enum;

import model.Artikel;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Rafael Polfliet
 */
public enum ArtikelDBEnum {
    ARTIKEL_DB_MEM("InMemory","database.ArtikelDBInMemory"),
    ARTIKEL_DB_MYSQL("MySQL","database.ArtikelDBMySQL");

    private final String omschrijving;
    private final String Klassenaam;

    ArtikelDBEnum(String omschrijving, String klassenaam) {
        this.omschrijving = omschrijving;
        this.Klassenaam = klassenaam;
    }

    public static List<String> valuesToString(){
        List<String> out = new ArrayList<>();
        for (ArtikelDBEnum artikelDBEnum:ArtikelDBEnum.values()){
            out.add(artikelDBEnum.toString());
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
