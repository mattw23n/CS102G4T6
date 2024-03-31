package GUI.Panel;
/*
 * RoundPanel.java
 * 
 * RoundPanel handles the display of each round, mainly the player's Hand.
 * 
 */
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;

import components.Card;
import components.Hand;
import components.Player;

public class RoundPanel extends JPanel{
    private JButton nextButton;
    private Scoreboard scoreBoard;
    private GameState gameState;
    private Player currPlayer;
    private int[] numOfClickedCards = {0};
    private ArrayList<Card> selection;
    private int numOfCardsToSelect = 2;

    // private JPanel contentPanel;
    private JLabel descriptionLabel;
    private JPanel handPanel;

    public RoundPanel(GameState gameState) {
        this.gameState = gameState;
        this.currPlayer = gameState.getCurrPlayer();
        this.selection = new ArrayList<>();
        initializeRoundRoundRound();
    }
    private void initializeRoundRoundRound(){
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

        // Create components for RoundOnePanel
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(gridBagLayoutGrid);
        contentPanel.setBackground(background);

        // Add content to RoundOnePanel
        descriptionLabel = new JLabel("Round " + gameState.getRound() 
            + " Player " + currPlayer.getPlayerID() +": Pick " 
            + numOfCardsToSelect +  " Cards");

        descriptionLabel.setFont(new Font("Segoe UI", Font.PLAIN, 40));
        descriptionLabel.setForeground(textColor);
        gridConstraints.weightx = 0.1;
        gridConstraints.gridx = 0;
        gridConstraints.gridy = 0;
        contentPanel.add(descriptionLabel, gridConstraints);

        scoreBoard = new Scoreboard();
        scoreBoard.setBackground(background);

        mainPanel.add(scoreBoard);
        mainPanel.add(contentPanel);

        // Set image as "next" button
        ImageIcon nextIcon = new ImageIcon("images/next.png");
        Image nextIconImage = nextIcon.getImage();
        Image scaledImage = nextIconImage.getScaledInstance(200, 100, java.awt.Image.SCALE_SMOOTH);
        nextIcon = new ImageIcon(scaledImage);
        JButton nextButton = new JButton(nextIcon);
        nextButton.setBorder(null);

        // gridConstraints.weightx = 0.5;
        gridConstraints.gridx = 0;
        gridConstraints.gridy = 3;
        contentPanel.add(nextButton, gridConstraints);

        // Create and display multiple images
        handPanel = new JPanel(new FlowLayout(FlowLayout.LEFT)); // Example grid layout with 2x2 images
        handPanel.setBackground(background);
        setHandPanel(gameState);

        add(mainPanel, BorderLayout.CENTER);
        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (numOfClickedCards[0] != 2) {
                    JOptionPane.showMessageDialog(contentPanel, "Select 2 cards only.", "Selection Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    Container parent = getParent();
                    if (parent instanceof GamePanel) {
                        GamePanel gamePanel = (GamePanel) parent;
                        gamePanel.updateTurnPanel();
                        gamePanel.switchToPanel("Turn");
                        gameState.setSelectedCards(selection);
                        // return; // Exit the actionPerformed method after switching panels
                    }
                }         
            repaint();
            revalidate();
            }
        });

        gridConstraints.gridx = 0;
        gridConstraints.gridy = 2;
        contentPanel.add(handPanel, gridConstraints);
    }

    public void setDescriptionLabel(GameState gameState) {
        this.descriptionLabel.setText("Round " + gameState.getRound() + " Player " 
            + gameState.getCurrPlayer().getPlayerID() +": Pick 2 Cards");
    }

    public void setHandPanel(GameState gameState){
        selection.clear();
        handPanel.removeAll();
        handPanel.revalidate();
        handPanel.repaint();
        currPlayer = gameState.getCurrPlayer();
        Hand playerHand = currPlayer.getHand();
        numOfClickedCards[0] = 0;   // Reset the number of clicked cards
        selection = gameState.getSelectedCards();

        for (int i = 0; i < playerHand.getNumberOfCards(); i++){
            Card card = playerHand.getCard(i);
            JLabel cardImage = new JLabel();
            cardImage.setName(card.toString());
            setImage(cardImage, "images/"+ card.toString() +".gif");
            cardImage.addMouseListener(new MouseAdapter() {
                
                @Override
                public void mouseClicked(MouseEvent arg) {
                    if (cardImage.getBorder() == null) {
                        cardImage.setBorder(BorderFactory.createLineBorder(Color.white, 3));
                        selection.add(card);
                        numOfClickedCards[0]++;
                    } else {
                        cardImage.setBorder(null);
                        selection.remove(selection.indexOf(card));
                        numOfClickedCards[0]--;
                    }
                }
            });
            handPanel.add(cardImage);
        }

        gameState.setSelectedCards(selection);
        handPanel.revalidate();
        handPanel.repaint();
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