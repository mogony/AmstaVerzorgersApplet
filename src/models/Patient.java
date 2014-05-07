package models;

/**
 * Object to store patient data in.
 * @author Jelle Mogony, AMS04
 */
public class Patient {
    private int patientId;
    private String name, room, dob, comments; //dob = date of birth
    
    /*Table attributes*/
    public final static String TABLE = "bewoner";
    public final static String ID = "bewoner_id";
    public final static String NAME = "naam";
    public final static String ROOM = "kamer";
    public final static String DOB = "geb_datum";
    public final static String COMMENTS = "opmerkingen";

    public Patient(int patientId, String name, String room, String dob, String comments) {
        this.patientId = patientId;
        this.name = name;
        this.room = room;
        this.dob = dob;
        this.comments = comments;
    }

    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }
}
