//Compile.bat and Run.bat runs this file

import java.util.*;

import javax.swing.SwingUtilities;

import org.w3c.dom.ranges.Range;

import GUI.Window;

public class JackTest {



    public static Deck initialize_numbers(){
        //initializing deck of 52
        Deck d1 = new Deck();
        Rank.setKingHigh();
        List r = Rank.VALUES_NUMBER;
        List s = Suit.VALUES;

        //adding cards to deck
        for(int i = 0; i < 4; i++){
            for(int j = 0; j < r.size(); j++){
                d1.addCard(new Card((Suit)s.get(i), (Rank)r.get(j), null));
            }
        }
        
        d1.shuffle();
        return d1;
    }

    public static boolean isWildCard(Card card){
        for(int i = 10; i < 13; i++){
            if(card.getRank().getSymbol() == "j" || card.getRank().getSymbol() == "q" || card.getRank().getSymbol() == "k")
                return true;
        }

        return false;
    }

    public static Deck initialize_whole(){
        //initializing deck of 52
        Deck d1 = new Deck();
        Rank.setKingHigh();
        List r = Rank.VALUES;
        List s = Suit.VALUES;

        //adding cards to deck
        for(int i = 0; i < 4; i++){
            for(int j = 0; j < r.size(); j++){
                d1.addCard(new Card((Suit)s.get(i), (Rank)r.get(j), null));
            }
        }
        
        d1.shuffle();
        return d1;
    }
    
    public static void DealRange(Deck d1, Hand hand){
        for(int i = 0; i < 6; i++){
            hand.addCard(d1.dealCard());
        }

    }

