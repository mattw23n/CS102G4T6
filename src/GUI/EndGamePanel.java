package GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;
import javax.swing.*;

public class EndGamePanel extends JPanel {
    // private JButton nextButton;

    public EndGamePanel() {
        setLayout(new BorderLayout());

        // Colours Used (Can change later)
        Color background = new Color(98, 171, 55);
        Color textColor = new Color(244, 250, 255);

        // Create GridBagLayout
        GridBagLayout GridBagLayoutGrid = new GridBagLayout();
        GridBagConstraints GridConstraints = new GridBagConstraints();

        // After 3 Rounds
        JLabel titleLabel = new JLabel("After 3 Rounds");
        titleLabel.setHorizontalAlignment(SwingConstants.LEFT);
        titleLabel.setForeground(textColor);
        add(titleLabel, BorderLayout.NORTH);
        JPanel contentPanel = new JPanel();
        setBackground(background);
        contentPanel.setLayout(GridBagLayoutGrid);
        contentPanel.setBackground(background);

        // Add content to RoundOnePanel
        JLabel descriptionLabel = new JLabel("After 3 Rounds");
        descriptionLabel.setForeground(textColor);
        descriptionLabel.setFont(new Font("Arial", Font.BOLD, 24));
        GridConstraints.weightx = 0.5;
        GridConstraints.gridx = 0;
        GridConstraints.gridy = 0;
        contentPanel.add(descriptionLabel, GridConstraints);

        JLabel pointsLabel = new JLabel("Score");
        pointsLabel.setForeground(textColor);
        pointsLabel.setFont(new Font("Arial", Font.BOLD, 24));
        GridConstraints.gridx = 0;
        GridConstraints.gridy = 1;
        contentPanel.add(pointsLabel, GridConstraints);
        
        JLabel p1PointsLabel = new JLabel("Player 1: ");
        p1PointsLabel.setForeground(textColor);
        p1PointsLabel.setFont(new Font("Arial", Font.BOLD, 16));
        GridConstraints.gridx = 0;
        GridConstraints.gridy = 2;
        contentPanel.add(p1PointsLabel, GridConstraints);

        JLabel p2PointsLabel = new JLabel("Player 2: ");
        p2PointsLabel.setForeground(textColor);
        p2PointsLabel.setFont(new Font("Arial", Font.BOLD, 16));
        GridConstraints.gridx = 0;
        GridConstraints.gridy = 3;
        contentPanel.add(p2PointsLabel, GridConstraints);

        JLabel p3PointsLabel = new JLabel("Player 3: ");
        p3PointsLabel.setForeground(textColor);
        p3PointsLabel.setFont(new Font("Arial", Font.BOLD, 16));
        GridConstraints.gridx = 0;
        GridConstraints.gridy = 4;
        contentPanel.add(p3PointsLabel, GridConstraints);

        JLabel p4PointsLabel = new JLabel("Player 4: ");
        p4PointsLabel.setForeground(textColor);
        p4PointsLabel.setFont(new Font("Arial", Font.BOLD, 16));
        GridConstraints.gridx = 0;
        GridConstraints.gridy = 5;
        contentPanel.add(p4PointsLabel, GridConstraints);

        //Listener for "Next" Button
        add(contentPanel, BorderLayout.CENTER);
        // nextButton.addActionListener(new ActionListener() {
        //     @Override
        //     public void actionPerformed(ActionEvent e) {
        //         // Get the parent GamePanel
        //         Container parent = getParent();
        //         if (parent instanceof GamePanel) {
        //             GamePanel gamePanel = (GamePanel) parent;
        //             // Switch to IntermediatePanel
        //             gamePanel.switchToPanel("Intermediate");
        //         }
        //     }
        // });
    }
    // private static void setImage (JLabel label, String imagePath){
    //     ImageIcon icon = new ImageIcon(imagePath);
    //     label.setIcon(icon);
    // }
}
