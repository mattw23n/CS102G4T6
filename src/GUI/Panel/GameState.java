package GUI.Panel;
/*
 * GameState.java
 * 
 * GameState is a custom Object containing the details after each change in the game.
 * It makes for easier data retrieval and is in line with SRP.
 * 
 */


import java.util.ArrayList;

import components.Card;
import components.Deck;
import components.Player;

public class GameState {
    private ArrayList<Player> playersList;
    private ArrayList<Integer> playerScores;
    private Player currPlayer;
    private Deck allDeck;
    private Deck rangeDeck;
    private int round;
    private ArrayList<Card> selectedCards;
    private boolean finishTurn;

    public GameState(ArrayList<Player> playersList, Deck allDeck, Deck rangeDeck, int round, ArrayList<Card> selectedCards, boolean finishTurn){
        this.playersList = playersList;
        this.currPlayer = playersList.get(0);
        this.allDeck = allDeck;
        this.rangeDeck = rangeDeck;
        this.round = 1;
        this.selectedCards = selectedCards;
        this.finishTurn = finishTurn;
        this.playerScores = new ArrayList<>();
        for (int i = 0; i < playersList.size(); i++) {
            playerScores.add(playersList.get(i).getPoints());
        }
    }

    public ArrayList<Player> getPlayersList() {
        return playersList;
    }

    public void setPlayersList(ArrayList<Player> playersList) {
        this.playersList = playersList;
    }

    public void moveToNextPlayer() {
        updatePlayerPoints(currPlayer, currPlayer.getPoints());
        int currentIndex = playersList.indexOf(currPlayer);
        int nextIndex = (currentIndex + 1) % playersList.size();
        currPlayer = playersList.get(nextIndex);

    }

    public void updatePlayerPoints(Player player, int newPoints) {
        int index = player.getPlayerID() - 1;
        playerScores.set(index, newPoints);
    }

    public void removePlayer(Player playerToRemove) {
        playersList.remove(playerToRemove);
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
        return "Current Player: " + currPlayer.toString() + "\n"+
            playersList + "\n" +
            "Round:" + round;
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

    public boolean isFinishTurn() {
        return finishTurn;
    }

    public void setFinishTurn(boolean finishTurn) {
        this.finishTurn = finishTurn;
    }

    public ArrayList<Integer> getPlayerScores() {
        return playerScores;
    }

    public void setPlayerScores(ArrayList<Integer> playerScores) {
        this.playerScores = playerScores;
    }
    
}
