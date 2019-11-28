package database;

/**
 * @author Rafael Polfliet
 */
public enum LoadSaveEnum {
    TEKTST("Tekst","database.ArtikelTekstLoadSave"),
    EXCEL("Excel","database.ArtikelXLLoadSave");

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
