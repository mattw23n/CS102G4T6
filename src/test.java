import javax.swing.*;  
import java.awt.*;
public class test {
    
    public static void main(String[] args) {  
    JFrame f=new JFrame();//creating instance of JFrame  
            
    // JButton b=new JButton("click");//creating instance of JButton  
    
    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    JButton b = new JButton(new ImageIcon("2c.gif"));
    b.setBounds(50,100,90, 30);//x axis, y axis, width, height  
    f.getContentPane().add(b);
    f.pack();      
    f.setVisible(true);
            
    f.add(b);//adding button in JFrame  
            
    f.setSize(800,500);//400 width and 500 height  
    f.setLayout(null);//using no layout managers  
    f.setVisible(true);//making the frame visible  
    }  


}
