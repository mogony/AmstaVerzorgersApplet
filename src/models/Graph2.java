package models;

import java.util.*;
import org.jfree.chart.*;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.data.category.DefaultCategoryDataset;

/**
 * Class that can be called to create a graph. 
 * It does not draw it, call method getCp() and add it to a panel to show
 * it on screen.
 * @author Jelle Mogony, AMS04
 */
public class Graph2 {
    private final ChartPanel cp;

    /**
     * Creates a graph based on the parameter 'scores'. 
     * @param scores 
     */
    public Graph2(LinkedList<Score> scores) {
        DefaultCategoryDataset dcd = new DefaultCategoryDataset();

        Iterator scoresList = scores.iterator();
        while(scoresList.hasNext()) {
            Score currentScore = (Score) scoresList.next();
            String xAxisLabel = currentScore.getDate() + "\nLevel: " + currentScore.getLevel();
            //TODO: find out why integers must be converted to numbers (not important)
            dcd.setValue((Number) currentScore.getScore(), "score", xAxisLabel);
            dcd.setValue((Number) currentScore.getCollisions(), "collisions", xAxisLabel);
            /* No idea why the ints should be cast to Numbers, but it works. I have
            used regular integers in the exact same way with the exact same method,
            and have never run into this problem until now. */
        }
        
        JFreeChart chart = ChartFactory.createLineChart("Score van "+(ldgraph.Session.storedPatientName2), "Datum", "Score/botsingen", dcd);
        chart.setBackgroundPaint(new java.awt.Color(0xFF, 0xFF, 0xFF, 0)); //Transparant
        
        /* This makes it so that the X Axis Category Labels wrap around mutliple lines,
        instead of one line. With just one line, not everything is visible on the labels.
        By default, only one line is set up. */
        CategoryPlot plot = chart.getCategoryPlot();
        CategoryAxis categoryAxis = plot.getDomainAxis();
        categoryAxis.setMaximumCategoryLabelLines(3);
        
        cp = new ChartPanel(chart);
    }
    
    /**
     * Retrieves a chart panel that must be placed in a panel to show it on screen.
     * @return chart panel of a graph object
     */
    public ChartPanel getCp() {
        return cp;
    }
}