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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.BorderFactory;

import GUI.Listener.MouseListener;
import components.Card;
import components.Hand;
import components.Player;

public class RoundOnePanel extends JPanel{
    private JButton nextButton;
    private Scoreboard scoreBoard;
    private GameState gameState;
    private Player currPlayer;

    public RoundOnePanel(GameState gameState) {
        this.gameState = gameState;
        this.currPlayer = gameState.getCurrPlayer();
        initialise();
    }
    private void initialise(){
        setLayout(new BorderLayout());

        // Colours Used (Can change later)
        Color background = new Color(98, 171, 55);
        Color textColor = new Color(244, 250, 255);
        
        // Panel creation
        JPanel mainPanel = new JPanel();
        BoxLayout boxLayout = new BoxLayout(mainPanel, BoxLayout.Y_AXIS);
        mainPanel.setLayout(boxLayout);
        mainPanel.setBackground(background);
        
        // Create GridBagLayout
        GridBagLayout GridBagLayoutGrid = new GridBagLayout();
        GridBagConstraints GridConstraints = new GridBagConstraints();

        // Create components for RoundOnePanel
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(GridBagLayoutGrid);
        contentPanel.setBackground(background);


        // Add content to RoundOnePanel
        JLabel descriptionLabel = new JLabel("Round " + gameState.getRound() + " Player " + gameState.getCurrPlayer() +": Pick 2 Cards");
        descriptionLabel.setFont(new Font("Segoe UI", Font.PLAIN, 40));
        descriptionLabel.setForeground(textColor);
        GridConstraints.weightx = 0.1;
        GridConstraints.gridx = 0;
        GridConstraints.gridy = 0;
        contentPanel.add(descriptionLabel, GridConstraints);

        
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

        // GridConstraints.weightx = 0.5;
        GridConstraints.gridx = 0;
        GridConstraints.gridy = 3;
        contentPanel.add(nextButton, GridConstraints);

        // Create and display multiple images
        JPanel imagePanel = new JPanel(new FlowLayout(FlowLayout.LEFT)); // Example grid layout with 2x2 images
        imagePanel.setBackground(background);
        // imagePanel.setBorder(BorderFactory.createEmptyBorder());

        // Hand playerHand = currPlayer.getHand();
        // for (int i = 0; i < playerHand.getNumberOfCards(); i++){
        //     Card card = playerHand.getCard(i);
        //     JLabel cardImage = new JLabel();
        //     // cardImage.setBackground(background);
        //     cardImage.setName(card.toString());
        //     setImage(cardImage, "images/"+ card.toString() +".gif");
        //     cardImage.addMouseListener(new MouseListener(cardImage.getName()));
        //     imagePanel.add(cardImage);
        // }
        printHand(imagePanel);
                
        //Listener for "Next" Button
        // add(mainPanel, BorderLayout.CENTER);
        // nextButton.addActionListener(new ActionListener() {
        //     @Override
        //     public void actionPerformed(ActionEvent e) {
        //         // Get the parent GamePanel
        //         Container parent = getParent();
        //         if (parent instanceof GamePanel) {
        //             GamePanel gamePanel = (GamePanel) parent;
        //             // Switch to IntermediatePanel
        //             gamePanel.switchToPanel("Intermediate");
        //             scoreBoard.updateScore(1, 5);
        //         }
        //     }
        // });

        add(mainPanel, BorderLayout.CENTER);
        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //LOGIC TEST
                System.out.println(gameState.toString());
                if (gameState.isPickingState()) {
                    // Handle picking cards state
                    Container parent = getParent();
                    if (parent instanceof GamePanel) {
                        GamePanel gamePanel = (GamePanel) parent;
                        // Switch to IntermediatePanel
                        gamePanel.switchToPanel("P"+ gameState.getCurrPlayer().getPlayerID() + "Picking");
                    }
                    // For example:
                    descriptionLabel.setText("Round " + gameState.getRound() + " Player " + gameState.getCurrPlayer().toString() + ": Pick 2 Cards");
                    // Update other UI components or game logic for picking cards
                    
                    // Switch to the next state (choosing to bet) for the same player
                    gameState.setPickingState(false);
                    gameState.setBettingState(true);
                } else if (gameState.isBettingState()) {
                    // Handle betting state
                    Container parent = getParent();
                    if (parent instanceof GamePanel) {
                        GamePanel gamePanel = (GamePanel) parent;
                        // Switch to IntermediatePanel
                        gamePanel.switchToPanel("Turn");
                    }
                    // gameState.moveToNextPlayer();
                    // For example:
                    // descriptionLabel.setText("Round " + gameState.getRound() + " Player " + gameState.getCurrPlayer().toString() + ": Choose to Bet or Not");
                    // Update other UI components or game logic for betting
                    // printHand(imagePanel);
                    gameState.moveToNextPlayer();
                    // Switch back to intermediate state for the next player
                    gameState.setPickingState(false);
                    gameState.setBettingState(false);
                    // switchToScreen
                } else if ((!gameState.isPickingState()) && (!gameState.isBettingState())) {
                    // Container parent = getParent();
                    // if (parent instanceof GamePanel) {
                    //     GamePanel gamePanel = (GamePanel) parent;
                    //     // Switch to IntermediatePanel
                    //     gamePanel.switchToPanel("Intermediate");
                    // }
                    // Switch back to picking state for the next player
                    // gameState.moveToNextPlayer();
                    gameState.setPickingState(true);
                    gameState.setBettingState(false);
                }
                //Working
                // gameState.moveToNextPlayer();
                // descriptionLabel.setText("Round 1 Player" + gameState.getCurrPlayer().toString() + ": Pick 2 Cards");
                repaint();
                revalidate();
            }
        });
        
        //Testing Multiple Images
        // for (int i = 1; i <= 4; i++) {
        //     JLabel imageLabel = new JLabel();
        //     imageLabel.setBackground(background);
        //     // Set a unique identifier for each image label
        //     imageLabel.setName("Image" + i);
        //     // Set the image (using a relative path)
        //     setImage(imageLabel, "images/" + i + "c.gif"); // Adjust image file names
        //     // Add a mouse listener to each image label
        //     imageLabel.addMouseListener(new MouseListener(imageLabel.getName())); // Attach the mouse listener
        //     // Add the image label to the panel
        //     imagePanel.add(imageLabel);
        // }
        GridConstraints.gridx = 0;
        GridConstraints.gridy = 2;
        contentPanel.add(imagePanel, GridConstraints);
    }
    private void printHand(JPanel imagePanel){
        imagePanel.removeAll();
        imagePanel.revalidate();
        imagePanel.repaint();
        currPlayer = gameState.getCurrPlayer();
        Hand playerHand = currPlayer.getHand();
        for (int i = 0; i < playerHand.getNumberOfCards(); i++){
            Card card = playerHand.getCard(i);
            JLabel cardImage = new JLabel();
            // cardImage.setBackground(background);
            cardImage.setName(card.toString());
            setImage(cardImage, "images/"+ card.toString() +".gif");
            cardImage.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent arg) {
                    cardImage.setBorder(BorderFactory.createLineBorder(Color.white, 3));
                }
                @Override
                public void mouseReleased(MouseEvent arg) {
                    cardImage.setBorder(BorderFactory.createLineBorder(Color.white, 3));
                }
            });
            imagePanel.add(cardImage);
        }
        imagePanel.revalidate();
        imagePanel.repaint();
        Container parentContainer = imagePanel.getParent();
        if (parentContainer != null) {
            parentContainer.revalidate();
            parentContainer.repaint();
        }
    }
    private static void setImage (JLabel label, String imagePath){
        ImageIcon icon = new ImageIcon(imagePath);
        label.setIcon(icon);
    }
}