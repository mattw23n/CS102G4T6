package GUI;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class Window{
    public static void showWindow() {
        // Create a JFrame
        JFrame frame = new JFrame("My Application");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Create and display multiple images
        JPanel imagePanel = new JPanel(new FlowLayout(FlowLayout.LEFT)); // Example grid layout with 2x2 images
        for (int i = 1; i <= 4; i++) {
            JLabel imageLabel = new JLabel();
            // Set a unique identifier for each image label
            imageLabel.setName("Image" + i);
            // Set the image (using a relative path)
            setImage(imageLabel, "images/" + i + "c.gif"); // Adjust image file names
            // Add a mouse listener to each image label
            addMouseListener(imageLabel);
            // Add the image label to the panel
            imagePanel.add(imageLabel);
        }
        
        // Add the image label to the content pane
        frame.getContentPane().add(imagePanel, BorderLayout.CENTER);
        
        // Set frame size and make it visible
        frame.setSize(400, 300); // Adjust size as needed
        frame.setVisible(true);
        
        // System.out.println(System.getProperty("user.dir"));
         
    }
    private static void setImage (JLabel label, String imagePath){
        ImageIcon icon = new ImageIcon(imagePath);
        label.setIcon(icon);
    }
    private static void addMouseListener(JLabel label) {
        // Add a mouse listener to the label
        label.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Handle mouse click event
                System.out.println("Image clicked: " + label.getName());
            }
        });
    }
    
}
