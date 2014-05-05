package models;

/**
 *
 * @author Jelle Mogony, AMS04
 */
public class User {
    private int id;
    private String naam;
    private String gebDatum;
    private String wachtwoord;

    public User() {}
    
    public User(int id, String naam, String gebDatum, String wachtwoord) {
        this.id = id;
        this.naam = naam;
        this.gebDatum = gebDatum;
        this.wachtwoord = wachtwoord;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public String getGebDatum() {
        return gebDatum;
    }

    public void setGebDatum(String gebDatum) {
        this.gebDatum = gebDatum;
    }

    public String getWachtwoord() {
        return wachtwoord;
    }

    public void setWachtwoord(String wachtwoord) {
        this.wachtwoord = wachtwoord;
    }
}
