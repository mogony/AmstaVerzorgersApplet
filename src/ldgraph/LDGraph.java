package ldgraph;

import views.*;

/**
 * This is the AVA - the Amsta Verzorgers Applet
 * @author Jelle Mogony, AMS04
 */
public class LDGraph {

    
    /**
     * Shows the login screen
     */
    public static void showLogin()
    {
        Login login = new Login();
        login.setVisible(true);
    }
    
    /**
     * Shows an overview with a graph containing the patients scores.
     */
    public static void showPatientOverview()
    {
        System.out.println("Setting up patients overview...");
        PatientOverview po = new PatientOverview();
        po.setVisible(true);
    }
    
    public static void showPatientGraph()
    {
        System.out.println("Setting up the graph...");
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