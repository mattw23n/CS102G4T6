package GUI.Panel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import components.Card;
import components.Hand;
import components.Player;
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

        // draw card from deck and add to hand
        // Hand playerHand = currPlayer.getHand();
        // Deck.dealCard(allDeck, hand);
        //     dealCard(allDeck, hand);
        //     Card newest = hand.getCard(hand.getNumberOfCards() - 1);
        //     System.out.println("\nYOU GOT " + newest);

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

        GridConstraints.gridx = 0;
        GridConstraints.gridy = 5;
        contentPanel.add(drawButton, GridConstraints);

        Hand currHand = gameState.getCurrPlayer().getHand();
        // Deck deck = DeckUtils.initializeWhole();
        Deck deck = gameState.getAllDeck();
        
        //Listener for "draw" Button
        add(mainPanel, BorderLayout.CENTER);
        drawButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Get the parent GamePanel
                Container parent = getParent();
                if (parent instanceof GamePanel) {
                    GamePanel gamePanel = (GamePanel) parent;
                    // implement draw card feature
                    Card dealtCard = DeckUtils.dealCard(deck, currHand);
                    System.out.println("dealt = " + dealtCard.toString());
                    // updateSelectedCardsPanel(gameState, dealtCard);
                    setSelectedCardsPanel(gameState, dealtCard);
                    // if Q/K extend bounds
                    if (dealtCard.getRank().getSymbol().equals("q")) {
                        // extend lower by 2 but max 10
                        System.out.println("queen drawn");
                    }
                    if (dealtCard.getRank().getSymbol().equals("k")) {
                        // extend upper by 2 but max 10
                        System.out.println("king drawn");
                    }
                    // if jack swap cards
                    if (dealtCard.getRank().getSymbol().equals("j")) {
                        System.out.println("jack drawn");

                        String[] options = {"Swap Lower Bound Card", "Swap Upper Bound Card"};
                        int choice = 0;

                        JOptionPane.showOptionDialog(null, 
                                            "Jack drawn. Swap one of your cards.", 
                                            "Jack Drawn", 
                                            JOptionPane.DEFAULT_OPTION, 
                                            JOptionPane.QUESTION_MESSAGE, 
                                            null, options, options[0]);

                        switch (choice) {
                            case 0:
                                System.out.println("swap lower");
                                Utils.processJack(currPlayer, selectedCards.get(0), dealtCard);
                                break;
                            case 1:
                                System.out.println("swap upper");
                                break;
                            default:
                                break;
                        }
                        
                    }
                    System.out.println("DRAW CARD");
                }
                scoreBoard.updateScore(currPlayer.getPlayerID(), currPlayer().getPoints);
            }
        });



        // set image for "finish turn" button
        ImageIcon finishIcon = new ImageIcon("images/finish.png");
        Image finishIconImage = finishIcon.getImage();
        Image scaledImage2 = finishIconImage.getScaledInstance(200, 100, java.awt.Image.SCALE_SMOOTH);
        finishIcon = new ImageIcon(scaledImage2);
        JButton finishButton = new JButton(finishIcon);
        finishButton.setBorder(null);

        JLabel disabledFinish = new JLabel(finishIcon);
        GridConstraints.gridx = 0;
        GridConstraints.gridy = 7;
        contentPanel.add(disabledFinish, GridConstraints);

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
    public void setSelectedCardsPanel (GameState gameState, Card middleCard){
        selectedCardsPanel.removeAll();
        ArrayList<Card> cards = new ArrayList<>(gameState.getSelectedCards());
        Card upperBoundCard = cards.get(1);
        cards.set(1, middleCard);
        cards.add(upperBoundCard);
        
        for (Card card : cards) {
            JPanel cardPanel = new JPanel(new BorderLayout());
            // gameState.getCurrPlayer().getHand().removeCard(card);
            System.out.println("card = " + gameState.getSelectedCards());
            JLabel cardImage = new JLabel();
            // cardImage.addMouseListener(new MouseListener(cardImage.getName()));
            if (card == null){
                // cardImage.setName("as");
                setImage(cardImage, "images/cardback.png");
                cardPanel.add(cardImage, BorderLayout.NORTH);
            } else {
                // cardImage.setName(card.toString());
                if (cards.getFirst().isSameAs(card)){
                    cardPanel.add(cardImage, BorderLayout.NORTH);
                    lowerBoundLabel = new JLabel("Lower");
                    lowerBoundLabel.setFont(new Font("Segoe UI", Font.PLAIN, 28));
                    lowerBoundLabel.setBackground(new Color(27, 109, 50));
                    cardPanel.add(lowerBoundLabel, BorderLayout.SOUTH);
                } else if (cards.getLast().isSameAs(card)) {
                    cardPanel.add(cardImage,BorderLayout.NORTH);
                    upperBoundLabel = new JLabel("Upper");
                    upperBoundLabel.setFont(new Font("Segoe UI", Font.PLAIN, 28));
                    upperBoundLabel.setBackground(new Color(27, 109, 50));
                    cardPanel.add(upperBoundLabel, BorderLayout.SOUTH);
                } else {
                    cardPanel.add(cardImage);
                }
                setImage(cardImage, "images/" + card.toString() +".gif");
            }
            selectedCardsPanel.add(cardPanel);

        }
        repaint();
        revalidate();
    }
    public void updateSelectedCardsPanel (GameState gameState, Card dealtCard){
        selectedCardsPanel.removeAll();
        ArrayList<Card> cards = gameState.getSelectedCards();
        System.out.println(cards);
        cards.set(1, dealtCard);
        // Collections.swap(cards, 1, 2);
        
        System.out.println("3 cards = " + cards.toString());
        for (Card card : cards) {
            JPanel cardPanel = new JPanel(new BorderLayout());
            // gameState.getCurrPlayer().getHand().removeCard(card);
            System.out.println("card = " + gameState.getSelectedCards());
            JLabel cardImage = new JLabel();
            setImage(cardImage, "images/" + card.toString() +".gif");
            cardPanel.add(cardImage);
            selectedCardsPanel.add(cardPanel);
        }
        repaint();
        revalidate();
    }
    private static void setImage (JLabel label, String imagePath){
        ImageIcon icon = new ImageIcon(imagePath);
        label.setIcon(icon);
    }
}
