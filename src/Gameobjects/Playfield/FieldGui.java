/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gameobjects.Playfield;

import com.sun.java.swing.plaf.windows.WindowsTreeUI;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.plaf.ColorUIResource;

/**
 *
 * @author Tobias
 */
public class FieldGui extends JButton {
    
    String fieldStatusPlayer;
    String fieldStatusOpponent;
    
    private boolean active;

    public FieldGui() {
        setFont(new Font("Serif", Font.BOLD, 30 ));
        setBorder(new LineBorder(new Color(200, 214, 222)));
        setVisible(true);
        setBackground(new Color(64, 164, 223));
        this.active = true;
        
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
    
    

    @Override
    public void setText(String text) {
        super.setText(text); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
}
