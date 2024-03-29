package GUI.Panel;

import java.util.ArrayList;

import components.Deck;
import components.Player;
import components.Card;

public class GameState {
    private ArrayList<Player> playersList;
    private Player currPlayer;
    private Deck allDeck;
    private Deck rangeDeck;
    private int round;
    private ArrayList<Card> selectedCards;

    public GameState(ArrayList<Player> playersList, Deck allDeck, Deck rangeDeck, int round, ArrayList<Card> selectedCards){
        this.playersList = playersList;
        this.currPlayer = playersList.get(0);
        this.allDeck = allDeck;
        this.rangeDeck = rangeDeck;
        this.round = 1;
        this.selectedCards = selectedCards;
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
    @Override
    public String toString() {
        return "Current Player: " + currPlayer.toString() +
            "\nPicking State: " + isPickingState +
            "\nBetting State: " + isBettingState;
    }
    public int getRound() {
        return round;
    }
    public void setRound(int round){
        this.round = round;
    }
    public void nextRound() {
        this.round++;
    }
    public ArrayList<Card> getSelectedCards() {
        return selectedCards;
    }
    public void clearSelectedCards(){
        this.selectedCards.clear();
    }
    public void setSelectedCards(ArrayList<Card> selectedCards) {
        this.selectedCards = selectedCards;
    }
}
