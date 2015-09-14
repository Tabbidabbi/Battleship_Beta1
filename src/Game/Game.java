package Game;

import Gameobjects.Ships.Ship;
import Gameobjects.Player.AiPlayer;
import Gameobjects.Player.HumanPlayer;
import Gameobjects.Player.Player;
import Gameobjects.Playfield.Playfield;
import IO.IO;

import java.io.Serializable;
import java.util.ArrayList;

import com.sun.org.apache.xml.internal.security.utils.HelperNodeList;

import Helper.Helper;
import SaveLoad.SaveLoad;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Helper.*;

import java.io.UnsupportedEncodingException;

public class Game implements Serializable, ActionListener {

    /**
     *
     */
    private static final long serialVersionUID = -4356896699088096722L;
    private ArrayList<Player> playerList;
    private GameGui gameGui;
    private HelperNextPlayerDialog playerDialog;
    private Settings gameSettings;
    private boolean shipsNotPlaced = true;
    private boolean shipOrientation;
    private int player = 0;
    private int roundNumber = 1;
    private int shipsPlaced = 0;

    /**
     * Konstruktor der Klasse Game
     *
     * @param gameSettings Einstellungen zum Erstellen des Spiels
     */
    public Game(Settings gameSettings) {
        this.gameSettings = gameSettings;
        this.playerList = buildPlayerArray(gameSettings);
        this.gameGui = new GameGui(gameSettings);
        gamePreperation();
    }

    /**
     * Vorbereitung des Spiels und Prüfung ob ein Ki Spieler vorhanden ist.
     */
    private void gamePreperation() {
        System.out.println("Willkommen bei Schiffeversenken Alpha 4!!!" + "\n");
        addGameGui();
        addPlayerToGameGui(playerList);
        if (playerList.get(player) instanceof AiPlayer) {
                for (int j = 0; j < playerList.get(player).getShips().size();) {
                    if (!placeAiShip(player)) {
                        System.out.println("Schiff konnte nicht gesetzt werden, bitte erneut setzen!");
                    } else {
                        placeAiShip(player);
                        shipsPlaced++;
                        j++;

                    }
                }
                player++;
                System.out.println("Nächster Spieler");
        } else {
            interactWithPlayer(playerList);
            addPlayFieldMatrixListener();
        }
    }

    /**
     * Gibt gameGui zurück
     *
     * @return GameGui gameGui
     */
    public GameGui getGameGui() {
        return gameGui;
    }

    private void setUpAiGameContent() {

    }

    /**
     * Der GameGui wird das Spieldfeld des Spielers hinzugefügt und angezeigt.
     *
     * @param playerList
     */
    private void addPlayerToGameGui(ArrayList<Player> playerList) {
        gameGui.addPlayerPlayField(player, playerList);
        gameGui.showPlayerPlayField(player);

    }

    /**
     * Der GameGui wird die Spieler- und die Schiffsliste hinzugefügt und
     * angezeigt.
     */
    private void addGameGui() {
        gameGui.showPlayers(playerList);
        gameGui.showPlayerShips(player, playerList);
        playerDialog = new HelperNextPlayerDialog("Alle Schiffe wurden gesetzt.");
        addNextPlayerDialogListener();
    }

    /**
     * Textinteraktion mit dem Spieler
     *
     * @param playerList
     */
    private void interactWithPlayer(ArrayList<Player> playerList) {
        System.out.println("Spieler " + playerList.get(player).getName() + ", " + "Sie sind am Zug.");
        System.out.println("Setzen Sie Bitte alle vefuegbaren Schiffe." + "\n");
        System.out.println("Klicken Sie auf das Spielfeld um das Schiff " + playerList.get(player).getShips().get(shipsPlaced).getName() + " zu setzen: ");
    }

    /**
     * Textinteraktion mit dem Spieler
     */
    private void nextShipDialog() {
        System.out.println("Klicken Sie auf das Spielfeld um das Schiff " + playerList.get(player).getShips().get(shipsPlaced).getName() + " zu setzen: ");
    }

