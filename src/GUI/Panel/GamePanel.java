package GUI.Panel;

import java.awt.CardLayout;

import javax.swing.JPanel;
public class GamePanel extends JPanel {
    private CardLayout cardLayout;

    public GamePanel() {
        cardLayout = new CardLayout();
        setLayout(cardLayout);

        // Create and add each scene panel to the GamePanel
        RoundOnePanel roundOnePanel = new RoundOnePanel();
        IntermediatePanel intermediatePanel = new IntermediatePanel();
        TurnPanel turnPanel = new TurnPanel();
        // RoundTwoPanel roundTwoPanel = new RoundTwoPanel();
        DisplayScoresPanel displayScoresPanel = new DisplayScoresPanel();

        add(roundOnePanel, "RoundOne");
        add(intermediatePanel, "Intermediate");
        add(turnPanel, "Turn");
        // add(roundTwoPanel, "RoundTwo");
        add(displayScoresPanel, "Scoreboard");

        // Show the initial scene (e.g., RoundOne)
        cardLayout.show(this, "RoundOne");
    }
    public void switchToPanel(String panelName) {
        cardLayout.show(this, panelName);
    }
    //////////////////////////////
    //BELOW TO BE REMOVED//
    //////////////////////////////
    // public GamePanel( ) {
    //     // Set the layout of the panel (e.g., GridLayout, FlowLayout, etc.)
    //     setLayout(new GridLayout(2, 3)); // Example: 2 rows and 3 columns for card images

    //     // Create and display multiple images
    //     JPanel imagePanel = new JPanel(new FlowLayout(FlowLayout.LEFT)); // Example grid layout with 2x2 images
    
    //     //Testing Multiple Images
    //     for (int i = 1; i <= 4; i++) {
    //         JLabel imageLabel = new JLabel();
    //         // Set a unique identifier for each image label
    //         imageLabel.setName("Image" + i);
    //         // Set the image (using a relative path)
    //         setImage(imageLabel, "images/" + i + "c.gif"); // Adjust image file names
    //         // Add a mouse listener to each image label
    //         imageLabel.addMouseListener(new MouseListener(imageLabel.getName())); // Attach the mouse listener
    //         // Add the image label to the panel
    //         imagePanel.add(imageLabel);
    //     }
    //     add(imagePanel);
    // }
    // private static void setImage (JLabel label, String imagePath){
    //     ImageIcon icon = new ImageIcon(imagePath);
    //     label.setIcon(icon);
    // }
}
