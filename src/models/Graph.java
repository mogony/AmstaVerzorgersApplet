package models;

import java.util.*;
import org.jfree.chart.*;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.labels.StandardCategoryToolTipGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.data.category.DefaultCategoryDataset;

/**
 * Class that can be called to create a graph. 
 * It does not draw it, call method getCp() and add it to a panel to show
 * it on screen.
 * @author Riekelt Goucem, AMS04
 */
public class Graph {
    private final ChartPanel cp;

    /**
     * Creates a graph based on the parameter 'scores'. 
     * @param scores 
     */
    public Graph(LinkedList<Score> scores) {
           DefaultCategoryDataset scoreData = new DefaultCategoryDataset();
        DefaultCategoryDataset colData = new DefaultCategoryDataset();

        Iterator scoresList = scores.iterator();
        while(scoresList.hasNext()) {
            Score currentScore = (Score) scoresList.next();
            String xAxisLabel = currentScore.getDate() + "\n Level: " + currentScore.getLevel();
            //TODO: find out why integers must be converted to numbers (not important)
            scoreData.setValue((Number) currentScore.getScore(), "score", xAxisLabel);
            colData.setValue((Number) currentScore.getCollisions(), "botsingen", xAxisLabel);
            /* No idea why the ints should be cast to Numbers, but it works. I have
            used regular integers in the exact same way with the exact same method,
            and have never run into this problem until now. */
        }
        
        JFreeChart chart = ChartFactory.createLineChart(
                "Score van "+(ldgraph.Session.storedPatientName), "Datum", 
                "Score", scoreData);
        //Transparant
        chart.setBackgroundPaint(new java.awt.Color(0xFF, 0xFF, 0xFF, 0));
        
        /* This makes it so that the X Axis Category Labels wrap around mutliple lines,
        instead of one line. With just one line, not everything is visible on the labels.
        By default, only one line is set up. */
        CategoryPlot plot = chart.getCategoryPlot();
        CategoryAxis categoryAxis = plot.getDomainAxis();
        categoryAxis.setMaximumCategoryLabelLines(3);
        
        /* Enhance the score line */
        LineAndShapeRenderer scoreLineRenderer = new LineAndShapeRenderer();
        scoreLineRenderer.setToolTipGenerator(new StandardCategoryToolTipGenerator());
        plot.setRenderer(0, scoreLineRenderer);
        
        /* Now we make the second line, collisions, visisble aswell */
        ValueAxis colAxis = new NumberAxis("Botsingen");
        plot.setRangeAxis(1, colAxis);
        LineAndShapeRenderer colLineRenderer = new LineAndShapeRenderer();
        colLineRenderer.setToolTipGenerator(new StandardCategoryToolTipGenerator());
        plot.setRenderer(1, colLineRenderer);
        plot.setDataset(1, colData);
        plot.mapDatasetToRangeAxis(1, 1);
        
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