package models;

import java.util.*;
import org.jfree.chart.*;
import org.jfree.data.category.DefaultCategoryDataset;

/**
 *
 * @author jelle
 */
public class Graph {
    DefaultCategoryDataset dcd;
    JFreeChart chart;
    ChartPanel cp;


    /**
     * Creates a graph based on the parameter
     * @param scores 
     */
    public Graph(ArrayList<Scores> scores) {
        dcd = new DefaultCategoryDataset();
        dcd.clear();
        ArrayList<Integer> curSubScores; //stores all scores of a patient
        ArrayList<String> curSubDates; //stores all dates of the scores

        for(Scores userScores : scores) {
            curSubScores = userScores.getScore();
            curSubDates = userScores.getDate();
            for(int i = 0; i < curSubScores.size(); i++) {
                /* First sets a score (x axis); 
                /* then binds a patients name to it (the line);
                /* then binds a date to that score (y axis). */
                dcd.setValue(curSubScores.get(i), userScores.getName(), 
                        curSubDates.get(i));
            }
        }
        
        chart = ChartFactory.createLineChart("Scores", "Date", "Score", dcd);
        cp = new ChartPanel(chart);
    }
    
    public void addValue() {
        
    }

    public DefaultCategoryDataset getDcd() {
        return dcd;
    }

    public void setDcd(DefaultCategoryDataset dcd) {
        this.dcd = dcd;
    }

    public JFreeChart getChart() {
        return chart;
    }

    public void setChart(JFreeChart chart) {
        this.chart = chart;
    }

    public ChartPanel getCp() {
        return cp;
    }

    public void setCp(ChartPanel cp) {
        this.cp = cp;
    }
}