/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gameobjects.Player;

import java.util.ArrayList;

/**
 *
 * Methoden werden für den AiPlayer benötigt.
 */
public interface PlayerInterface {
    public int getAiLastHitOpponentIndex();
    public void setAiLastHitOpponentIndex(int aiLastHitOpponentIndex);
    public String getAiLastHitCoordinate();
    public void setAiLastHitCoordinate(String aiLastHitCoordinate);
    public int getAiOpponent(ArrayList<Player> playerList, int playerIndex);
    public int getRandomShip(ArrayList<Player> playerList, int playerIndex);
    public boolean getAiOrientation();
    public int getAiCount(ArrayList<Player> playerList, int playerIndex);
    public String getAiShootCoordinate(ArrayList<Player> playerList, int opponentIndex, String lastHitCoordinate);
    public String getRandomCoordinate(ArrayList<Player> playerList, int opponentIndex);
    public int[] splitCoordinate(String stringCoordinate);
    public String aiShootOnPlayField(ArrayList<Player> playerList, int aiOpponentIndex, int shootRange, boolean orientation, String coordinate);
    
}
