package models;

/**
 * Object to store score data in.
 * @author Jelle Mogony, AMS04
 */
public class Score {
    private int level, collisions, score;
    private String name, date;
    
    /* Table attributes */
    public final static String TABLE = "score";
    public final static String LVL = "level_id";
    public final static String SCORE = "score";
    public final static String COLS = "collisions";
    public final static String DATE = "datum";

    /**
     * Creates new object of type Score
     * @param level the level the score is associated with
     * @param name the name of the patient that scored
     * @param score the score in minutes and seconds
     * @param collisions the amount of collisions made in the level
     * @param date the date the level was played
     */
    public Score(int level, String name, int score, int collisions, String date) {
        int lengthOfSecs = 5;
        this.level = level;
        this.name = name;
        this.score = score;
        this.collisions = collisions;
        this.date = date.substring(0, date.length()-lengthOfSecs); //removes seconds
    }
    
   /* private static int convertScoreToSecs(String score) {
        String[] minSecs = score.split(":");
        int mins = Integer.parseInt(minSecs[0]);
        int secs = Integer.parseInt(minSecs[1]);
        return mins * 60 + secs;
    }
*/
    /**
     * @return the level the patients has played in
     */
    public int getLevel() {
        return level -3;
    }

    /**
     * @param level the correct level the patient has played in
     */
    public void setLevel(int level) {
        this.level = level;
    }

    /**
     * @return the name of the patient that has played
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the correct name to be set of the patient
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the score in seconds
     */
    public int getScore() {
        return score;
    }

    /**
     * @param score the correct score to be set
     */
    public void setScore(int score) {
        this.score = score;
    }

    /**
     * @return number of collisions in the level
     */
    public int getCollisions() {
        return collisions*100;
    }

    /**
     * @param collisions correct amount of collisions to be set
     */
    public void setCollisions(int collisions) {
        this.collisions = collisions;
    }

    /**
     * @return date the level was played
     */
    public String getDate() {
        return date;
    }

    /**
     * @param date the correct date the level was played on
     */
    public void setDate(String date) {
        this.date = date;
    }
}