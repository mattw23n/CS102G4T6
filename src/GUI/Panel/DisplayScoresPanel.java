package GUI.Panel;

import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.awt.GridBagConstraints;
import java.awt.Color;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import GUI.MainWindow;
import GUI.Listener.ButtonListener;
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
        initialize();
    }
    private void initialize(){
        setLayout(new BorderLayout());

        // Colours Used (Can change later)
        Color background = new Color(27, 109, 50);
        Color textColor = new Color(244, 250, 255);
        
        // Panel creation
        JPanel mainPanel = new JPanel();
        BoxLayout boxLayout = new BoxLayout(mainPanel, BoxLayout.Y_AXIS);
        mainPanel.setLayout(boxLayout);
        mainPanel.setBackground(background);

        GridBagLayout GridBagLayoutGrid = new GridBagLayout();
        GridBagConstraints GridConstraints = new GridBagConstraints();

        // Create components for RoundOnePanel
        contentPanel = new JPanel();
        contentPanel.setLayout(GridBagLayoutGrid);
        contentPanel.setBackground(background);
        
        ImageIcon titleIcon = new ImageIcon("images/finalScore.png");
        java.awt.Image titleIconImage = titleIcon.getImage();
        java.awt.Image scaledTitleImage = titleIconImage.getScaledInstance(500, 150, java.awt.Image.SCALE_SMOOTH);
        titleIcon = new ImageIcon(scaledTitleImage);
        JLabel title = new JLabel(titleIcon);
        GridConstraints.gridx = 0;
        GridConstraints.gridy = 0;
        contentPanel.add(title, GridConstraints);

        scoreBoard = new Scoreboard();
        scoreBoard.setBackground(background);

        GridConstraints.gridx = 0;
        GridConstraints.gridy = 1;
        contentPanel.add(scoreBoard, GridConstraints);
        
        winLabel = new JLabel();

        // winLabel = new JLabel("Player " + gameState.getCurrPlayer().getPlayerID() + " Wins");
        // winLabel.setFont(new Font("Segoe UI", Font.PLAIN, 40));
        // winLabel.setForeground(textColor);
        GridConstraints.gridx = 0;
        GridConstraints.gridy = 2;
        contentPanel.add(winLabel, GridConstraints);

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
                getTopLevelAncestor().setVisible(false);
            }
        });

        GridConstraints.gridx = 0;
        GridConstraints.gridy = 3;
        contentPanel.add(exitButton, GridConstraints);

        add(contentPanel, BorderLayout.CENTER);
        contentPanel.repaint();
        contentPanel.revalidate();

    }
    // public void refreshScoreboard() {
    //     scoreBoard.updateScore(1, gameState.getPlayersList().get(0).getPoints());
    //     scoreBoard.updateScore(2, gameState.getPlayersList().get(1).getPoints());
    //     scoreBoard.updateScore(3, gameState.getPlayersList().get(2).getPoints());
    //     scoreBoard.updateScore(4, gameState.getPlayersList().get(3).getPoints());
    //     scoreBoard.repaint();
    //     scoreBoard.revalidate();
    // }
    public void refreshScoreboard() {
        gameState.printScores();
        System.out.println("ScoreBoard");
        System.out.println("-------------------");
        ArrayList<Integer> playerScores = gameState.getPlayerScores();
        for (int i = 0; i < playerScores.size(); i++) {
            scoreBoard.updateScore(i + 1, playerScores.get(i));
            System.out.println(playerScores.get(i));
        }
        System.out.println("-------------------");
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
