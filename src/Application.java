/*
 * Application.java
 * 
 * CS102 G4 T6 - InBetween++
 * 
 * InBetween++ is a modification to the classic card game of InBetween,
 * where players bet that the cards they'll draw will be in between the range.
 * However, this version implements wildcards which can dramatically alter the game.
 * 
 */

import java.util.*;
import javax.swing.SwingUtilities;
import GUI.MainWindow;

public class Application {

    public static void runGUI (){
        SwingUtilities.invokeLater(() -> {
            MainWindow mainWindow = new MainWindow();
            mainWindow.showWindow();
        });
    }

    public static void main(String[] args) {
        runGUI();
    }
}
