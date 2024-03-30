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
import java.util.Collections;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import components.Card;
import components.Player;
import utilities.Utils;

public class TurnPanel extends JPanel {
    private JButton nextButton;
    private Scoreboard scoreBoard;
    private GameState gameState;
    private Player currPlayer;
    private JPanel selectedCardsPanel;
    private ArrayList<Card> selectedCards;
    private JLabel descriptionLabel;

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

        System.out.println("selected = " + selectedCards.toString());

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
        betField.setFont(new Font("Segoe UI", Font.PLAIN, 16));
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
                            gameState.getCurrPlayer().setBet(bet);
                            betButton.setEnabled(true);
                            betField.setText(null);
                            // finishButton.setEnabled(true);
                            Container parent = getParent();
                            if (parent instanceof GamePanel) {
                                GamePanel gamePanel = (GamePanel) parent;
                                // Switch to IntermediatePanel
                                gameState.getCurrPlayer().setLower(Utils.getCardValueInt(gameState.getSelectedCards().get(0)));
                                gameState.getCurrPlayer().setUpper(Utils.getCardValueInt(gameState.getSelectedCards().get(1)));
                                gamePanel.updateDrawPanel(null);
                                gamePanel.switchToPanel("Draw");
                            } 
                        } catch (NumberFormatException e) {
                            e.printStackTrace();
                        }
                    } 
                } 
            }
        });
        add(mainPanel, BorderLayout.CENTER);
        
    }
    public void setDescriptionLabel(GameState gameState) {
        this.descriptionLabel.setText("Player " + gameState.getCurrPlayer().getPlayerID() + "'s Turn");
    }
    public void setSelectedCardsPanel (GameState gameState){
        selectedCardsPanel.removeAll();
        ArrayList<Card> cards = gameState.getSelectedCards();
        Collections.sort(cards);
        for (Card card : cards) {
            gameState.getCurrPlayer().getHand().removeCard(card);
            System.out.println("card = " + gameState.getSelectedCards());
            JLabel cardImage = new JLabel();
            cardImage.setName(card.toString());
            setImage(cardImage, "images/" + card.toString() +".gif");
            // cardImage.addMouseListener(new MouseListener(cardImage.getName()));
            selectedCardsPanel.add(cardImage);
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
