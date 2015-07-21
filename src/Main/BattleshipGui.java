/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import Game.GameGui;
import Game.InstructionsGui;
import Game.SettingsGui;
import Gameobjects.Playfield.PlayerPlayfieldGui;
import Multimedia.BackgroundImagePanel;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 *
 * @author Tobias
 */
public class BattleshipGui extends JFrame {

    BackgroundImagePanel backgroundImagePanel;

    MenuHandler menuHandler;

    public BattleshipGui() {
        setTitle("Battleship");
        setExtendedState(MAXIMIZED_BOTH);
        setContentPane(backgroundImagePanel = new BackgroundImagePanel("G:\\hs\\Prog2\\Battleship_Alpha2\\Images\\background.jpg"));

        menuHandler = new MenuHandler();

        add(menuHandler);

        setVisible(true);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }
}
