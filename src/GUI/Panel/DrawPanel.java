package GUI.Panel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import components.Card;
import components.Deck;
import components.Hand;
import components.Player;
import components.Rank;
import components.Deck;
import utilities.DeckUtils;
import utilities.Utils;

public class DrawPanel extends JPanel {
    private JButton nextButton;
    private Scoreboard scoreBoard;
    private GameState gameState;
    private Player currPlayer;
    private JPanel selectedCardsPanel;
    private ArrayList<Card> selectedCards;
    private JLabel descriptionLabel;
    private JLabel lowerBoundLabel;
    private JLabel upperBoundLabel;
    private JLabel upperBoundValueLabel;
    private JLabel lowerBoundValueLabel;
    private JLabel middleLabel;
    private JLabel middleLabel2;

    public DrawPanel(GameState gameState) {
        this.gameState = gameState;
        this.currPlayer = gameState.getCurrPlayer();
        this.selectedCards = gameState.getSelectedCards();
        initialise();
    }

    private void initialise(){
        setLayout(new BorderLayout());

        // Colours Used (Can change later)
        Color background = new Color(27, 109, 50);
        Color textColor = new Color(244, 250, 255);

        // Panel creation
        JPanel mainPanel = new JPanel();
        BoxLayout boxLayout = new BoxLayout(mainPanel, BoxLayout.Y_AXIS);
        mainPanel.setLayout(boxLayout);
        mainPanel.setBackground(background);
        
        // Create GridBagLayout
        GridBagLayout GridBagLayoutGrid = new GridBagLayout();
        GridBagConstraints GridConstraints = new GridBagConstraints();

        // Create components for Turn Panel
        JPanel contentPanel = new JPanel();
        contentPanel.setBackground(background);
        contentPanel.setLayout(GridBagLayoutGrid);
        // GridConstraints.fill = GridConstraints.HORIZONTAL;

        // Add content to Turn Panel
        System.out.println("current player = " + gameState.getCurrPlayer().getPlayerID());
        descriptionLabel = new JLabel("Player " + gameState.getCurrPlayer().getPlayerID() + "'s Turn");
        descriptionLabel.setFont(new Font("Segoe UI", Font.PLAIN, 40));
        descriptionLabel.setForeground(textColor);
        GridConstraints.weightx = 0.5;
        GridConstraints.gridx = 0;
        GridConstraints.gridy = 0;
        contentPanel.add(descriptionLabel, GridConstraints);

        scoreBoard = new Scoreboard();
        scoreBoard.setBackground(background);

        mainPanel.add(scoreBoard);
        mainPanel.add(contentPanel);

        // Create and display multiple images
        selectedCardsPanel = new JPanel(new FlowLayout(FlowLayout.LEFT)); // Example grid layout with 2x2 images
        selectedCardsPanel.setBackground(background);

        lowerBoundLabel = new JLabel(" ");
        lowerBoundLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));
        lowerBoundLabel.setForeground(textColor);
        lowerBoundLabel.setBackground(new Color(27, 109, 50));

        lowerBoundValueLabel = new JLabel(" ");
        lowerBoundValueLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));
        lowerBoundValueLabel.setForeground(textColor);
        lowerBoundValueLabel.setBackground(new Color(27, 109, 50));

        upperBoundLabel = new JLabel(" ");
        upperBoundLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));
        upperBoundLabel.setForeground(textColor);
        upperBoundLabel.setBackground(new Color(27, 109, 50));

        upperBoundValueLabel = new JLabel(" ");
        upperBoundValueLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));
        upperBoundValueLabel.setForeground(textColor);
        upperBoundValueLabel.setBackground(new Color(27, 109, 50));

        middleLabel = new JLabel(". ");
        middleLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));
        middleLabel.setForeground(background);
        middleLabel.setBackground(background);

        middleLabel2 = new JLabel(". ");
        middleLabel2.setFont(new Font("Segoe UI", Font.BOLD, 16));
        middleLabel2.setForeground(background);
        middleLabel2.setBackground(background);

        GridConstraints.gridx = 0;
        GridConstraints.gridy = 3;
        selectedCardsPanel.setVisible(true);
        contentPanel.add(selectedCardsPanel, GridConstraints);
        
        // Set image as "draw" button
        ImageIcon drawIcon = new ImageIcon("images/draw.png");
        Image drawIconImage = drawIcon.getImage();
        Image scaledImage = drawIconImage.getScaledInstance(200, 100, java.awt.Image.SCALE_SMOOTH);
        drawIcon = new ImageIcon(scaledImage);
        JButton drawButton = new JButton(drawIcon);
        drawButton.setBorder(null);

        // set image for "finish turn" button
        ImageIcon finishIcon = new ImageIcon("images/finish.png");
        Image finishIconImage = finishIcon.getImage();
        Image scaledImage2 = finishIconImage.getScaledInstance(200, 100, java.awt.Image.SCALE_SMOOTH);
        finishIcon = new ImageIcon(scaledImage2);
        JButton finishButton = new JButton(finishIcon);
        finishButton.setBorder(null);
        finishButton.setEnabled(false);

        JLabel disabledFinish = new JLabel(finishIcon);
        GridConstraints.gridx = 0;
        GridConstraints.gridy = 7;
        contentPanel.add(disabledFinish, GridConstraints);

        GridConstraints.gridx = 0;
        GridConstraints.gridy = 5;
        contentPanel.add(drawButton, GridConstraints);

        Hand currHand = gameState.getCurrPlayer().getHand();
        // Deck deck = DeckUtils.initializeWhole();
        Deck deck = gameState.getAllDeck();
        Deck boundDeck = gameState.getRangeDeck();
        
        //Listener for "draw" Button
        add(mainPanel, BorderLayout.CENTER);
        drawButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                ArrayList<Card> selectedCards = gameState.getSelectedCards();
                Card lowerCard = selectedCards.get(0);
                Card higherCard = selectedCards.get(1);
                String lower = Utils.getCardValue(lowerCard);
                String higher = Utils.getCardValue(higherCard);
                Player currPlayer = gameState.getCurrPlayer();
                gameState.getCurrPlayer().setLower(Integer.parseInt(lower));
                gameState.getCurrPlayer().setUpper(Integer.parseInt(higher));
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
                    System.out.println("dealt = " + dealtCard.toString());
                    setSelectedCardsPanel(gameState, dealtCard);

                    if (!(Utils.isWildCard(dealtCard))) {
                        int points = Utils.calculatePoints(gameState.getCurrPlayer(), dealtCard);
                        System.out.println("points = " + points);
                        gameState.getCurrPlayer().setPoints(points);
                        finishButton.setEnabled(true);
                        System.out.println("player " + gameState.getCurrPlayer().getPlayerID() + " points = " + gameState.getCurrPlayer().getPoints());
                    } else {
                            // if Q/K extend bounds
                        if (dealtCard.getRank().getSymbol().equals("q")) {
                            // extend lower by 2 but max 10
                            System.out.println("queen drawn");
                            Utils.processWildCard(gameState.getCurrPlayer(), lowerCard);
                            setLowerBoundLabel();
                            setLowerBoundValueLabel();
                            
                        }
                        if (dealtCard.getRank().getSymbol().equals("k")) {
                            // extend upper by 2 but max 10
                            System.out.println("king drawn");
                            Utils.processWildCard(gameState.getCurrPlayer(), higherCard);
                            setUpperBoundLabel();
                            setUpperBoundValueLabel();
                            
                        }
                        // if jack swap cards
                        if (dealtCard.getRank().getSymbol().equals("j")) {
                            System.out.println("jack drawn");
                            Card newDealtCard = DeckUtils.dealCard(boundDeck, currHand);
                            System.out.println("new dealt card = " + newDealtCard);

                            String[] options = {"Swap Lower Bound Card", "Swap Upper Bound Card"};
                            int choice = 0;

                            JOptionPane.showOptionDialog(null, 
                                                "Jack drawn. Swap one of your cards.", 
                                                "Jack Drawn", 
                                                JOptionPane.DEFAULT_OPTION, 
                                                JOptionPane.QUESTION_MESSAGE, 
                                                null, options, options[0]);

                            if (choice == 0) {
                                System.out.println("swap lower");
                                Utils.processJack(gameState.getCurrPlayer(), lowerCard, newDealtCard);
                            } else {
                                System.out.println("swap upper");
                                Utils.processJack(gameState.getCurrPlayer(), higherCard, newDealtCard);
                            }

                            Card newLower = Utils.stringToCard(gameState.getCurrPlayer().getLower() + "c");
                            System.out.println("new lower = " + newLower);
                            Card newHigher = Utils.stringToCard(gameState.getCurrPlayer().getUpper() + "c");
                            System.out.println("new higher = " + newHigher);

                            ArrayList<Card> newCards = new ArrayList<>();
                            newCards.add(newLower);
                            newCards.add(newHigher);
                            gameState.setSelectedCards(newCards);
                            System.out.println("new cards = " + gameState.getSelectedCards());
                            setSelectedCardsPanel(gameState, dealtCard);
                        }
                        finishButton.setEnabled(true);
                    }
                    System.out.println("DRAW CARD");
                }
                
                scoreBoard.updateScore(gameState.getCurrPlayer().getPlayerID(), gameState.getCurrPlayer().getPoints());
                
            }
        });

        GridConstraints.weightx = 0.5;
        GridConstraints.gridx = 0;
        GridConstraints.gridy = 7;
        contentPanel.add(finishButton, GridConstraints);

        //Listener for "finish turn" Button
        add(mainPanel, BorderLayout.CENTER);
        finishButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameState.moveToNextPlayer();
                finishButton.setEnabled(false);
                // Get the parent GamePanel
                Container parent = getParent();
                if (parent instanceof GamePanel) {
                    GamePanel gamePanel = (GamePanel) parent;
                    // Switch to IntermediatePanel
                    System.out.println("a" + gameState.getSelectedCards());
                    gameState.clearSelectedCards();
                    System.out.println("b" + gameState.getSelectedCards());
                    gamePanel.updateIntermediatePanel();
                    gamePanel.switchToPanel("Intermediate");                
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
        Card lowerCard= gameState.getSelectedCards().get(0);
        lowerBoundValueLabel.setText("Value: " + lowerCard.getRank().getSymbol());      
    }
    public void setUpperBoundLabel (){
        String currText = upperBoundLabel.getText();
        upperBoundLabel.setText(currText + "+2");
    }
    public void setUpperBoundValueLabel (){
        Card upperCard= gameState.getSelectedCards().get(gameState.getSelectedCards().size() - 1);
        upperBoundValueLabel.setText("Value: " + upperCard.getRank().getSymbol()); 
    }
    public void setSelectedCardsPanel (GameState gameState, Card middleCard){
        selectedCardsPanel.removeAll();
        ArrayList<Card> cards = new ArrayList<>(gameState.getSelectedCards());
        Card upperBoundCard = cards.get(1);
        cards.set(1, middleCard);
        cards.add(upperBoundCard);
        
        for (int i = 0; i < 3; i++){
        // for (Card card : cards) {
            Card card = cards.get(i);
            JPanel cardPanel = new JPanel(new BorderLayout());
            cardPanel.setBackground(new Color(27, 109, 50));
            // gameState.getCurrPlayer().getHand().removeCard(card);
            System.out.println("card = " + gameState.getSelectedCards());
            JLabel cardImage = new JLabel();
            // cardImage.addMouseListener(new MouseListener(cardImage.getName()));
            if (card == null){
                // cardImage.setName("as");
                setImage(cardImage, "images/cardback.png");
                cardPanel.add(cardImage, BorderLayout.NORTH);
                cardPanel.add(middleLabel, BorderLayout.CENTER);
                cardPanel.add(middleLabel2, BorderLayout.SOUTH);
            } else {
                // cardImage.setName(card.toString());
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
        scoreBoard.updateScore(1, gameState.getPlayersList().get(0).getPoints());
        scoreBoard.updateScore(2, gameState.getPlayersList().get(1).getPoints());
        scoreBoard.updateScore(3, gameState.getPlayersList().get(2).getPoints());
        scoreBoard.updateScore(4, gameState.getPlayersList().get(3).getPoints());
        scoreBoard.repaint();
        scoreBoard.revalidate();
    }
}
