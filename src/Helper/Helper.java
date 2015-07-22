/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Helper;

import java.util.ArrayList;

import IO.IO;
import Gameobjects.Player.Player;

/**
 * 
 * @author Tobias
 */
public class Helper {

	private static int input;

	/**
	 * 
	 * @param message Hint
	 * @param min Minimum Value
	 * @param max Maximum Value
	 * @return
	 */
	public static int checkUserInput(String message, int min, int max) {
		boolean error = false;
		do {
			IO.println(message);
			input = IO.readInt();
			if (input < min || input > max) {
				IO.println("Eingabe, außerhalb des gültigen Bereiches (" + min + "-" + max + ")");
				error = true;
			} else {
				error = false;
			}
		} while (error);
		return input;
	}

	public static int checkUserInput(int min, int max) {
		boolean error = false;
		do {
			input = IO.readInt();
			if (input < min || input > max) {
				IO.println("Eingabe, außerhalb des gültigen Bereiches (" + min
						+ "-" + max + ")");
				error = true;
			} else {
				error = false;

			}
		} while (error);
		return input;
	}

	/**
	 * Setzt die Ausrichtung des Schiffes
	 *
	 * @return Integerwert fuer die Richtung
	 */
	public static boolean checkOrientation() {
		boolean orientation = false;
		boolean error = false;
		do {
			IO.print("Bitte geben Sie die Ausrichtung des Schiffes an (h = horizontal, v = vertical: ");
			String o = IO.readString();
			if (o.equals("h")) {
				error = false;
				orientation = true;
			} else if (o.equals("v")) {
				error = false;
				orientation = false;
			} else {
				IO.println("Falsche Eingabe, bitte wiederholen Sie die Eingabe!");
				error = true;
			}
		} while (error);

		return orientation;
	}

	/**
	 * Gibt Summe der noch im Spiel befindenen Spieler zurueck
	 * 
	 * @param player Spielerarray
	 * @return Summe der noch im Spiel befindenen Spieler
	 */
	public static int checkAmountOfAvailablePlayers(ArrayList<Player> playerList) {
		int result = 0;
		for (int i = 0; i < playerList.size(); i++) {
			if (playerList.get(i).getisLost() == false) {
				result++;
			}
		}
		return result;

	}

	/**
	 * Gibt Schiffindex zurück, mit dem angegriffen werden soll
	 * 
	 * @param player Spielerarray
	 * @param playerN Spielernummer
	 * @return shipIndex
	 */
	public static int getAvailableOpponentsToShoot(
			ArrayList<Player> playerList, int playerindex) {
		boolean error = true;
		int opponentIndex;
		int[] tempOpponentpArray;
		IO.println("Auf welchen Gegner willst du schiessen?");
		tempOpponentpArray = listOfAvalableOpponents(playerList, playerindex);
		IO.println("Gib die Nummer des Schiffs ein: ");
		do {
			opponentIndex = IO.readInt() - 1;
			for (int counter = 0; counter > tempOpponentpArray.length; counter++) {
				if (tempOpponentpArray[counter] == opponentIndex) {
					error = false;
				}
			}
		} while (error);
		IO.println("Sie haben das Schiff mit der Nummer "
				+ playerList.get(playerindex).getShips().get(opponentIndex)
						.getNumber()
				+ " vom Typ "
				+ playerList.get(playerindex).getShips().get(opponentIndex)
						.getName() + " ausgewaehlt!");
		return opponentIndex;
	}

	/**
	 * Gibt Liste der Gegner aus, die zur Verfügung stehen
	 *
	 * @param player Playerarray
	 * @param playerN Index des Spielers in Player-Array
	 */
	public static int[] listOfAvalableOpponents(ArrayList<Player> playerList,
			int playerindex) {
		int[] tempOpponentArray;
		int arrayLength = 0;
		for (int opponents = 1; opponents < playerList.size(); opponents++) {
			if (playerList.get(opponents).getNumber() == playerList.get(playerindex).getNumber()
					&& playerList.get(opponents).getisLost() == false) {
				arrayLength = arrayLength++;
			}
		}
		tempOpponentArray = new int[arrayLength];
		for (int opponents = 1; opponents < playerList.size(); opponents++) {
			if (playerList.get(opponents).getNumber() == playerList.get(playerindex).getNumber()
					&& playerList.get(opponents).getisLost() == false) {
				tempOpponentArray[opponents] = playerList.get(opponents).getNumber();
			}
		}
		return tempOpponentArray;
	}

	/**
	 * Prüft, ob Spieler mindestens ein Schiff hat.
	 * 
	 * @param player Spielerarray
	 * @param opponent Nummer des gegnerischen Spielers
	 * @return Booleanwert, ob Schiff vorhanden ist
	 */
	public static boolean checkIfShipAvailable(ArrayList<Player> playerList,
			int opponentIndex) {
		boolean result = false;
		for (int i = 0; i < playerList.get(opponentIndex).getShips().size(); i++) {
			if (playerList.get(opponentIndex).getShips().get(i).getIsSunk() == false) {
				result = true;
			}
		}
		return result;
	}

	/**
	 * Gibt Gewinner aus
	 *
	 * @param playerList Spielerliste
	 */
	public static void printWinner(ArrayList<Player> playerList) {
		for (int i = 0; i < playerList.size(); i++) {
			if (playerList.get(i).getisLost() == false) {
				IO.println("Spieler " + playerList.get(i).getName()	+ " hat gewonnen!");
			}
		}
	}

}
