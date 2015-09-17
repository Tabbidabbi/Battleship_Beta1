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
public class OpponentViewGui extends JPanel implements ActionListener {

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
                opponentViewMatrix[i][j].addActionListener(this);
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



    @Override
    public void actionPerformed(ActionEvent e) {
        String coordinateInput = e.getActionCommand();

    }

}
