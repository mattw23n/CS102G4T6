package GUI.Panel;

import java.awt.FlowLayout;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class Scoreboard extends JPanel {

    private final JTable scoreTable;
    private final String[] playerNames = {"Player 1", "Player 2", "Player 3", "Player 4"};
    private int[] playerScores;
    public Scoreboard() {
        playerScores = loadScores();
        // Create the table model with player names and initial scores of 0
        DefaultTableModel model = new DefaultTableModel(new Object[][]{
                {playerNames[0], playerScores[0]},
                {playerNames[1], playerScores[1]},
                {playerNames[2], playerScores[2]},
                {playerNames[3], playerScores[3]}
        }, new String[]{"Player", "Score"});

        // Create the table using the model
        scoreTable = new JTable(model);
        // scoreTable.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        scoreTable.setEnabled(false); // Disable editing table cells
        setLayout(new FlowLayout(FlowLayout.RIGHT));

        JLabel title = new JLabel("Scoreboard");
        // Add the table to the panel
        add(title);
        add(scoreTable);
    }

    private int[] loadScores() {
        try (FileInputStream file = new FileInputStream("scores.dat");
            ObjectInputStream object = new ObjectInputStream(file)) {
            return (int[]) object.readObject();
        } catch (IOException | ClassNotFoundException e) {
            // Handle exceptions (create a new scores array with initial values)
            return new int[]{0, 0, 0, 0};
        }
    }
    // Method to update a player's score
    public void updateScore(int playerIndex, int newScore) {
        playerScores[playerIndex] = newScore;
        ((DefaultTableModel) scoreTable.getModel()).setValueAt(newScore, playerIndex, 1);
        for(int i:playerScores){
            System.out.println(i);
        }
        saveScores();
    }
    public void saveScores() {
        try (FileOutputStream file = new FileOutputStream("scores.dat");
            ObjectOutputStream object = new ObjectOutputStream(file)) {
            object.writeObject(playerScores);
        } catch (IOException e) {
            // Handle exceptions if saving fails
        }
    }
    
}
