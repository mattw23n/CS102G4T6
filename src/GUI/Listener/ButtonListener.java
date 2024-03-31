package GUI.Listener;
/*
 * ButtonListener.java
 * 
 * ButtonListener handles all Button inputs
 * 
 */


import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

import GUI.Panel.GamePanel;

public class ButtonListener implements MouseListener{
    private JFrame frame;
    public ButtonListener (JFrame frame){
        this.frame= frame;
    }
    @Override
    public void mouseClicked(MouseEvent e) {
        // Handle mouse click event (e.g., left-click)
        System.out.println("Mouse clicked on the button!");
        // Create a GamePanel and add it to the main window
        GamePanel gamePanel = new GamePanel();
        switchToScreen(gamePanel);
    }
    private void switchToScreen(JPanel panel) {
        frame.getContentPane().removeAll(); // Remove all components from content pane
        frame.getContentPane().add(panel); // Add the new panel (GameScreen)
        frame.revalidate(); // Revalidate the frame to reflect changes
        frame.repaint(); // Repaint the frame
    }
    @Override
    public void mousePressed(MouseEvent e) {
        // Ignore mouse press events
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // Ignore mouse release events
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // Ignore mouse enter events
    }

    @Override
    public void mouseExited(MouseEvent e) {
        // Ignore mouse exit events
    }
}
