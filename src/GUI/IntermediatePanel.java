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

        // Create components for IntermediatePanel
        JLabel titleLabel = new JLabel("Intermediate");
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(titleLabel, BorderLayout.NORTH);

        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

        // Add content to IntermediatePanel
        JLabel descriptionLabel = new JLabel("Description of Intermediate");
        contentPanel.add(descriptionLabel);

        nextButton = new JButton("Next");
        contentPanel.add(nextButton);

        add(contentPanel, BorderLayout.CENTER);
    }
}
