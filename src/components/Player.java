package components;
/*
 * Player.java
 * 
 * Player initializes a Player object with the main purpose of 
 * manipulating Player attributes
 * 
 */

import java.util.*;

public class Player {
    private int playerID;    
    private int points;
    private Hand handCards = new PlayerHand();
    private int upperBound;
    private int lowerBound;
    private int bet;
    private int WildCardCount;
    private Card originalUpper;
    private Card originalLower;

    public Player(int playerID, int points, Hand handCards) {
        this.playerID = playerID;
        this.points = points;
        this.handCards = handCards;
    }
    
    public int getWildcardCount(){
        return WildCardCount;
    }

    public void setWildCardCount(int wildCardCount) {
        WildCardCount = wildCardCount;
    }

    public int getBet() {
        return bet;
    }

    public void setBet(int bet) {
        this.bet = bet;
    }

    public void setPoints(int n) {
        this.points = n;
    }

    public int getPoints() {
        return points;
    }

    public void setPlayerID(int id) {
        this.playerID = id;
    }

    public int getPlayerID() {
        return playerID;
    }

    public Hand getHand() {
        return handCards;
    }

    public int getUpper(){
        return upperBound;
    }

    public int getLower(){
        return lowerBound;
    }

    public void setUpper(int upper){
        this.upperBound= upper;
    }

    public void setLower(int lower){
        this.lowerBound= lower;
    }

    public Card getOriginalUpper() {
        return originalUpper;
    }

    public void setOriginalUpper(Card originalUpper) {
        this.originalUpper = originalUpper;
    }

    public Card getOriginalLower() {
        return originalLower;
    }

    public void setOriginalLower(Card originalLower) {
        this.originalLower = originalLower;
    }

}