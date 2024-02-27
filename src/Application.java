//Compile.bat and Run.bat runs this file

import javax.swing.SwingUtilities;

import GUI.Window;

public class Application {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                Window mainWindow = new Window();
                mainWindow.setVisible(true);
            }
        });
    }
}
