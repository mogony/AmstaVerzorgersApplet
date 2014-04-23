package views;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import ldgraph.LDGraph;

/**
 *
 * @author Jelle Mogony AMS04
 */
public class Login extends Screen {
    JLabel jlUsername, jlPassword;
    JTextField tfUsername, tfPassword;
    JButton bSubmit;
    
    public Login() {
        initContainers();
        
        //initialize components
        tfUsername = new JTextField(); 
        tfPassword = new JPasswordField();
        jlUsername = new JLabel("Username:");
        jlPassword = new JLabel("Password:");
        bSubmit = new JButton("Login");
        //if button is clicked, goes to next screen
        bSubmit.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent event)
                {
                    frame.dispose();
                    LDGraph.showPatientOverview(tfUsername.getText());
                }
        });
        
        //add components to the panel
        panel.setLayout(new GridLayout(3,2));
        panel.add(jlUsername);
        panel.add(tfUsername);
        panel.add(jlPassword);
        panel.add(tfPassword);
        panel.add(bSubmit);
        
        readyFrame(300, 150); //ads panel to frame and sets it visible
    }
}