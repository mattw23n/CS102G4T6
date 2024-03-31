package GUI.Panel;
/*
 * IntermediatePanel.java
 * 
 * IntermediatePanel displays the panel in between turns 
 * and updates the subsequent panels accordingly.
 * 
 */


import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import components.Player;

public class IntermediatePanel extends JPanel {
    private JButton nextButton;
    // private Scoreboard scoreBoard;
    private Player nextPlayer;
    private GameState gameState;
    private JLabel descriptionLabel;

    public IntermediatePanel(GameState gameState) {
        this.gameState = gameState;
        initializeIntermediate();
    }

    private void initializeIntermediate(){
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
        // JLabel titleLabel = new JLabel("Round One");
        // titleLabel.setHorizontalAlignment(SwingConstants.LEFT);
        // add(titleLabel, BorderLayout.NORTH);
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(gridBagLayoutGrid);
        contentPanel.setBackground(background);


        // Add content to RoundOnePanel
        descriptionLabel = new JLabel("Pass on to next player");
        descriptionLabel.setFont(new Font("Segoe UI", Font.PLAIN, 40));
        descriptionLabel.setForeground(textColor);
        gridConstraints.weightx = 0.1;
        gridConstraints.gridx = 0;
        gridConstraints.gridy = 0;
        contentPanel.add(descriptionLabel, gridConstraints);

        

        // mainPanel.add(scoreBoard);
        mainPanel.add(contentPanel);
        
        // Set image as "next" button
        ImageIcon nextIcon = new ImageIcon("images/next.png");
        Image nextIconImage = nextIcon.getImage();
        Image scaledImage = nextIconImage.getScaledInstance(200, 100, java.awt.Image.SCALE_SMOOTH);
        nextIcon = new ImageIcon(scaledImage);
        JButton nextButton = new JButton(nextIcon);
        nextButton.setBorder(null);

        gridConstraints.gridx = 0;
        gridConstraints.gridy = 3;
        contentPanel.add(nextButton, gridConstraints);

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
                    gamePanel.updateRoundPanel();
                    gamePanel.switchToPanel("Round");
                }
            }
        });
    }
    
    public void setDescriptionLabelToRound(GameState gameState) {
        this.descriptionLabel.setText("Round "+ gameState.getRound());
    }

    public void setDescriptionLabelToPass(GameState gameState) {
        this.descriptionLabel.setText("Pass on to next player");
    }
}
