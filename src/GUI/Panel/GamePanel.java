package GUI.Panel;

import java.awt.CardLayout;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.swing.JPanel;

import components.Card;
import components.Deck;
import components.Hand;
import components.Player;
import components.PlayerHand;
import components.Rank;
import components.Suit;
public class GamePanel extends JPanel {
    private CardLayout cardLayout;
    private GameState gameState;

    public GamePanel() {
        initialise();
        cardLayout = new CardLayout();
        setLayout(cardLayout);
        ArrayList<Player> playersList = gameState.getPlayersList();
        ArrayList<Card> selectedCards = gameState.getSelectedCards();
        initialisePanels(playersList, selectedCards);
        doRound();
        // for (int i = 0; i < playersList.size(); i++){
        //     doRound(i);
        // }
        // Create and add each scene panel to the GamePanel
        // RoundPanel roundPanel = new roundPanel(gameState);
        // RoundOnePanel roundOnePanel = new RoundOnePanel();
        IntermediatePanel intermediatePanel = new IntermediatePanel(gameState );
        TurnPanel turnPanel = new TurnPanel(gameState);
        // RoundTwoPanel roundTwoPanel = new RoundTwoPanel();
        DisplayScoresPanel displayScoresPanel = new DisplayScoresPanel();

        // add(roundPanel, "round");
        // add(roundOnePanel, "RoundOne");
        add(intermediatePanel, "Intermediate");
        add(turnPanel, "Turn");
        // add(roundTwoPanel, "RoundTwo");
        add(displayScoresPanel, "Scoreboard");

        // Show the initial scene (e.g., RoundOne)
        // cardLayout.show(this, "RoundOne0");
        switchToPanel("P"+ gameState.getCurrPlayer().getPlayerID() + "Picking");
    }
    public void initialisePanels(ArrayList<Player> playersList, ArrayList<Card> selectedCards){
        for (int i = 0; i < playersList.size(); i++){
            Player player = playersList.get(i);
            RoundOnePanel roundOnePanel = new RoundOnePanel(gameState, player);
            System.out.println("---"+ player.toString() +" Time pass here---");
            add(roundOnePanel, "P"+ player.getPlayerID() + "Picking");
        }
    }
    public void doRound() {
        ArrayList<Player> players = gameState.getPlayersList();
        // Scanner scan = new Scanner(System.in);
        for(Player p : players){
            Deck allDeck = gameState.getAllDeck();
            Deck rangeDeck = gameState.getRangeDeck();
            //each player has a turn
            Hand hand = p.getHand();
            String playerId = "Player " + p.getPlayerID() + "'s Hand";
            System.out.printf("=================%s=================\n", playerId);
            System.out.println(hand.toString());
            System.out.println("=================================================");
            
            // RoundOnePanel roundOnePanel = new RoundOnePanel(gameState);
            // add(roundOnePanel, "P"+p.getPlayerID() + "Picking");

        //     //select upper bound and lower bound
        //     //upper and lower bound will be removed from hand afterwards
        //     p.chooseRange();

        //     //make bet
        //     int temp = p.makeBet();
        //     if(temp < 0){
        //         System.out.println("You are OUT!!!");
        //         continue;
        //     }
        //     p.setBet(temp);
            
        //     //draw card from deck and add to hand
        //     dealCard(allDeck, hand);
        //     Card newest = hand.getCard(hand.getNumberOfCards() - 1);
        //     System.out.println("\nYOU GOT " + newest);

        //     //calculate points

        //     boolean isNonWild = false;

        //     do {
        //         if(isWildCard(newest)){
        //             p.incrementWildcardCount();

        //             //check if wildcard count >= 3
        //             if (p.getWildcardCount() >= 3) { 
        //                 int currPoints = p.getPoints(); 
        //                 p.setPoints(currPoints - 1); 
        //             }
                    
        //             //if not jack
        //             if(newest.getRank().getSymbol() != "j"){
        //                 p.processWildCard(hand, newest);

        //             }else{
        //                 System.out.print("Choose range card to swap:");
        //                 String input2 = scan.nextLine();
        //                 Card cardToSwap = p.StringtoCard(input2);

        //                 dealCard(rangeDeck, hand);
        //                 Card swapped = hand.getCard(hand.getNumberOfCards() - 1);
        //                 p.processJack(hand, cardToSwap, swapped);
        //             }
                    
        //             //call process wildcard
        //             //remove the last card from the player's deck
        //             hand.removeCard(newest);
        //             dealCard(allDeck, hand);
        //         }else{

        //             int add = p.calculatePoints(hand, newest);
        //             p.setPoints(add);
                    
        //             isNonWild = true;
        //             //remove the last card from the player's deck
        //             hand.removeCard(newest);
        //             break;
        //             //calculate points
        //         }

        //         newest = hand.getCard(hand.getNumberOfCards() - 1);
                
        //     } while (isWildCard(newest));

        //     if(!isNonWild){
        //         System.out.println("upper" + p.getUpper() + "lower" + p.getLower());
        //         int add = p.calculatePoints(hand, newest);
        //         p.setPoints(add);
                
        //         //remove the last card from the player's deck
        //         hand.removeCard(newest);
        //     }
                

        //     System.out.println("Your Current Points: " + p.getPoints());

            
        //     System.out.println("\n=================YOUR CURRENT HAND=================\n" + hand);

        //     //move to next player
        //     System.out.println("\n=================================================\n");
        //     System.out.println("PLEASE PASS TO THE NEXT PLAYER");
        //     System.out.println("\n=================================================\n");
        }
    }
    public static boolean isWildCard(Card card){
        for(int i = 10; i < 13; i++){
            if(Rank.VALUES.get(i) == card.getRank())
                return true;
        }

        return false;
    }
    public void switchToPanel(String panelName) {
        cardLayout.show(this, panelName);
    }
    private void initialise(){
        Deck rangeDeck = initializeNumbers();
        Deck allDeck = initializeWhole();

        Player p1 = new Player(1, 5, new PlayerHand());
        Player p2 = new Player(2, 5, new PlayerHand());
        Player p3 = new Player(3, 5, new PlayerHand());
        Player p4 = new Player(4, 5, new PlayerHand());

        ArrayList<Player> playersList = new ArrayList<>();
        playersList.add(p1);
        playersList.add(p2);
        playersList.add(p3);
        playersList.add(p4);

        for(Player p : playersList){
            dealRange(rangeDeck, p.getHand());
        }
        int roundCount = 1;
        ArrayList<Card> selectedCards = new ArrayList<>();
        this.gameState = new GameState(playersList, allDeck, rangeDeck, roundCount, selectedCards);
    }
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
    public void dealRange(Deck d1, Hand hand){
        for(int i = 0; i < 6; i++){
            hand.addCard(d1.dealCard());
        }

    }

    public void dealCard(Deck d1, Hand hand){
        hand.addCard(d1.dealCard());
    }
}
