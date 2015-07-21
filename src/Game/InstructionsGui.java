/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Game;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.Document;

/**
 *
 * @author Tobias
 */
public class InstructionsGui extends JPanel{
    
    JLabel header;
    
    JTextArea instructionsTextArea;
    JPanel instructionsPanel;
    JButton backButton;
    
    public InstructionsGui() {
        setPreferredSize(new Dimension(800, 600));
        setLayout(new FlowLayout());
        
        header = new JLabel("Anleitung");
        header.setFont(new Font("Serif",0, 25));
        
        instructionsTextArea = new JTextArea(text);
        instructionsTextArea.setForeground(Color.red);
        instructionsTextArea.setEditable(false);
        
        backButton = new JButton("Hauptmenü");
        backButton.setActionCommand("Instructions-MainMenuButton");
        
        
        instructionsPanel = new JPanel();
        instructionsPanel.setLayout(new BoxLayout(instructionsPanel, BoxLayout.Y_AXIS));
        instructionsPanel.add(header);
        instructionsPanel.add(Box.createRigidArea(new Dimension(0, 80)));
        instructionsPanel.add(instructionsTextArea);
        instructionsPanel.add(Box.createRigidArea(new Dimension(0, 80)));
        instructionsPanel.add(backButton);
        instructionsPanel.add(Box.createRigidArea(new Dimension(0, 80)));
        
        add(instructionsPanel);
        
        
        
        
        
        
        
        
        
        setVisible(true);
        
        
        
        
        
        
        
    }
    
     public void setListener(ActionListener l) {
        this.backButton.addActionListener(l);
    }
    
    public static final String text = "";
    
}
