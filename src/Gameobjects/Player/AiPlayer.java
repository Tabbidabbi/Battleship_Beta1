package Gameobjects.Player;

import java.io.Serializable;
import java.util.ArrayList;
import Game.Settings;

public class AiPlayer extends Player implements Serializable {

	private static final long serialVersionUID = -2046818718759120112L;
	private int aiLastHitOpponentIndex;
	private String aiLastHitCoordinate;

	public AiPlayer(int number, String name, Settings gameSettings) {
		super(number, name, gameSettings);
		setAI(true);
		setAiLastHitCoordinate(null);
	}
	    
	/**
	 * Gibt letzten getroffenen Gegner zurück
	 * @return int lastHitOpponentNumber
	 */
	public int getaiLastHitOpponentIndex() {
		return aiLastHitOpponentIndex;
	}

	/**
	 * Setzt letzten getroffenen Gegner
	 * 
	 * @param int lastHitOpponentNumber
	 */
	public void setAiLastHitOpponentIndex(int aiLastHitOpponentIndex) {
		this.aiLastHitOpponentIndex = aiLastHitOpponentIndex;
	}

	/**
	 * Gibt letzte getroffene Koordinate zurück
	 * @return String aiLastHitCoordinate
	 */
	public String getAiLastHitCoordinate() {
		return aiLastHitCoordinate;
	}

	/**
	 * Setzt letzte getroffene Koordinate
	 * @param String aiLastHitCoordinate
	 */
	public void setAiLastHitCoordinate(String aiLastHitCoordinate) {
		this.aiLastHitCoordinate = aiLastHitCoordinate;
	}

