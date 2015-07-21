package Gameobjects.Player;

import Game.Settings;
import Gameobjects.Playfield.Playfield;
import Gameobjects.Ships.*;
import IO.IO;
import java.io.Serializable;
import java.util.ArrayList;
import Game.Game;
import Gameobjects.Playfield.PlayerPlayfieldGui;

public class Player implements Serializable {

    private Settings gameSettings;

    /**
     *
     */
    private static final long serialVersionUID = -3542755719003023085L;

    private int input;

    private int number;

    private String name;

    private ArrayList<Ship> ships;

    private Playfield playfield;
    private PlayerPlayfieldGui playerPlayFieldGui;

    private Playfield opponentField;

    private boolean lost;

    private boolean isAI;

    private int lastHitOpponentNumber;

    private String aiLastHitCoordinate;

    /**
     * Konstruktor für den Spieler
     *
     * @param number Nummer des Spielers
     * @param gameSettings Spieleinstellungen
     */
    public Player(int number,String name,Settings gameSettings) {
        this.gameSettings = gameSettings;
        this.name = name;
        this.number = number;
        buildShipArray(gameSettings);
        this.playerPlayFieldGui = new PlayerPlayfieldGui(gameSettings);
//        playfield = new Playfield(gameSettings.getPlayfieldSize(),gameSettings.getPlayfieldSize());
//        playfield.printPlayField();
//        opponentField = new Playfield(gameSettings.getPlayfieldSize(),gameSettings.getPlayfieldSize());
    }

    /**
     * Gibt SpielerGUI zurück
     *
     * @return playerPlayFieldGui SpielerGui
     */
    public PlayerPlayfieldGui getPlayerPlayFieldGui() {
        return playerPlayFieldGui;
    }

    /**
     * Setzt SpielerGUI
     *
     * @param playerPlayFieldGui SpielerGUI
     */
    public void setPlayerPlayFieldGui(PlayerPlayfieldGui playerPlayFieldGui) {
        this.playerPlayFieldGui = playerPlayFieldGui;
    }

    /**
     * Erzeugt Schiffsarray
     *
     * @param cSettings Spieleinstellungen
     */
    private void buildShipArray(Settings cSettings) {
        ships = new ArrayList<>();
        int shipNumber = 1;
        for (int i = 1; i <= cSettings.getAmountOfDestroyer(); i++) {
            Ship ship = new Destroyer(shipNumber);
            ships.add(ship);
            shipNumber++;
        }
        for (int i = 1; i <= cSettings.getAmountOfFrigate(); i++) {
            Ship ship = new Frigate(shipNumber);
            ships.add(ship);
            shipNumber++;

        }
        for (int i = 1; i <= cSettings.getAmountOfCorvette(); i++) {
            Ship ship = new Corvette(shipNumber);
            ships.add(ship);
            shipNumber++;

        }
        for (int i = 1; i <= cSettings.getAmountOfSubmarine(); i++) {
            Ship ship = new Submarine(shipNumber);
            ships.add(ship);
            shipNumber++;

        }
    }

    /**
     * Gibt Nummer des Spielers zurück
     *
     * @return int number
     */
    public int getNumber() {
        return number;
    }

    /**
     * Setzt Spielernummmer
     *
     * @param int number
     */
    public void setNumber(int number) {
        this.number = number;
    }

    /**
     * Gibt Name des Spielers zurück
     *
     * @return String name
     */
    public String getName() {
        return name;
    }

    /**
     * Setzt Name des Spielers
     *
     * @param String name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gibt ArrayList vom Typ Ship zurück
     *
     * @return ArrayList<Ship> ships
     */
    public ArrayList<Ship> getShips() {
        return ships;
    }

    /**
     * Setzt ArrayList vom Typ Ship
     *
     * @param ArrayList<Ship> ships
     */
    public void setShips(ArrayList<Ship> ships) {
        this.ships = ships;
    }

    /**
     * Gibt Playfield zurück
     *
     * @return Playfield playfield
     */
    public Playfield getPlayfield() {
        return playfield;
    }

    /**
     * Setzt Playfield
     *
     * @param Playfield playfield
     */
    public void setPlayfield(Playfield playfield) {
        this.playfield = playfield;
    }

    /**
     * Gibt gegnerisches Spielfeld zurück
     *
     * @return Playfield opponentField
     */
    public Playfield getOpponentField() {
        return opponentField;
    }

    /**
     * Setzt gegnerisches Spielfeld
     *
     * @param Playfield opponentField
     */
    public void setOpponentField(Playfield opponentField) {
        this.opponentField = opponentField;
    }

    /**
     * Gibt zurück, ob Gegner verloren hat
     *
     * @return boolean lost
     */
    public boolean getisLost() {
        return lost;
    }

    /**
     * Setzt, dass Gegner verloren hat
     *
     * @param booelan lost
     */
    public void setLost(boolean lost) {
        this.lost = lost;
    }

    /**
     * Gibt zurück, ob Spieler KI ist
     *
     * @return booelan isAI
     */
    public boolean getIsAI() {
        return isAI;
    }

    /**
     * Setzt, das Spieler KI ist
     *
     * @param boolean isAI
     */
    public void setIsAI(boolean isAI) {
        this.isAI = isAI;
    }

    /**
     * Gibt Index des Spieler, der letzte Runde angegriffen wurde, zurück
     *
     * @return int lastHitOpponentNumber
     */
    public int getLastHitOpponentNumber() {
        return lastHitOpponentNumber;
    }

    /**
     * Setzt Index des Spieler, der letzte Runde angegriffen wurde
     *
     * @param lastHitOpponentNumber
     */
    public void setLastHitOpponentNumber(int lastHitOpponentNumber) {
        this.lastHitOpponentNumber = lastHitOpponentNumber;
    }

    /**
     * Gibt die letzte getroffene Koordinate zurück
     *
     * @return String aiLastHitCoordinate
     */
    public String getAiLastHitCoordinate() {
        return aiLastHitCoordinate;
    }

    /**
     * Setzt Koordinate, die als letztes getroffen wurde
     *
     * @param String aiLastHitCoordinate
     */
    public void setAiLastHitCoordinate(String aiLastHitCoordinate) {
        this.aiLastHitCoordinate = aiLastHitCoordinate;
    }

    /**
     * Gibt die ArrayListe der Schiffe aus
     */
    public void printShipList() {

        for (Ship ship : ships) {
            IO.println(ship.getName() + "\t" + ship.getNumber() + "\t"
                    + " Größe " + "(" + ship.getSize() + ")");
        }
    }

}
