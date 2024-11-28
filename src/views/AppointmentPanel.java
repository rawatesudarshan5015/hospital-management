package views;

import controllers.AppointmentController;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class AppointmentPanel extends JPanel {
    private static final Color PRIMARY_COLOR = new Color(79, 70, 229);    // Indigo
    private static final Color BACKGROUND_COLOR = new Color(243, 244, 246); // Light gray
    private static final Color TEXT_COLOR = new Color(31, 41, 55);        // Dark gray
    private static final Color FIELD_BACKGROUND = Color.WHITE;
    private static final Font TITLE_FONT = new Font("Segoe UI", Font.BOLD, 28);
    private static final Font LABEL_FONT = new Font("Segoe UI", Font.PLAIN, 14);
    private static final Font INPUT_FONT = new Font("Segoe UI", Font.PLAIN, 14);

    private JTextField patientIdField, doctorIdField, notesField, appointmentIdField, dateField, timeField;
    private JComboBox<String> statusComboBox;

    public AppointmentPanel() {
        setLayout(new BorderLayout(20, 20));
        setBackground(BACKGROUND_COLOR);
        setBorder(BorderFactory.createEmptyBorder(30, 40, 30, 40));

        // Create main content panel
        JPanel contentPanel = new JPanel(new GridBagLayout());
        contentPanel.setBackground(BACKGROUND_COLOR);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(8, 12, 8, 12);

        // Header Panel
        JPanel headerPanel = createHeaderPanel();
        add(headerPanel, BorderLayout.NORTH);

        // Initialize fields with custom styling
        initializeFields();

        // Add components to the content panel with improved layout
        addComponentsToPanel(contentPanel, gbc);

        // Add button panel
        JPanel buttonPanel = createButtonPanel();

        // Add panels to main panel
        add(contentPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    private void initializeFields() {
        patientIdField = createStyledTextField();
        doctorIdField = createStyledTextField();
        notesField = createStyledTextField();
        appointmentIdField = createStyledTextField();
        dateField = createStyledTextField();
        timeField = createStyledTextField();

        // Configure read-only fields
        appointmentIdField.setText(generateAppointmentId());
        appointmentIdField.setEditable(false);
        dateField.setText(getCurrentDate());
        dateField.setEditable(false);
        timeField.setText(getCurrentTime());
        timeField.setEditable(false);

        // Style the combo box
        statusComboBox = new JComboBox<>(new String[]{"Scheduled", "Finished", "Postponed"});
        statusComboBox.setFont(INPUT_FONT);
        statusComboBox.setBackground(FIELD_BACKGROUND);
        statusComboBox.setBorder(createFieldBorder());
        statusComboBox.setPreferredSize(new Dimension(statusComboBox.getPreferredSize().width, 35));
    }

    private JTextField createStyledTextField() {
        JTextField field = new JTextField(15);
        field.setFont(INPUT_FONT);
        field.setBackground(FIELD_BACKGROUND);
        field.setBorder(createFieldBorder());
        field.setPreferredSize(new Dimension(field.getPreferredSize().width, 35));
        return field;
    }

    private Border createFieldBorder() {
        return BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(209, 213, 219)),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)
        );
    }

    private JPanel createHeaderPanel() {
        JPanel headerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        headerPanel.setBackground(BACKGROUND_COLOR);

        JLabel title = new JLabel("Schedule Appointment");
        title.setFont(TITLE_FONT);
        title.setForeground(PRIMARY_COLOR);

        headerPanel.add(title);
        return headerPanel;
    }

    private void addComponentsToPanel(JPanel panel, GridBagConstraints gbc) {
        // Create a sub-panel for the form with white background and rounded corners
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(Color.WHITE);
        formPanel.setBorder(BorderFactory.createCompoundBorder(
                new RoundedBorder(15),
                BorderFactory.createEmptyBorder(20, 25, 20, 25)
        ));

        addLabelAndField(formPanel, gbc, "Patient ID", patientIdField, 0);
        addLabelAndField(formPanel, gbc, "Doctor ID", doctorIdField, 1);
        addLabelAndField(formPanel, gbc, "Appointment ID", appointmentIdField, 2);
        addLabelAndField(formPanel, gbc, "Date", dateField, 3);
        addLabelAndField(formPanel, gbc, "Time", timeField, 4);
        addLabelAndField(formPanel, gbc, "Status", statusComboBox, 5);
        addLabelAndField(formPanel, gbc, "Notes", notesField, 6);

        panel.add(formPanel);
    }

    private void addLabelAndField(JPanel panel, GridBagConstraints gbc, String labelText, Component field, int row) {
        // Label
        JLabel label = new JLabel(labelText);
        label.setFont(LABEL_FONT);
        label.setForeground(TEXT_COLOR);

        gbc.gridx = 0;
        gbc.gridy = row;
        gbc.weightx = 0.3;
        gbc.anchor = GridBagConstraints.WEST;
        panel.add(label, gbc);

        // Field
        gbc.gridx = 1;
        gbc.weightx = 0.7;
        panel.add(field, gbc);
    }

    private JPanel createButtonPanel() {
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        buttonPanel.setBackground(BACKGROUND_COLOR);

        JButton scheduleButton = new JButton("Schedule Appointment");
        scheduleButton.setFont(new Font("Segoe UI", Font.BOLD, 14));
        scheduleButton.setForeground(Color.WHITE);
        scheduleButton.setBackground(PRIMARY_COLOR);
        scheduleButton.setPreferredSize(new Dimension(200, 40));
        scheduleButton.setBorder(new RoundedBorder(8));
        scheduleButton.setFocusPainted(false);
        scheduleButton.addActionListener(e -> scheduleAppointment());

        // Add hover effect
        scheduleButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                scheduleButton.setBackground(PRIMARY_COLOR.darker());
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                scheduleButton.setBackground(PRIMARY_COLOR);
            }
        });

        buttonPanel.add(scheduleButton);
        return buttonPanel;
    }

    // Custom rounded border class
    private static class RoundedBorder extends AbstractBorder {
        private final int radius;

        RoundedBorder(int radius) {
            this.radius = radius;
        }

        @Override
        public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
            Graphics2D g2d = (Graphics2D) g.create();
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2d.setColor(new Color(209, 213, 219));
            g2d.drawRoundRect(x, y, width - 1, height - 1, radius, radius);
            g2d.dispose();
        }
    }

    // Utility methods remain the same
    private String generateAppointmentId() {
        return UUID.randomUUID().toString();
    }

    private String getCurrentDate() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return dateFormat.format(new Date());
    }

    private String getCurrentTime() {
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
        return timeFormat.format(new Date());
    }

    private void scheduleAppointment() {
        String patientId = patientIdField.getText();
        String doctorId = doctorIdField.getText();
        String status = (String) statusComboBox.getSelectedItem();
        String notes = notesField.getText();

        // Call the AppointmentController with the correct parameters
        boolean success = AppointmentController.scheduleAppointment(
                patientId, doctorId, status, notes);

        if (success) {
            showStyledMessage("Appointment scheduled successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
        } else {
            showStyledMessage("Failed to schedule appointment.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }


    private void showStyledMessage(String message, String title, int messageType) {
        UIManager.put("OptionPane.messageFont", LABEL_FONT);
        UIManager.put("OptionPane.buttonFont", LABEL_FONT);
        UIManager.put("OptionPane.background", BACKGROUND_COLOR);
        UIManager.put("Panel.background", BACKGROUND_COLOR);

        JOptionPane.showMessageDialog(this, message, title, messageType);
    }
}