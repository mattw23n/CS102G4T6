//Compile.bat and Run.bat runs this file

import javax.swing.SwingUtilities;

import GUI.Window;

public class Application {
    public static void runGUI (){
        // SwingUtilities.invokeLater(() -> {
            Window mainWindow = new Window();
            mainWindow.setVisible(true);
        // });
    }
    public static void main(String[] args) {
        runGUI();
    }
}