    /**
     * Gibt gameSettings zurück
     *
     * @return Settings gameSettings
     */
    public Settings getGameSettings() {
        return gameSettings;
    }

    /**
     * Gibt ArrayList vom Typ Player
     *
     * @return ArrayList<Player> playerList
     */
    public ArrayList<Player> getPlayerList() {
        return playerList;
    }

    /**
     * Erstellt die Spieler
     *
     * @param Settings gameSettings
     * @return ArrayList<Player> playerList
     */
    private ArrayList buildPlayerArray(Settings gameSettings) {
        this.playerList = new ArrayList<>();
        int playerNumber = 1;
        for (int i = 0; i < gameSettings.getAmountOfPlayer(); i++) {
            if (gameSettings.getAiArray()[i] == false) {
                Player player = new HumanPlayer(playerNumber, gameSettings.getPlayerNames()[i], gameSettings, false);
                playerList.add(player);
                playerNumber++;
            } else if (gameSettings.getAiArray()[i] == true) {
                AiPlayer player = new AiPlayer(playerNumber, gameSettings.getPlayerNames()[i], gameSettings, true);
                playerList.add(player);
                playerNumber++;
            }
        }
        return playerList;
    }

    /**
     * Setzt Schiffe
     *
     * @param ship Schiffobjet
     * @param input Koordinate zum Setzen
     * @param orientation Richtung des Schiffes
     * @param playfield Spielfeld des auf Sicht des Spielers
     * @param opponentfield playfield Spielfeld des auf Sicht des Gegners
     * @return boolean, ob Schiff gesetzt werden konnte
     */
    private boolean checkShipPlacement(ActionEvent e) {

        String input = e.getActionCommand();

        String[] splitted = input.split("\\#");

        if (shipOrientation == true) {
            for (int i = 0; i < playerList.get(player).getShips().get(shipsPlaced).getSize(); i++) {
                try {
                    // Abfrage, welche prüft ob das Feld auf der das
                    // Schiff gesetzt werden soll, deaktiviert ist.
                    // Falls ja:
                    // gibt die ganze Methode "false zurück".
                    if (!playerList.get(player).getPlayerPlayFieldGui().getPlayfieldMatrix()[Integer.parseInt(splitted[0])][Integer.parseInt(splitted[1]) + i]
                            .isActive()) {
                        System.out.println("Leider nicht moeglich," + "\n" + "das Schiff muss mindestens 1 Feld Abstand zum naechsten Schiff haben!");
                        return false;
                    }
                    // Falls das Schiff mit der Größe nicht in das
                    // Array passt, fange die Fehlermeldung ab und
                    // gib folgendes aus...
                } catch (ArrayIndexOutOfBoundsException ex) {
                    IO.println("Das Schiff passt so nicht auf das Spielfeld, bitte neue koordinaten eingeben!!!");
                    return false;
                }
            }
        } else {
            for (int i = 0; i < playerList.get(player).getShips().get(shipsPlaced).getSize(); i++) {
                try {
                    // Abfrage, welche prüft ob das Feld auf der das
                    // Schiff gesetzt werden soll, deaktiviert ist.
                    // Falls ja:
                    // gibt die ganze Methode "false zurück".
                    if (!playerList.get(player).getPlayerPlayFieldGui().getPlayfieldMatrix()[Integer.parseInt(splitted[0]) + i][Integer.parseInt(splitted[1])]
                            .isActive()) {
                        System.out.println("Leider nicht moeglich," + "\n" + "das Schiff muss mindestens 1 Feld Abstand zum naechsten Schiff haben!");
                        return false;
                    }
                    // Falls das Schiff mit der Größe nicht in das
                    // Array passt, fange die Fehlermeldung ab und
                    // gib folgendes aus...
                } catch (ArrayIndexOutOfBoundsException ex) {
                    IO.println("Das Schiff passt so nicht auf das Spielfeld, bitte neue koordinaten eingeben!!!");
                    return false;
                }
            }
        }
        return true;
    }
    /**
     * 
     * @param e
     * @param orientation
     * @param playerList
     * @return
     */
    public boolean placeShip(ActionEvent e, boolean orientation,
            ArrayList<Player> playerList) {

        String input = e.getActionCommand();

        String[] splitted = input.split("\\#");
        // true = horizontal
        if (orientation == true) {
            // Setze Schiff
            for (int i = 0; i < playerList.get(player).getShips().get(shipsPlaced).getSize(); i++) {
                playerList.get(player).getPlayerPlayFieldGui().getPlayfieldMatrix()[Integer.parseInt(splitted[0])][Integer.parseInt(splitted[1]) + i]
                        .setText(playerList.get(player).getShips().get(shipsPlaced).getSign());
//                            playfield.getFieldMatrix()[y][x + i]
//                                    .setOpponentStatus(shipsPlaced.getSign());
//                            playfield.getFieldMatrix()[y][x + i]
//                                    .setIsWater(false);
//                            playfield.getFieldMatrix()[y][x + i]
//                                    .setHasShip(true);
//                            playfield.getFieldMatrix()[y][x + i]
//                                    .setShipNumber(shipsPlaced.getNumber());
//
//                            opponentfield.getFieldMatrix()[y][x + i]
//                                    .setIsWater(false);
//                            opponentfield.getFieldMatrix()[y][x + i]
//                                    .setHasShip(true);
//                            opponentfield.getFieldMatrix()[y][x + i]
//                                    .setShipNumber(shipsPlaced.getNumber());

            }

//                         Deaktiviere Felder um das Schiff herum
            for (int i = (Integer.parseInt(splitted[1]) - 1); i <= playerList.get(player).getShips().get(shipsPlaced).getSize() + Integer.parseInt(splitted[1]); i++) {
                for (int j = (Integer.parseInt(splitted[0]) - 1); j < Integer.parseInt(splitted[0]) + 2; j++) {
                    try {
                        playerList.get(player).getPlayerPlayFieldGui().getPlayfieldMatrix()[j][i]
                                .setActive(false);
                        // Tetstweise eingebaut um zu sehen welche
                        // Felder deaktiviert werden
//                        playerList.get(player).getPlayerPlayFieldGui().getPlayfieldMatrix()[j][i].setText("D");
                    } catch (ArrayIndexOutOfBoundsException ex) {

                    }
                }
            }
//                    }
//                }
//            }

        } // false = vertikal
        else {
            // Setze Schiff
            for (int i = 0; i < playerList.get(player).getShips().get(shipsPlaced).getSize(); i++) {
                playerList.get(player).getPlayerPlayFieldGui().getPlayfieldMatrix()[Integer.parseInt(splitted[0]) + i][Integer.parseInt(splitted[1])]
                        .setText(playerList.get(player).getShips().get(shipsPlaced).getSign());
//                            playfield.getFieldMatrix()[y + i][x]
//                                    .setOpponentStatus(shipsPlaced.getSign());
//                            playfield.getFieldMatrix()[y + i][x]
//                                    .setIsWater(false);
//                            playfield.getFieldMatrix()[y + i][x]
//                                    .setHasShip(true);
//                            playfield.getFieldMatrix()[y + i][x]
//                                    .setShipNumber(shipsPlaced.getNumber());
//
//                            opponentfield.getFieldMatrix()[y][x + i]
//                                    .setIsWater(false);
//                            opponentfield.getFieldMatrix()[y][x + i]
//                                    .setHasShip(true);
//                            opponentfield.getFieldMatrix()[y][x + i]
//                                    .setShipNumber(shipsPlaced.getNumber());
//
            }

            // Deaktiviere Felder um das Schiff herum
            for (int i = (Integer.parseInt(splitted[0]) - 1); i <= playerList.get(player).getShips().get(shipsPlaced).getSize() + Integer.parseInt(splitted[0]); i++) {
                for (int j = (Integer.parseInt(splitted[1]) - 1); j < Integer.parseInt(splitted[1]) + 2; j++) {
                    try {
                        playerList.get(player).getPlayerPlayFieldGui().getPlayfieldMatrix()[i][j]
                                .setActive(false);
                        // Tetstweise eingebaut um zu sehen welche
                        // Felder deaktiviert werden
//                        playerList.get(player).getPlayerPlayFieldGui().getPlayfieldMatrix()[i][j].setText("D");
                    } catch (ArrayIndexOutOfBoundsException exc) {

                    }
                }
            }

//                    }
//                }
//            }
        }
        return true;
    }

