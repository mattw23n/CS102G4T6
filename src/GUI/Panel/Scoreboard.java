package GUI.Panel;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class Scoreboard extends JPanel {

    private final JTable scoreTable;
    private final String[] playerNames = {"Player 1", "Player 2", "Player 3", "Player 4"};
    private int[] playerScores;

    public Scoreboard() {
        // Default scores set to 5 for each player
        playerScores = new int[]{5, 5, 5, 5};

        // Create the table model with player names and initial scores
        DefaultTableModel model = new DefaultTableModel(new Object[][]{
                {playerNames[0], playerScores[0]},
                {playerNames[1], playerScores[1]},
                {playerNames[2], playerScores[2]},
                {playerNames[3], playerScores[3]}
        }, new String[]{"Player", "Score"});

        // Create the table using the model
        scoreTable = new JTable(model);
        scoreTable.setFont(new Font("Segoe UI", Font.BOLD, 24));
        // scoreTable.setBackground(new Color(27, 109, 50));
        scoreTable.setRowHeight(24); // Set row height to 40 pixels
        scoreTable.getColumnModel().getColumn(0).setPreferredWidth(150); // Set column 0 width to 200 pixels

        scoreTable.setEnabled(false); // Disable editing table cells
        setLayout(new FlowLayout(FlowLayout.RIGHT));

        add(scoreTable);
    }
    public int getScore (int playerIndex){
        return playerScores[playerIndex];
    }
    public void updateScore(int playerIndex, int newScore) {
        playerScores[playerIndex-1] = newScore;
        ((DefaultTableModel) scoreTable.getModel()).setValueAt(newScore, playerIndex, 1);
    }
}
