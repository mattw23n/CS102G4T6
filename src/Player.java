
import java.util.*;

public class Player {
    private int playerID;    
    private int points;
    private Hand handCards = new PlayerHand();
    private int upperBound;
    private int lowerBound;
    private int bet;
    private int WildCardCount;
    private boolean isOut = false;

    public Player(int playerID, int points, Hand handCards) {
        this.playerID = playerID;
        this.points = points;
        this.handCards = handCards;
    }
    
    //visualize player hand
    public void playerHandToString(){
        String playerId = "Player" + playerID + "'s Hand";
        System.out.printf("=================%s=================\n", playerId);
        System.out.println(handCards.toString());
        System.out.println("=================================================");

    }
    
    //setting upper and lower bound
    public void chooseRange() {

        Scanner sc = new Scanner(System.in);
        try {
            //LOWER BOUND
            System.out.print("Choose card for lower bound of range:");
            Card lower = Utils.stringToCard(sc.nextLine());
           
            if (this.handCards.containsCard(lower)) {
                lowerBound = Utils.getCardValueInt(lower);
            }

            System.out.println(lowerBound);

            //UPPER BOUND
            System.out.print("Choose card for upper bound of range:");
            Card upper = Utils.stringToCard(sc.nextLine());

            if (this.handCards.containsCard(upper)) {
                upperBound = Utils.getCardValueInt(upper);
            }

            System.out.println(upperBound);

            //remove the range cards
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

    public int makeBet() { 
        // Set min bet 
        int minBet = 1; 
         
        int maxBet = this.getPoints() - 1; 

        //if player is broke
        if(maxBet <= 0){
            return -1;
        }

        // Initialise bet 
        int bet = 0; 
        boolean invalidInt = true; 
 
        Scanner readBet = new Scanner(System.in); 
 
        // Ask player to input their bet, if their bet is below minBet or if they entered a non-integer, ask again 
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

}