package Gameobjects.Playfield;


import java.io.Serializable;

import Game.Settings;
import Gameobjects.Playfield.Field;
import IO.IO;

public class Playfield implements Serializable{

    /**
	 * 
	 */
	private static final long serialVersionUID = -5068259163966030799L;

	private Field[][] fieldMatrix;

    private String fieldNumber;

   private char charStorage;

    private final String alphabet = "#abcdefghijklmnopqrstuvwxyz";

    private int counterI = 0;

    private int counterJ;

    private String convertInString;
    
    /**
     * Konstruktor für Playfield
     * @param int xCoordinate
     * @param int yCoordinate
     */
    public Playfield(int xCoordinate, int yCoordinate) {
        createPlayfield(xCoordinate,yCoordinate);
    }
       
    /**
     * Erstellt das Spielfeld
     * @param int x
     * @param int y
     */
    private void createPlayfield(int x,int y) {
        this.fieldMatrix = new Field[x + 1][y +1];
//        Hier wird jeder Zeile ein Buchstabe aus dem "alphabet" String hinzugefügt bis die Länge des Arrays erreicht wurde.
        for (int i = 0; i < fieldMatrix.length; i++) {
            if (i >= 1 && counterI <= fieldMatrix.length) {
                convertInString = Integer.toString(counterI);//in der Variable wird der vorher in String konvertierte int gespeichert.
            }
            counterJ = 0;//Zähler für die Spalten wird resetet
//        Hier wird jeder Spalte eine Zahl  hinzugefügt bis die Länge des Arrays erreicht wurde
            for (int j = 0; j < fieldMatrix[i].length ; j++) {
                if (j > 0 && i > 0) {
                    charStorage = alphabet.charAt(counterJ);//In Letter wird pro durchlauf je 1 char gespeichert welcher im alphabet String steht.
                }
                counterJ++;
                fieldNumber = Integer.toString(counterI) + charStorage; // Die Nummer des Feldes wird zusammengebaut
                fieldMatrix[i][j] = new Field();
                fieldMatrix[i][j].setFieldNumber(fieldNumber);
            }
            counterI++;//Zähler für die characters

        }
        
    }
    
    /**
     * Setzt Schuss aufs Spielfeld
     * @param String coordinate
     * @param int shootRange
     * @param boolean orientation
     * @return hitShips int-Array mit Anzahl der getroffenen Schiffe
     */
    public int[] setShot(String coordinate, int shootRange, boolean orientation) {
        //Array, in dem  die getroffenen Schiffe stehen
        int[] hitShips = new int[shootRange];
        if (orientation == true) {
            for (int y = 0; y < getFieldMatrix().length; y++) {
                for (int x = 0; x < getFieldMatrix()[y].length; x++) {
                    if (coordinate.equals(getFieldMatrix()[y][x].getFieldNumber())) {
                        for (int i = 0; i < shootRange; i++) {
                        	try{
                        		hitShips[i] = this.fieldMatrix[y][x + i].setIsShot();
                        	}
                        	catch(IndexOutOfBoundsException e){
                        		e.printStackTrace();
                        	}
                        }
                    }
                }
            }
        } else {
            for (int y = 0; y < getFieldMatrix().length; y++) {
                for (int x = 0; x < getFieldMatrix()[y].length; x++) {
                    if (coordinate.equals(getFieldMatrix()[y][x].getFieldNumber())) {
                        for (int i = 0; i < shootRange; i++) {
                        	try{
                        		hitShips[i] = this.fieldMatrix[y + i][x].setIsShot();
                        	}
                            catch(IndexOutOfBoundsException e){
                            	e.printStackTrace();
                            }
                        }
                    }
                }
            }
        }
        return hitShips;
    }
    
    /**
     * Gibt das Spielfeld aus der Spielersicht aus
     */
    public void printPlayField() {
        for (int i = 0; i < fieldMatrix.length; i++) {
            //Das Array wird auf der Position [0][0] vertikal mit Zahlen gefüllt2
            if (i + 1 < fieldMatrix.length) {
                fieldMatrix[i + 1][0].setPlayerStatus(Integer.toString(i + 1));
                fieldMatrix[i][0].setActive(false);
            }
            for (int j = 0; j < fieldMatrix[i].length; j++) {
                //Das Array wird auf der Position [0][0] horizontal mit Buchstaben gefüllt
                if (j < fieldMatrix[i].length) {
                    fieldMatrix[0][j].setPlayerStatus(Character.toString(alphabet.charAt(j)));
                    fieldMatrix[0][j].setActive(false);
                }
                fieldMatrix[i][j].printPlayerPlayfield();
            }
            IO.println("");
        }
        IO.println("");
    }

    /**
     * Gibt das Spielfeld aus der Gegnersicht aus
     */
    public void printOpponentField() {
        for (int i = 0; i < fieldMatrix.length; i++) {
            if (i + 1 < fieldMatrix.length) {
                fieldMatrix[i + 1][0].setOpponentStatus(Integer.toString(i + 1));
                fieldMatrix[i][0].setActive(false);
            }
            for (int j = 0; j < fieldMatrix[i].length; j++) {
                if (j < fieldMatrix[i].length) {
                    fieldMatrix[0][j].setOpponentStatus(Character.toString(alphabet.charAt(j)));
                    fieldMatrix[0][j].setActive(false);
                }
                fieldMatrix[i][j].prinOpponentPlayfield();
            }
            IO.println("");
        }
        IO.println("");
    }

    /**
     * Gibt Feldnummer zurück
     * @return String fieldNumber
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
     * Gibt Spielfeld zurück
     * @return Field[][] fieldMatrix
     */
    public Field[][] getFieldMatrix() {
        return fieldMatrix;
    }

    /**
     * Setzt Spielfeld
     * @param Field[][] fieldMatrix
     */
    public void setFieldMatrix(Field[][] fieldMatrix) {
        this.fieldMatrix = fieldMatrix;
    }
}

