package GUI.Listener;
/*
 * MouseListener.java
 * 
 * MouseListener handles Mouse-related events
 * 
 */


import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MouseListener extends MouseAdapter {
    private String name;

    public MouseListener(String name) {
        this.name = name;
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
        // Handle mouse click event
        System.out.println("Image clicked: " + name);
    }
}
