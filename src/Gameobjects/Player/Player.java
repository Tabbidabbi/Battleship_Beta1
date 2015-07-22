package Gameobjects.Player;

import Game.Settings;
import Gameobjects.Playfield.Playfield;
import Gameobjects.Ships.*;
import IO.IO;

import java.io.Serializable;
import java.util.ArrayList;

import Gameobjects.Playfield.PlayerPlayfieldGui;

public class Player implements Serializable {

	private static final long serialVersionUID = -3542755719003023085L;
	private Settings gameSettings;
	private int number;
	private String name;
	private ArrayList<Ship> ships;
	private Playfield playfield;
	private Playfield opponentField;
	private PlayerPlayfieldGui playerPlayFieldGui;
	private boolean lost;
	private boolean isAI;

	/**
	 * Konstruktor für den Spieler
	 *
	 * @param number
	 *            Nummer des Spielers
	 * @param gameSettings
	 *            Spieleinstellungen
	 */
	public Player(int number, String name, Settings gameSettings) {
		this.gameSettings = gameSettings;
		this.name = name;
		this.number = number;
		buildShipArray(gameSettings);
		this.playerPlayFieldGui = new PlayerPlayfieldGui(gameSettings);
		// playfield = new
		// Playfield(gameSettings.getPlayfieldSize(),gameSettings.getPlayfieldSize());
		// playfield.printPlayField();
		// opponentField = new
		// Playfield(gameSettings.getPlayfieldSize(),gameSettings.getPlayfieldSize());
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
	 * @param String
	 *            name
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
	 * @param ArrayList
	 *            <Ship> ships
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
	 * @param Playfield
	 *            playfield
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
	 * @param Playfield
	 *            opponentField
	 */
	public void setOpponentField(Playfield opponentField) {
		this.opponentField = opponentField;
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
	 * @param playerPlayFieldGui
	 *            SpielerGUI
	 */
	public void setPlayerPlayFieldGui(PlayerPlayfieldGui playerPlayFieldGui) {
		this.playerPlayFieldGui = playerPlayFieldGui;
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
	 * @param booelan
	 *            lost
	 */
	public void setLost(boolean lost) {
		this.lost = lost;
	}
	
	/**
	 * Gibt boolean-Wert zurück, ob Player KI ist.
	 * @return boolean isAi
	 */
	public boolean getIsAI() {
		return isAI;
	}

	/**
	 * Setzt Attribut auf einen boolean Wert
	 * @param boolean isAI
	 */
	public void setAI(boolean isAI) {
		this.isAI = isAI;
	}

	/**
	 * Erzeugt Schiffsarray
	 *
	 * @param cSettings
	 *            Spieleinstellungen
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
	 * Gibt die ArrayListe der Schiffe aus
	 */
	public void printShipList() {
		for (Ship ship : ships) {
			IO.println(ship.getName() + "\t" + ship.getNumber() + "\t" + " Größe " + "(" + ship.getSize() + ")");
		}
	}

	/**
	 * Gibt Koordinate des Schusses zurueck
	 *
	 * @return Gibt Koordinate zurueck
	 */
	public String coordinateToShoot() {
		String coordinate;
		boolean error = false;
		//IO.print("Bitte geben Sie die Koordinaten fuer das Schiessen ein:");
		do {
			coordinate = IO.readString().toLowerCase(); // Grossbuchstaben->Kleinbuchstaben
			if (coordinate.matches("^[1-9]{1}[0-9]{0,1}[a-z]{1}$")) { 
				// Teste Eingabe mit RegEx(^Anfang, 1 oder 2 Zahlen(0-9) & 1 Buchstabe (a-z), $ Ende
				error = false;
			} else {
				IO.println("Fehler");
				error = true;
			}
		} while (error);
		return coordinate;
	}

	/**
	 * 
	 * @param playerList
	 * @param opponentIndex
	 * @param shootRange
	 * @param orientation
	 * @param coordinate
	 */
	public void shootOnPlayField(ArrayList<Player> playerList, int opponentIndex, int shootRange, boolean orientation, String coordinate) {
		int[] hitShips;
		hitShips = playerList.get(opponentIndex).getPlayfield().setShot(coordinate, shootRange, orientation);
		playerList.get(opponentIndex).getOpponentField().setShot(coordinate, shootRange, orientation);
		// Prüfen ob schiffe getroffen
		for (int i = 0; i < hitShips.length; i++) {
			for (int shipIndex = 0; shipIndex < playerList.get(opponentIndex).getShips().size(); shipIndex++) {
				if (playerList.get(opponentIndex).getShips().get(shipIndex).getNumber() == hitShips[i]) {
					playerList.get(opponentIndex).getShips().get(shipIndex).setHitpoints();
				}
			}
		}
		playerList.get(opponentIndex).getOpponentField().printOpponentField();
	}

	/**
	 * Gibt Liste der Schiffe aus, die zur Verfügung stehen
	 *
	 * @param player
	 *            Playerarray
	 * @param playerN
	 *            Index des Spielers in Player-Array
	 *
	 */
	public int[] listOfAvalableShips(ArrayList<Player> playerList,
		int playerindex) {
		int[] tempShipArray;
		int arrayLength = 0;
		for (int ships = 1; ships < playerList.get(playerindex).getShips().size(); ships++) {
			if (playerList.get(playerindex).getShips().get(ships).getIsSunk() == false
					&& playerList.get(playerindex).getShips().get(ships).getCurrentReloadTime() == 0) {
				arrayLength = arrayLength++;
			}
		}
		tempShipArray = new int[arrayLength];
		for (int ships = 1; ships < tempShipArray.length; ships++) {
			if (playerList.get(playerindex).getShips().get(ships).getIsSunk() == false
					&& playerList.get(playerindex).getShips().get(ships).getCurrentReloadTime() == 0) {
				tempShipArray[ships] = playerList.get(playerindex).getShips().get(ships).getNumber();
			}
		}
		return tempShipArray;
	}

	/**
	 * Gibt Schiffindex zurück, mit dem angegriffen werden soll
	 * 
	 * @param player
	 *            Spielerarray
	 * @param playerN
	 *            Spielernummer
	 * @return shipIndex
	 */
	public int getAvailableShipToShoot(ArrayList<Player> playerList,
			int playerindex) {
		boolean error = true;
		int shipIndex;
		int[] tempShipArray;
		IO.println("Mit welchem Schiff willst du schiessen?");
		// printListOfReloadingShips(player, playerN);
		// printListOfSunkShips(player, playerN);
		// IO.println("Schiffe, die zur Verfuegung stehen");
		tempShipArray = listOfAvalableShips(playerList, playerindex);
		IO.println("Gib die Nummer des Schiffs ein: ");
		do {
			shipIndex = IO.readInt() - 1;
			for (int counter = 0; counter > tempShipArray.length; counter++) {
				if (tempShipArray[counter] == shipIndex) {
					error = false;
				}
			}
		} while (error);
		IO.println("Sie haben das Schiff mit der Nummer "
				+ playerList.get(playerindex).getShips().get(shipIndex).getNumber()
				+ " vom Typ "
				+ playerList.get(playerindex).getShips().get(shipIndex).getName() + " ausgewaehlt!");
		return shipIndex;
	}

}
