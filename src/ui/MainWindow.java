package ui;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class MainWindow extends JFrame{
    public MainWindow() {
        // Set up the Window window
        setTitle("My Swing App");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center the window
        // Create a button
        JButton button = new JButton("Click Me");
        // Add the button to the Window
        add(button);
    }
    public static void main(String[] args) {
        // Create and show the GUI
            SwingUtilities.invokeLater(() -> {
            MainWindow gui = new MainWindow();
            gui.setVisible(true);
        });
    }
}
