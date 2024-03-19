package GUI;

import java.awt.*;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;


public class MainWindow{

    public static void showWindow() {
        // Create a JFrame
        JFrame frame = new JFrame("My Application");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Create a panel for the main content
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        // Create a title label and add it to the main panel
        JLabel titleLabel = new JLabel("Card Game Application");
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        mainPanel.add(titleLabel, BorderLayout.NORTH);

        // Create a panel for the buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());

        // Create a start button
        JButton startButton = new Button("Start");
        ButtonListener buttonListener = new ButtonListener(frame);
        startButton.addMouseListener(buttonListener);
        buttonPanel.add(startButton);

        // Add the button panel to the main panel
        mainPanel.add(buttonPanel, BorderLayout.CENTER);

        // Add the main panel to the content pane
        frame.getContentPane().add(mainPanel);

        // Set frame size and make it visible
        frame.setSize(800, 600); // Adjust size as needed
        frame.setVisible(true);
                 
    }
}
