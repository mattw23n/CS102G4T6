package GUI.Panel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import components.Player;

public class IntermediatePanel extends JPanel {
    private JButton nextButton;
    private Scoreboard scoreBoard;
    private Player nextPlayer;
    private GameState gameState;
    private JLabel descriptionLabel;

    public IntermediatePanel(GameState gameState) {
        this.gameState = gameState;
        initialize();
    }
    private void initialize(){
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
        // JLabel titleLabel = new JLabel("Round One");
        // titleLabel.setHorizontalAlignment(SwingConstants.LEFT);
        // add(titleLabel, BorderLayout.NORTH);
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(GridBagLayoutGrid);
        contentPanel.setBackground(background);


        // Add content to RoundOnePanel
        descriptionLabel = new JLabel("Pass on to next player");
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

        GridConstraints.gridx = 0;
        GridConstraints.gridy = 3;
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
