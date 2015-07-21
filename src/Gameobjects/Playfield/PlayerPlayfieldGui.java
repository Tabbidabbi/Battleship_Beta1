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
public class PlayerPlayfieldGui extends JPanel implements ActionListener {

    Settings gameSettings;

    FieldGui playfieldButton;
    FieldGui[][] playfieldMatrix;
    JPanel playfieldMatrixPanel;

    public PlayerPlayfieldGui(Settings gameSettings) {
        this.gameSettings = gameSettings;
        playfieldMatrix = new FieldGui[gameSettings.getPlayfieldSize() + 1][gameSettings.getPlayfieldSize() + 1];

        playfieldMatrixPanel = new JPanel();
        playfieldMatrixPanel.setLayout(new GridLayout(gameSettings.getPlayfieldSize() + 1, gameSettings.getPlayfieldSize() + 1));
        playfieldMatrixPanel.setPreferredSize(new Dimension(900, 700));
        for (int i = 0; i < playfieldMatrix.length; i++) {
            for (int j = 0; j < playfieldMatrix[i].length; j++) {
                playfieldMatrix[i][j] = new FieldGui();
                playfieldMatrix[i][j].setActionCommand("" + i +"#"+ j);
                
                playfieldMatrix[i][j].addActionListener(this);
                playfieldMatrix[i][0].setText("" + i);
                playfieldMatrix[i][0].setEnabled(false);
                playfieldMatrix[i][0].setActive(false);
                playfieldMatrix[0][j].setText("" + j);
                playfieldMatrix[0][0].setText("Y  /  X");
                playfieldMatrix[0][j].setEnabled(false);
                playfieldMatrix[0][j].setActive(false);
                playfieldMatrixPanel.add(playfieldMatrix[i][j]);
            }
        }

        add(playfieldMatrixPanel);
        setOpaque(false);
        setVisible(true);

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

    @Override
    public void actionPerformed(ActionEvent e) {
        String coordinateInput = e.getActionCommand();

    }

}
