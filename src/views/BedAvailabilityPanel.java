package views;

import javax.swing.*;
import java.awt.*;

public class BedAvailabilityPanel extends JPanel {

    public BedAvailabilityPanel() {
        setLayout(new BorderLayout(20, 20));
        setBackground(Color.WHITE);

        // Panel title with icon
        JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        titlePanel.setBackground(Color.WHITE);
        JLabel iconLabel = new JLabel("\uD83D\uDECF"); // Bed icon emoji as a placeholder
        iconLabel.setFont(new Font("SansSerif", Font.PLAIN, 18));
        JLabel titleLabel = new JLabel("Bed Availability");
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 18));
        titlePanel.add(iconLabel);
        titlePanel.add(titleLabel);
        add(titlePanel, BorderLayout.NORTH);

        // Main availability info panel
        JPanel availabilityPanel = new JPanel(new GridLayout(1, 2, 20, 20));
        availabilityPanel.setBackground(Color.WHITE);

        // Available Beds
        JPanel availablePanel = new JPanel(new BorderLayout());
        availablePanel.setBackground(new Color(230, 255, 230)); // Light green background
        JLabel availableLabel = new JLabel("Available", SwingConstants.CENTER);
        availableLabel.setFont(new Font("SansSerif", Font.BOLD, 16));
        JLabel availableCount = new JLabel("35", SwingConstants.CENTER);
        availableCount.setFont(new Font("SansSerif", Font.BOLD, 24));
        availableCount.setForeground(new Color(0, 128, 0));
        availablePanel.add(availableLabel, BorderLayout.NORTH);
        availablePanel.add(availableCount, BorderLayout.CENTER);

        // Occupied Beds
        JPanel occupiedPanel = new JPanel(new BorderLayout());
        occupiedPanel.setBackground(new Color(230, 240, 255)); // Light blue background
        JLabel occupiedLabel = new JLabel("Occupied", SwingConstants.CENTER);
        occupiedLabel.setFont(new Font("SansSerif", Font.BOLD, 16));
        JLabel occupiedCount = new JLabel("165", SwingConstants.CENTER);
        occupiedCount.setFont(new Font("SansSerif", Font.BOLD, 24));
        occupiedCount.setForeground(new Color(0, 102, 204));
        occupiedPanel.add(occupiedLabel, BorderLayout.NORTH);
        occupiedPanel.add(occupiedCount, BorderLayout.CENTER);

        availabilityPanel.add(availablePanel);
        availabilityPanel.add(occupiedPanel);
        add(availabilityPanel, BorderLayout.CENTER);

        // ICU and Emergency bed details
        JPanel detailsPanel = new JPanel(new GridLayout(2, 1, 10, 10));
        detailsPanel.setBackground(Color.WHITE);

        // ICU Beds
        JPanel icuPanel = new JPanel(new BorderLayout());
        icuPanel.setBackground(new Color(245, 245, 245)); // Light gray background
        JLabel icuLabel = new JLabel("ICU Beds Available:");
        icuLabel.setFont(new Font("SansSerif", Font.PLAIN, 14));
        JLabel icuCount = new JLabel("5/20", SwingConstants.RIGHT);
        icuCount.setFont(new Font("SansSerif", Font.BOLD, 14));
        icuPanel.add(icuLabel, BorderLayout.WEST);
        icuPanel.add(icuCount, BorderLayout.EAST);

        // Emergency Beds
        JPanel emergencyPanel = new JPanel(new BorderLayout());
        emergencyPanel.setBackground(new Color(245, 245, 245)); // Light gray background
        JLabel emergencyLabel = new JLabel("Emergency Beds Available:");
        emergencyLabel.setFont(new Font("SansSerif", Font.PLAIN, 14));
        JLabel emergencyCount = new JLabel("3/15", SwingConstants.RIGHT);
        emergencyCount.setFont(new Font("SansSerif", Font.BOLD, 14));
        emergencyPanel.add(emergencyLabel, BorderLayout.WEST);
        emergencyPanel.add(emergencyCount, BorderLayout.EAST);

        detailsPanel.add(icuPanel);
        detailsPanel.add(emergencyPanel);
        add(detailsPanel, BorderLayout.SOUTH);

        // Add padding around the main content
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
    }
}
