package models;

import java.util.*;
import org.jfree.chart.*;
import org.jfree.data.category.DefaultCategoryDataset;

/**
 * Class that can be called to create a graph. 
 * It does not draw it, call method getCp() and add it to a panel to show
 * it on screen.
 * @author Jelle Mogony, AMS04
 */
public class Graph {
    DefaultCategoryDataset dcd;
    JFreeChart chart;
    ChartPanel cp;

    /**
     * Creates a graph based on the parameter 'scores'. 
     * @param scores should include a patients name and a list of scores and 
     * dates. 
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
        chart.setBackgroundPaint(new java.awt.Color(0xFF, 0xFF, 0xFF, 0)); //Transparant
        cp = new ChartPanel(chart);
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