	/**
     * Gibt einen Gegner-Index zurück
     * @param playerList ArrayList vom Typ Player
     * @return aiOpponent Zufällig berechneten Index
     */
    public int getAiOpponent(ArrayList<Player> playerList, int playerIndex) {
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
     * Gibt eine belibige Schiffsindex zurück
     * @param playerList
     * @param playerIndex, der an der Reihe ist.
     * @return Schiffsindex
     */
    public int getRandomShip(ArrayList<Player> playerList, int playerIndex){
    	int randomShipIndex; 
    	boolean error = false;
        do {
        	randomShipIndex = (int)(Math.random() * playerList.get(playerIndex).getShips().size());
            if (playerList.get(playerIndex).getShips().get(randomShipIndex).getIsSunk() == false && playerList.get(playerIndex).getShips().get(randomShipIndex).getCurrentReloadTime() == 0) {
                error = false;
            }
            else{
            	error = true;
            }
        } while (error);    	    	
    	return randomShipIndex;
    }
    
    /**
	 * Berechnet eine zufällige Orientierung
	 * @return boolean orientation
	 */
	public boolean getAiOrientation() {
		boolean orientation = false;
		int orient = (int) (Math.random() * 2);
		if (orient == 1) {
			orientation = true;
		} else {
			orientation = false;
		}
		return orientation;
	}
	
    /**
	 * Wählt eine zufällige Integer-Koordinate
	 * int pool ist die Menge an Ganzzahlen, die gewählt werden dürfen.
	 * @param ArrayList<Player>playerList
	 * @param int playerIndex
	 * @return int Integer-Coordinate
	 */
	public int getAiIntCoordinate(ArrayList<Player> playerList, int playerIndex) {
		int pool = playerList.get(playerIndex).getPlayfield().getFieldMatrix().length - 1;
		return (int) (Math.random() * pool) + 1;
	}

	/**
	 * Wählt eine zufällige Koorindate auf dem Spielfeld aus
	 * Benötigt für das Setzen von Schiffen und zum Schiessen
	 * 
	 * @param playerList ArrayList vom Typ Player
	 * @param playerIndex Index des aktuellen Spielers
	 * @return coordinate Koordinate vom Type String
	 */
	public String getAiShootCoordinate(ArrayList<Player> playerList, int opponentIndex, String lastHitCoordinate) {
		boolean error = false;
		String aiCoordinate = null;
		if(lastHitCoordinate == null){
			do {
				int yCoordinate = getAiIntCoordinate(playerList, opponentIndex);
				int xCoordinate = getAiIntCoordinate(playerList, opponentIndex);
				aiCoordinate = Integer.toString(yCoordinate) + "#" + Integer.toString(xCoordinate);
				error = false;
				if (playerList.get(opponentIndex).getPlayfield().getFieldMatrix()[yCoordinate][xCoordinate].getIsHit() == true) {
					error = true;
				}
			} while (error);	
		}
		else{
			int[] lastHitCoordinateArray = splitCoordinate(lastHitCoordinate);
			int yCoordinate = lastHitCoordinateArray[0];
			int xCoordinate = lastHitCoordinateArray[1];
			int range = playerList.get(opponentIndex).getPlayfield().getFieldMatrix().length - 1;
			error = true;
			do{
				//Randomwert aus der Menge {0, 1, 2, 3}
				int choice = (int) (Math.random() * 4);
				//Schuss auf die umliegenden Felder
				switch(choice){ 
		        case 0: 
		        	//Entpricht oben
		        	if(yCoordinate - 1 <= range && yCoordinate - 1 > 0){
		        		if(playerList.get(opponentIndex).getPlayfield().getFieldMatrix()[yCoordinate - 1][xCoordinate].getIsShot() == false){
		        			aiCoordinate = Integer.toString(yCoordinate - 1) + "#" + Integer.toString(xCoordinate);
		        			error = false;
		        		}
		        	}
		            break; 
		        case 1: 
		        	//Entspricht rechts
		        	if(xCoordinate + 1 <= range && xCoordinate + 1 > 0){
		        		if(playerList.get(opponentIndex).getPlayfield().getFieldMatrix()[yCoordinate][xCoordinate + 1].getIsShot() == false){
		        			aiCoordinate = Integer.toString(yCoordinate) + "#" + Integer.toString(xCoordinate + 1);
		        			error = false;
		        		}
		        	}
		            break; 
		        case 2:
		        	//Entspricht unten
		        	if(yCoordinate + 1 <= range && yCoordinate + 1 > 0){
		        		if(playerList.get(opponentIndex).getPlayfield().getFieldMatrix()[yCoordinate + 1][xCoordinate].getIsShot() == false){
		        			aiCoordinate = Integer.toString(yCoordinate + 1) + "#" + Integer.toString(xCoordinate); 
		        			error = false;
		        		}
		        	}
		            break; 
		        case 3:
		        	//Entspricht links
		        	if(xCoordinate - 1 <= range && xCoordinate - 1 > 0){
		        		if(playerList.get(opponentIndex).getPlayfield().getFieldMatrix()[yCoordinate][xCoordinate - 1].getIsShot() == false){
			        		aiCoordinate = Integer.toString(yCoordinate) + "#" + Integer.toString(xCoordinate - 1);  
			        		error = false;
		        		}
		        	} 
		            break;	
				}
			}while(error);
		}
		return aiCoordinate;
	}
	
	/**
	 * Methode convertiert einen Kordinaten-String in ein int-Array
	 * @param stringCoordinate Koordinate mit Typ String
	 * @return int[] intCoordinates 
	 */
	public int[] splitCoordinate(String stringCoordinate){
		int[] intCoordinates = new int[2];
		String[] splitted = stringCoordinate.split("\\#");
		intCoordinates[0] = Integer.parseInt(splitted[0]);
		intCoordinates[1] = Integer.parseInt(splitted[1]);
		return intCoordinates;
	}
	
	/**
     * Schussfunktion
     * @param playerList ArrayList vom Typ Player
     * @param aiOpponentIndex Gegner-Index
     * @param shootRange Schussreichweite
     * @param orientation Schussrichtung
     * @param coordinate Koordinate, die beschossen werden soll
     * @return hitCoordinate Coordinate mit Treffer
     */
    public String aiShootOnPlayField(ArrayList<Player> playerList, int aiOpponentIndex, int shootRange, boolean orientation, String coordinate) {
        int[] hitShips;
        int yCoordinate = 0;
        int xCoordinate = 0;
        String hitCoordinate = null;
        if(coordinate != null){
        	int[] tempIntCoordinates = splitCoordinate(coordinate);
        	yCoordinate = tempIntCoordinates[0];
        	xCoordinate = tempIntCoordinates[1];
        }
        else{
        	coordinate = getAiShootCoordinate(playerList, aiOpponentIndex, coordinate);
        }
        hitShips = playerList.get(aiOpponentIndex).getPlayfield().setShot(coordinate, shootRange, orientation);
        playerList.get(aiOpponentIndex).getOpponentField().setShot(coordinate, shootRange, orientation);
        //Prüft, ob schiffe getroffen wurden und setzt Hitpoints
        for (int i = 0; i < hitShips.length; i++) {
            for (int shipIndex = 0; shipIndex < playerList.get(aiOpponentIndex).getShips().size(); shipIndex++) {
                if (playerList.get(aiOpponentIndex).getShips().get(shipIndex).getNumber() == hitShips[i]) {
                	playerList.get(aiOpponentIndex).getShips().get(shipIndex).setHitpoints();
                }
            }
        }
        if(playerList.get(aiOpponentIndex).getPlayfield().getFieldMatrix()[yCoordinate][xCoordinate].getHasShip() == true){
        	hitCoordinate = coordinate;
        	setAiLastHitOpponentIndex(aiOpponentIndex);
        }
        
        return hitCoordinate;
    }
}