    /**
     * Platziert KI-Schiffe
     * @param player Spielerindex
     * @return Gibt Booleanwert zurück, ob das Schiff gesetzt werden kann
     */
    private boolean placeAiShip(int player) {

        boolean orientation = ((AiPlayer) playerList.get(player)).getAiOrientation();
        int xCoordinate = ((AiPlayer) playerList.get(player)).getAiRandomNumber(playerList, player);
        int yCoordinate = ((AiPlayer) playerList.get(player)).getAiRandomNumber(playerList, player);
        // true = horizontal
        if (orientation == true) {
            for (int i = 0; i < playerList.get(player).getShips().get(shipsPlaced).getSize(); i++) {
                try {
                    // Abfrage, welche prüft ob das Feld, auf der das
                    // Schiff gesetzt werden soll, deaktiviert ist.
                    // Falls ja:
                    // gibt die ganze Methode "false zurück".
                    if (!playerList.get(player).getPlayerPlayFieldGui().getPlayfieldMatrix()[yCoordinate][xCoordinate + i]
                            .isActive()) {
                        System.out.println("Leider nicht moeglich," + "\n" + "das Schiff muss mindestens 1 Feld Abstand zum naechsten Schiff haben!");
                        return false;
                    }
                    // Falls das Schiff mit der Größe nicht in das
                    // Array passt, fange die Fehlermeldung ab und
                    // gib folgendes aus...
                } catch (ArrayIndexOutOfBoundsException ex) {
                    IO.println("Das Schiff passt so nicht auf das Spielfeld, bitte neue koordinaten eingeben!!!");
                    return false;
                }
            }

            // 1. ALLE Felder sind active
            // Alle Felder liegen innerhalb des playfields
            // Setze Schiff
            for (int i = 0; i < playerList.get(player).getShips().get(shipsPlaced).getSize(); i++) {
                playerList.get(player).getPlayerPlayFieldGui().getPlayfieldMatrix()[yCoordinate][xCoordinate + i].setText(playerList.get(player).getShips().get(shipsPlaced).getSign());
                playerList.get(player).getPlayerPlayFieldGui().getPlayfieldMatrix()[yCoordinate][xCoordinate + i].setIsWater(false);
                playerList.get(player).getPlayerPlayFieldGui().getPlayfieldMatrix()[yCoordinate][xCoordinate + i].setHasShip(true);
                playerList.get(player).getPlayerPlayFieldGui().getPlayfieldMatrix()[yCoordinate][xCoordinate + i].setShipNumber(playerList.get(player).getShips().get(shipsPlaced).getNumber());
                playerList.get(player).getOpponentPlayFieldGui().getOpponentfieldMatix()[yCoordinate][xCoordinate + i].setIsWater(true);
                playerList.get(player).getOpponentPlayFieldGui().getOpponentfieldMatix()[yCoordinate][xCoordinate + i].setHasShip(true);
                playerList.get(player).getOpponentPlayFieldGui().getOpponentfieldMatix()[yCoordinate][xCoordinate + i].setShipNumber(playerList.get(player).getShips().get(shipsPlaced).getNumber());
            }

            // Deaktiviere Felder um das Schiff herum für Spieleransicht/ -matrix
            for (int i = (xCoordinate - 1); i <= playerList.get(player).getShips().get(shipsPlaced).getSize() + xCoordinate; i++) {
                for (int j = (yCoordinate - 1); j < yCoordinate + 2; j++) {
                    try {
                        playerList.get(player).getPlayerPlayFieldGui().getPlayfieldMatrix()[j][i]
                                .setActive(false);
                    } catch (ArrayIndexOutOfBoundsException ex) {

                    }
                }
            }
            
         //Deaktiviere Felder um das Schiff herum für Gegneransicht/ -matrix
         for (int i = (xCoordinate - 1); i <= playerList.get(player).getShips().get(shipsPlaced).getSize() + xCoordinate; i++) {
             for (int j = (yCoordinate - 1); j < yCoordinate + 2; j++) {
                 try {
                     playerList.get(player).getOpponentPlayFieldGui().getOpponentfieldMatix()[j][i].setActive(false);
                 } catch (ArrayIndexOutOfBoundsException ex) {

                 }
             }
         }
        } 
        // false = vertikal
        else {
			//            for (int y = 0; y < playerList.get(player).getPlayerPlayFieldGui().getPlayfieldMatrix().length; y++) {
			//                for (int x = 0; x < playerList.get(player).getPlayerPlayFieldGui().getPlayfieldMatrix()[y].length; x++) {
			//                    if (input.equals(playfield.getFieldMatrix()[y][x]
			//                            .getFieldNumber())) {
            for (int i = 0; i < playerList.get(player).getShips().get(shipsPlaced).getSize(); i++) {
                try {
                    // Abfrage, welche prüft ob das Feld auf der das
                    // Schiff gesetzt werden soll, deaktiviert ist.
                    // Falls ja:
                    // gibt die ganze Methode "false zurück".
                    if (!playerList.get(player).getPlayerPlayFieldGui().getPlayfieldMatrix()[yCoordinate + i][xCoordinate]
                            .isActive()) {
                        System.out.println("Leider nicht moeglich," + "\n" + "das Schiff muss mindestens 1 Feld Abstand zum naechsten Schiff haben!");
                        return false;
                    }
                    // Falls das Schiff mit der Größe nicht in das
                    // Array passt, fange die Fehlermeldung ab und
                    // gib folgendes aus...
                } catch (ArrayIndexOutOfBoundsException ex) {
                    IO.println("Das Schiff passt so nicht auf das Spielfeld, bitte neue koordinaten eingeben!!!");
                    return false;
                }
            }
            // Setze Schiff
            for (int i = 0; i < playerList.get(player).getShips().get(shipsPlaced).getSize(); i++) {
                playerList.get(player).getPlayerPlayFieldGui().getPlayfieldMatrix()[yCoordinate + i][xCoordinate].setText(playerList.get(player).getShips().get(shipsPlaced).getSign());
                playerList.get(player).getPlayerPlayFieldGui().getPlayfieldMatrix()[yCoordinate + i][xCoordinate].setIsWater(false);
                playerList.get(player).getPlayerPlayFieldGui().getPlayfieldMatrix()[yCoordinate + i][xCoordinate].setHasShip(true);
                playerList.get(player).getPlayerPlayFieldGui().getPlayfieldMatrix()[yCoordinate + i][xCoordinate].setShipNumber(playerList.get(player).getShips().get(shipsPlaced).getNumber());
                playerList.get(player).getOpponentPlayFieldGui().getOpponentfieldMatix()[yCoordinate + i][xCoordinate].setIsWater(false);
                playerList.get(player).getOpponentPlayFieldGui().getOpponentfieldMatix()[yCoordinate + i][xCoordinate].setHasShip(true);
                playerList.get(player).getOpponentPlayFieldGui().getOpponentfieldMatix()[yCoordinate + i][xCoordinate].setShipNumber(playerList.get(player).getShips().get(shipsPlaced).getNumber());
            }

            // Deaktiviere Felder um das Schiff herum
            for (int i = (yCoordinate - 1); i <= playerList.get(player).getShips().get(shipsPlaced).getSize() + yCoordinate; i++) {
                for (int j = (xCoordinate - 1); j < xCoordinate + 2; j++) {
                    try {
                        playerList.get(player).getPlayerPlayFieldGui().getPlayfieldMatrix()[i][j]
                                .setActive(false);
                        // Tetstweise eingebaut um zu sehen welche
                        // Felder deaktiviert werden
                        // playerList.get(player).getPlayerPlayFieldGui().getPlayfieldMatrix()[i][j].setText("F");
                    } catch (ArrayIndexOutOfBoundsException exc) {
                    }
                }
            }
        }
        return true;
    }

