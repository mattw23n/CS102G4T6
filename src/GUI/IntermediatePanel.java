package GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;
import javax.swing.*;

public class IntermediatePanel extends JPanel {
    // private JButton nextButton;

    public IntermediatePanel() {
        setLayout(new BorderLayout());

        // Colours Used (Can change later)
        Color background = new Color(98, 171, 55);
        Color textColor = new Color(244, 250, 255);

        // Create GridBagLayout
        GridBagLayout GridBagLayoutGrid = new GridBagLayout();
        GridBagConstraints GridConstraints = new GridBagConstraints();

        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(GridBagLayoutGrid);
        contentPanel.setBackground(background);

        // Create components for IntermediatePanel
        JLabel titleLabel = new JLabel("Intermediate");
        titleLabel.setHorizontalAlignment(SwingConstants.LEFT);
        titleLabel.setForeground(textColor);
        add(titleLabel, BorderLayout.NORTH);
        setBackground(background);

        // Add content to IntermediatePanel
        JLabel descriptionLabel = new JLabel("Pass on to next player");
        descriptionLabel.setForeground(textColor);
        descriptionLabel.setFont(new Font("Arial", Font.BOLD, 24));
        GridConstraints.weightx = 0.2;
        GridConstraints.gridx = 0;
        GridConstraints.gridy = 0;
        contentPanel.add(descriptionLabel, GridConstraints);

        JLabel pointLabel = new JLabel("Score");
        pointLabel.setForeground(textColor);
        pointLabel.setFont(new Font("Arial", Font.BOLD, 24));
        GridConstraints.gridx = 5;
        GridConstraints.gridy = 0;
        contentPanel.add(pointLabel, GridConstraints);

        JLabel p1PointsLabel = new JLabel("Player 1: ");
        p1PointsLabel.setForeground(textColor);
        p1PointsLabel.setFont(new Font("Arial", Font.BOLD, 16));
        GridConstraints.gridx = 4;
        GridConstraints.gridy = 2;
        GridConstraints.gridheight = 2;
        contentPanel.add(p1PointsLabel, GridConstraints);

        JLabel p2PointsLabel = new JLabel("Player 2: ");
        p2PointsLabel.setForeground(textColor);
        p2PointsLabel.setFont(new Font("Arial", Font.BOLD, 16));
        GridConstraints.gridx = 6;
        GridConstraints.gridy = 2;
        GridConstraints.gridheight = 2;
        contentPanel.add(p2PointsLabel, GridConstraints);

        JLabel p3PointsLabel = new JLabel("Player 3: ");
        p3PointsLabel.setForeground(textColor);
        p3PointsLabel.setFont(new Font("Arial", Font.BOLD, 16));
        GridConstraints.gridx = 4;
        GridConstraints.gridy = 5;
        GridConstraints.gridheight = 2;
        contentPanel.add(p3PointsLabel, GridConstraints);

        JLabel p4PointsLabel = new JLabel("Player 4: ");
        p4PointsLabel.setForeground(textColor);
        p4PointsLabel.setFont(new Font("Arial", Font.BOLD, 16));
        GridConstraints.gridx = 6;
        GridConstraints.gridy = 5;
        GridConstraints.gridheight = 2;
        contentPanel.add(p4PointsLabel, GridConstraints);

        // nextButton = new JButton("Next");

        // Set image as "next" button
        ImageIcon nextIcon = new ImageIcon("images/next.png");
        Image nextIconImage = nextIcon.getImage();
        Image scaledImage = nextIconImage.getScaledInstance(200, 100, java.awt.Image.SCALE_SMOOTH);
        nextIcon = new ImageIcon(scaledImage);
        JButton nextButton = new JButton(nextIcon);
        nextButton.setBorder(null);

        GridConstraints.gridx = 0;
        GridConstraints.gridy = 5;
        contentPanel.add(nextButton, GridConstraints);

        //Listener for "Next" Button
        add(contentPanel, BorderLayout.CENTER);
        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Get the parent GamePanel
                Container parent = getParent();
                if (parent instanceof GamePanel) {
                    GamePanel gamePanel = (GamePanel) parent;
                    // Switch to TurnPanel
                    gamePanel.switchToPanel("EndGame");
                }
            }
        });
    }
}
