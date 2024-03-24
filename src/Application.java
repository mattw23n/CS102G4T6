//Compile.bat and Run.bat runs this file

import java.util.*;

import javax.swing.SwingUtilities;

import org.w3c.dom.ranges.Range;

import GUI.Window;

public class Application {

    public static Deck initializeNumbers(){
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
            if(Rank.VALUES.get(i) == card.getRank())
                return true;
        }

        return false;
    }

    public static Deck initializeWhole(){
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
    
    public static void dealRange(Deck d1, Hand hand){
        for(int i = 0; i < 6; i++){
            hand.addCard(d1.dealCard());
        }

    }

    public static void dealCard(Deck d1, Hand hand){
        hand.addCard(d1.dealCard());
    }

    // public static Card stringToCard(String target){
    //     Map<String, Card> map = new HashMap<>();

    //     Rank.setKingHigh();
    //     List r = Rank.VALUES;
    //     List s = Suit.VALUES;

    //     //adding cards to deck
    //     for(int i = 0; i < 4; i++){
    //         for(int j = 0; j < r.size(); j++){
    //             Card temp = new Card((Suit)s.get(i), (Rank)r.get(j), null);
    //             // System.out.println(temp.toString());
    //             map.put(temp.toString(), temp);
    //         }
    //     }
        
    //     //System.out.println(map);

    //     Set keys = map.keySet();
    //     Iterator keys_iterator = keys.iterator();
    //     while(keys_iterator.hasNext()){
    //         String current = (String) keys_iterator.next();
    //         if(current.equals(target)){
    //             return map.get(current);
    //         }
    //     }

    //     return null;
    // }

    public static void runGUI (){
        // SwingUtilities.invokeLater(() -> {
            Window mainWindow = new Window();
            mainWindow.setVisible(true);
        // });
    }

    //Runs one round of the game
    public static void doRound(ArrayList<Player> players, Deck rangeDeck, Deck allDeck, int roundCount){
        
        Scanner scan = new Scanner(System.in);

        for(Player p : players){

            //Show player's hand
            Hand hand = p.getHand();
            p.playerHandToString();

            //select upper bound and lower bound
            //upper and lower bound will be removed from hand afterwards
            p.chooseRange();

            //make bet
            int bet = p.makeBet();
            if(bet < 0){
                System.out.println("You are OUT!!!");
                continue;
            }
            p.setBet(bet);
            
            //draw card from deck and add to hand
            dealCard(allDeck, hand);
            Card newest = hand.getCard(hand.getNumberOfCards() - 1);
            // System.out.println("\nYOU GOT " + newest);

            //calculate points
            boolean isNonWild = false;

            do {
                if(isWildCard(newest)){
                    p.incrementWildcardCount();

                    //check if wildcard count >= 3
                    if (p.getWildcardCount() >= 3) { 
                        int currPoints = p.getPoints(); 
                        p.setPoints(currPoints - 1); 
                    }
                    
                    //if not jack
                    if(newest.getRank().getSymbol() != "j"){
                        p.processWildCard(hand, newest);

                    }else{
                        System.out.print("Choose range card to swap:");
                        String input2 = scan.nextLine();
                        Card cardToSwap = Player.stringToCard(input2);

                        dealCard(rangeDeck, hand);
                        Card swapped = hand.getCard(hand.getNumberOfCards() - 1);
                        p.processJack(hand, cardToSwap, swapped);
                    }
                    
                    //call process wildcard
                    //remove the last card from the player's deck
                    hand.removeCard(newest);
                    dealCard(allDeck, hand);
                }else{

                    int add = p.calculatePoints(hand, newest);
                    p.setPoints(add);
                    
                    isNonWild = true;
                    //remove the last card from the player's deck
                    hand.removeCard(newest);
                    break;
                    //calculate points
                }

                newest = hand.getCard(hand.getNumberOfCards() - 1);
                
            } while (isWildCard(newest));

            if(!isNonWild){
                System.out.println("upper" + p.getUpper() + "lower" + p.getLower());
                int add = p.calculatePoints(hand, newest);
                p.setPoints(add);
                
                //remove the last card from the player's deck
                hand.removeCard(newest);
            }
                

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
        Deck rangeDeck = initializeNumbers();

        //initialize deck of all cards
        Deck allDeck = initializeWhole();

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
            dealRange(rangeDeck, p.getHand());
        }
        
        //run game 3 times
        for(int i = 0 ; i < 3; i++){
            doRound(players, rangeDeck, allDeck, i + 1);
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
