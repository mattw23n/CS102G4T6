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
import javax.swing.JPanel;

import components.Card;
import components.Player;

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
    private JPanel wildStatusPanel;

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
        ImageIcon nextIcon = new ImageIcon("images/finish.png");
        Image nextIconImage = nextIcon.getImage();
        Image scaledImage = nextIconImage.getScaledInstance(200, 100, java.awt.Image.SCALE_SMOOTH);
        nextIcon = new ImageIcon(scaledImage);
        JButton nextButton = new JButton(nextIcon);
        nextButton.setBorder(null);

        GridConstraints.gridx = 0;
        GridConstraints.gridy = 5;
        contentPanel.add(nextButton, GridConstraints);

        //Listener for "Next" Button
        add(mainPanel, BorderLayout.CENTER);
        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Get the parent GamePanel
                Container parent = getParent();
                if (parent instanceof GamePanel) {
                    GamePanel gamePanel = (GamePanel) parent;
                    // Switch to IntermediatePanel
                    System.out.println("DRAW CARD");
                }
            }
        });



        // set image for "finish turn" button
        ImageIcon finishIcon = new ImageIcon("images/next.png");
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
    public void setSelectedCardsPanel (GameState gameState){
        selectedCardsPanel.removeAll();
        ArrayList<Card> cards = gameState.getSelectedCards();
        System.out.println(cards);
        cards.add(null);
        Collections.swap(cards, 1, 2);

        
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
                    lowerBoundLabel.setBackground(new Color(27, 109, 50));
                    cardPanel.add(lowerBoundLabel, BorderLayout.SOUTH);
                } else {
                    cardPanel.add(cardImage,BorderLayout.NORTH);
                    upperBoundLabel = new JLabel("Upper");
                    upperBoundLabel.setBackground(new Color(27, 109, 50));
                    cardPanel.add(upperBoundLabel, BorderLayout.SOUTH);
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
}
