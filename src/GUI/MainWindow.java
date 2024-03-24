package GUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.WindowListener;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import GUI.Listener.ButtonListener;

public class MainWindow{
    private final JFrame frame;
    public MainWindow(){
        // Colours Used (Can change later)
        Color background = new Color(98, 171, 55);
        Color textColor = new Color(244, 250, 255);

        // Create a JFrame
        frame = new JFrame("My Application");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Create a panel for the main content
        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(background);
        mainPanel.setLayout(new GridLayout(2, 1));

        // Create a title label and add it to the main panel
        JLabel titleLabel = new JLabel("Card Game Application");
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setForeground(textColor);
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));
        mainPanel.add(titleLabel, null);

        // Create a panel for the buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(background);
        buttonPanel.setLayout(new GridBagLayout());

        // Create a start button
        // JButton startButton = new Button("Start");

        // Set image as "start" button
        ImageIcon startIcon = new ImageIcon("images/start2.png");
        Image startIconImage = startIcon.getImage();
        Image scaledImage = startIconImage.getScaledInstance(200, 100, java.awt.Image.SCALE_SMOOTH);
        startIcon = new ImageIcon(scaledImage);
        JLabel startButton2 = new JLabel(startIcon);
        // startButton2.setBorder(BorderFactory.createEmptyBorder());
        ButtonListener buttonListener = new ButtonListener(frame);
        // startButton.addMouseListener(buttonListener);
        startButton2.addMouseListener(buttonListener);
        buttonPanel.add(startButton2);

        // Add the button panel to the main panel
        mainPanel.add(buttonPanel, null);

        // Add the main panel to the content pane
        frame.getContentPane().add(mainPanel);

        // Set frame size and make it visible
        frame.setSize(800, 600); // Adjust size as needed
    }
    public void showWindow() {
        frame.setVisible(true);
    }
}