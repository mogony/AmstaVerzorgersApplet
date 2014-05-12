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

    /**
     * Creates a new object of type Patient.
     * @param patientId ID associated with the patient.
     * @param name the name of the patient
     * @param room room the patient lives in.
     * @param dob date of birth
     * @param comments comments concerning the patients.
     */
    public Patient(int patientId, String name, String room, String dob, String comments) {
        this.patientId = patientId;
        this.name = name;
        this.room = room;
        this.dob = dob;
        this.comments = comments;
    }

    /**
     * @return the ID associated with the patient.
     */
    public int getPatientId() {
        return patientId;
    }

    /**
     * @param patientId the new patient ID to be set.
     */
    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    /**
     * @return the patients name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the new name to be set for the patient.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the room the patient lives in.
     */
    public String getRoom() {
        return room;
    }

    /**
     * @param room the new room of the patient.
     */
    public void setRoom(String room) {
        this.room = room;
    }

    /**
     * @return the patients date of birth
     */
    public String getDob() {
        return dob;
    }

    /**
     * @param dob the date to be set as date of birth
     */
    public void setDob(String dob) {
        this.dob = dob;
    }

    /**
     * @return comments concerning the patient
     */
    public String getComments() {
        return comments;
    }

    /**
     * @param comments new comment to be set
     */
    public void setComments(String comments) {
        this.comments = comments;
    }
}
