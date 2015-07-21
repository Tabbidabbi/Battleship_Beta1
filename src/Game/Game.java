package Game;

import Gameobjects.Ships.Ship;
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

    private ArrayList<Ship> shipList;

    private int input;

    private String coordinateInput;

    private Settings gameSettings;

    private Settings currentGameSettings;

    private boolean error;

    private boolean shipOrientation;

    private int roundNumber = 1;

    int player = 0;

    int ship = 0;

    boolean shipsNotPlaced = true;

    GameGui gameGui;

    /**
     * Konstruktor der Klasse Game
     *
     * @param gameSettings Einstellungen zum Erstellen des Spiels
     */
    public Game(Settings gameSettings) {
        this.gameSettings = gameSettings;
        this.playerList = buildPlayerArray(gameSettings);
        this.gameGui = new GameGui(gameSettings);
//        gameGui.addPlayerPlayField(0, playerList);
//        gameGui.showPlayers(playerList);
//        gameGui.showPlayerShips(0, playerList);
//        placeAllShips();
        initializeGame();
    }

    private void initializeGame() {

        System.out.println("Willkommen bei Schiffeversenken Alpha 3!!!" + "\n");

        initializePlayer(playerList);

    }

    /**
     * Gibt gameGui zurück
     *
     * @return GameGui gameGui
     */
    public GameGui getGameGui() {
        return gameGui;
    }

    private void initializePlayer(ArrayList<Player> playerList) {

        int amountOfAllShips = playerList.get(player).getShips().size();

        gameGui.addPlayerPlayField(player, playerList);
        gameGui.showPlayerPlayField(player);
        gameGui.showPlayers(playerList);
        gameGui.showPlayerShips(player, playerList);
        addPlayFieldMatrixListener();

        System.out.println("Spieler " + playerList.get(player).getName() + " Sie sind an der Reihe, Bitte setzen Sie "
                + playerList.get(player).getShips().get(ship).getName() + " mit der Groesse " + playerList.get(player).getShips().get(ship).getSize()
                + ". " + "\n");
        System.out.println("Klicken Sie dazu Bitte auf ein beliebiges Feld!");

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
            Player player = new Player(playerNumber, gameSettings.getPlayerNames()[i], gameSettings);
            playerList.add(player);
            playerNumber++;

        }
        return playerList;
    }

    /**
     * Platziert alle Schiffe
     */
