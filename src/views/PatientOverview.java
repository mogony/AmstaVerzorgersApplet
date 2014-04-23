package views;

import java.awt.BorderLayout;
import java.util.*;
import models.*;

/**
 *
 * @author jelle
 */
public class PatientOverview extends Screen {
    public PatientOverview(String name) {
        /* DUMMY DATA */
        String[] names = {"de Vries", "de Boer", "de Man", "de Aap", "van der Hoek",
            "Goucem", "Steur", "Baas", "Amakran", "Mogony" };
        ArrayList<Integer> scores;// = new ArrayList();
        ArrayList<String> dates;// = new ArrayList();
        ArrayList<Scores> patientScores = new ArrayList();
        Random rand = new Random();
        
        for (int i = 0; i < 5; i++) {
            scores = new ArrayList();
            dates = new ArrayList();
            for (int j = 0; j < 10; j++) {
                scores.add(rand.nextInt(100));
                dates.add(j + "-04-2014");
            }
            patientScores.add(new Scores(names[i], scores, dates));
        }
        /* END DUMMY DATA */
        
        initContainers();
        panel.add(new Graph(patientScores).getCp(), BorderLayout.CENTER);
        frame.setTitle(name + " - Bewoner Resultaten");
        readyFrame(700, 500);
    }
}