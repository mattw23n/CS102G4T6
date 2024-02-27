package GUI;

import javax.swing.JButton;
import javax.swing.JFrame;

public class Window extends JFrame {
    public Window (){
        setTitle("My Swing App");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        JButton button = new JButton("Click Me");
        add(button);
    }
}
