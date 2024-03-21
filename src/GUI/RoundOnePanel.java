package GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;
import javax.swing.*;

public class RoundOnePanel extends JPanel{
    private JButton nextButton;

    public RoundOnePanel() {
        setLayout(new BorderLayout());

        // Colours Used (Can change later)
        Color background = new Color(98, 171, 55);
        Color textColor = new Color(244, 250, 255);

        // Create GridBagLayout
        GridBagLayout GridBagLayoutGrid = new GridBagLayout();
        GridBagConstraints GridConstraints = new GridBagConstraints();

        // Create components for RoundOnePanel
        JLabel titleLabel = new JLabel("Round One");
        titleLabel.setHorizontalAlignment(SwingConstants.LEFT);
        add(titleLabel, BorderLayout.NORTH);
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(GridBagLayoutGrid);
        contentPanel.setBackground(background);

        // Add content to RoundOnePanel
        JLabel descriptionLabel = new JLabel("Description of Round One");
        descriptionLabel.setForeground(textColor);
        GridConstraints.weightx = 0.1;
        GridConstraints.gridx = 0;
        GridConstraints.gridy = 0;
        contentPanel.add(descriptionLabel, GridConstraints);

        JLabel pointLabel = new JLabel("Score");
        pointLabel.setForeground(textColor);
        GridConstraints.gridx = 5;
        GridConstraints.gridy = 0;
        contentPanel.add(pointLabel, GridConstraints);

        JLabel p1PointsLabel = new JLabel("Player 1: ");
        p1PointsLabel.setForeground(textColor);
        GridConstraints.gridx = 4;
        GridConstraints.gridy = 2;
        contentPanel.add(p1PointsLabel, GridConstraints);

        JLabel p2PointsLabel = new JLabel("Player 2: ");
        p2PointsLabel.setForeground(textColor);
        GridConstraints.gridx = 6;
        GridConstraints.gridy = 2;
        contentPanel.add(p2PointsLabel, GridConstraints);

        JLabel p3PointsLabel = new JLabel("Player 3: ");
        p3PointsLabel.setForeground(textColor);
        GridConstraints.gridx = 4;
        GridConstraints.gridy = 3;
        contentPanel.add(p3PointsLabel, GridConstraints);

        JLabel p4PointsLabel = new JLabel("Player 4: ");
        p4PointsLabel.setForeground(textColor);
        GridConstraints.gridx = 6;
        GridConstraints.gridy = 3;
        contentPanel.add(p4PointsLabel, GridConstraints);

        // nextButton = new JButton();
        // Set image as "next" button
        ImageIcon nextIcon = new ImageIcon("images/next.png");
        Image nextIconImage = nextIcon.getImage();
        Image scaledImage = nextIconImage.getScaledInstance(200, 100, java.awt.Image.SCALE_SMOOTH);
        nextIcon = new ImageIcon(scaledImage);
        JButton nextButton = new JButton(nextIcon);
        nextButton.setBorder(null);

        // GridConstraints.weightx = 0.5;
        GridConstraints.gridx = 0;
        GridConstraints.gridy = 2;
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
                    // Switch to IntermediatePanel
                    gamePanel.switchToPanel("Intermediate");
                }
            }
        });

        // Create and display multiple images
        JPanel imagePanel = new JPanel(new FlowLayout(FlowLayout.LEFT)); // Example grid layout with 2x2 images
        imagePanel.setBackground(background);
        // imagePanel.setBorder(BorderFactory.createEmptyBorder());

        //Testing Multiple Images
        for (int i = 1; i <= 4; i++) {
            JLabel imageLabel = new JLabel();
            imageLabel.setBackground(background);
            // Set a unique identifier for each image label
            imageLabel.setName("Image" + i);
            // Set the image (using a relative path)
            setImage(imageLabel, "images/" + i + "c.gif"); // Adjust image file names
            // Add a mouse listener to each image label
            imageLabel.addMouseListener(new MouseListener(imageLabel.getName())); // Attach the mouse listener
            // Add the image label to the panel
            imagePanel.add(imageLabel);
        }
        GridConstraints.gridx = 0;
        GridConstraints.gridy = 3;
        contentPanel.add(imagePanel, GridConstraints);
    }
    private static void setImage (JLabel label, String imagePath){
        ImageIcon icon = new ImageIcon(imagePath);
        label.setIcon(icon);
    }
}