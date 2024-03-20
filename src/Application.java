//Compile.bat and Run.bat runs this file

import java.util.*;

import javax.swing.SwingUtilities;

import GUI.Window;

public class Application {

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
            if(Rank.VALUES.get(i) == card.getRank())
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
        hand.addCard(d1.dealCard());
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
            ArrayList<Card> range = p.chooseRange();

            //make bet
            p.setBet( p.makeBet(isfirstRound));
            
            //draw card from deck and add to hand
            DealCard(AllDeck, hand);
            Card newest = hand.getCard(hand.getNumberOfCards() - 1);
            System.out.println("\nYOU GOT " + newest);

            //calculate points
            //Wildcard processing still messy, problem with looping n everything
            //Still cannot figure out Jack logic
            int add = p.calculatePoints(hand, newest, p, isWildCard(newest), range, AllDeck);
            p.setPoints(add);
            System.out.println("Your Current Points: " + p.getPoints());

            //remove the last card from the player's deck
            hand.removeCard(newest);
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
        
        System.out.println("Player " + players.getFirst().getPlayerID() + " is the winner!!");

        for(Player p : players){
            System.out.println("Player " + p.getPlayerID() + ": " + p.getPoints());
        }

        System.out.println("Thank you for playing!");

    }
}
