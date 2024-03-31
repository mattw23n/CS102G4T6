package GUI.Panel;
/*
 * DrawPanel.java
 * 
 * DrawPanel manipulates the screen when Player draws a card from the Deck.
 * It adjusts the points and cards in the Player's Hand.
 * 
 */
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;

import components.Card;
import components.Deck;
import components.Hand;
import components.Player;
import components.Rank;
import utilities.DeckUtils;
import utilities.PanelUtils;
import utilities.Utils;

public class DrawPanel extends JPanel {
    private JButton nextButton;
    private Scoreboard scoreBoard;
    private GameState gameState;
    private Player currPlayer;
    private JPanel selectedCardsPanel;
    private boolean finishTurn;
    private ArrayList<Card> selectedCards;
    private JLabel descriptionLabel;
    private JLabel lowerBoundLabel;
    private JLabel upperBoundLabel;
    private JLabel upperBoundValueLabel;
    private JLabel lowerBoundValueLabel;
    private JLabel middleLabel;
    private JLabel middleLabel2;
    private final int maxRound = 3;

    public DrawPanel(GameState gameState) {
        this.gameState = gameState;
        this.currPlayer = gameState.getCurrPlayer();
        this.selectedCards = gameState.getSelectedCards();
        initializeDraw();
    }

