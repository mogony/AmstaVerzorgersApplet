package ldgraph;

import views.*;

/**
 *
 * @author jam
 */
public class LDGraph {

    
    public static void showLogin()
    {
        Login login = new Login();
    }
    
    public static void showPatientOverview(String name)
    {
        PatientOverview po = new PatientOverview(name);
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        showLogin();
    }
    
}