// pls feel free to cmiiw....................................
import java.util.*;

public class Player {
    int playerID;    
    int points;
    Hand handCards = new PlayerHand();
    int upperBound;
    int lowerBound;
    int bet;
    int WildCardCount;

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

    public ArrayList<Card> chooseRange() {
        ArrayList<Card> range = new ArrayList<>();

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
            range.add(lower);
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
            range.add(upper);
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
        return range;
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

    public int makeBet(boolean isFirstRound) { 
         
        // Set min bet 
        int minBet = 1; 
         
        int maxBet = this.getPoints() - 1; 
        if (isFirstRound) { 
            maxBet = 4; 
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

    public int calculatePoints(Hand h, Card c, Player p, boolean isWildCard, ArrayList<Card> range, Deck d) {

        // Needs Wildcard attribute in Player

        // If it's the 3rd wildcard onwards, deduct a point
        if (isWildCard && p.getWildcardCount() > 2) {
            p.incrementWildcardCount();
            return p.getBet() * -1;
            
        }


        if (isWildCard) {
            processWildCard(c, p, h, d, range);
            return p.getPoints();
        }

        int points = 0;
        int lower = p.getLower();
        int upper = p.getUpper();

        //set the current number after drawing a card
        int current;
        if (c.getRank().getSymbol().equals("a")) {
            current = 1;
        } else {
            current = Integer.parseInt(c.getRank().getSymbol());
        }

        //point logic
        if (current == upper || current == lower) {
            points = p.getBet() * 2;
            System.out.println("Right on the dot!");

        } else if (current > lower && current < upper) {
            points = p.getBet() * 1;
            System.out.println("In between!");

        } else {
            points = p.getBet() * -1;
            System.out.println("Good luck next time!");
        }
        return p.getPoints() + points;
    }

    public void processWildCard(Card c, Player p, Hand h, Deck d, ArrayList<Card> range) {
        Scanner sc = new Scanner(System.in);

        // If queen, extend lower bound
        if (c.getRank().getSymbol() == "q") {
            int lower = p.getLower();
            lower -= 2;
            if (lower < 1) {
                lower = 1;
            }
            lowerBound = lower;
        
        // If king, extend upper bound
        } else if (c.getRank().getSymbol() == "k") {
            int upper = p.getUpper();
            upper += 2;
            if (upper > 10) {
                upper = 10;
            }
            upperBound = upper;

        // If jack, run ProcessJack function
        }else if(c.getRank().getSymbol() == "j"){

            System.out.println("you got a jack!");

            // System.out.print("Choose your swapped card:");
            // String swapped = sc.nextLine();

            // //remove swapped card from range
            // Card temp = StringtoCard(swapped);
            // for(Card ca : range){
            //     if(ca.isSameAs(temp)){
            //         range.remove(ca);
            //     }
            // }

            // //get new card
            // Card newer = d.dealCard();
            // range.add(newer);

            // int new_


            



        // If joker, minus (2 * bet) points
        } else {
            int points = p.getBet() * -2;
            p.setPoints(p.getPoints() + points);
        }

    }
}