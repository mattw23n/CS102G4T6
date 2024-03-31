package GUI.Panel;

/*
 * GamePanel.java
 * 
 * GamePanel initializes the players, deck, and starting points 
 */

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
import utilities.DeckUtils;

public class GamePanel extends JPanel {
    private CardLayout cardLayout;
    private GameState gameState;
    private RoundPanel roundPanel;
    private IntermediatePanel intermediatePanel;
    private TurnPanel turnPanel;
    private DrawPanel drawPanel;
    private Scoreboard scoreBoard;
    private DisplayScoresPanel displayScoresPanel;

    public GamePanel() {
        initializeGame();
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
    
    public void updateIntermediatePanel (){
        System.out.println("Updating IntermediatePanel");
        if (gameState.getCurrPlayer().getPlayerID() == 1){
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
    
    private void initializeGame(){
        int startingPoints = 5;

        Deck rangeDeck = DeckUtils.initializeNumbers();
        Deck allDeck = DeckUtils.initializeWhole();

        Player p1 = new Player(1, startingPoints, new PlayerHand());
        Player p2 = new Player(2, startingPoints, new PlayerHand());
        Player p3 = new Player(3, startingPoints, new PlayerHand());
        Player p4 = new Player(4, startingPoints, new PlayerHand());

        ArrayList<Player> playersList = new ArrayList<>();
        playersList.add(p1);
        playersList.add(p2);
        playersList.add(p3);
        playersList.add(p4);

        for(Player p : playersList){
            DeckUtils.dealRange(rangeDeck, p.getHand());
        }

        int roundCount = 1;
        boolean finishTurn = false;
        ArrayList<Card> selectedCards = new ArrayList<>();
        this.gameState = new GameState(playersList, allDeck, rangeDeck, roundCount, selectedCards, finishTurn);
    }
}
