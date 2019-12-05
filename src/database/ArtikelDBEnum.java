package database;

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
        Klassenaam = klassenaam;
    }

    public String getOmschrijving() {
        return omschrijving;
    }

    public String getKlassenaam() {
        return Klassenaam;
    }


}
