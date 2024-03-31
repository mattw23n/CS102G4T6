package GUI;
/*
 * MainWindow.java
 * 
 * MainWindow displays the start screen and updates the subsequent panels accordingly.
 * 
 */

import java.awt.*;
import javax.swing.*;

import GUI.Listener.ButtonListener;

public class MainWindow{
    private final JFrame frame;
    public MainWindow(){
        // Colours Used (Can change later)
        Color background = new Color(27, 109, 50);
        Color textColor = new Color(244, 250, 255);

        // Create a JFrame
        frame = new JFrame("My Application");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Create a panel for the main content
        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(background);
        mainPanel.setLayout(new GridLayout(2, 1));
        
        ImageIcon titleIcon = new ImageIcon("images/title.png");
        Image titleIconeImage = titleIcon.getImage();
        Image scaledTitleImage = titleIconeImage.getScaledInstance(600, 400, java.awt.Image.SCALE_SMOOTH);
        titleIcon = new ImageIcon(scaledTitleImage);
        JLabel title = new JLabel(titleIcon);
        mainPanel.add(title, null);

        // Create a panel for the buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(background);
        buttonPanel.setLayout(new GridBagLayout());

        // Create a start button and set image as "start" 
        ImageIcon startIcon = new ImageIcon("images/start.png");
        Image startIconImage = startIcon.getImage();
        Image scaledImage = startIconImage.getScaledInstance(200, 100, java.awt.Image.SCALE_SMOOTH);
        startIcon = new ImageIcon(scaledImage);
        JLabel startButton = new JLabel(startIcon);
        ButtonListener buttonListener = new ButtonListener(frame);
        startButton.addMouseListener(buttonListener);
        buttonPanel.add(startButton);

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
