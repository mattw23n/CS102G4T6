//Compile.bat and Run.bat runs this file

import java.util.List;

import javax.swing.SwingUtilities;

import GUI.MainWindow;
// import GUI.Window;

public class Application {

    public static void runGUI (){
        SwingUtilities.invokeLater(() -> {
            MainWindow mainWindow = new MainWindow();
            mainWindow.showWindow();
        });
    }

    //check if round should end early
    public static boolean endRound(ArrayList<Player> players){
        int isOutCount = 0;
        for(Player p : players){
            if(p.isOut()){
                isOutCount++;
            }
        }

        return isOutCount >= players.size() - 1;
    }
    
    public static void main(String[] args) {
        runGUI();
        
    }
}
