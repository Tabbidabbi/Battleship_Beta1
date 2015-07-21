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

	static int input;

	/**
	 * 
	 * @param message
	 *            Hint
	 * @param min
	 *            Minimum Value
	 * @param max
	 *            Maximum Value
	 * @return
	 */
        
        
	public static int checkUserInput(String message, int min, int max) {
		boolean error = false;
		do {
			IO.println(message);
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
	 * Wählt eine zufällige Koorindate auf dem Spielfeld aus
	 * 
	 * @param playerList
	 *            ArrayList vom Typ Player
	 * @param playerNumber
	 *            Index des aktuellen Spielers
	 * @return coordinate Koordinate vom Type String
	 */
	public static String aiChooseCoordinate(ArrayList<Player> playerList, int playerNumber) {
		boolean error = false;
		String coordinateInput = null;
		do {

			int Coordinate = getAiYCoordinate(playerList, playerNumber);
			String yCoordinate = Integer.toString(Coordinate);
			String xCoordinate = getAiXCoordinate(playerList, playerNumber);
			coordinateInput = yCoordinate + xCoordinate;
                        error = false;
			// Prueft, ob Koordinate getroffen
			for (int i = 0; i < playerList.get(playerNumber).getPlayfield().getFieldMatrix().length; i++) {
				for (int j = 0; j < playerList.get(playerNumber).getPlayfield().getFieldMatrix()[i].length; j++) {
					if (coordinateInput == playerList.get(playerNumber).getPlayfield().getFieldMatrix()[i][j].getFieldNumber()
							&& playerList.get(playerNumber).getPlayfield().getFieldMatrix()[i][j].getIsHit() == true) {
						error = true;
					}
				}
			}
		} while (error);
		// Koordinate

		// R�ckgabewert Koordinate
		return coordinateInput;
	}
	
//	public static String aiChooseCoordinate(ArrayList<Player> playerList, int playerNumber, String lastHitCoordinate){
//    	//Koordinate
//    	String coordinateInput = null;
//    	String newY, newX;
//    	
//    	if(lastHitCoordinate != null){
//    		//Um die Koordinate rum schiessen
//    		
//    		//Findet x und y heraus fuer die lastHitCoordinate
//    		for (int y = 0; y < playerList.get(playerNumber).getPlayfield().getFieldMatrix().length; y++) {
//                for (int x = 0; x < playerList.get(playerNumber).getPlayfield().getFieldMatrix()[y].length; x++) {
//                    if (lastHitCoordinate.equals(playerList.get(playerNumber).getPlayfield().getFieldMatrix()[y][x].getFieldNumber())) {
//                        	newY = Integer.toString(y);
//                        	newX = Integer.toString(x);
//                        }
//                    }
//                }
//    		
//    	}
//    	else{
//        	coordinateInput = aiChooseCoordinate(playerList, playerNumber);
//    	}
//    	//R�ckgabewert Koordinate
//    	return coordinateInput;
//    }

	public static int getAiYCoordinate(
			ArrayList<Gameobjects.Player.Player> playerList, int playerNumber) {

		// Range der Zufallszahlen
		int pool = playerList.get(playerNumber).getPlayfield().getFieldMatrix().length - 1;
		// Zufallszahl zwischen
		return (int) (Math.random() * pool) + 1;
	}

	public static String getAiXCoordinate(
			ArrayList<Gameobjects.Player.Player> playerList, int playerNumber) {
		// char 97-122 = abc.....
		String coordinate;
		int randomNumber = 0;
		int endOfRange = playerList.get(playerNumber).getPlayfield()
				.getFieldMatrix().length + 96;
		while (randomNumber < 97) {
			randomNumber = (int) (Math.random() * endOfRange);
		}
		char letter = (char) randomNumber;
		coordinate = Character.toString(letter);
		return coordinate;
	}
	
	/**
	 * Berechnet eine zufällige Orientierung
	 * @return orientation
	 */
	public static boolean chooseAiOrientation() {
		boolean orientation = false;
		int orient = (int) (Math.random() * 2) + 1;
		if (orient == 1) {
			orientation = true;
		} else {
			orientation = false;
		}
		return orientation;
	}
	
	/**
     * Gibt Summe der noch im Spiel befindenen Spieler zurueck
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
     * Gibt eine belibige Schiffsindex zurück
     * @param playerList
     * @param playerNumber
     * @return Schiffsindex
     */
    public static int getRandomShip(ArrayList<Player> playerList, int playerIndex){
    	int randomShipNumber; 
    	boolean error = false;
        do {
        	randomShipNumber = (int)(Math.random() * playerList.get(playerIndex).getShips().size());
            
            if (playerList.get(playerIndex).getShips().get(randomShipNumber).getIsSunk() == false && playerList.get(playerIndex).getShips().get(randomShipNumber).getCurrentReloadTime() == 0) {
                error = false;
            }
            else{
            	error = true;
            }
        } while (error);    	    	
    	return randomShipNumber;
    }
    
    /**
    * Gibt Schiffindex zurück, mit dem angegriffen werden soll
    * @param player Spielerarray
    * @param playerN Spielernummer
    * @return shipIndex6
    */
   public static int getAvailableShipToShoot(ArrayList<Player> playerList, int playerindex) {
	   boolean error = true;
       int shipIndex;
       int[] tempShipArray;
       IO.println("Mit welchem Schiff willst du schiessen?");
       //printListOfReloadingShips(player, playerN);
       //printListOfSunkShips(player, playerN);
       //IO.println("Schiffe, die zur Verfuegung stehen");
       tempShipArray = listOfAvalableShips(playerList, playerindex);
       IO.println("Gib die Nummer des Schiffs ein: ");
       do{
    	   shipIndex = IO.readInt() - 1;
    	   for(int counter = 0; counter > tempShipArray.length; counter++){
    		   if(tempShipArray[counter] == shipIndex){
    			   error = false;
    		   }
    	   }
       }while(error);
       IO.println("Sie haben das Schiff mit der Nummer " + playerList.get(playerindex).getShips().get(shipIndex).getNumber() + " vom Typ " + playerList.get(playerindex).getShips().get(shipIndex).getName() + " ausgewaehlt!");
       return shipIndex;
   } 
   
   /**
    * Gibt Liste der Schiffe aus, die zur Verf�gung stehen
    *
    * @param player Playerarray
    * @param playerN Index des Spielers in Player-Array
    *
    */
   public static int[] listOfAvalableShips(ArrayList<Player> playerList, int playerindex) {
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
    * @param player Spielerarray
    * @param playerN Spielernummer
    * @return shipIndex
    */
   public static int getAvailableOpponentsToShoot(ArrayList<Player> playerList, int playerindex) {
	   boolean error = true;
       int opponentIndex;
       int[] tempOpponentpArray;
       IO.println("Auf welchen Gegner willst du schiessen?");
       tempOpponentpArray = listOfAvalableOpponents(playerList, playerindex);
       IO.println("Gib die Nummer des Schiffs ein: ");
       do{
    	   opponentIndex = IO.readInt() - 1;
    	   for(int counter = 0; counter > tempOpponentpArray.length; counter++){
    		   if(tempOpponentpArray[counter] == opponentIndex){
    			   error = false;
    		   }
    	   }
       }while(error);
       IO.println("Sie haben das Schiff mit der Nummer " + playerList.get(playerindex).getShips().get(opponentIndex).getNumber() + " vom Typ " + playerList.get(playerindex).getShips().get(opponentIndex).getName() + " ausgewaehlt!");
       return opponentIndex;
   } 
   
   /**
    * Gibt Liste der Gegner aus, die zur Verfügung stehen
    *
    * @param player Playerarray
    * @param playerN Index des Spielers in Player-Array
    *
    */
   public static int[] listOfAvalableOpponents(ArrayList<Player> playerList, int playerindex) {
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
     * Gibt einen Gegner-Index zurück
     * @param playerList ArrayList vom Typ Player
     * @return aiOpponent Zufällig berechneten Index
     */
    public static int chooseAiOpponent(ArrayList<Player> playerList, int playerIndex) {
    	int aiOpponentIndex; 
    	boolean error = false;
        do {
        	aiOpponentIndex = (int)(Math.random() * playerList.size());
            
            if (playerIndex != aiOpponentIndex && playerList.get(aiOpponentIndex).getisLost() == false) {
                error = false;
            }
            else{
            	error = true;
            }
        } while (error);
		return aiOpponentIndex;
	}
    
    /**
     * Schussfunktion
     * @param playerList ArrayList vom Typ Player
     * @param aiOpponentIndex Gegner-Index
     * @param shootRange Schussreichweite
     * @param orientation Schussrichtung
     * @param coordinate Koordinate, die beschossen werden soll
     * @return lastHitCoordinate
     */
    public static String aiShootOnPlayField(ArrayList<Player> playerList, int aiOpponentIndex, int shootRange, boolean orientation, String coordinate) {
        int[] hitShips;
        String hitCoordinate = null;

        //Felder des gegnerischen Spielers werden auf abgeschossen gesetzt
        //hitShips = player[opponent].getField().setShot(x, y, shootRange, orientation);
        //player[opponent].getOpponentField().setShot(x, y, shootRange, orientation);
        hitShips = playerList.get(aiOpponentIndex).getPlayfield().setShot(coordinate, shootRange, orientation);
        playerList.get(aiOpponentIndex).getOpponentField().setShot(coordinate, shootRange, orientation);

        //Pruefen ob schiffe getroffen
        for (int i = 0; i < hitShips.length; i++) {
            for (int shipIndex = 0; shipIndex < playerList.get(aiOpponentIndex).getShips().size(); shipIndex++) {
                if (playerList.get(aiOpponentIndex).getShips().get(shipIndex).getNumber() == hitShips[i]) {
                	playerList.get(aiOpponentIndex).getShips().get(shipIndex).setHitpoints();
                }
            }

        }
        if(coordinate.equals(playerList.get(aiOpponentIndex).getPlayfield().getFieldNumber())){
        	hitCoordinate = coordinate;
        }
      //Gibt das Feld des Gegner aus
        IO.println("Spielfeld vom Gegner: " + playerList.get(aiOpponentIndex).getName());
        playerList.get(aiOpponentIndex).getOpponentField().printOpponentField();
        return hitCoordinate;
    }
    
    /**
     * Prüft, ob Spieler mindestens ein Schiff hat.
     * @param player Spielerarray
     * @param opponent Nummer des gegnerischen Spielers
     * @return Booleanwert, ob Schiff vorhanden ist
     */
    public static boolean checkIfShipAvailable(ArrayList<Player> playerList, int opponentIndex) {
        boolean result = false;
        for (int i = 0; i < playerList.get(opponentIndex).getShips().size(); i++) {
            if (playerList.get(opponentIndex).getShips().get(i).getIsSunk() == false) {
                result = true;
            }
        }
        return result;
    }
    
    /**
     * Gibt Koordinate des Schusses zurueck
     *
     * @return Gibt Koordinate zurueck
     */
    public static String coordinateToShoot() {
        String coordinate;
        boolean error = false;
        IO.print("Bitte geben Sie die Koordinaten fuer das Schiessen ein:");
        do {
            coordinate = IO.readString().toLowerCase(); //Grossbuchstaben-> Kleinbuchstaben
            if (coordinate.matches("^[1-9]{1}[0-9]{0,1}[a-z]{1}$")) { //Teste Eingabe mit RegEx(^ Anfang, 1 oder 2 Zahlen(0-9) & 1 Buchstabe (a-z), $ Ende
                error = false;
            } else {
                IO.println("Fehler");
                error = true;
            }
        } while (error);
        return coordinate;
    }
    
    public static void shootOnPlayField(ArrayList <Player> playerList, int opponentIndex, int shootRange, boolean orientation, String coordinate) {
        int[] hitShips;

        //Felder des gegnerischen Spielers werden auf abgeschossen gesetzt
        //hitShips = player[opponent].getField().setShot(x, y, shootRange, orientation);
        //player[opponent].getOpponentField().setShot(x, y, shootRange, orientation);
        hitShips = playerList.get(opponentIndex).getPlayfield().setShot(coordinate, shootRange, orientation);
        playerList.get(opponentIndex).getOpponentField().setShot(coordinate, shootRange, orientation);

        //Pruefen ob schiffe getroffen
        for (int i = 0; i < hitShips.length; i++) {
            for (int shipIndex = 0; shipIndex < playerList.get(opponentIndex).getShips().size(); shipIndex++) {
                if (playerList.get(opponentIndex).getShips().get(shipIndex).getNumber() == hitShips[i]) {
                	playerList.get(opponentIndex).getShips().get(shipIndex).setHitpoints();
                }
            }

        }

        //Gibt das Feld des Gegner aus
        playerList.get(opponentIndex).getOpponentField().printOpponentField();
    }
    
    /**
     * Gibt Gewinner aus
     *
     * @param playerList Spielerliste
     */
    public static void printWinner(ArrayList<Player> playerList) {
        for (int i = 0; i < playerList.size(); i++) {
            if (playerList.get(i).getisLost() == false) {
                IO.println("Spieler " + playerList.get(i).getName() + " hat gewonnen!");
            }
        }
    }
    
}
