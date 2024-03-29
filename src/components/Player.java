package components;
/*
 * Player.java
 * 
 * Player initializes a Player object with the main purpose of 
 * manipulating Player attributes
 * 
 */

import java.util.*;
import utilities.Utils;

public class Player {
    private int playerID;    
    private int points;
    private Hand handCards = new PlayerHand();
    private int upperBound;
    private int lowerBound;
    private int bet;
    private int WildCardCount;
    private boolean isOut = false;
    private Card originalUpper;
    private Card originalLower;

    public Player(int playerID, int points, Hand handCards) {
        this.playerID = playerID;
        this.points = points;
        this.handCards = handCards;
    }
    
    //Visualize player hand
    public void playerHandToString(){
        String playerId = "Player" + playerID + "'s Hand";
        System.out.printf("=================%s=================\n", playerId);
        System.out.println(handCards.toString());
        System.out.println("=================================================");

    }
    
    //Setting upper and lower bound
    public void chooseRange() {

        Scanner sc = new Scanner(System.in);
        try {
            //LOWER BOUND
            System.out.print("Choose card for lower bound of range:");
            Card lower = Utils.stringToCard(sc.nextLine());
           
            //UPPER BOUND
            System.out.print("Choose card for upper bound of range:");
            Card upper = Utils.stringToCard(sc.nextLine());

            //Sorts upper and lower automatically
            if (this.handCards.containsCard(upper) && this.handCards.containsCard(lower)) {
                int lowerTemp = Utils.getCardValueInt(lower);
                int upperTemp = Utils.getCardValueInt(upper);

                upperBound = Math.max(lowerTemp, upperTemp);
                lowerBound = Math.min(lowerTemp, upperTemp);

                if(lowerBound == lowerTemp){
                    originalLower = lower;
                    originalUpper = upper;
                }else{
                    originalLower = upper;
                    originalUpper = lower;
                }
            }

            System.out.println(lowerBound);
            System.out.println(upperBound);

            //Remove the range cards
            int upperIndex = handCards.findCard(upper);
            int lowerIndex = handCards.findCard(lower);

            if(upperIndex > lowerIndex){
                handCards.removeCard(upperIndex);
                handCards.removeCard(lowerIndex);
            }else{
                handCards.removeCard(lowerIndex);
                handCards.removeCard(upperIndex);
            }


            } catch (InputMismatchException e) { 
                System.out.println("Please enter a valid card!");
            } finally {
                System.out.println();
            }
    }

    //Betting logic for Player object
    public int makeBet() { 
        //Set min bet 
        int minBet = 1; 
         
        int maxBet = this.getPoints() - 1; 

        //Player has no money
        if(maxBet <= 0){
            return -1;
        }

        //Initialise bet 
        int bet = 0; 
        boolean invalidInt = true; 
 
        Scanner readBet = new Scanner(System.in); 
 
        //Keep asking player for bet until valid input
        do { 
             
            System.out.printf("The minimum bet is %d and the maximum bet is %d, please enter your bet: ", minBet, maxBet); 
            try { 
                bet = readBet.nextInt(); 
                 
                if (bet < minBet) { 
                    System.out.println("Please do not enter a bet lower than the minimum bet"); 
                    continue; 
 
                } else if (bet > maxBet) { 
                    System.out.println("Please do not enter a bet greater than the maximum bet"); 
                    continue; 
 
                } else { 
                    invalidInt = false; 
                } 
 
            } catch (InputMismatchException e) { 
                System.out.println("PLease enter an integer value"); 
                readBet.next(); 
            } 
             
        } while(invalidInt); 
 
        return bet; 
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

    public boolean isOut() {
        return isOut;
    }

    public void setOut(boolean isOut) {
        this.isOut = isOut;
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