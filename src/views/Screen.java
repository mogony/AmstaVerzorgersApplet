/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package views;

import javax.swing.*;

/**
 *
 * @author jelle
 */
abstract class Screen {
    JFrame frame;
    JPanel panel;
    
    public void initContainers() {
        panel = new JPanel();
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    public void readyFrame(int width, int height) {
        frame.add(panel);
        frame.setSize(width, height); //setSize(width, height)
        frame.setVisible(true);
    }
}
