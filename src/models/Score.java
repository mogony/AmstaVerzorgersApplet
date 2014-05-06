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
    
    public Score(int level, String name, String score, int collisions, String date) {
        int lengthOfSecs = 5;
        this.level = level;
        this.name = name;
        this.score = convertScoreToSecs(score);
        this.collisions = collisions;
        this.date = date.substring(0, date.length()-lengthOfSecs); //removes seconds
    }
    
    private static int convertScoreToSecs(String score) {
        String[] minSecs = score.split(":");
        int mins = Integer.parseInt(minSecs[0]);
        int secs = Integer.parseInt(minSecs[1]);
        return mins * 60 + secs;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = convertScoreToSecs(score);
    }

    public int getCollisions() {
        return collisions;
    }

    public void setCollisions(int collisions) {
        this.collisions = collisions;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}