/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import Multimedia.BackgroundImagePanel;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import Game.*;
import Gameobjects.Playfield.*;

/**
 *
 * @author Tobias
 */
public class BattleshipGui_old extends JFrame {

    BackgroundImagePanel backgroundImagePanel;
    JPanel panelContainer;
    MainMenuGui mainMenuGui;
    SettingsGui settingsGui;
    InstructionsGui instructionsGui;
    GameGui gameGui;
    PlayerPlayfieldGui playfieldGui;

    GridBagLayout gameGuiLayout;
    GridBagConstraints gridBagConstraints;

    CardLayout cardLayout;

    public BattleshipGui_old() {
        setTitle("Battleship");
        setLayout(null);
        setBounds(450, 50, 1024, 768);
        setContentPane(backgroundImagePanel = new BackgroundImagePanel("G:\\hs\\Prog2\\Battleship_Alpha2\\Images\\background.jpg"));

        mainMenuGui = new MainMenuGui();
        mainMenuGui.setOpaque(false);

//        settingsGui = new SettingsGui();

        instructionsGui = new InstructionsGui();

//        gameGui = new GameGui();

//        playfieldGui = new PlayerPlayfieldGui();
        cardLayout = new CardLayout();
        panelContainer = new JPanel(cardLayout);
//        panelContainer.setPreferredSize(getSize());
        panelContainer.setOpaque(false);
        panelContainer.add(mainMenuGui, "menu");
//        panelContainer.add(settingsGui, "settings");
        panelContainer.add(instructionsGui, "instructions");
//        panelContainer.add(gameGui, "newGame");
//        panelContainer.add(playfieldGui, "playfield");

//        cardLayout.show(panelContainer, "menu");

        add(panelContainer);
        addListener();


        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void addGame() {
        this.gameGui.setGame(new Game());
    }

    private void addListener() {
        this.mainMenuGui.setListener(new MenuHandler());
//        this.settingsGui.setListener(new MenuHandler());
        this.instructionsGui.setListener(new MenuHandler());
    }
    private void addGameGuiListener() {
        this.gameGui.setGameButtonListener(new MenuHandler());

    }
    private void addSettingsGuiListener() {
         this.settingsGui.setListener(new MenuHandler());
    }

    class MenuHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            String command = e.getActionCommand();

            switch (command) {

                case "Menu-NewGame":
                    settingsGui = new SettingsGui();
                    panelContainer.add(settingsGui);
                    addSettingsGuiListener();
                    cardLayout.show(super, "settings");
                    break;
                case "Game-MainMenu":
                    cardLayout.show(panelContainer, "menu");
                case "Menu-LoadGame":
                    break;
                case "Menu-Instructions":
                    cardLayout.show(panelContainer, "instructions");
                    break;
                case "Menu-ExitGame":
                    System.exit(0);
                    break;
                case "Settings-MainMenu":
                    cardLayout.show(panelContainer, "menu");
                    break;
                case "Settings-StartGame":
                    gameGui = new GameGui();
                    panelContainer.add(gameGui, "newGame");
                    addGameGuiListener();
                    cardLayout.show(panelContainer, "newGame");
                    break;
                case "Instructions-MainMenuButton":
                    cardLayout.show(panelContainer, "menu");
                    break;

            }

        }

    }

    public static void main(String[] args) {
        BattleshipGui_old battleshipGui = new BattleshipGui_old();
    }

}
