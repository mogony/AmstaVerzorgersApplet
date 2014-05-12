package models;

/**
 * Object to store user data in.
 * @author Jelle Mogony, AMS04
 */
public class User {
    private int id;
    private String naam, gebDatum, wachtwoord;

    /* Table attributes */
    public final static String TABLE = "verzorger";
    public final static String ID = "werknemer_id";
    public final static String NAME = "naam";
    public final static String DOB = "geb_datum";
    public final static String PW = "wachtwoord";
    
    /**
     * Creates new object of type user.
     * @param id the id associated with the user
     * @param naam the name of the user
     * @param gebDatum the date of birth of the user
     * @param wachtwoord the password of the user
     */
    public User(int id, String naam, String gebDatum, String wachtwoord) {
        this.id = id;
        this.naam = naam;
        this.gebDatum = gebDatum;
        this.wachtwoord = wachtwoord;
    }

    /**
     * @return the ID of the user
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the correct ID of the user
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the name of the user (first and last name)
     */
    public String getNaam() {
        return naam;
    }

    /**
     * @param naam the correct name of the user (first and last name)
     */
    public void setNaam(String naam) {
        this.naam = naam;
    }

    /**
     * @return the date of birth of the user
     */
    public String getGebDatum() {
        return gebDatum;
    }

    /**
     * @param gebDatum the correct date of birth of the user
     */
    public void setGebDatum(String gebDatum) {
        this.gebDatum = gebDatum;
    }

    /**
     * @return the password of the user
     */
    public String getWachtwoord() {
        return wachtwoord;
    }

    /**
     * @param wachtwoord the correct password of the user
     */
    public void setWachtwoord(String wachtwoord) {
        this.wachtwoord = wachtwoord;
    }
}
