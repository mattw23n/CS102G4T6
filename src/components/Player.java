package components;
// pls feel free to cmiiw....................................
import java.util.*;

public class Player {
    private int playerID;    
    private int points;
    private Hand handCards = new PlayerHand();
    private int upperBound;
    private int lowerBound;
    private int bet;
    private int WildCardCount;

    public Player(int playerID, int points, Hand handCards) {
        this.playerID = playerID;
        this.points = points;
        this.handCards = handCards;
    }

    public static Card StringtoCard(String target){
        Map<String, Card> map = new HashMap<>();

        Rank.setKingHigh();
        List r = Rank.VALUES;
        List s = Suit.VALUES;

        //adding cards to deck
        for(int i = 0; i < 4; i++){
            for(int j = 0; j < r.size(); j++){
                Card temp = new Card((Suit)s.get(i), (Rank)r.get(j), null);
                // System.out.println(temp.toString());
                map.put(temp.toString(), temp);
            }
        }
        
        //System.out.println(map);

        Set keys = map.keySet();
        Iterator keys_iterator = keys.iterator();
        while(keys_iterator.hasNext()){
            String current = (String) keys_iterator.next();
            if(current.equals(target)){
                return map.get(current);
            }
        }

        return null;
    }

    public void chooseRange() {

        Scanner sc = new Scanner(System.in);
        try {
            //LOWER BOUND
            //ask for lower bound and convert into card
            System.out.print("Choose card for lower bound of range:");
            String input1 = sc.nextLine();
            Card lower = StringtoCard(input1);
           
            //add into range arraylist for storage
            if (this.handCards.containsCard(lower)) {
                // System.out.println(lower);

                if (lower.getRank().getSymbol().equals("a")) {
                  lowerBound = 1;
                } else {
                   lowerBound = Integer.parseInt(lower.getRank().getSymbol());
                }
            }
            System.out.println(lowerBound);

            //UPPER BOUND
            //ask for upper bound and convert into card
            System.out.print("Choose card for upper bound of range:");
            String input2 = sc.nextLine();
            Card upper = StringtoCard(input2);
            
            //add into range arraylist for storage
            if (this.handCards.containsCard(upper)) {
                // System.out.println(upper);

                if (upper.getRank().getSymbol().equals("a")) {
                    upperBound = 1;
                } else {
                    upperBound = Integer.parseInt(upper.getRank().getSymbol());
                }

            }
            System.out.println(upperBound);
            
            // System.out.println("player" + range);

            //remove the range cards
            int upper_i = handCards.findCard(upper);
            int lower_i = handCards.findCard(lower);

            if(upper_i > lower_i){
                handCards.removeCard(upper_i);
                handCards.removeCard(lower_i);
            }else{
                handCards.removeCard(lower_i);
                handCards.removeCard(upper_i);
            }

            //System.out.println(handCards);


            } catch (InputMismatchException e) { //what to put here idk / Or just use if-else no need exception?
                System.out.println("Please enter a valid card!");
            } finally {
                System.out.println();
            }
    }

    public int getWildcardCount(){
        return WildCardCount;
    }

    public void incrementWildcardCount(){
        WildCardCount++;
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

    public int makeBet() { 
         
        // Set min bet 
        int minBet = 1; 
         
        int maxBet = this.getPoints() - 1; 
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
                // readBet.nextLine();
                 
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

    public boolean inBetween(Card card){
        int card_value;
        //getting card value
        if (card.getRank().getSymbol().equals("a")) {
            card_value = 1;
        } else {
            System.out.println();
            card_value = Integer.parseInt(card.getRank().getSymbol());
        }
        
        //if in between return true, else false
        if(card_value >= lowerBound && card_value <= upperBound)
            return true;
        
        return false;
    }

    public int calculatePoints(Hand h, Card c) {

        // Needs Wildcard attribute in Player

        System.out.println("YOU GOT" + c.toString());

        int points = 0;
        int lower = this.getLower();
        int upper = this.getUpper();

        //set the current number after drawing a card
        int current;
        if (c.getRank().getSymbol().equals("a")) {
            current = 1;
        } else {
            current = Integer.parseInt(c.getRank().getSymbol());
        }

        //point logic
        if (current == upper || current == lower) {
            points = this.getBet() * 2;
            System.out.println("Right on the dot!");

        } else if (current > lower && current < upper) {
            points = this.getBet() * 1;
            System.out.println("In between!");

        } else {
            points = this.getBet() * -1;
            System.out.println("Good luck next time!");
        }
        return this.getPoints() + points;
    }

    public void processWildCard(Hand h, Card c) {

        // // If it's the 3rd wildcard onwards, deduct a point
        // if (p.getWildcardCount() > 2) {
        //     p.incrementWildcardCount();
        //     p.setPoints(p.getPoints() - 1);
            
        // }

        // If queen, extend lower bound
        if (c.getRank().getSymbol() == "q") {
            int lower = this.getLower();
            lower -= 2;
            if (lower < 1) {
                lower = 1;
            }
            this.setLower(lower);

        // If king, extend upper bound
        } else if (c.getRank().getSymbol() == "k") {
            int upper = this.getUpper();
            upper += 2;
            if (upper > 10) {
                upper = 10;
            }
            this.setUpper(upper);


        // If joker, minus (2 * bet) points
        } else {
            int points = this.getBet() * -2;
            this.setPoints(this.getPoints() + points);
        }

    }

    public void processJack(Hand h, Card cardToSwap, Card swapped) {

        // Swaps the selected bound card for a new bound card
        int current;
        if (cardToSwap.getRank().getSymbol().equals("a")) {
            current = 1;
        } else {
            current = Integer.parseInt(cardToSwap.getRank().getSymbol());
        }

        
               
        int swapped_card;
        if (swapped.getRank().getSymbol().equals("a")) {
            swapped_card = 1;
        } else {
            swapped_card = Integer.parseInt(swapped.getRank().getSymbol());
        }

        if(current == upperBound){
            if(swapped_card < this.getLower()){
                upperBound = lowerBound;
                this.setLower(swapped_card);
            }else{
                this.setUpper(swapped_card);
            }

            
        }else{
            if(swapped_card > this.getUpper()){
                lowerBound = upperBound;
                this.setUpper(swapped_card);
            }else{
                this.setLower(swapped_card);
            }

        }

        if(swapped_card > this.getUpper() || swapped_card > this.getLower()){
            this.setUpper(swapped_card);
        }else if(swapped_card < this.getLower() ){
            this.setLower(swapped_card);
        }

        //check if it is upper or lower


    }
}