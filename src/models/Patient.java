package models;

/**
 *
 * @author Jelle Mogony, AMS04
 */
public class Patient {
    private int patientId;
    private String name;
    private String room;
    private String dob; //date of birth
    private String comments;

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
