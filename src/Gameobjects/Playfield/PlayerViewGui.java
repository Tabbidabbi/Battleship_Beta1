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

/**
 *
 * @author Tobias
 */
public class PlayerViewGui extends JPanel  {

    private Settings gameSettings;
    private FieldGui playfieldButton;

    private FieldGui[][] playerViewMatrix;
    private JPanel playerViewMatrixPanel;

    public PlayerViewGui(Settings gameSettings) {
        //1. Spieleransicht
        setLayout(new BorderLayout());
        this.gameSettings = gameSettings;
        playerViewMatrix = new FieldGui[gameSettings.getPlayfieldSize() + 1][gameSettings.getPlayfieldSize() + 1];

        playerViewMatrixPanel = new JPanel();

        playerViewMatrixPanel.setLayout(new GridLayout(gameSettings.getPlayfieldSize() + 1, gameSettings.getPlayfieldSize() + 1));
        for (int i = 0; i < playerViewMatrix.length; i++) {
            for (int j = 0; j < playerViewMatrix[i].length; j++) {
                playerViewMatrix[i][j] = new FieldGui();
                playerViewMatrix[i][j].setActionCommand("" + i + "#" + j);
                playerViewMatrix[i][0].setText("" + i);
                playerViewMatrix[i][0].setActive(false);
                playerViewMatrix[0][j].setText("" + j);
                playerViewMatrix[0][j].setActive(false);
                playerViewMatrix[0][0].setText("Y  /  X");
                playerViewMatrix[0][0].setFont(new Font("Serif", Font.BOLD, 10));
                playerViewMatrix[i][j].setBackground(Color.gray);
                playerViewMatrix[i][j].setEnabled(false);
                playerViewMatrixPanel.add(playerViewMatrix[i][j]);
//                if (playfieldMatrix[i][j].isActive()) {
//                    playfieldMatrix[i][j].setText("a");
//                } else { 
//                    playfieldMatrix[i][j].setText("d");
//                }
            }
        }
        add(playerViewMatrixPanel);
        setOpaque(false);
        setVisible(true);

    }


    public FieldGui getPlayfieldButton() {
        return playfieldButton;
    }

    public void setPlayfieldButton(FieldGui playfieldButton) {
        this.playfieldButton = playfieldButton;
    }

    public void setPlayerViewButtonListener(ActionListener l) {

        for (int i = 0; i < playerViewMatrix.length; i++) {
            for (int j = 0; j < playerViewMatrix[i].length; j++) {
                playerViewMatrix[i][j].addActionListener(l);
            }

        }
    }

    public FieldGui[][] getPlayerViewMatrix() {
        return playerViewMatrix;
    }


    public void enablePlayfield() {
        for (int i = 0; i < playerViewMatrix.length; i++) {
            for (int j = 0; j < playerViewMatrix[i].length; j++) {
                playerViewMatrix[i][j].setEnabled(true);
                playerViewMatrix[i][j].setBackground(new Color(64, 164, 223));
                playerViewMatrix[i][0].setBackground(Color.white);
                playerViewMatrix[0][j].setBackground(Color.white);
                playerViewMatrix[i][0].setEnabled(false);
                playerViewMatrix[0][j].setEnabled(false);
                repaint();

            }
        }

    }

    public void disablePlayfield() {
        for (int i = 0; i < playerViewMatrix.length; i++) {
            for (int j = 0; j < playerViewMatrix[i].length; j++) {
                playerViewMatrix[i][j].setEnabled(false);
                playerViewMatrix[i][j].setBackground(Color.gray);
                repaint();

            }
        }

    }


}