    /**
     * Spielrunden beginnen
     *
     * @param Übergebeparameter ArrayList<> playerList
     */
    public void playRounds(ArrayList<Player> playerList) {
        // Runden beginnen
        // Solange es mehr als einen spieler gibt, wird diese Schleife
        // ausgeführt
        while (Helper.getAmountOfLivingPlayers(playerList) > 1) {
            for (int playerNumber = 0; playerNumber < playerList.size(); playerNumber++) {

                // Spieler, die verloren haben, kommen nicht mehr an die Reihe
                if (playerList.get(playerNumber).getisLost() == false) {
                    // Setzt die Nachladezeit aller Schiffe in jeder Runde um 1 runter
                    for (int shipNumber = 0; shipNumber < playerList.get(playerNumber).getShips().size(); shipNumber++) {
                        if (playerList.get(playerNumber).getShips().get(shipNumber).getCurrentReloadTime() >= 1) {
                            playerList.get(playerNumber).getShips().get(shipNumber).setDownReloadTime();
                        }
                    }

                    IO.println("Runde " + this.roundNumber + " beginnt.");

                    // Runde des Spielers playerCounter
                    for (int playerCounter = 0; playerCounter < playerList.size(); playerCounter++) {
                        if (playerList.get(playerCounter).getisLost() == false) {

                            if (playerList.get(playerCounter).getIsAi() == true) {

                                //1. Auswahl des Schiffes
                                IO.println("Spieler " + playerList.get(playerCounter).getNumber()
                                        + ": " + playerList.get(playerCounter).getName()
                                        + " ist am Zug!");
                                playerList.get(playerCounter).getPlayfield().printPlayField();
                                //Vorher casten
                                int aiShipIndex = ((AiPlayer) playerList.get(playerCounter)).getRandomShip(playerList, playerCounter);
                                int shootRange = playerList.get(playerCounter).getShips().get(aiShipIndex).getShootRange();
                                boolean orientation = false;
                                if (shootRange > 1) {
                                    orientation = ((AiPlayer) playerList.get(playerCounter)).getAiOrientation();
                                }

                                // 2. Auswahl eines Gegners.
                                int aiOpponentIndex;
                                if (((AiPlayer) playerList.get(playerCounter)).getAiLastHitOpponentIndex() == 9) {
                                    aiOpponentIndex = ((AiPlayer) playerList.get(playerCounter)).getAiOpponent(playerList, playerCounter);
                                } else {
                                    aiOpponentIndex = ((AiPlayer) playerList.get(playerCounter)).getAiLastHitOpponentIndex();
                                }
                                //IO.println("Spielfeld vom Gegner: " + playerList.get(aiOpponentIndex).getName());
                                //playerList.get(aiOpponentIndex).getOpponentField().printOpponentField();
                                // Koordinate wird gewählt

                                // 3. Koordinate auf dem Spielfeld auswählen.
                                String aiCoordinateToShoot = ((AiPlayer) playerList.get(playerCounter)).getAiShootCoordinate(playerList, aiOpponentIndex, ((AiPlayer) playerList.get(playerCounter)).getAiLastHitCoordinate());
								//String aiCoordinateToShoot = Helper.aiChooseCoordinate(playerList, playerCounter, playerList.get(playerCounter).getAiLastHitCoordinate());

                                // 4.Schiessen
                                String lastHitCoordinate = ((AiPlayer) playerList.get(playerCounter)).aiShootOnPlayField(playerList, aiOpponentIndex, shootRange, orientation, aiCoordinateToShoot);
                                ((AiPlayer) playerList.get(playerCounter)).setAiLastHitCoordinate(lastHitCoordinate);

                                // 5. Rundenende.
                                // Nachladezeiten werden gesetzt
                                playerList.get(playerCounter).getShips().get(aiShipIndex).setCurrentReloadTime();
                                // Es wird geprüft, ob der Gegner verloren hat.
                                if (Helper.checkIfShipAvailable(playerList, aiOpponentIndex) == false) {
                                    playerList.get(aiOpponentIndex).setLost(true);
                                }
                                if (playerList.get(aiOpponentIndex).getisLost() == true) {
                                    IO.println(playerList.get(aiOpponentIndex).getName() + " hat verloren!");
                                }
                            } else {
                                IO.println("Spieler " + playerList.get(playerCounter).getNumber()
                                        + ": " + playerList.get(playerCounter).getName() + " ist am Zug!");
                                playerList.get(playerCounter).getPlayfield().printPlayField();

                                // 1. Auswahl eines verfuegbaren Schiffes.
                                int shipIndex = playerList.get(playerCounter).getAvailableShipToShoot(playerList, playerCounter);
                                int shootRange = playerList.get(playerCounter).getShips().get(shipIndex).getShootRange();
                                boolean orientation = false;
                                if (shootRange > 1) {
                                    HelperOrientationDialog orientationDialog = new HelperOrientationDialog("Bitte geben Sie die Ausrichtung ein");
                                    orientation = orientationDialog.getOrientation();
                                }

                                // 2. Auswahl eines Gegners.
                                int opponent = playerList.get(playerCounter).getAvailableOpponentsToShoot(playerList, playerCounter);
                                playerList.get(opponent).getOpponentField().printOpponentField();

                                // 3. Koordinate auf dem Spielfeld auswählen.
                                String koordinate = playerList.get(playerCounter).coordinateToShoot();

                                // 4.Schiessen
                                playerList.get(playerCounter).shootOnPlayField(playerList, opponent, shootRange, orientation, koordinate);
                                playerList.get(playerCounter).getShips().get(shipIndex).setCurrentReloadTime();

                                if (Helper.checkIfShipAvailable(playerList, opponent) == false) {
                                    playerList.get(opponent).setLost(true);
                                }

                                if (playerList.get(opponent).getisLost() == true) {
                                    IO.println(playerList.get(opponent).getName() + " hat verloren!");
                                }
                            }
                        }

                    }
                }
                // Setzt den Counter der for-Schleife auf 0, damit eine neue Runde beginnt.
                if (playerNumber + 1 == playerList.size()) {
                    playerNumber = 0;

                }
            }
            //Rundennummer wird einen hochgesetzt
            this.roundNumber++;
            // Speichert das Spiel
            SaveLoad.save(this);
        }
        //Gibt es Gewinner aus
        Helper.printWinner(playerList);
    }

