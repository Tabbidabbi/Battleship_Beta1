package Gameobjects.Playfield;

import java.io.Serializable;

import IO.IO;

public class Field implements Serializable{

    /**
	 * 
	 */
	private static final long serialVersionUID = -8054963586333812399L;

	private boolean isShot;

    private boolean isWater;

    private boolean isHit;

    private boolean hasShip;

    private boolean active;

    private String playerStatus;

    private String opponentStatus;

    private String fieldNumber;

    private int shipNumber;
    
    /**
     * Konstuktor der Klasse Field
     */
    public Field() {
		this.isShot = false;
		this.isWater = true;
		this.isHit = false;
		this.hasShip = false;
                this.active = true;
		this.playerStatus = "~";
                this.opponentStatus = "~";
    }

    /**
     * Gibt zurück, ob Feld getroffen wurde
     * @return boolean isShot
     */
    public boolean getIsShot() {
        return isShot;
    }

    /**
     * Setzt, dass Feld getroffen wurde
     * @return Gibt die Schiffsnummer zurück
     */
    public int setIsShot(){
		if(this.isShot == false){
			this.isShot = true;
			if(getHasShip() == true){
				this.setPlayerStatus("X");
				this.setOpponentStatus("X");
				this.setIsHit(true);
				//IO.println("Sie haben ein Schiff getroffen!");
			}
			else{
				this.setPlayerStatus("O");
				this.setOpponentStatus("O");
				//IO.println("Sie haben auf Wasser geschossen!");
			}
		}
		else{
			IO.println("Sie haben bereits auf dieses Feld geschossen. Ein verschenkter Schuss!");
		}
		return getShipNumber();
	}

    /**
     * Gibt zurück, ob Feld Wasser ist
     * @return boolean isWater
     */
    public boolean getIsWater() {
        return isWater;
    }

    /**
     * Setzt Feld als Wasser
     * @param booelan isWater
     */
    public void setIsWater(boolean isWater) {
        this.isWater = true;
    }

    /**
     * Gibt zurück, ob Feld getroffen wurde
     * @return boolean isHit
     */
    public boolean getIsHit() {
        return isHit;
    }

    /**
     * Setzt Feld als getroffen
     * @param booelan isHit
     */
    public void setIsHit(boolean isHit) {
        this.isHit = isHit;
    }

    /**
     * Gibt zurück, ob auf dem Feld ein Schiff liegt
     * @return boolean hasShip
     */
    public boolean getHasShip() {
        return hasShip;
    }

    /**
     * Gibt zurück, ob auf dem Feld ein Schiff liegt
     * @param boolean hasShip
     */
    public void setHasShip(boolean hasShip) {
        this.hasShip = hasShip;
    }

    /**
     * Gibt zurück, ob Feld aktiv ist
     * @return boolean active
     */
    public boolean isActive() {
        return active;
    }

    /**
     * Setzt Feld aktiv
     * @param booelan active
     */
    public void setActive(boolean active) {
        this.active = active;
    }

    /**
     * Gibt Spielersicht zurück
     * @return Sting playerStatus
     */
    public String getPlayerStatus() {
        return playerStatus;
    }

    /**
     * Setzt des Spielersicht
     * @param Stiring playerStatus
     */
    public void setPlayerStatus(String playerStatus) {
        this.playerStatus = playerStatus;
    }

    /**
     * Gibt Gegnersicht zurück
     * @return Sting opponentStatus
     */
    public String getOpponentStatus() {
        return opponentStatus;
    }

    /**
     * Setzt des Gegnersicht
     * @param Stiring opponentStatus
     */
    public void setOpponentStatus(String opponentStatus) {
        this.opponentStatus = opponentStatus;
    }

    /**
     * Gibt die Feldnummer zurück
     * @return String Fieldnummer
     */
    public String getFieldNumber() {
        return fieldNumber;
    }

    /**
     * Setzt Feldnummer
     * @param String fieldNumber
     */
    public void setFieldNumber(String fieldNumber) {
        this.fieldNumber = fieldNumber;
    }

    /**
     * Gibt Schiffsnummer zurück
     * @return int shipNumber
     */
    public int getShipNumber() {
        return shipNumber;
    }

    /**
     * Setzt Schiffsnummer
     * @param int shipNumber
     */
    public void setShipNumber(int shipNumber) {
        this.shipNumber = shipNumber;
    }
    
    /**
     * Gibt Spielersicht aus
     */
    public void printPlayerPlayfield(){
		IO.print(this.playerStatus + "\t");
//		IO.printPlayerPlayfield(fieldnumber);
	}
	
    /**
     * Gibt Gegnersicht aus
     */
	public void prinOpponentPlayfield(){
		IO.print(this.opponentStatus + "\t");
	}
    
    
}
