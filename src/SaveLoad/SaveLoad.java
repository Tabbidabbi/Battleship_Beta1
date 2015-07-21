package SaveLoad;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import Game.Game;

public class SaveLoad {
	
	/**
	 * Speichert das Spiel
	 * @param Game game, welches gespeichert werden soll
	 */
	public static void save(Game game){
		try{
			//Erzeugt Datei
			FileOutputStream createFile = new FileOutputStream("save.txt");
			ObjectOutputStream writeFile = new ObjectOutputStream(createFile);
			
			//Schreibt in Datei
			writeFile.writeObject(game);
			//Schliesst Datei
			writeFile.close();
		}
		catch(IOException e){
			e.printStackTrace();			
		}
	}
	
	public static Game load() {
		Game game = null;
		try{
			//Lädt Datei
			FileInputStream loadFile = new FileInputStream("save.txt");
			//Liesst Datei
			ObjectInputStream readFile = new ObjectInputStream(loadFile);
			
			try {
				//Gameobjekt aus der Datei wird dem Gameobjekt game zugeordnet
				game = (Game)readFile.readObject();
				
			} 
			catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			//Schliesst Datei
			readFile.close();			
		}
		catch(IOException e){
			e.printStackTrace();			
		}	
		//Gibt Spiel zurück
		return game;
	}

}