    private void addPlayFieldMatrixListener() {

        for (Player player : playerList) {
            player.getPlayerPlayFieldGui().setFieldButtonListener(this);
        }
    }

    private void addNextPlayerDialogListener() {
        playerDialog.setActionListener(this);
    }

    private void showNextPlayerDialog() {
        playerDialog.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (shipsPlaced < playerList.get(player).getShips().size()) {
            HelperOrientationDialog orientationDialog = new HelperOrientationDialog("Bitte geben Sie die Ausrichtung des Schiffes ein: ");
            shipOrientation = orientationDialog.getOrientation();
            if (!checkShipPlacement(e)) {
                System.out.println("Schiff konnte nicht gesetzt werden, bitte erneut versuchen.");
            } else {
                placeShip(e, shipOrientation, playerList);
                shipsPlaced++;
                if (shipsPlaced < playerList.get(player).getShips().size()) {
                    nextShipDialog();
                } else {
                    if (player < playerList.size() - 1) {
                        showNextPlayerDialog();
                    } else {
                        System.out.println("Runde beginnt");
                    }
                }
            }
        } else {
            shipsPlaced = 0;
        }

        if (e.getActionCommand().equals("Helper-NextPlayer")) {
            playerDialog.setVisible(false);
            playerDialog.dispose();
            player++;
            addPlayerToGameGui(playerList);
        }
    }
}
