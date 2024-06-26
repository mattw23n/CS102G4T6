package GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;
import javax.swing.*;

public class TurnPanel extends JPanel {
    // private JButton nextButton;

    public TurnPanel() {
        setLayout(new BorderLayout());

        // Colours Used (Can change later)
        Color background = new Color(98, 171, 55);
        Color textColor = new Color(244, 250, 255);

        // Create GridBagLayout
        GridBagLayout GridBagLayoutGrid = new GridBagLayout();
        GridBagConstraints GridConstraints = new GridBagConstraints();

        // Create components for RoundOnePanel
        JLabel titleLabel = new JLabel("Player X's Turn");
        titleLabel.setForeground(textColor);
        titleLabel.setHorizontalAlignment(SwingConstants.LEFT);
        add(titleLabel, BorderLayout.NORTH);
        JPanel contentPanel = new JPanel();
        setBackground(background);
        setForeground(textColor);
        contentPanel.setBackground(background);
        contentPanel.setLayout(GridBagLayoutGrid);
        // GridConstraints.fill = GridConstraints.HORIZONTAL;

        // Add content to RoundOnePanel
        JLabel descriptionLabel = new JLabel("Player X's Turn");
        descriptionLabel.setForeground(textColor);
        descriptionLabel.setFont(new Font("Arial", Font.BOLD, 24));
        GridConstraints.weightx = 0.5;
        GridConstraints.gridx = 0;
        GridConstraints.gridy = 0;
        contentPanel.add(descriptionLabel, GridConstraints);

        JLabel betTitleLabel = new JLabel("Place Bet");
        betTitleLabel.setForeground(textColor);
        betTitleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        betTitleLabel.setBorder(null);
        GridConstraints.gridx = 2;
        GridConstraints.gridy = 0;
        contentPanel.add(betTitleLabel, GridConstraints);

        JTextField betField = new JTextField(10);
        GridConstraints.gridx = 2;
        GridConstraints.gridy = 3;
        contentPanel.add(betField, GridConstraints);

        // Listener for text field
        // betField.addActionListener(new ActionListener() {
            
        // });

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
        contentPanel.add(p1PointsLabel, GridConstraints);

        JLabel p2PointsLabel = new JLabel("Player 2: ");
        p2PointsLabel.setForeground(textColor);
        p2PointsLabel.setFont(new Font("Arial", Font.BOLD, 16));
        GridConstraints.gridx = 6;
        GridConstraints.gridy = 2;
        contentPanel.add(p2PointsLabel, GridConstraints);

        JLabel p3PointsLabel = new JLabel("Player 3: ");
        p3PointsLabel.setForeground(textColor);
        p3PointsLabel.setFont(new Font("Arial", Font.BOLD, 16));
        GridConstraints.gridx = 4;
        GridConstraints.gridy = 3;
        contentPanel.add(p3PointsLabel, GridConstraints);

        JLabel p4PointsLabel = new JLabel("Player 4: ");
        p4PointsLabel.setForeground(textColor);
        p4PointsLabel.setFont(new Font("Arial", Font.BOLD, 16));
        GridConstraints.gridx = 6;
        GridConstraints.gridy = 3;
        contentPanel.add(p4PointsLabel, GridConstraints);

        // Create and display multiple images
        JPanel imagePanel = new JPanel(new FlowLayout(FlowLayout.LEFT)); // Example grid layout with 2x2 images
        imagePanel.setBackground(background);
        

        // Card 1 
        JLabel card1Label = new JLabel();
        card1Label.setName("Image1");
        setImage(card1Label, "images/2h.gif");
        card1Label.addMouseListener(new MouseListener(card1Label.getName()));
        GridConstraints.gridx = 0;
        GridConstraints.gridy = 5;
        imagePanel.add(card1Label, GridConstraints);

        // Random Card from deck
        JLabel card3Label = new JLabel();
        card3Label.setName("Image3");
        setImage(card3Label, "images/6c.gif");
        card3Label.addMouseListener(new MouseListener(card3Label.getName()));
        GridConstraints.gridx = 2;
        GridConstraints.gridy = 5;
        imagePanel.add(card3Label, GridConstraints);
        
        // Card 2
        JLabel card2Label = new JLabel();
        card2Label.setName("Image2");
        setImage(card2Label, "images/9h.gif");
        card2Label.addMouseListener(new MouseListener(card2Label.getName()));
        GridConstraints.gridx = 3;
        GridConstraints.gridy = 5;
        imagePanel.add(card2Label, GridConstraints);

        //Testing Multiple Images
        // for (int i = 1; i <= 3; i++) {
        //     JLabel imageLabel = new JLabel();
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
        GridConstraints.gridy = 3;
        contentPanel.add(imagePanel, GridConstraints);

        // Finish Turn Button (not the same as nextButton?)
        // nextButton = new JButton("Finish Turn");

        // set image for "finish turn" button
        ImageIcon finishIcon = new ImageIcon("images/finish.png");
        Image finishIconImage = finishIcon.getImage();
        Image scaledImage = finishIconImage.getScaledInstance(200, 100, java.awt.Image.SCALE_SMOOTH);
        finishIcon = new ImageIcon(scaledImage);
        JButton nextButton = new JButton(finishIcon);
        nextButton.setBorder(null);

        GridConstraints.weightx = 0.5;
        GridConstraints.gridx = 2;
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
                    // Switch to IntermediatePanel
                    gamePanel.switchToPanel("RoundTwo");
                }
            }
        });
    }
    private static void setImage (JLabel label, String imagePath){
        ImageIcon icon = new ImageIcon(imagePath);
        label.setIcon(icon);
    }
}
