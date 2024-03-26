package GUI.Panel;

import java.util.ArrayList;

import components.Deck;
import components.Player;

public class GameState {
    private ArrayList<Player> playersList;
    private Player currPlayer;
    private Deck allDeck;
    private Deck rangeDeck;
    private boolean isPickingState;
    private boolean isBettingState;

    public GameState(ArrayList<Player> playersList, Deck allDeck, Deck rangeDeck){
        this.playersList = playersList;
        this.currPlayer = playersList.get(0);
        this.allDeck = allDeck;
        this.rangeDeck = rangeDeck;
        this.isPickingState = true;
        this.isBettingState = false;
    }
    public ArrayList<Player> getPlayersList() {
        return playersList;
    }
    public void setPlayersList(ArrayList<Player> playersList) {
        this.playersList = playersList;
    }
    public void moveToNextPlayer() {
        int currentIndex = playersList.indexOf(currPlayer);
        int nextIndex = (currentIndex + 1) % playersList.size();
        currPlayer = playersList.get(nextIndex);
    }
    public Player getCurrPlayer() {
        return currPlayer;
    }
    public void setCurrPlayer(Player currPlayer) {
        this.currPlayer = currPlayer;
    }
    public Deck getAllDeck() {
        return allDeck;
    }
    public void setAllDeck(Deck allDeck) {
        this.allDeck = allDeck;
    }
    public Deck getRangeDeck() {
        return rangeDeck;
    }
    public void setRangeDeck(Deck rangeDeck) {
        this.rangeDeck = rangeDeck;
    }
    public boolean isBettingState() {
        return isBettingState;
    }
    public void setBettingState(boolean isBettingState) {
        this.isBettingState = isBettingState;
    }
    public boolean isPickingState() {
        return isPickingState;
    }
    public void setPickingState(boolean isPickingState) {
        this.isPickingState = isPickingState;
    }
    @Override
    public String toString() {
        return "Current Player: " + currPlayer.toString() +
            "\nPicking State: " + isPickingState +
            "\nBetting State: " + isBettingState;
    }
}
