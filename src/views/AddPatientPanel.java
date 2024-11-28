package views;

import controllers.AddPatientController;

import javax.swing.*;
import java.awt.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.awt.event.*;

public class AddPatientPanel extends JPanel {
    private JTextField nameField, ageField, contactField, addressField, emailField, dobField, emergencyContactField;
    private JComboBox<String> genderComboBox, bloodGroupComboBox;
    private Color primaryColor = new Color(41, 128, 185); // Nice blue color
    private Font labelFont = new Font("Arial", Font.BOLD, 14);
    private Font fieldFont = new Font("Arial", Font.PLAIN, 14);

    public AddPatientPanel() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 10);

        // Set background color
        setBackground(new Color(245, 245, 245)); // Light gray background

        // Title
        JLabel title = new JLabel("Add New Patient");
        title.setFont(new Font("Arial", Font.BOLD, 28));
        title.setForeground(primaryColor);
        title.setHorizontalAlignment(SwingConstants.CENTER);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        add(title, gbc);

        // Subtitle
        JLabel subtitle = new JLabel("Please fill in the patient details below");
        subtitle.setFont(new Font("Arial", Font.PLAIN, 16));
        subtitle.setForeground(Color.GRAY);
        gbc.gridy = 1;
        gbc.insets = new Insets(0, 10, 20, 10);
        add(subtitle, gbc);

        // Reset insets for form fields
        gbc.insets = new Insets(10, 10, 10, 10);

        // Initialize text fields and combo boxes with enhanced styling
        nameField = createStyledTextField("Enter patient's full name");
        ageField = createStyledTextField("Age");
        contactField = createStyledTextField("Contact number");
        addressField = createStyledTextField("Full address");
        emailField = createStyledTextField("Email address");
        dobField = createStyledTextField("YYYY-MM-DD");
        emergencyContactField = createStyledTextField("Emergency contact number");

        // Style combo boxes
        genderComboBox = createStyledComboBox(new String[]{"Select Gender", "Male", "Female", "Other"});
        bloodGroupComboBox = createStyledComboBox(new String[]{"Select Blood Group", "A+", "A-", "B+", "B-", "O+", "O-", "AB+", "AB-"});

        // Add components with enhanced styling
        addFormRow(gbc, 2, "Name:", nameField);
        addFormRow(gbc, 3, "Age:", ageField);
        addFormRow(gbc, 4, "Gender:", genderComboBox);
        addFormRow(gbc, 5, "Contact:", contactField);
        addFormRow(gbc, 6, "Address:", addressField);
        addFormRow(gbc, 7, "Email:", emailField);
        addFormRow(gbc, 8, "Date of Birth:", dobField);
        addFormRow(gbc, 9, "Blood Group:", bloodGroupComboBox);
        addFormRow(gbc, 10, "Emergency Contact:", emergencyContactField);

        // Add Button with enhanced styling
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0));
        buttonPanel.setOpaque(false);

        JButton addButton = createStyledButton("Add Patient", primaryColor);
        JButton clearButton = createStyledButton("Clear Form", new Color(149, 165, 166));

        addButton.addActionListener(e -> addPatient());
        clearButton.addActionListener(e -> clearForm());

        buttonPanel.add(addButton);
        buttonPanel.add(clearButton);

        gbc.gridx = 0;
        gbc.gridy = 11;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(20, 10, 10, 10);
        add(buttonPanel, gbc);
    }

    private JTextField createStyledTextField(String placeholder) {
        JTextField field = new JTextField(20);
        field.setFont(fieldFont);
        field.setPreferredSize(new Dimension(250, 35));
        field.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(200, 200, 200)),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));

        // Add placeholder text
        field.setText(placeholder);
        field.setForeground(Color.GRAY);

        field.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (field.getText().equals(placeholder)) {
                    field.setText("");
                    field.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (field.getText().isEmpty()) {
                    field.setText(placeholder);
                    field.setForeground(Color.GRAY);
                }
            }
        });

        return field;
    }

    private JComboBox<String> createStyledComboBox(String[] items) {
        JComboBox<String> comboBox = new JComboBox<>(items);
        comboBox.setFont(fieldFont);
        comboBox.setPreferredSize(new Dimension(250, 35));
        comboBox.setBackground(Color.WHITE);
        return comboBox;
    }

    private JButton createStyledButton(String text, Color bgColor) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setPreferredSize(new Dimension(150, 40));
        button.setBackground(bgColor);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(5, 15, 5, 15));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // Add hover effect
        button.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                button.setBackground(bgColor.brighter());
            }
            public void mouseExited(MouseEvent e) {
                button.setBackground(bgColor);
            }
        });

        return button;
    }

    private void addFormRow(GridBagConstraints gbc, int row, String labelText, Component component) {
        // Label
        JLabel label = new JLabel(labelText);
        label.setFont(labelFont);
        label.setForeground(new Color(70, 70, 70));

        gbc.gridx = 0;
        gbc.gridy = row;
        gbc.gridwidth = 1;
        gbc.weightx = 0.3;
        gbc.anchor = GridBagConstraints.EAST;
        add(label, gbc);

        // Component
        gbc.gridx = 1;
        gbc.weightx = 0.7;
        gbc.anchor = GridBagConstraints.WEST;
        add(component, gbc);
    }

    private void clearForm() {
        nameField.setText("");
        ageField.setText("");
        contactField.setText("");
        addressField.setText("");
        emailField.setText("");
        dobField.setText("");
        emergencyContactField.setText("");
        genderComboBox.setSelectedIndex(0);
        bloodGroupComboBox.setSelectedIndex(0);
    }

    private void addPatient() {
        // Show loading cursor
        setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));

        try {
            // Basic validation
            if (!validateFields()) {
                return;
            }

            String name = nameField.getText();
            int age = Integer.parseInt(ageField.getText());
            String gender = (String) genderComboBox.getSelectedItem();
            String contact = contactField.getText();
            String address = addressField.getText();
            String email = emailField.getText();
            String dob = dobField.getText();
            String bloodGroup = (String) bloodGroupComboBox.getSelectedItem();
            String emergencyContact = emergencyContactField.getText();

            boolean success = AddPatientController.addPatient(name, age, gender, contact, address,
                    email, dob, bloodGroup, emergencyContact);

            if (success) {
                JOptionPane.showMessageDialog(this,
                        "Patient added successfully!",
                        "Success",
                        JOptionPane.INFORMATION_MESSAGE);
                clearForm();
            } else {
                JOptionPane.showMessageDialog(this,
                        "Failed to add patient.",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                    "Error: " + e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        } finally {
            setCursor(Cursor.getDefaultCursor());
        }
    }

    private boolean validateFields() {
        // Name validation
        if (nameField.getText().isEmpty() || nameField.getText().equals("Enter patient's full name")) {
            showError("Please enter patient's name");
            return false;
        }

        // Age validation
        try {
            int age = Integer.parseInt(ageField.getText());
            if (age <= 0 || age > 150) {
                showError("Please enter a valid age between 1 and 150");
                return false;
            }
        } catch (NumberFormatException e) {
            showError("Please enter a valid age");
            return false;
        }

        // Gender validation
        if (genderComboBox.getSelectedIndex() == 0) {
            showError("Please select a gender");
            return false;
        }

        // Email validation
        String emailRegex = "^[A-Za-z0-9+_.-]+@(.+)$";
        if (!emailField.getText().matches(emailRegex)) {
            showError("Please enter a valid email address");
            return false;
        }

        // Date validation
        if (!isValidDate(dobField.getText())) {
            showError("Please enter a valid date in YYYY-MM-DD format");
            return false;
        }

        return true;
    }

    private void showError(String message) {
        JOptionPane.showMessageDialog(this,
                message,
                "Validation Error",
                JOptionPane.ERROR_MESSAGE);
    }

    private boolean isValidDate(String date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        sdf.setLenient(false);
        try {
            sdf.parse(date);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }
}