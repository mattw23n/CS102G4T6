package GUI.Panel;

import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.util.ArrayList;
import java.util.Collections;
import java.awt.GridBagConstraints;
import java.awt.Color;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import components.Player;
import components.PlayerComparator;

public class DisplayScoresPanel extends JPanel{
    private Scoreboard scoreBoard;
    private JPanel contentPanel;
    private GameState gameState;

    public DisplayScoresPanel (GameState gameState){
        this.gameState = gameState;
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
        java.awt.Image titleIconeImage = titleIcon.getImage();
        java.awt.Image scaledTitleImage = titleIconeImage.getScaledInstance(500, 150, java.awt.Image.SCALE_SMOOTH);
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
        
        ArrayList<Player> players = gameState.getPlayersList();
        Collections.sort(players, new PlayerComparator());
        Player winner = players.get(0);

        // String imageFilePath = "images/p" + winner.getPlayerID() + "Wins.png";
        ImageIcon winIcon = new ImageIcon("images/p1Wins.png");
        java.awt.Image winIconImage = winIcon.getImage();
        java.awt.Image scaledWinImage = winIconImage.getScaledInstance(350, 100, java.awt.Image.SCALE_SMOOTH);
        winIcon = new ImageIcon(scaledWinImage);
        JLabel winLabel = new JLabel(winIcon);
        GridConstraints.gridx = 0;
        GridConstraints.gridy = 2;
        contentPanel.add(winLabel, GridConstraints);

        // Set image as "exit" button
        // ImageIcon exitIcon = new ImageIcon("images/exit.png");
        // java.awt.Image exitIconImage = exitIcon.getImage();
        // java.awt.Image scaledImage = exitIconImage.getScaledInstance(100, 50, java.awt.Image.SCALE_SMOOTH);
        // exitIcon = new ImageIcon(scaledImage);
        // JButton exitButton = new JButton(exitIcon);
        // exitButton.setBorder(null);

        // GridConstraints.gridx = 0;
        // GridConstraints.gridy = 2;
        // contentPanel.add(exitButton, GridConstraints);

        add(contentPanel, BorderLayout.CENTER);

    }
}
