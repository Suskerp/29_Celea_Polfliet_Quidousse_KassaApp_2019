package model;

import database.DatabaseException;

import java.util.Objects;

/**
 * @author Luca Celea
 */
public class Artikel implements Comparable<Artikel>{
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
        } else throw new DatabaseException("Ogeldige code");
    }

    public String getOmschrijving() {
        return omschrijving;
    }

    public void setOmschrijving(String omschrijving) {
        if (omschrijving != null && !omschrijving.trim().isEmpty()) {
            this.omschrijving = omschrijving;
        } else throw new DatabaseException("Ongeldige omschrijving");
    }

    public Double getVerkoopprijs() {
        return verkoopprijs;
    }

    public void setVerkoopprijs(Double verkoopprijs) {
        if (verkoopprijs != null) {
            this.verkoopprijs = verkoopprijs;
        } else throw new DatabaseException("Ongeldige verkoopprijs");
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock =stock; /* stock mag negatief */
    }

    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        if(naam != null && !naam.trim().isEmpty()){
            this.naam = naam;
        } else throw new DatabaseException("Ongeldige naam");
    }

    @Override
    public String toString(){
        return this.getCode() + ";" + this.getNaam() + ";" + this.getOmschrijving() + ";" + this.getVerkoopprijs() + ";" + this.getStock();
    }

    @Override
    public int compareTo(Artikel o) {
        return this.getNaam().compareTo(o.getNaam());
    }

    public void verkoop(int aantal){
        this.stock = this.stock-aantal;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Artikel artikel = (Artikel) o;
        return code.equals(artikel.code);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code);
    }
}
