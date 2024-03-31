package GUI.Panel;
/*
 * GamePanel.java
 * 
 * GamePanel initializes the Game and displays it on a panel.
 * It updates the following panels to match the game flow.
 * 
 */


import java.awt.CardLayout;
import java.util.ArrayList;

import javax.swing.JPanel;

import components.Card;
import components.Deck;
import components.Player;
import components.PlayerHand;
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
    private final int initialPoints = 5;
    private final int numOfCards = 6;
    private final int maxPlayers = 4;

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

        add(roundPanel, "Round");
        add(intermediatePanel, "Intermediate");
        add(turnPanel, "Turn");
        add(drawPanel, "Draw");
        add(displayScoresPanel, "Scoreboard");

        // Show the initial scene (e.g., RoundOne)
        switchToPanel("Round");
    }

    public void updateDrawPanel (Card card){
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

    private void initializeGame(){
        Deck rangeDeck = DeckUtils.initializeNumbers();
        Deck allDeck = DeckUtils.initializeWhole();

        ArrayList<Player> playersList = new ArrayList<>();
        
        for(int i = 0; i < maxPlayers; i++){
            int playerIndex = i + 1;
            playersList.add(new Player(playerIndex, initialPoints, new PlayerHand()));
        }

        playersList.forEach(p -> DeckUtils.dealRange(rangeDeck, p.getHand(), numOfCards));

        int roundCount = 1;
        boolean finishTurn = false;
        ArrayList<Card> selectedCards = new ArrayList<>();
        this.gameState = new GameState(playersList, allDeck, rangeDeck, roundCount, selectedCards, finishTurn);
    }
}
