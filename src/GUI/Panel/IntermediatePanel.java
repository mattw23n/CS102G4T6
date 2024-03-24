package GUI.Panel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class IntermediatePanel extends JPanel {
    private JButton nextButton;
    private Scoreboard scoreBoard;

    public IntermediatePanel() {
        initialize();
    }
    private void initialize(){
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
        // JLabel titleLabel = new JLabel("Round One");
        // titleLabel.setHorizontalAlignment(SwingConstants.LEFT);
        // add(titleLabel, BorderLayout.NORTH);
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(GridBagLayoutGrid);
        contentPanel.setBackground(background);


        // Add content to RoundOnePanel
        JLabel descriptionLabel = new JLabel("Pass on to next player");
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

        // JLabel pointLabel = new JLabel("Score");
        // pointLabel.setForeground(textColor);
        // GridConstraints.gridx = 5;
        // GridConstraints.gridy = 0;
        // contentPanel.add(pointLabel, GridConstraints);

        // JLabel p1PointsLabel = new JLabel("Player 1: ");
        // p1PointsLabel.setForeground(textColor);
        // GridConstraints.gridx = 4;
        // GridConstraints.gridy = 2;
        // contentPanel.add(p1PointsLabel, GridConstraints);

        // JLabel p2PointsLabel = new JLabel("Player 2: ");
        // p2PointsLabel.setForeground(textColor);
        // GridConstraints.gridx = 6;
        // GridConstraints.gridy = 2;
        // contentPanel.add(p2PointsLabel, GridConstraints);

        // JLabel p3PointsLabel = new JLabel("Player 3: ");
        // p3PointsLabel.setForeground(textColor);
        // GridConstraints.gridx = 4;
        // GridConstraints.gridy = 3;
        // contentPanel.add(p3PointsLabel, GridConstraints);

        // JLabel p4PointsLabel = new JLabel("Player 4: ");
        // p4PointsLabel.setForeground(textColor);
        // GridConstraints.gridx = 6;
        // GridConstraints.gridy = 3;
        // contentPanel.add(p4PointsLabel, GridConstraints);

        // nextButton = new JButton("Next");

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
                    // Switch to TurnPanel
                    gamePanel.switchToPanel("Turn");
                }
            }
        });
    }
}
