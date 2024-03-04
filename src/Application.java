//Compile.bat and Run.bat runs this file

import java.util.List;

import javax.swing.SwingUtilities;

import GUI.Window;

public class Application {

    public static void initialize(){
        //initializing deck of 52
        Deck d1 = new Deck();
        Rank.setKingHigh();
        List r = Rank.VALUES_NUMBER;
        List s = Suit.VALUES;

        //adding cards to deck
        for(int i = 0; i < 4; i++){
            for(int j = 0; j < r.size(); j++){
                d1.addCard(new Card((Suit)s.get(i), (Rank)r.get(j), null));
            }
        }
        
        d1.shuffle();

        for(int i = 0; i < 6; i++){
            System.out.println(d1.dealCard().toString());
        }
    }
    public static void runGUI (){
        SwingUtilities.invokeLater(() -> {
            // Window mainWindow = new Window();
            Window.showWindow();
            // mainWindow.setVisible(true);
        });
    }
    public static void main(String[] args) {
        // initialize();
        runGUI();   
    }
}
