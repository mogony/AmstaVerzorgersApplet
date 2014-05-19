package ldgraph;

import views.*;

/**
 * This is the AVA - the Amsta Verzorgers Applet
 * @author Jelle Mogony, AMS04
 */
public class LDGraph {

    /**
     * Shows the login screen.
     */
    public static void showLogin()
    {
        Login login = new Login();
        login.setVisible(true);
    }
    
    /**
     * Shows an overview of all patients in the database with further options.
     */
    public static void showPatientOverview()
    {
        PatientOverview po = new PatientOverview();
        po.setVisible(true);
    }
    
    public static void showPatientCompareGraph()
    {
        PatientCompareGraph pcg = new PatientCompareGraph();
        pcg.setVisible(true);
    }
        public static void showPatientCompare()
    {
        PatientCompare da = new PatientCompare();
        da.setVisible(true);
    }
    
    /**
     * Shows a graph containing a patients scores and collisions over time.
     */
    public static void showPatientGraph()
    {
        PatientGraph pg = new PatientGraph();
        pg.setVisible(true);
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        showLogin();
    }
    
}