//    private void placeAllShips() throws InterruptedException {
//        error = false;
//
//        for (int playerNumber = 0; playerNumber < playerList.size();) {
//            gameGui.addPlayerPlayField(playerNumber, playerList);
//            gameGui.showPlayers(playerList);
//            gameGui.showPlayerShips(playerNumber, playerList);
//            if (playerList.get(playerNumber).getIsAI() == false) {
//                IO.println("Spieler " + playerList.get(playerNumber).getName()
//                        + " Sie können nun folgende Schiffe setzen: ");
//                playerList.get(playerNumber).printShipList();
//                int ammountOfShips = playerList.get(playerNumber).getShips().size();
//                if (ammountOfShips > 0) {
//                    for (int s = 0; s < playerList.get(playerNumber).getShips().size(); s++) {
//                        IO.print("Bitte klicken Sie mit der Maus auf das Feld, in der das Schiff  "
//                                + playerList.get(playerNumber).getShips().get(s).getName() + " "
//                                + playerList.get(playerNumber).getShips().get(s).getNumber() + " platziert werden soll:");
//                        ammountOfShips--;
////                    coordinateInput = playerList.get(s).getPlayerPlayFieldGui().getPlayfieldButton().getActionCommand();
////                    do {
////                        coordinateInput = IO.readString().toLowerCase(); // Großbuchstaben->
////                        // Kleinbuchstaben
////                        // Teste Eingabe mit RegEx(^ Anfang, 1 Zahl (1-9) und 1
////                        // oder keine Zahl(0-9) und 1 Buchstabe (a-z), $ Ende
////                        if (coordinateInput
////                                .matches("^[1-9]{1}[0-9]{0,1}[a-z]{1}$")) {
////                            error = false;
////                        } else {
////                            IO.println("Falsche Eingabe, bitte geben sie zuerst die Nummer und dann den Buchstaben des Feldes ein: ");
////                            error = true;
////                        }
////                    } while (error);
//                        shipOrientation = Helper.checkOrientation();
//                        // Schiff wird gesetzt
//                        if (!placeShip(ships.get(s), coordinateInput,
//                                shipOrientation, playerList.get(playerNumber)
//                                .getPlayfield(),
//                                playerList.get(playerNumber).getOpponentField())) {
//                            IO.println("Das Schiff konnte nicht gesetzt werden. Bitte erneut versuchen.");
//                        } else {
//                            s++;
//                        }
//
//                    }
//                }
//                } else {
//                    IO.println("Spieler " + playerList.get(playerNumber).getName()
//                            + " Sie können nun folgende Schiffe setzen: ");
//                    playerList.get(playerNumber).printShipList();
//                    ArrayList<Ship> ships = playerList.get(playerNumber).getShips();
//                    System.out.println(ships.size());
//                    for (int s = 0; s < ships.size();) {
//                        IO.print("Bitte geben Sie die Koordinaten für "
//                                + ships.get(s).getName() + " "
//                                + ships.get(s).getNumber() + " ein:");
//                        do {
//                            coordinateInput = Helper.aiChooseCoordinate(playerList,
//                                    playerNumber);
//						// Teste Eingabe mit RegEx(^ Anfang, 1 Zahl (1-9) und 1
//                            // oder keine Zahl(0-9) und 1 Buchstabe (a-z), $ Ende
//                            if (coordinateInput
//                                    .matches("^[1-9]{1}[0-9]{0,1}[a-z]{1}$")) {
//                                error = false;
//                            } else {
//                                IO.println("Falsche Eingabe, bitte geben sie zuerst die Nummer und dann den Buchstaben des Feldes ein: ");
//                                error = true;
//                            }
//                        } while (error);
//                        shipOrientation = Helper.chooseAiOrientation();
//                        // Schiff wird gesetzt
//                        if (!placeShip(ships.get(s), coordinateInput,
//                                shipOrientation, playerList.get(playerNumber)
//                                .getPlayfield(),
//                                playerList.get(playerNumber).getOpponentField())) {
//                            IO.println("Das Schiff konnte nicht gesetzt werden. Bitte erneut versuchen.");
//                        } else {
//                            s++;
//                        }
//
//                    }
//                }
//
//            }
//        }
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
    public boolean placeShip(ActionEvent e, boolean orientation,
            ArrayList<Player> playerList) {
        
        String input = e.getActionCommand();
        
        String[] splitted =input.split("\\#");
        // true = horizontal
        if (orientation == true) {
//            for (int y = 0; y < playerList.get(player).getPlayerPlayFieldGui().getPlayfieldMatrix().length; y++) {
//                for (int x = 0; x < playerList.get(player).getPlayerPlayFieldGui().getPlayfieldMatrix()[y].length; x++) {
//                    if (e.getActionCommand().equals(playfield.getFieldMatrix()[y][x]
//                            .getFieldNumber())) {
                        // 1. ALLE Felder sind active
                        // Alle Felder liegen innerhalb des playfields
                        for (int i = 0; i < playerList.get(player).getShips().get(ship).getSize(); i++) {
                            try {
                                // Abfrage, welche prüft ob das Feld auf der das
                                // Schiff gesetzt werden soll, deaktiviert ist.
                                // Falls ja:
                                // gibt die ganze Methode "false zurück".
                                if (!playerList.get(player).getPlayerPlayFieldGui().getPlayfieldMatrix()[Integer.parseInt(splitted[0])][Integer.parseInt(splitted[1]) + i]
                                        .isActive()) {
                                    System.out.println("Leider nicht möglich, das Schiff muss mindestens 1 Feld Abstand zum nächsten Schiff haben!");
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

                        for (int i = 0; i < playerList.get(player).getShips().get(ship).getSize(); i++) {
                            playerList.get(player).getPlayerPlayFieldGui().getPlayfieldMatrix()[Integer.parseInt(splitted[0])][Integer.parseInt(splitted[1]) + i]
                                    .setText(playerList.get(player).getShips().get(ship).getSign());
//                            playfield.getFieldMatrix()[y][x + i]
//                                    .setOpponentStatus(ship.getSign());
//                            playfield.getFieldMatrix()[y][x + i]
//                                    .setIsWater(false);
//                            playfield.getFieldMatrix()[y][x + i]
//                                    .setHasShip(true);
//                            playfield.getFieldMatrix()[y][x + i]
//                                    .setShipNumber(ship.getNumber());
//
//                            opponentfield.getFieldMatrix()[y][x + i]
//                                    .setIsWater(false);
//                            opponentfield.getFieldMatrix()[y][x + i]
//                                    .setHasShip(true);
//                            opponentfield.getFieldMatrix()[y][x + i]
//                                    .setShipNumber(ship.getNumber());

                        }

                        // Deaktiviere Felder um das Schiff herum
//                        for (int i = (x - 1); i <= playerList.get(player).getShips().get(ship).getSize() + x; i++) {
//                            for (int j = (y - 1); j < y + 2; j++) {
//                                try {
//                                    playerList.get(player).getPlayerPlayFieldGui().getPlayfieldMatrix()[j][i]
//                                            .setActive(false);
//                                    // Tetstweise eingebaut um zu sehen welche
//                                    // Felder deaktiviert werden
//                                    // playfield.getPlayField()[j][i].setStatus("F");
//                                } catch (ArrayIndexOutOfBoundsException ex) {
//
//                                }
//                            }
//                        }
//                    }
//                }
//            }

        } // false = vertikal
        else {
//            for (int y = 0; y < playerList.get(player).getPlayerPlayFieldGui().getPlayfieldMatrix().length; y++) {
//                for (int x = 0; x < playerList.get(player).getPlayerPlayFieldGui().getPlayfieldMatrix()[y].length; x++) {
//                    if (input.equals(playfield.getFieldMatrix()[y][x]
//                            .getFieldNumber())) {
                        for (int i = 0; i < playerList.get(player).getShips().get(ship).getSize(); i++) {
                            try {
                                // Abfrage, welche prüft ob das Feld auf der das
                                // Schiff gesetzt werden soll, deaktiviert ist.
                                // Falls ja:
                                // gibt die ganze Methode "false zurück".
                                if (!playerList.get(player).getPlayerPlayFieldGui().getPlayfieldMatrix()[Integer.parseInt(splitted[0]) + i][Integer.parseInt(splitted[1])]
                                        .isActive()) {
                                    IO.println("Leider nicht möglich, das Schiff muss mindestens 1 Feld Abstand zum nächsten Schiff haben!");
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
                        for (int i = 0; i < playerList.get(player).getShips().get(ship).getSize(); i++) {
                            playerList.get(player).getPlayerPlayFieldGui().getPlayfieldMatrix()[Integer.parseInt(splitted[0]) + i][Integer.parseInt(splitted[1])]
                                    .setText(playerList.get(player).getShips().get(ship).getSign());
//                            playfield.getFieldMatrix()[y + i][x]
//                                    .setOpponentStatus(ship.getSign());
//                            playfield.getFieldMatrix()[y + i][x]
//                                    .setIsWater(false);
//                            playfield.getFieldMatrix()[y + i][x]
//                                    .setHasShip(true);
//                            playfield.getFieldMatrix()[y + i][x]
//                                    .setShipNumber(ship.getNumber());
//
//                            opponentfield.getFieldMatrix()[y][x + i]
//                                    .setIsWater(false);
//                            opponentfield.getFieldMatrix()[y][x + i]
//                                    .setHasShip(true);
//                            opponentfield.getFieldMatrix()[y][x + i]
//                                    .setShipNumber(ship.getNumber());
//
                        }

                        // Deaktiviere Felder um das Schiff herum
//                        for (int i = (y - 1); i <= playerList.get(player).getShips().get(ship).getSize() + y; i++) {
//                            for (int j = (x - 1); j < x + 2; j++) {
//                                try {
//                                    playerList.get(player).getPlayerPlayFieldGui().getPlayfieldMatrix()[i][j]
//                                            .setActive(false);
//                                    // Tetstweise eingebaut um zu sehen welche
//                                    // Felder deaktiviert werden
//                                    // playfield.getPlayField()[i][j].setStatus("F");
//                                } catch (ArrayIndexOutOfBoundsException ex) {
//
//                                }
//                            }
//                        }

//                    }
//                }
//            }
        }
//        playfield.printPlayField();

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
        while (Helper.checkAmountOfAvailablePlayers(playerList) > 1) {
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

                            if (playerList.get(playerCounter).getIsAI() == true) {

                                //1. Auswahl des Schiffes
                                IO.println("Spieler " + playerList.get(playerCounter).getNumber()
                                        + ": " + playerList.get(playerCounter).getName()
                                        + " ist am Zug!");
                                playerList.get(playerCounter).getPlayfield().printPlayField();
                                int aiShipIndex = Helper.getRandomShip(playerList, playerCounter);
                                int shootRange = playerList.get(playerCounter).getShips().get(aiShipIndex).getShootRange();
                                boolean orientation = false;
                                if (shootRange > 1) {
                                    orientation = Helper.chooseAiOrientation();
                                }

                                // 2. Auswahl eines Gegners.
                                int aiOpponentIndex = Helper.chooseAiOpponent(playerList, playerCounter);
                                IO.println("Spielfeld vom Gegner: " + playerList.get(aiOpponentIndex).getName());
                                playerList.get(aiOpponentIndex).getOpponentField().printOpponentField();
								// Koordinate wird gewählt

                                // 3. Koordinate auf dem Spielfeld auswählen.
                                String aiCoordinateToShoot = Helper.aiChooseCoordinate(playerList, playerCounter);
								//String aiCoordinateToShoot = Helper.aiChooseCoordinate(playerList, playerCounter, playerList.get(playerCounter).getAiLastHitCoordinate());

                                // 4.Schiessen
                                String lastHitCoordinate = Helper.aiShootOnPlayField(playerList, aiOpponentIndex, shootRange, orientation, aiCoordinateToShoot);
                                playerList.get(playerCounter).setAiLastHitCoordinate(lastHitCoordinate);

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
                                        + ": " + playerList.get(playerCounter).getName()
                                        + " ist am Zug!");
                                playerList.get(playerCounter).getPlayfield().printPlayField();

                                // 1. Auswahl eines verfuegbaren Schiffes.
                                int shipIndex = Helper.getAvailableShipToShoot(playerList, playerCounter);
                                int shootRange = playerList.get(playerCounter).getShips().get(shipIndex).getShootRange();
                                boolean orientation = false;
                                if (shootRange > 1) {
                                    orientation = Helper.checkOrientation();
                                }

                                // 2. Auswahl eines Gegners.
                                int opponent = Helper.getAvailableOpponentsToShoot(playerList, playerCounter);
                                playerList.get(opponent).getOpponentField().printOpponentField();

                                // 3. Koordinate auf dem Spielfeld auswählen.
                                String koordinate = Helper.coordinateToShoot();

                                // 4.Schiessen
                                Helper.shootOnPlayField(playerList, opponent, shootRange, orientation, koordinate);
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

    @Override
    public void actionPerformed(ActionEvent e) {

        if (shipsNotPlaced) {
            HelperOrientationDialog dialog = new HelperOrientationDialog("Bitte geben Sie die Ausrichtung des Schiffes ein: ");
            shipOrientation = dialog.getOrientation();
            placeShip(e, shipOrientation, playerList);
            
        }

    }
}
