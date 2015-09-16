/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Game;

import Gameobjects.Player.Player;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.*;
import java.awt.*;
import Gameobjects.Playfield.*;
import Gameobjects.Ships.Ship;
import Main.BattleshipGui_old;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import Main.BattleshipGui_old;
import Main.MenuHandler;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import javax.swing.GroupLayout.SequentialGroup;

/**
 *
 * @author Tobias
 */
public class GameGui extends JPanel {

    BoxLayout boxLayout;

    CardLayout playFieldCardLayout;

    JPanel playerPlayFieldPanel;
    JPanel[] playerPlayFieldArray;

    Settings gameSettings;

    JLabel playerListLabel;
    JButton[] playerButton;
    JPanel playerListPanel;

    JTextArea textOutputArea;
    JScrollPane textOutputPanel;
    JPanel midPanel;

    JLabel shipListLabel;
    JButton[] shipListButtons;
    JPanel shipListPanel;

    JPanel componentPanel;

    JButton menuButton, saveGameButton, startGameButton;
    JPanel buttonPanel;

    private PrintStream standardOut;

    public GameGui(Settings gameSettings) {
        this.gameSettings = gameSettings;
        setOpaque(false);
        GroupLayout gameGuiLayout = new GroupLayout(this);

        playerPlayFieldPanel = new JPanel();
        playFieldCardLayout = new CardLayout();
        playerPlayFieldPanel.setLayout(playFieldCardLayout);
        playerPlayFieldPanel.setOpaque(false);

        playerListLabel = new JLabel("Spieler: ");
        playerListPanel = new JPanel();
        playerListPanel.add(playerListLabel);
        playerListPanel.setLayout(new BoxLayout(playerListPanel, BoxLayout.Y_AXIS));

        textOutputArea = new JTextArea();
        textOutputArea.setEditable(false);
        textOutputArea.setLineWrap(true);
        textOutputArea.setFont(new Font("Serif", Font.BOLD, 12));
        PrintStream printStream = new PrintStream(new CustomOutputStream(textOutputArea), true);
        standardOut = System.out;
        System.setOut(printStream);

        textOutputPanel = new JScrollPane(textOutputArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        shipListLabel = new JLabel("Schiffe: ");
        shipListPanel = new JPanel();
        shipListPanel.add(shipListLabel);
        shipListPanel.setLayout(new BoxLayout(shipListPanel, BoxLayout.Y_AXIS));

        startGameButton = new JButton("Spiel-Starten");
        startGameButton.setActionCommand("Game-StartGame");
        startGameButton.setFont(new Font("Serif", 10, 13));
        startGameButton.setBackground(Color.white);
        startGameButton.setForeground(Color.black);
        menuButton = new JButton("Hauptmenü");
        menuButton.setActionCommand("Game-MainMenu");
        menuButton.setFont(new Font("Serif", 10, 13));
        menuButton.setBackground(Color.white);
        menuButton.setForeground(Color.black);
        saveGameButton = new JButton("Spiel Speichern");
        saveGameButton.setActionCommand("Game-SaveGame");
        saveGameButton.setFont(new Font("Serif", 10, 13));
        saveGameButton.setBackground(Color.white);
        saveGameButton.setForeground(Color.black);
        buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
        buttonPanel.add(menuButton);
        buttonPanel.add(saveGameButton);

        setLayout(gameGuiLayout);
        gameGuiLayout.setVerticalGroup(
                gameGuiLayout.createSequentialGroup()
                .addGroup(gameGuiLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                        .addGroup(gameGuiLayout.createSequentialGroup()
                                .addComponent(playerPlayFieldPanel, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(startGameButton))
                        .addGroup(gameGuiLayout.createSequentialGroup()
                                .addComponent(textOutputPanel, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(gameGuiLayout.createParallelGroup()
                                        .addComponent(playerListPanel)
                                        .addComponent(shipListPanel))
                        )
                )
                .addComponent(buttonPanel)
        );
        gameGuiLayout.setHorizontalGroup(
                gameGuiLayout.createSequentialGroup()
                .addGroup(gameGuiLayout.createParallelGroup(GroupLayout.Alignment.CENTER)
                        .addComponent(playerPlayFieldPanel, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(startGameButton))
                .addGroup(gameGuiLayout.createParallelGroup(GroupLayout.Alignment.CENTER)
                        .addComponent(textOutputPanel, 0, GroupLayout.DEFAULT_SIZE, 350)
                        .addGroup(gameGuiLayout.createSequentialGroup()
                                .addComponent(playerListPanel)
                                .addComponent(shipListPanel))
                        .addComponent(buttonPanel))
        );

        gameGuiLayout.setAutoCreateGaps(true);
        gameGuiLayout.setAutoCreateContainerGaps(true);
        
        setPreferredSize(new Dimension(1024, 768));
        setVisible(true);
    }

    public void addPlayerPlayField(int playerNumber, ArrayList<Player> playerList) {

        playerPlayFieldPanel.add(playerList.get(playerNumber).getPlayerPlayFieldGui(), "Player" + playerNumber);

    }

    public void showPlayerPlayField(int playerNumber) {
        playFieldCardLayout.show(playerPlayFieldPanel, "Player" + playerNumber);
//        repaint();

    }

    public void showPlayers(ArrayList<Player> playerList) {
        playerButton = new JButton[playerList.size()];
        for (int i = 0; i < playerButton.length; i++) {
            playerButton[i] = new JButton(playerList.get(i).getName());
            playerListPanel.add(playerButton[i]);
        }
//        revalidate();

    }

    public void showPlayerShips(int playerNumber, ArrayList<Player> playerList) {
        shipListButtons = new JButton[playerList.get(playerNumber).getShips().size()];
        Dimension maxButtonSize;
        for (int i = 0; i < shipListButtons.length; i++) {
            shipListButtons[i] = new JButton(playerList.get(playerNumber).getShips().get(i).getName() + "(Gr. " + playerList.get(playerNumber).getShips().get(i).getSize() + ")");
            maxButtonSize = shipListButtons[0].getMaximumSize();
            shipListButtons[i].setMaximumSize(maxButtonSize);
            shipListPanel.add(shipListButtons[i]);
        }
//        revalidate();
    }

    public void setGameButtonListener(ActionListener l) {
        this.menuButton.addActionListener(l);
        this.saveGameButton.addActionListener(l);
    }
    public void setStartGameButtonListener(ActionListener l) {
        this.startGameButton.addActionListener(l);
    }
    
    public void disableButton() {
        this.startGameButton.setVisible(false);
    }

    public class CustomOutputStream extends OutputStream {

        private JTextArea textArea;

        public CustomOutputStream(JTextArea textArea) {
            this.textArea = textArea;
        }

        @Override
        public void write(int b) throws IOException {
            // redirects data to the text area
            textArea.append(String.valueOf((char) b));
            // scrolls the text area to the end of data
            textArea.setCaretPosition(textArea.getDocument().getLength());
        }
    }
}
