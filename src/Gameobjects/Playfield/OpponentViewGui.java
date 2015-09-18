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
import IO.IO;

import java.awt.color.ColorSpace;

/**
 *
 * @author Tobias
 */
public class OpponentViewGui extends JPanel {

    private Settings gameSettings;
    private FieldGui playfieldButton;

    private FieldGui[][] opponentViewMatrix;
    private JPanel opponentViewMatrixPanel;

    public OpponentViewGui(Settings gameSettings) {
        setLayout(new BorderLayout());
        this.gameSettings = gameSettings;
        //Gegneransicht
        opponentViewMatrix = new FieldGui[gameSettings.getPlayfieldSize() + 1][gameSettings.getPlayfieldSize() + 1];

        opponentViewMatrixPanel = new JPanel();
        opponentViewMatrixPanel.setLayout(new GridLayout(gameSettings.getPlayfieldSize() + 1, gameSettings.getPlayfieldSize() + 1));
        for (int i = 0; i < opponentViewMatrix.length; i++) {
            for (int j = 0; j < opponentViewMatrix[i].length; j++) {
                opponentViewMatrix[i][j] = new FieldGui();
                opponentViewMatrix[i][j].setEnabled(true);
                opponentViewMatrix[i][j].setActionCommand("" + i + "#" + j);
                opponentViewMatrix[i][0].setText("" + i);
                opponentViewMatrix[i][0].setBackground(Color.white);
                opponentViewMatrix[i][0].setEnabled(false);
                opponentViewMatrix[i][0].setActive(false);
                opponentViewMatrix[0][j].setText("" + j);
                opponentViewMatrix[0][j].setEnabled(false);
                opponentViewMatrix[0][j].setActive(false);
                opponentViewMatrix[0][j].setBackground(Color.white);
                opponentViewMatrix[0][0].setText("Y  /  X");
                opponentViewMatrix[0][0].setFont(new Font("Serif", Font.BOLD, 10));
                opponentViewMatrixPanel.add(opponentViewMatrix[i][j]);
//                if (playfieldMatrix[i][j].isActive()) {
//                    playfieldMatrix[i][j].setText("a");
//                } else { 
//                    playfieldMatrix[i][j].setText("d");
//                }
            }
        }
        add(opponentViewMatrixPanel);
        setOpaque(false);
        setVisible(true);

    }


    public FieldGui getPlayfieldButton() {
        return playfieldButton;
    }

    public void setPlayfieldButton(FieldGui playfieldButton) {
        this.playfieldButton = playfieldButton;
    }



    public FieldGui[][] getOpponentViewMatrix() {
        return opponentViewMatrix;
    }
    
    /**
     * Setzt Schuß auf das Feld, das getroffen wurde
     * @param String coordinate
     * @param int shootRange
     * @param boolean orientation
     * @return hitShips int-Array mit Anzahl der getroffenen Schiffe
     */
    public int[] setShot(String coordinate, int shootRange, boolean orientation) {
        //Array, in dem  die getroffenen Schiffe stehen
        int[] hitShips = new int[shootRange];
        if (orientation == true) {
            for (int y = 0; y < getOpponentViewMatrix().length; y++) {
                for (int x = 0; x < getOpponentViewMatrix()[y].length; x++) {
                    if (coordinate.equals(getOpponentViewMatrix()[y][x].getFieldNumber())) {
                        for (int i = 0; i < shootRange; i++) {
                        	try{
                        		hitShips[i] = this.opponentViewMatrix[y][x + i].setIsShot();
                        	}
                        	catch(IndexOutOfBoundsException e){
                        		e.printStackTrace();
                        	}
                        }
                    }
                }
            }
        } else {
            for (int y = 0; y < getOpponentViewMatrix().length; y++) {
                for (int x = 0; x < getOpponentViewMatrix()[y].length; x++) {
                    if (coordinate.equals(getOpponentViewMatrix()[y][x].getFieldNumber())) {
                        for (int i = 0; i < shootRange; i++) {
                        	try{
                        		hitShips[i] = this.opponentViewMatrix[y + i][x].setIsShot();
                        	}
                            catch(IndexOutOfBoundsException e){
                            	e.printStackTrace();
                            }
                        }
                    }
                }
            }
        }
        return hitShips;
    }
    
      public void setOpponentViewButtonListener(ActionListener l) {

        for (int i = 0; i < opponentViewMatrix.length; i++) {
            for (int j = 0; j < opponentViewMatrix[i].length; j++) {
                opponentViewMatrix[i][j].addActionListener(l);
            }

        }
    }




}
