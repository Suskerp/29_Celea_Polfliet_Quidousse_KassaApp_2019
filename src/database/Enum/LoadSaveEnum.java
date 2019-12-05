package database.Enum;

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

    public String getOmschrijving() {
        return omschrijving;
    }

    public String getKlassenaam() {
        return Klassenaam;
    }
}
