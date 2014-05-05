/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package models;

import java.util.ArrayList;

/**
 *
 * @author jelle
 */
public class Score {
    private int level;
    private String name;
    private int score;
    private int collisions;
    private String date;

    public Score(int level, String name, int score, int collisions, String date) {
        this.name = name;
        this.score = score;
        this.collisions = collisions;
        this.date = date;
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

    public void setScore(int score) {
        this.score = score;
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