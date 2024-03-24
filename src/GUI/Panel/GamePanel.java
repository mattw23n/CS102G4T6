package GUI.Panel;

import java.awt.CardLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import components.Deck;
import components.Hand;
import components.Player;
import components.PlayerHand;
import components.Rank;
import components.Suit;
import components.Card;
public class GamePanel extends JPanel {
    private CardLayout cardLayout;
    private ArrayList<Player> playersList;

    public GamePanel() {
        initialise();
        
        cardLayout = new CardLayout();
        setLayout(cardLayout);
        GameState gameState = new GameState(true, 1, playersList.get(0));
        
        // Create and add each scene panel to the GamePanel
        RoundOnePanel roundOnePanel = new RoundOnePanel();
        IntermediatePanel intermediatePanel = new IntermediatePanel();
        TurnPanel turnPanel = new TurnPanel();
        // RoundTwoPanel roundTwoPanel = new RoundTwoPanel();
        DisplayScoresPanel displayScoresPanel = new DisplayScoresPanel();


        add(roundOnePanel, "RoundOne");
        add(intermediatePanel, "Intermediate");
        add(turnPanel, "Turn");
        // add(roundTwoPanel, "RoundTwo");
        add(displayScoresPanel, "Scoreboard");

        // Show the initial scene (e.g., RoundOne)
        cardLayout.show(this, "RoundOne");
    }
    public void switchToPanel(String panelName) {
        cardLayout.show(this, panelName);
    }
    private void initialise(){
        //initializing deck of only range cards
        Deck rangeDeck = initializeNumbers();

        //initialize deck of all cards
        Deck allDeck = initializeWhole();

        // //initialize players
        Player p1 = new Player(1, 5, new PlayerHand());
        Player p2 = new Player(2, 5, new PlayerHand());
        Player p3 = new Player(3, 5, new PlayerHand());
        Player p4 = new Player(4, 5, new PlayerHand());

        playersList = new ArrayList<>();
        playersList.add(p1);
        playersList.add(p2);
        playersList.add(p3);
        playersList.add(p4);

        //initialize each players hand
        for(Player p : playersList){
            dealRange(rangeDeck, p.getHand());
        }
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
