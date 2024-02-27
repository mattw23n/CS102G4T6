package GUI;

import java.awt.BorderLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Window extends JFrame {
    public Window (){
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JLabel b = createCardLabel("ts.gif");
        b.setBounds(50, 100, 90, 30);
        getContentPane().add(b);
        pack();
        setSize(800, 500);
        // setLayout(null);
    }
    private JLabel createCardLabel (String imagePath){
        JLabel label = new JLabel(new ImageIcon(imagePath));
        // label.setIcon(icon);

        // Attach mouse listener to the card label
        // label.addMouseListener(new MouseAdapter() {
        //     @Override
        //     public void mouseClicked(MouseEvent e) {
        //         // Handle mouse click event
        //         System.out.println("Card clicked: " + imagePath);
        //     }
        // });
        return label;
    }
}