    public static void DealCard(Deck d1, Hand hand){
        
        Card c = d1.dealCard();
        // while (c.getRank().getSymbol() != "j") {
        //     c = d1.dealCard();
        // }
        
        hand.addCard(c);
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

    public static boolean isKing(Card card){
        return card.getRank() == Rank.VALUES.get(12);

    }

    public static void runGUI (){
        // SwingUtilities.invokeLater(() -> {
            Window mainWindow = new Window();
            mainWindow.setVisible(true);
        // });
    }

    public static void doRound(ArrayList<Player> players, Deck RangeDeck, Deck AllDeck, int round_count){
        //one round of the game

        Scanner scan = new Scanner(System.in);
        boolean isfirstRound = (round_count == 1) ? true : false;

        for(Player p : players){

            //each player has a turn
            Hand hand = p.getHand();
            String playerId = "Player " + p.getPlayerID() + "'s Hand";
            System.out.printf("=================%s=================\n", playerId);
            System.out.println(hand.toString());
            System.out.println("=================================================");

            //select upper bound and lower bound
            //upper and lower bound will be removed from hand afterwards
            p.chooseRange();

            //make bet
            int temp = p.makeBet(isfirstRound);
            if(temp < 0){
                System.out.println("You are OUT!!!");
                continue;
            }
            p.setBet(temp);
            
            //draw card from deck and add to hand
            DealCard(AllDeck, hand);
            Card newest = hand.getCard(hand.getNumberOfCards() - 1);
            

            //calculate points
            //Wildcard processing still messy, problem with looping n everything
            //Still cannot figure out Jack logic

            int maxJack = 4;
            int jackCount = 0;
            boolean isNonWild = false;
            do {

                System.out.println("\nYOU GOT " + newest);

                if(isWildCard(newest)){
                    System.out.println("(It's a wildcard)");
                    p.incrementWildcardCount();

                    //check if wildcard count >= 3
                    if (p.getWildcardCount() >= 3) { 
                        int currPoints = p.getPoints(); 
                        p.setPoints(currPoints - 1); 
                    }
                    
                    //if not jack
                    if(newest.getRank().getSymbol() != "j"){
                        System.out.println("(It's not a jack)");
                        p.processWildCard(hand, newest);

                    }else{
                        System.out.println("(It's a jack)");
                        jackCount++;
                        
                        Scanner sc = new Scanner(System.in);
                        System.out.print("Choose range card to swap:");
                        String input2 = sc.nextLine();
                        Card cardToSwap = StringtoCard(input2);

                        DealCard(RangeDeck, hand);
                        Card swapped = hand.getCard(hand.getNumberOfCards() - 1);
                        Utils.processJack(p, cardToSwap, swapped);
                        System.out.println("new bound is " + swapped);
                    }
                    System.out.println("(" + p.getLower() + ", " + p.getUpper() + ")");
                    //call process wildcard
                    //remove the last card from the player's deck
                    hand.removeCard(newest);
                    DealCard(AllDeck, hand);
                }else{
                    System.out.println("(It's not a wildcard)");
                    int add = Utils.calculatePoints(p, newest);
                    p.setPoints(add);
                    
                    isNonWild = true;
                    //remove the last card from the player's deck
                    hand.removeCard(newest);
                    break;
                    //calculate points
                }

                if (jackCount == maxJack) {
                    System.out.println("(Max num of jacks reached)");
                    boolean isJack = true;
                    int i = 1;
                    do {
                        newest = hand.getCard(hand.getNumberOfCards() - i);
                        if (newest.getRank().getSymbol() != "j") {
                            
                            isJack = false;
                        }
                        i++;
                    } while (isJack);
                
                } else {
                    newest = hand.getCard(hand.getNumberOfCards() - 1);
                }

                // boolean isJack = true;
                // // int jackCount = 0;
                // int i = 1;
                // do {
                //     System.out.println("(No more than 2 jacks allowed)");
                //     newest = hand.getCard(hand.getNumberOfCards() - i);
                //     if (newest.getRank().getSymbol() != "j") {
                //         // jackCount++;
                //         isJack = false;
                //     }
                //     i++;
                // } while (isJack);
                
            } while (isWildCard(newest));

            if(!isNonWild){
                System.out.println("upper" + p.getUpper() + "lower" + p.getLower());
                int add = Utils.calculatePoints(p, newest);
                p.setPoints(add);
                
                //remove the last card from the player's deck
                hand.removeCard(newest);
            }
                


            // int add = p.calculatePoints(hand, newest, p, isWildCard(newest), range, AllDeck);
            // p.setPoints(add);
            System.out.println("Your Current Points: " + p.getPoints());

            
            System.out.println("\n=================YOUR CURRENT HAND=================\n" + hand);

            //move to next player
            System.out.println("\n=================================================\n");
            System.out.println("PLEASE PASS TO THE NEXT PLAYER");
            System.out.println("\n=================================================\n");
        }
    }


    public static void main(String[] args) {
        //initializing deck of only range cards
        Deck RangeDeck = initialize_numbers();

        //initialize deck of all cards
        Deck AllDeck = initialize_whole();

        // //initialize players
        Player p1 = new Player(1, 5, new PlayerHand());
        Player p2 = new Player(2, 5, new PlayerHand());
        Player p3 = new Player(3, 5, new PlayerHand());
        Player p4 = new Player(4, 5, new PlayerHand());

        ArrayList<Player> players = new ArrayList<>();
        players.add(p1);
        players.add(p2);
        players.add(p3);
        players.add(p4);

        //initialize each players hand
        for(Player p : players){
            DealRange(RangeDeck, p.getHand());
        }
        
        //run game 3 times
        for(int i = 0 ; i < 3; i++){
            doRound(players, RangeDeck, AllDeck, i + 1);
        }
        
        //find out the winner
        Collections.sort(players, new PlayerComparator());
        ArrayList<Player> winner = new ArrayList<>();
        winner.add(players.get(0));

        int max_points = players.get(0).getPoints();
        for(int i = 1; i < players.size(); i++){
            if(players.get(i).getPoints() == max_points){
                winner.add(players.get(i));
            }
        }

        for(Player pw : winner){
            System.out.println("Player " + pw.getPlayerID() + " is the winner!!");
        }
        

        for(Player p : players){
            System.out.println("Player " + p.getPlayerID() + ": " + p.getPoints());
        }

        System.out.println("Thank you for playing!");

    }
}

    

