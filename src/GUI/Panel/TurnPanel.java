package GUI.Panel;

import java.util.*;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.text.MaskFormatter;

import GUI.Listener.MouseListener;
import components.Card;
import components.Player;
import components.Deck;
import components.Hand;

public class TurnPanel extends JPanel {
    private JButton nextButton;
    private Scoreboard scoreBoard;
    private GameState gameState;
    private Player currPlayer;
    private ArrayList<Card> selectedCards;

    public TurnPanel(GameState gameState) {
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
        System.out.println("current player = " + gameState.getCurrPlayer());
        JLabel descriptionLabel = new JLabel("Player " + gameState.getCurrPlayer() + "'s Turn");
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
        JPanel imagePanel = new JPanel(new FlowLayout(FlowLayout.LEFT)); // Example grid layout with 2x2 images
        imagePanel.setBackground(background);

        System.out.println("selected = " + selectedCards.toString());

        // for (Card card : gameState.getSelectedCards()) {
        //     System.out.println("card = " + gameState.getSelectedCards());
        //     JLabel imageLabel = new JLabel();
        //     imageLabel.setName(card.toString());
        //     // System.out.println("card = " + card.toString());
        //     ImageIcon cardimg = card.getCardImage();
        //     // System.out.println("cardimg = " + cardimg);
        //     imageLabel.setIcon(cardimg);
        //     // imageLabel.addMouseListener(new MouseListener(imageLabel.getName()));
        //     imagePanel.add(imageLabel);
        // }

        // draw card from deck and add to hand
        // Hand playerHand = currPlayer.getHand();
        // Deck.dealCard(allDeck, hand);
        //     dealCard(allDeck, hand);
        //     Card newest = hand.getCard(hand.getNumberOfCards() - 1);
        //     System.out.println("\nYOU GOT " + newest);


        GridConstraints.gridx = 0;
        GridConstraints.gridy = 3;
        imagePanel.setVisible(true);
        contentPanel.add(imagePanel, GridConstraints);

        //Testing Multiple Images
        // for (int i = 0; i <= 3; i++) {
        //     JLabel imageLabel = new JLabel();
        //     // Set a unique identifier for each image label
        //     imageLabel.setName("Image" + i);
        //     // Set the image (using a relative path)
        //     setImage(imageLabel, "images/" + i + "c.gif"); // Adjust image file names
        //     // Add a mouse listener to each image label
        //     imageLabel.addMouseListener(new MouseListener(imageLabel.getName())); // Attach the mouse listener
        //     // Add the image label to the panel
        //     imagePanel.add(imageLabel);
        // }
        

        // Betting label
        JLabel betLabel = new JLabel("Make your bet");
        betLabel.setFont(new Font("Segoe UI", Font.PLAIN, 30));
        betLabel.setBackground(background);
        betLabel.setForeground(textColor);
        GridConstraints.gridx = 0;
        GridConstraints.gridy = 4;
        contentPanel.add(betLabel, GridConstraints);

        // Bet Panel
        JPanel betPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        betPanel.setBackground(background);

        // Betting field
        // try {
            // MaskFormatter mask = new MaskFormatter("#");
            // mask.setPlaceholderCharacter('_');
            // JFormattedTextField betField = new JFormattedTextField(mask);
        JTextField betField = new JTextField(5);
        betField.setBackground(Color.WHITE);
        betField.setBorder(null);
        betPanel.add(betField);
        // } catch (ParseException e) {
        //     e.printStackTrace();
        // }

        // Place Bet button
        ImageIcon betIcon = new ImageIcon("images/bet.png");
        Image betIconImage = betIcon.getImage();
        Image scaledImage2 = betIconImage.getScaledInstance(150, 75, java.awt.Image.SCALE_SMOOTH);
        betIcon = new ImageIcon(scaledImage2);
        JButton betButton = new JButton(betIcon);
        betButton.setBorder(null);
        betPanel.add(betButton);
        betButton.setActionCommand("bet");

        GridConstraints.gridx = 0;
        GridConstraints.gridy = 5;
        contentPanel.add(betPanel, GridConstraints);
        
        // Finish Turn Button
        // nextButton = new JButton("Finish Turn");

        // set image for "finish turn" button
        ImageIcon finishIcon = new ImageIcon("images/finish.png");
        Image finishIconImage = finishIcon.getImage();
        Image scaledImage = finishIconImage.getScaledInstance(200, 100, java.awt.Image.SCALE_SMOOTH);
        finishIcon = new ImageIcon(scaledImage);
        JButton finishButton = new JButton(finishIcon);
        finishButton.setBorder(null);
        finishButton.setEnabled(false);

        JLabel disabledFinish = new JLabel(finishIcon);
        GridConstraints.gridx = 0;
        GridConstraints.gridy = 7;
        contentPanel.add(disabledFinish, GridConstraints);

        GridConstraints.weightx = 0.5;
        GridConstraints.gridx = 0;
        GridConstraints.gridy = 7;
        contentPanel.add(finishButton, GridConstraints);

        // add listener to bet button
        betButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e1) {
                if ("bet".equals(e1.getActionCommand())) {
                    // get user input
                    String input = betField.getText();
                    if (input.isEmpty()) {
                        JOptionPane.showMessageDialog(mainPanel, "Please place bet", "Error", JOptionPane.ERROR_MESSAGE);
                    } else {
                        try {
                            int bet = Integer.parseInt(input);
                            currPlayer.setBet(bet);
                            betButton.setEnabled(true);
                            finishButton.setEnabled(true);
                        } catch (NumberFormatException e) {
                            e.printStackTrace();
                        }
                    } 
                } 
            }
        });

        //Listener for "finish turn" Button
        add(mainPanel, BorderLayout.CENTER);
        finishButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                betField.setText(null);
                if (gameState.getRound() != 3) {
                    gameState.moveToNextPlayer();
                    // Get the parent GamePanel
                    Container parent = getParent();
                    if (parent instanceof GamePanel) {
                        GamePanel gamePanel = (GamePanel) parent;
                        // Switch to IntermediatePanel
                        gamePanel.switchToPanel("Intermediate");
                    }
                } else {
                    // Get the parent GamePanel
                    Container parent = getParent();
                    if (parent instanceof GamePanel) {
                        GamePanel gamePanel = (GamePanel) parent;
                        // Switch to IntermediatePanel
                        gamePanel.switchToPanel("Scoreboard");
                    }
                }
                
            }
        });
    }
    private static void setImage (JLabel label, String imagePath){
        ImageIcon icon = new ImageIcon(imagePath);
        label.setIcon(icon);
    }
}
