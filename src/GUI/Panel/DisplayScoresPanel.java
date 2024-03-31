package GUI.Panel;
/*
 * DisplayScoresPanel.java
 * 
 * DisplayScorePanel handles displaying the total tally of scores at the end of the game
 * 
 * 
 */

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import javax.swing.*;

import GUI.MainWindow;
import components.Player;
import components.PlayerComparator;

public class DisplayScoresPanel extends JPanel{
    private Scoreboard scoreBoard;
    private JPanel contentPanel;
    private GameState gameState;
    private JLabel winLabel;
    private String filepath;
    private MainWindow mainWindow;

    public DisplayScoresPanel (GameState gameState){
        this.gameState = gameState;
        this.scoreBoard = scoreBoard;
        this.mainWindow = mainWindow;
        initializeDisplayScore();
    }
    
    private void initializeDisplayScore(){
        setLayout(new BorderLayout());

        // Colours Used (Can change later)
        Color background = new Color(27, 109, 50);
        Color textColor = new Color(244, 250, 255);
        
        // Panel creation
        JPanel mainPanel = new JPanel();
        BoxLayout boxLayout = new BoxLayout(mainPanel, BoxLayout.Y_AXIS);
        mainPanel.setLayout(boxLayout);
        mainPanel.setBackground(background);

        GridBagLayout gridBagLayoutGrid = new GridBagLayout();
        GridBagConstraints gridConstraints = new GridBagConstraints();

        // Create components for RoundOnePanel
        contentPanel = new JPanel();
        contentPanel.setLayout(gridBagLayoutGrid);
        contentPanel.setBackground(background);
        
        ImageIcon titleIcon = new ImageIcon("images/finalScore.png");
        java.awt.Image titleIconImage = titleIcon.getImage();
        java.awt.Image scaledTitleImage = titleIconImage.getScaledInstance(500, 150, java.awt.Image.SCALE_SMOOTH);
        titleIcon = new ImageIcon(scaledTitleImage);
        JLabel title = new JLabel(titleIcon);
        gridConstraints.gridx = 0;
        gridConstraints.gridy = 0;
        contentPanel.add(title, gridConstraints);

        scoreBoard = new Scoreboard();
        scoreBoard.setBackground(background);

        gridConstraints.gridx = 0;
        gridConstraints.gridy = 1;
        contentPanel.add(scoreBoard, gridConstraints);
        
        winLabel = new JLabel();
        
        gridConstraints.gridx = 0;
        gridConstraints.gridy = 2;
        contentPanel.add(winLabel, gridConstraints);

        // Set image as "exit" button
        ImageIcon exitIcon = new ImageIcon("images/exit.png");
        java.awt.Image exitIconImage = exitIcon.getImage();
        java.awt.Image scaledImage = exitIconImage.getScaledInstance(100, 50, java.awt.Image.SCALE_SMOOTH);
        exitIcon = new ImageIcon(scaledImage);
        JButton exitButton = new JButton(exitIcon);
        exitButton.setBorder(null);

        exitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Close the current frame (DisplayScoresPanel)
                System.exit(0);
            }
        });

        gridConstraints.gridx = 0;
        gridConstraints.gridy = 3;
        contentPanel.add(exitButton, gridConstraints);

        add(contentPanel, BorderLayout.CENTER);
        contentPanel.repaint();
        contentPanel.revalidate();

    }

    public void refreshScoreboard() {
        ArrayList<Integer> playerScores = gameState.getPlayerScores();
        for (int i = 0; i < playerScores.size(); i++) {
            scoreBoard.updateScore(i + 1, playerScores.get(i));
        }
        scoreBoard.repaint();
        scoreBoard.revalidate();
    }
    
    public void setFilepath(GameState gameState) {
        Collections.sort(gameState.getPlayersList(), new PlayerComparator());
        if (gameState.getPlayersList().isEmpty()){
            this.filepath = "images/noWinner.png";
        } else if (gameState.getPlayersList().size() == 1){
            this.filepath = "images/p" + gameState.getPlayersList().get(0).getPlayerID() + "Wins.png";
        } 
        else if((gameState.getPlayersList().get(0).getPoints()) == (gameState.getPlayersList().get(1).getPoints())) {
            this.filepath = "images/tie.png";
        } else {
            this.filepath = "images/p" + gameState.getPlayersList().get(0).getPlayerID() + "Wins.png";
        }
        
        System.out.println("filepath = " + filepath);
        ImageIcon winIcon = new ImageIcon(filepath);
        java.awt.Image winIconImage = winIcon.getImage();
        java.awt.Image scaledWinImage = winIconImage.getScaledInstance(350, 100, java.awt.Image.SCALE_SMOOTH);
        winIcon.setImage(scaledWinImage);
        winLabel.setIcon(winIcon);
    }
}