    private void initializeDraw(){
        setLayout(new BorderLayout());

        // Colours Used (Can change later)
        Color background = new Color(27, 109, 50);
        Color textColor = new Color(244, 250, 255);
        Font font = new Font("Segoe UI", Font.BOLD, 16);

        // Panel creation
        JPanel mainPanel = new JPanel();
        BoxLayout boxLayout = new BoxLayout(mainPanel, BoxLayout.Y_AXIS);
        mainPanel.setLayout(boxLayout);
        mainPanel.setBackground(background);
        
        // Create GridBagLayout
        GridBagLayout gridBagLayoutGrid = new GridBagLayout();
        GridBagConstraints gridConstraints = new GridBagConstraints();

        // Create components for Turn Panel
        JPanel contentPanel = new JPanel();
        contentPanel.setBackground(background);
        contentPanel.setLayout(gridBagLayoutGrid);

        // Add content to Turn Panel
        descriptionLabel = new JLabel("Player " + gameState.getCurrPlayer().getPlayerID() + "'s Turn");
        descriptionLabel.setFont(new Font("Segoe UI", Font.PLAIN, 40));
        descriptionLabel.setForeground(textColor);
        gridConstraints.gridx = 0;
        gridConstraints.gridy = 0;
        contentPanel.add(descriptionLabel, gridConstraints);

        scoreBoard = new Scoreboard();
        scoreBoard.setBackground(background);

        mainPanel.add(scoreBoard);
        mainPanel.add(contentPanel);

        // Create and display multiple images
        selectedCardsPanel = new JPanel(new FlowLayout(FlowLayout.LEFT)); // Example grid layout with 2x2 images
        selectedCardsPanel.setBackground(background);

        lowerBoundLabel = new JLabel(" ");
        PanelUtils.initializeLabel(lowerBoundLabel, font, textColor, background);

        lowerBoundValueLabel = new JLabel(" ");
        PanelUtils.initializeLabel(lowerBoundValueLabel, font, textColor, background);

        upperBoundLabel = new JLabel(" ");
        PanelUtils.initializeLabel(upperBoundLabel, font, textColor, background);

        upperBoundValueLabel = new JLabel(" ");
        PanelUtils.initializeLabel(upperBoundValueLabel, font, textColor, background);

        middleLabel = new JLabel(". ");
        PanelUtils.initializeLabel(middleLabel, font, background, background);

        middleLabel2 = new JLabel(". ");
        PanelUtils.initializeLabel(middleLabel2, font, background, background);

        gridConstraints.gridx = 0;
        gridConstraints.gridy = 3;
        selectedCardsPanel.setVisible(true);
        contentPanel.add(selectedCardsPanel, gridConstraints);
        
        // Set image as "draw" button
        ImageIcon drawIcon = new ImageIcon("images/draw.png");
        Image drawIconImage = drawIcon.getImage();
        Image scaledImage = drawIconImage.getScaledInstance(200, 100, java.awt.Image.SCALE_SMOOTH);
        drawIcon.setImage(scaledImage);
        JButton drawButton = new JButton(drawIcon);
        drawButton.setBorder(null);

        // set image for "finish turn" button
        ImageIcon finishIcon = new ImageIcon("images/finish.png");
        Image finishIconImage = finishIcon.getImage();
        Image scaledImage2 = finishIconImage.getScaledInstance(200, 100, java.awt.Image.SCALE_SMOOTH);
        finishIcon = new ImageIcon(scaledImage2);
        JButton finishButton = new JButton(finishIcon);
        finishButton.setBorder(null);

        gridConstraints.gridx = 0;
        gridConstraints.gridy = 5;
        contentPanel.add(drawButton, gridConstraints);

        Hand currHand = gameState.getCurrPlayer().getHand();
        Deck deck = gameState.getAllDeck();
        Deck boundDeck = gameState.getRangeDeck();
        
        contentPanel.repaint();
        contentPanel.revalidate();
        
        //Listener for "draw" Button
        add(mainPanel, BorderLayout.CENTER);
        drawButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (!(gameState.isFinishTurn())) {
                    ArrayList<Card> selectedCards = gameState.getSelectedCards();
                    Card lowerCard = selectedCards.get(0);
                    Card higherCard = selectedCards.get(1);

                    gameState.getCurrPlayer().setOriginalLower(lowerCard);
                    gameState.getCurrPlayer().setOriginalUpper(higherCard);
                    
                    // Get the parent GamePanel
                    Container parent = getParent();
                    if (parent instanceof GamePanel) {
                        GamePanel gamePanel = (GamePanel) parent;

                        // implement draw card feature
                        Card dealtCard = DeckUtils.dealCard(deck, currHand);
                        Card newest = currHand.getCard(currHand.getNumberOfCards() - 1);
                        currHand.removeCard(newest);
                        setSelectedCardsPanel(gameState, dealtCard);

                        if (!(Utils.isWildCard(dealtCard))) {
                            int points = Utils.calculatePoints(gameState.getCurrPlayer(), dealtCard);
                            gameState.getCurrPlayer().setPoints(points);
                            gameState.setFinishTurn(true);
                        
                        } else {
                            int wildCardCount = gameState.getCurrPlayer().getWildcardCount();
                            if (gameState.getCurrPlayer().getWildcardCount() <= 2) {
                                wildCardCount++;
                                gameState.getCurrPlayer().setWildCardCount(wildCardCount);

                                // get queen extend lower by 2 but max 1
                                if (Rank.QUEEN.getSymbol().equals(Utils.getCardValue(dealtCard))) {
                                    Utils.processWildCard(gameState.getCurrPlayer(), dealtCard);
                                    setLowerBoundLabel();
                                    setLowerBoundValueLabel();
                                }
                                // get king extend upper by 2 but max 10
                                if (Rank.KING.getSymbol().equals(Utils.getCardValue(dealtCard))) {
                                    Utils.processWildCard(gameState.getCurrPlayer(), dealtCard);
                                    setUpperBoundLabel();
                                    setUpperBoundValueLabel();
                                }
                                // if jack swap cards
                                if (Rank.JACK.getSymbol().equals(Utils.getCardValue(dealtCard))) {
                                    Card newDealtCard = DeckUtils.dealCard(boundDeck, currHand);
                                    newest = currHand.getCard(currHand.getNumberOfCards() - 1);
                                    currHand.removeCard(newest);

                                    String[] options = {"Swap Lower Bound Card", "Swap Upper Bound Card"};
                                    int choice = 0;

                                    choice = JOptionPane.showOptionDialog(null, 
                                                        "Jack drawn. Swap one of your cards.", 
                                                        "Jack Drawn", 
                                                        JOptionPane.DEFAULT_OPTION, 
                                                        JOptionPane.QUESTION_MESSAGE, 
                                                        null, options, options[0]);

                                    if (choice == 0) {
                                        Utils.processJack(gameState.getCurrPlayer(), lowerCard, newDealtCard);
                                    } else {
                                        Utils.processJack(gameState.getCurrPlayer(), higherCard, newDealtCard);
                                    }

                                    Card newLower = Utils.stringToCard(gameState.getCurrPlayer().getOriginalLower().toString());
                                    Card newHigher = Utils.stringToCard(gameState.getCurrPlayer().getOriginalUpper().toString());

                                    ArrayList<Card> newCards = new ArrayList<>();
                                    newCards.add(newLower);
                                    newCards.add(newHigher);
                                    gameState.setSelectedCards(newCards);
                                    setSelectedCardsPanel(gameState, dealtCard);
                                    setUpperBoundValueLabel();
                                    setLowerBoundValueLabel();
                                    lowerBoundLabel.setText(" ");
                                    upperBoundLabel.setText(" ");
                                }   
                            } else {
                                gameState.getCurrPlayer().setPoints(gameState.getCurrPlayer().getPoints() - 1);
                            }
                        }
                    }
                    scoreBoard.updateScore(gameState.getCurrPlayer().getPlayerID(), gameState.getCurrPlayer().getPoints());
                } else {
                    JOptionPane.showMessageDialog(mainPanel, "Your turn has finished. Press finish turn.", "Invalid Action", JOptionPane.ERROR_MESSAGE);
                }
            } 
        });

        gridConstraints.weightx = 0.5;
        gridConstraints.gridx = 0;
        gridConstraints.gridy = 7;
        contentPanel.add(finishButton, gridConstraints);

        //Listener for "finish turn" Button
        add(mainPanel, BorderLayout.CENTER);
        finishButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!gameState.isFinishTurn()){
                    JOptionPane.showMessageDialog(mainPanel, "Your turn hasn't finished. Please draw card.", "Invalid Action", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (gameState.getRound() != maxRound  || gameState.getCurrPlayer().getPlayerID() != gameState.getPlayersList().getLast().getPlayerID()) {
                    // reset wildCardCount
                    gameState.getCurrPlayer().setWildCardCount(0);
                    gameState.setFinishTurn(false);
                    
                    gridConstraints.gridx = 0;
                    gridConstraints.gridy = 5;
                    contentPanel.add(drawButton, gridConstraints);
                    // Get the parent GamePanel
                    Container parent = getParent();
                    if (parent instanceof GamePanel) {
                        GamePanel gamePanel = (GamePanel) parent;
                        // Switch to IntermediatePanel
                        gameState.clearSelectedCards();
                        
                        Player currentPlayer = gameState.getCurrPlayer();
                        gamePanel.updateIntermediatePanel();
                        gameState.moveToNextPlayer();
                        if (currentPlayer.getPoints() <= 1){
                            JOptionPane.showMessageDialog(mainPanel, "You have used up all your points, You are OUT!", "Player " 
                                + currentPlayer.getPlayerID() 
                                + "Out", JOptionPane.ERROR_MESSAGE);
                            gameState.removePlayer(currentPlayer);
                        } 
                        if (gameState.getPlayersList().size() <= 1){
                            gamePanel.updateScoresPanel();
                            gamePanel.switchToPanel("Scoreboard");
                        } else {
                            gamePanel.switchToPanel("Intermediate");    
                        }
                        lowerBoundLabel.setText(" ");
                        upperBoundLabel.setText(" ");
                    }
                } else {
                    Container parent = getParent();
                    if (parent instanceof GamePanel) {
                        GamePanel gamePanel = (GamePanel) parent;
                        // Switch to scoreboard
                        // refreshScoreboard();
                        System.out.println("Last player are you here" + gameState.getCurrPlayer().getPoints());
                        gameState.updatePlayerPoints(gameState.getCurrPlayer(), gameState.getCurrPlayer().getPoints());
                        gamePanel.updateScoresPanel();
                        gamePanel.switchToPanel("Scoreboard");   
                    }                   
                }
            }
        });
    }
    
    public void setDescriptionLabel(GameState gameState) {
        this.descriptionLabel.setText("Player " + gameState.getCurrPlayer().getPlayerID() + "'s Turn");
    }
    
    public void setLowerBoundLabel (){
        String currText = lowerBoundLabel.getText();
        lowerBoundLabel.setText(currText + "-2");
    }
    
    public void setLowerBoundValueLabel (){
        lowerBoundValueLabel.setText("Value: " + gameState.getCurrPlayer().getLower());      
    }

    public void setUpperBoundLabel (){
        String currText = upperBoundLabel.getText();
        upperBoundLabel.setText(currText + "+2");
    }

    public void setUpperBoundValueLabel (){
        upperBoundValueLabel.setText("Value: " + gameState.getCurrPlayer().getUpper()); 
    }

    public void setSelectedCardsPanel (GameState gameState, Card middleCard){
        selectedCardsPanel.removeAll();
        ArrayList<Card> cards = new ArrayList<>(gameState.getSelectedCards());
        Card upperBoundCard = cards.get(1);
        cards.set(1, middleCard);
        cards.add(upperBoundCard);
        
        for (int i = 0; i < 3; i++){
            Card card = cards.get(i);
            JPanel cardPanel = new JPanel(new BorderLayout());
            cardPanel.setBackground(new Color(27, 109, 50));
            JLabel cardImage = new JLabel();

            if (card == null){
                setImage(cardImage, "images/cardback.png");
                cardPanel.add(cardImage, BorderLayout.NORTH);
                cardPanel.add(middleLabel, BorderLayout.CENTER);
                cardPanel.add(middleLabel2, BorderLayout.SOUTH);
            } else {
                if (i == 0){
                    cardPanel.add(cardImage, BorderLayout.NORTH);
                    cardPanel.add(lowerBoundLabel, BorderLayout.CENTER);
                    cardPanel.add(lowerBoundValueLabel, BorderLayout.SOUTH);
                } else if (i == 2) {
                    cardPanel.add(cardImage,BorderLayout.NORTH);
                    cardPanel.add(upperBoundLabel, BorderLayout.CENTER);
                    cardPanel.add(upperBoundValueLabel, BorderLayout.SOUTH);
                } else {
                    cardPanel.add(cardImage, BorderLayout.NORTH);
                    cardPanel.add(middleLabel, BorderLayout.CENTER);
                    cardPanel.add(middleLabel2, BorderLayout.SOUTH);
                }
                setImage(cardImage, "images/" + card.toString() +".gif");
            }
            selectedCardsPanel.add(cardPanel);

        }
        repaint();
        revalidate();
    }

    private static void setImage (JLabel label, String imagePath){
        ImageIcon icon = new ImageIcon(imagePath);
        label.setIcon(icon);
    }

    public void refreshScoreboard() {
        ArrayList<Integer> playerScores = gameState.getPlayerScores();
        for (int i = 0; i < playerScores.size(); i++) {
            scoreBoard.updateScore(i + 1, playerScores.get(i));
        }
        scoreBoard.repaint();
        scoreBoard.revalidate();
    }
}
