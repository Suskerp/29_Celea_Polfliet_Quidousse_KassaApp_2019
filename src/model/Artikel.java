package model;

public class Artikel {
    private String code;
    private String naam;
    private String omschrijving;
    private Double verkoopprijs;
    private int stock;

    public Artikel(String code,String naam, String omschrijving, Double verkoopprijs, int stock) {
        setCode(code);
        setNaam(naam);
        setOmschrijving(omschrijving);
        setVerkoopprijs(verkoopprijs);
        setStock(stock);

    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        if (code != null && !code.trim().isEmpty()) {
            this.code = code;
        } else throw new IllegalArgumentException("Ogeldige code");
    }

    public String getOmschrijving() {
        return omschrijving;
    }

    public void setOmschrijving(String omschrijving) {
        if (omschrijving != null && !omschrijving.trim().isEmpty()) {
            this.omschrijving = omschrijving;
        } else throw new IllegalArgumentException("Ongeldige omschrijving");
    }

    public Double getVerkoopprijs() {
        return verkoopprijs;
    }

    public void setVerkoopprijs(Double verkoopprijs) {
        if (verkoopprijs != null) {
            this.verkoopprijs = verkoopprijs;
        } else throw new IllegalArgumentException("Ongeldige verkoopprijs");
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        if (stock >= 0){
        this.stock =stock;
        } else throw new IllegalArgumentException("Ongeldige stock");
    }

    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        if(naam != null && !naam.trim().isEmpty()){
            this.naam = naam;
        } else throw new IllegalArgumentException("Ongeldige naam");
    }

    @Override
    public String toString(){
        return this.getCode() + "," + this.getNaam() + "," + this.getOmschrijving() + "," + this.getVerkoopprijs() + "," + this.getStock();
    }
}
