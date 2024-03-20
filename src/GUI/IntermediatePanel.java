package GUI;

import javax.swing.*;
import java.awt.*;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class IntermediatePanel extends JPanel {
    private JButton nextButton;

    public IntermediatePanel() {
        setLayout(new BorderLayout());

        // Create GridBagLayout
        GridBagLayout GridBagLayoutGrid = new GridBagLayout();
        GridBagConstraints GridConstraints = new GridBagConstraints();

        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(GridBagLayoutGrid);

        // Create components for IntermediatePanel
        JLabel titleLabel = new JLabel("Round 1");
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(titleLabel, BorderLayout.NORTH);

        // Add content to IntermediatePanel
        JLabel descriptionLabel = new JLabel("Pass on to next player");
        GridConstraints.gridx = 0;
        GridConstraints.gridy = 30;
        contentPanel.add(descriptionLabel, GridConstraints);

        nextButton = new JButton("Next");
        GridConstraints.gridx = 0;
        GridConstraints.gridy = 50;
        contentPanel.add(nextButton, GridConstraints);

        add(contentPanel, BorderLayout.CENTER);
    }
}
