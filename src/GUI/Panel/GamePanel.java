package GUI.Panel;

import java.awt.CardLayout;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.JPanel;

import components.Card;
import components.Deck;
import components.Hand;
import components.Player;
import components.PlayerComparator;
import components.PlayerHand;
import components.Rank;
import components.Suit;
public class GamePanel extends JPanel {
    private CardLayout cardLayout;
    private GameState gameState;
    private RoundPanel roundPanel;
    private IntermediatePanel intermediatePanel;
    private TurnPanel turnPanel;
    private DrawPanel drawPanel;
    private Scoreboard scoreBoard;
    private DisplayScoresPanel displayScoresPanel;
    private final int initialPoints = 5;

    public GamePanel() {
        initialise();
        cardLayout = new CardLayout();
        setLayout(cardLayout);
        ArrayList<Player> playersList = gameState.getPlayersList();
        ArrayList<Card> selectedCards = gameState.getSelectedCards();
        this.scoreBoard = new Scoreboard();
        scoreBoard.setVisible(true);

        // Create and add each scene panel to the GamePanel
        roundPanel = new RoundPanel(gameState);
        intermediatePanel = new IntermediatePanel(gameState);
        turnPanel = new TurnPanel(gameState);
        drawPanel = new DrawPanel(gameState);
        displayScoresPanel = new DisplayScoresPanel(gameState);

        // add(roundPanel, "round");
        add(roundPanel, "Round");
        add(intermediatePanel, "Intermediate");
        add(turnPanel, "Turn");
        add(drawPanel, "Draw");
        add(displayScoresPanel, "Scoreboard");

        // Show the initial scene (e.g., RoundOne)
        switchToPanel("Round");
    }
    public void updateDrawPanel (Card card){
        System.out.println("Drawing");
        drawPanel.setDescriptionLabel(gameState);
        drawPanel.setSelectedCardsPanel(gameState, card);
        drawPanel.setLowerBoundValueLabel();
        drawPanel.setUpperBoundValueLabel();
    }
    public void updateIntermediatePanel() {
        int listSize = gameState.getPlayersList().size();
         if (gameState.getPlayersList().get(listSize-1).getPlayerID() == gameState.getCurrPlayer().getPlayerID()) {
            gameState.nextRound();
            intermediatePanel.setDescriptionLabelToRound(gameState);
        } else {
            intermediatePanel.setDescriptionLabelToPass(gameState);
        }
    }
        
    public void updateTurnPanel(){
        turnPanel.setDescriptionLabel(gameState);
        turnPanel.setSelectedCardsPanel(gameState);
        turnPanel.setBetLabel(gameState);
        turnPanel.refreshScoreboard();
    }
    public void updateRoundPanel(){
        roundPanel.setDescriptionLabel(gameState);
        roundPanel.setHandPanel(gameState);
        repaint();
        revalidate();
        roundPanel.refreshScoreboard();
    }
    public void updateScoresPanel() {
        displayScoresPanel.refreshScoreboard();
        displayScoresPanel.setFilepath(gameState);
    }
    public void switchToPanel(String panelName) {
        cardLayout.show(this, panelName);
    }
    private void initialise(){
        Deck rangeDeck = initializeNumbers();
        Deck allDeck = initializeWhole();

        ArrayList<Player> playersList = new ArrayList<>();

        for (int i = 1; i < 5; i++) {
            playersList.add(new Player(i, initialPoints, new PlayerHand()));
        }

        playersList.forEach(p -> dealRange(rangeDeck, p.getHand()));

        int roundCount = 1;
        boolean finishTurn = false;
        ArrayList<Card> selectedCards = new ArrayList<>();
        this.gameState = new GameState(playersList, allDeck, rangeDeck, roundCount, selectedCards, finishTurn);
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
