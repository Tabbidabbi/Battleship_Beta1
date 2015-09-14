/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gameobjects.Playfield;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import Game.*;
import java.awt.color.ColorSpace;

/**
 *
 * @author Tobias
 */
public class PlayerPlayfieldGui extends JPanel implements ActionListener {

    private Settings gameSettings;
    private FieldGui playfieldButton;
    private FieldGui[][] playfieldMatrix;
    private FieldGui[][] opponentfieldMatrix;
    private JPanel playfieldMatrixPanel;
    private JPanel opponentfieldMatrixPanel;

    public PlayerPlayfieldGui(Settings gameSettings) {
    	//1. Spieleransicht
        setLayout(new BorderLayout());
        this.gameSettings = gameSettings;
        playfieldMatrix = new FieldGui[gameSettings.getPlayfieldSize() + 1][gameSettings.getPlayfieldSize() + 1];

        playfieldMatrixPanel = new JPanel();
        playfieldMatrixPanel.setLayout(new GridLayout(gameSettings.getPlayfieldSize() + 1, gameSettings.getPlayfieldSize() + 1));
        for (int i = 0; i < playfieldMatrix.length; i++) {
            for (int j = 0; j < playfieldMatrix[i].length; j++) {
                playfieldMatrix[i][j] = new FieldGui();
                playfieldMatrix[i][j].setActionCommand("" + i +"#"+ j);
                playfieldMatrix[i][j].addActionListener(this);
                playfieldMatrix[i][0].setText("" + i);
                playfieldMatrix[i][0].setBackground(Color.white);
                playfieldMatrix[i][0].setEnabled(false);
                playfieldMatrix[i][0].setActive(false);
                playfieldMatrix[0][j].setText("" + j);
                playfieldMatrix[0][j].setEnabled(false);
                playfieldMatrix[0][j].setActive(false);
                playfieldMatrix[0][j].setBackground(Color.white);
                playfieldMatrix[0][0].setText("Y  /  X");
                playfieldMatrix[0][0].setFont(new Font("Serif", Font.BOLD, 10 ));
                playfieldMatrixPanel.add(playfieldMatrix[i][j]);
//                if (playfieldMatrix[i][j].isActive()) {
//                    playfieldMatrix[i][j].setText("a");
//                } else { 
//                    playfieldMatrix[i][j].setText("d");
//                }
            }
        }

        add(playfieldMatrixPanel);
        setOpaque(false);
        setVisible(true);
        
        //2. Gegneransicht
        //setLayout(new BorderLayout());
        //this.gameSettings = gameSettings;
        opponentfieldMatrix = new FieldGui[gameSettings.getPlayfieldSize() + 1][gameSettings.getPlayfieldSize() + 1];

        opponentfieldMatrixPanel = new JPanel();
        opponentfieldMatrixPanel.setLayout(new GridLayout(gameSettings.getPlayfieldSize() + 1, gameSettings.getPlayfieldSize() + 1));
        for (int i = 0; i < opponentfieldMatrix.length; i++) {
            for (int j = 0; j < opponentfieldMatrix[i].length; j++) {
            	opponentfieldMatrix[i][j] = new FieldGui();
            	opponentfieldMatrix[i][j].setActionCommand("" + i +"#"+ j);
            	opponentfieldMatrix[i][j].addActionListener(this);
            	opponentfieldMatrix[i][0].setText("" + i);
            	opponentfieldMatrix[i][0].setBackground(Color.white);
            	opponentfieldMatrix[i][0].setEnabled(false);
            	opponentfieldMatrix[i][0].setActive(false);
            	opponentfieldMatrix[0][j].setText("" + j);
            	opponentfieldMatrix[0][j].setEnabled(false);
            	opponentfieldMatrix[0][j].setActive(false);
            	opponentfieldMatrix[0][j].setBackground(Color.white);
            	opponentfieldMatrix[0][0].setText("Y  /  X");
            	opponentfieldMatrix[0][0].setFont(new Font("Serif", Font.BOLD, 10 ));
            	opponentfieldMatrixPanel.add(opponentfieldMatrix[i][j]);
//                if (playfieldMatrix[i][j].isActive()) {
//                    playfieldMatrix[i][j].setText("a");
//                } else { 
//                    playfieldMatrix[i][j].setText("d");
//                }
            }
        }

        add(opponentfieldMatrixPanel);
        setOpaque(false);
        setVisible(false);

    }

    public FieldGui getPlayfieldButton() {
        return playfieldButton;
    }

    public void setPlayfieldButton(FieldGui playfieldButton) {
        this.playfieldButton = playfieldButton;
    }

    public void setFieldButtonListener(ActionListener l) {

        for (int i = 0; i < playfieldMatrix.length; i++) {
            for (int j = 0; j < playfieldMatrix[i].length; j++) {
                playfieldMatrix[i][j].addActionListener(l);
            }

        }
    }

    public FieldGui[][] getPlayfieldMatrix() {
        return playfieldMatrix;
    }
    
    public FieldGui[][] getOpponentfieldMatix(){
    	return opponentfieldMatrix;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String coordinateInput = e.getActionCommand();

    }

}
