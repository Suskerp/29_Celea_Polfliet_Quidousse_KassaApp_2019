package model;

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

    public String getOmschrijving(){
        return omschrijving;
    }

    public String getKlassenaam(){
        return Klassenaam;
    }
}
