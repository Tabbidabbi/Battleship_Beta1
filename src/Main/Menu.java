package Main;

import Game.Settings;
import Game.Game;
import IO.IO;
import Helper.Helper;

public class Menu {

    private int input;
    private Settings gameSettings;

    private Game game;
    private boolean error;

    public Menu() {
        gameSettings = new Settings();
    }

    public void showMainMenu() {

        IO.println("Drücke die <1> für Neues Spiel");
        IO.println("Drücke die <2> für Spiel Laden");
        IO.println("Drücke die <3> für Einstellungen");
        IO.println("Drücke die <4> für Anleitung");
        IO.println("Drücke die <5> für Spiel Verlassen");

        error = false;
        do {
            input = IO.readInt();
            String i = Integer.toString(input);
            if (i.matches("^[1-5]{1}$")) {
                switch (input) {
                    case 1:
                        game = new Game(gameSettings);
                        break;
                    case 2:
                        IO.println("Platzhalter");
                        break;
                    case 3:
                        showGameSettings();
                        break;
                    case 4:
                        showInstructions();
                        break;
                    case 5:
                        exitGame();
                        break;
                }
            } else {
                IO.println("Falsche Eingabe, bitte erneut eingeben: ");
                error = true;
            }
        } while (error);
    }

    public void showGameSettings() {
        IO.println("######   Settings   ######");
        IO.println("<1> Anzahl der Spieler ändern" + "(" + gameSettings.getAmountOfPlayer() + ")" + ".");
        IO.println("<2> Anzahl der KI-Spieler ändern" + "(" + gameSettings.getAmountOfKIPlayer() + ")" + ".");
        IO.println("<3> Anzahl der Zerstörer ändern" + "(" + gameSettings.getAmountOfDestroyer()+ ")" + ".");
        IO.println("<4> Anzahl der Fregatten ändern" + "(" + gameSettings.getAmountOfFrigate()+ ")" + ".");
        IO.println("<5> Anzahl der Korvetten ändern" + "(" + gameSettings.getAmountOfCorvette()+ ")" + ".");
        IO.println("<6> Anzahl der U-Boote ändern" + "(" + gameSettings.getAmountOfSubmarine() + ")" + ".");
        IO.println("<7> Spielfeldgröße ändern" + "(" + gameSettings.getPlayfieldSize() + ")" + ".");
        IO.println("<8> in das Hauptmenü zurückkehren");

        error = false;

        do {
            input = IO.readInt();
            String i = Integer.toString(input);
            if (i.matches("^[1-8]{1}$")) {
                switch (input) {
                    case 1:
                        input = Helper.checkUserInput("Anzahl Spieler: ", 2, 6);
                        gameSettings.setAmountOfPlayer(input);
                        showGameSettings();
                        break;
                    case 2:
                        input = Helper.checkUserInput("Anzahl KI: ", 0, 5 );
                        gameSettings.setAmountOfKIPlayer(input);
                        showGameSettings();
                        break;
                    case 3:
                       input = Helper.checkUserInput("Anzahl Zerstörer: ", 0, 5);
                        gameSettings.setAmountOfDestroyer(input);
                        showGameSettings();
                        break;
                    case 4:
                        input = Helper.checkUserInput("Anzahl Fregatten: ", 0, 5);
                        gameSettings.setAmountOfFrigate(input);
                        showGameSettings();
                        break;
                    case 5:
                        input = Helper.checkUserInput("Anzahl Korvetten: ", 0, 10);
                        gameSettings.setAmountOfCorvette(input);
                        showGameSettings();
                        break;
                    case 6:
                    input = Helper.checkUserInput("Anzahl U-Boote: ", 0, 10);
                        gameSettings.setAmountOfSubmarine(input);
                        showGameSettings();
                        break;
                    case 7:
                       input = Helper.checkUserInput("Spielfeldgröße: ", 8, 26);
                        gameSettings.setPlayfieldSize(input);
                        showGameSettings();
                        break;
                    case 8:
                        showMainMenu();
                        break;
                }
            } else {
                IO.println("Falsche Eingabe, bitte erneut eingeben: ");
                error = true;
            }

        } while (error);
    }

    public void showInstructions() {
        IO.println("PLATZHALTER");
        IO.println("Drücke eine Taste um fortzufahren: ");
        IO.readString();
        showMainMenu();

    }

    public void loadGame() {

    }

    public void exitGame() {
        System.exit(0);

    }

}
