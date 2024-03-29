package GUI.Panel;

import java.util.*;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
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
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.BorderFactory;

import GUI.Listener.MouseListener;
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

    private JPanel contentPanel;
    private JLabel descriptionLabel;
    private JPanel handPanel;

    public RoundPanel(GameState gameState) {
        this.gameState = gameState;
        this.currPlayer = gameState.getCurrPlayer();
        this.selection = new ArrayList<>();
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

        // Create components for RoundOnePanel
        contentPanel = new JPanel();
        contentPanel.setLayout(GridBagLayoutGrid);
        contentPanel.setBackground(background);

        // Add content to RoundOnePanel
        descriptionLabel = new JLabel("Round " + gameState.getRound() + " Player " + currPlayer.getPlayerID() +": Pick 2 Cards");
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
        handPanel = new JPanel(new FlowLayout(FlowLayout.LEFT)); // Example grid layout with 2x2 images
        handPanel.setBackground(background);
        setHandPanel(gameState);

        add(mainPanel, BorderLayout.CENTER);
        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println(gameState.toString());
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

        GridConstraints.gridx = 0;
        GridConstraints.gridy = 2;
        contentPanel.add(handPanel, GridConstraints);
    }
    public void setDescriptionLabel(GameState gameState) {
        this.descriptionLabel.setText("Round " + gameState.getRound() + " Player " + gameState.getCurrPlayer().getPlayerID() +": Pick 2 Cards");;
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
        System.out.println("selection = " + gameState.getSelectedCards());
        handPanel.revalidate();
        handPanel.repaint();
    }
    private static void setImage (JLabel label, String imagePath){
        ImageIcon icon = new ImageIcon(imagePath);
        label.setIcon(icon);
    }
}