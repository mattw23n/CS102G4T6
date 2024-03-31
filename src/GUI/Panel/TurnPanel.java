package GUI.Panel;
/*
 * TurnPanel.java
 * 
 * TurnPanel is used to display a Player's turn, mainly their betting process.
 * 
 */

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
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
import javax.swing.SwingConstants;

import components.Card;
import components.Player;
import utilities.Utils;

public class TurnPanel extends JPanel {
    private Scoreboard scoreBoard;
    private GameState gameState;
    private Player currPlayer;
    private JPanel selectedCardsPanel;
    private ArrayList<Card> selectedCards;
    private JLabel descriptionLabel;
    private JLabel betLabel;

    public TurnPanel(GameState gameState) {
        this.gameState = gameState;
        this.currPlayer = gameState.getCurrPlayer();
        this.selectedCards = gameState.getSelectedCards();
        initializeTurn();
    }

    private void initializeTurn(){
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
        GridBagLayout gridBagLayoutGrid = new GridBagLayout();
        GridBagConstraints gridConstraints = new GridBagConstraints();

        // Create components for Turn Panel
        JPanel contentPanel = new JPanel();
        contentPanel.setBackground(background);
        contentPanel.setLayout(gridBagLayoutGrid);
        // gridConstraints.fill = gridConstraints.HORIZONTAL;

        // Add content to Turn Panel
        descriptionLabel = new JLabel("Player " + gameState.getCurrPlayer().getPlayerID() + "'s Turn");
        descriptionLabel.setFont(new Font("Segoe UI", Font.PLAIN, 40));
        descriptionLabel.setForeground(textColor);
        gridConstraints.weightx = 0.5;
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


        gridConstraints.gridx = 0;
        gridConstraints.gridy = 3;
        selectedCardsPanel.setVisible(true);
        contentPanel.add(selectedCardsPanel, gridConstraints);

        // Betting label
        betLabel = new JLabel(" ");
        betLabel.setFont(new Font("Segoe UI", Font.PLAIN, 24));
        betLabel.setBackground(background);
        betLabel.setForeground(textColor);
        betLabel.setHorizontalAlignment(SwingConstants.CENTER);
        gridConstraints.gridx = 0;
        gridConstraints.gridy = 4;
        contentPanel.add(betLabel, gridConstraints);

        // Betting field
        JTextField betField = new JTextField(5);
        betField.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        betField.setBackground(Color.WHITE);
        betField.setBorder(null);
        betField.setHorizontalAlignment(JTextField.CENTER);
        gridConstraints.insets = new Insets(10, 0, 0, 0);
        gridConstraints.gridx = 0;
        gridConstraints.gridy = 5;
        contentPanel.add(betField, gridConstraints);

        // Place Bet button
        ImageIcon betIcon = new ImageIcon("images/bet.png");
        Image betIconImage = betIcon.getImage();
        Image scaledImage2 = betIconImage.getScaledInstance(150, 75, java.awt.Image.SCALE_SMOOTH);
        betIcon = new ImageIcon(scaledImage2);
        JButton betButton = new JButton(betIcon);
        betButton.setBorder(null);
        gridConstraints.gridx = 0;
        gridConstraints.gridy = 6;
        contentPanel.add(betButton, gridConstraints);
        betButton.setActionCommand("bet");

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
                            if (bet <= 0){
                                throw new NumberFormatException();
                            } else if (bet >= gameState.getCurrPlayer().getPoints()){
                                throw new NumberFormatException();
                            }
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
                            // e.printStackTrace();
                            JOptionPane.showMessageDialog(mainPanel, "Enter a valid bet", "Error", JOptionPane.ERROR_MESSAGE);
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
    public void setBetLabel(GameState gameState){
        Player currPlayer = gameState.getCurrPlayer();
        this.betLabel.setText(String.format("<html>The minimum bet is 1 and the maximum bet is %d<div style='text-align: center;'>Enter your bet:</div></html>", currPlayer.getPoints() - 1));
    }
    public void setSelectedCardsPanel (GameState gameState){
        selectedCardsPanel.removeAll();
        ArrayList<Card> cards = gameState.getSelectedCards();
        Collections.sort(cards);
        for (Card card : cards) {
            gameState.getCurrPlayer().getHand().removeCard(card);
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
        ArrayList<Integer> playerScores = gameState.getPlayerScores();
        for (int i = 0; i < playerScores.size(); i++) {
            scoreBoard.updateScore(i + 1, playerScores.get(i));
        }
        scoreBoard.repaint();
        scoreBoard.revalidate();
    }
}
