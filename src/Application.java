//Compile.bat and Run.bat runs this file

import javax.swing.SwingUtilities;

import ui.MainWindow;

public class Application {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainWindow window = new MainWindow();
            window.setVisible(true);
        });
    }
}
