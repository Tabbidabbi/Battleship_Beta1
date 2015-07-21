/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gameobjects.Playfield;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 *
 * @author Tobias
 */
public class FieldGui extends JButton {
    
    String fieldStatusPlayer;
    String fieldStatusOpponent;
    
    private boolean active;

    public FieldGui() {
        setVisible(true);
        setText(this.fieldStatusPlayer = "~");
        setText(this.fieldStatusOpponent = "~");
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
