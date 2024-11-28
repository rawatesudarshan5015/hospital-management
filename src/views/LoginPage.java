package views;

import controllers.LoginController;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;

public class LoginPage extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JComboBox<String> roleComboBox;
    private JButton loginButton;

    public LoginPage() {
        setTitle("Hospital Management System - Login");
        setSize(450, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Main panel with padding
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout(20, 20));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(25, 25, 25, 25));

        // Logo/Title Panel
        JPanel titlePanel = new JPanel();
        JLabel titleLabel = new JLabel("Hospital Management System");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titlePanel.add(titleLabel);

        // Subtitle
        JLabel subtitleLabel = new JLabel("Please enter your credentials to login");
        subtitleLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        subtitleLabel.setForeground(Color.GRAY);
        JPanel subtitlePanel = new JPanel();
        subtitlePanel.add(subtitleLabel);

        // Form Panel
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 5, 10, 5);

        // Username field with icon
        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        usernameField = new JTextField(20);
        usernameField.setPreferredSize(new Dimension(200, 35));
        usernameField.setBorder(BorderFactory.createCompoundBorder(
                usernameField.getBorder(),
                BorderFactory.createEmptyBorder(5, 5, 5, 5)));

        // Password field with icon
        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        passwordField = new JPasswordField(20);
        passwordField.setPreferredSize(new Dimension(200, 35));
        passwordField.setBorder(BorderFactory.createCompoundBorder(
                passwordField.getBorder(),
                BorderFactory.createEmptyBorder(5, 5, 5, 5)));

        // Role selection
        JLabel roleLabel = new JLabel("Select Role:");
        roleLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        String[] roles = {"Admin", "Receptionist", "Doctor"};
        roleComboBox = new JComboBox<>(roles);
        roleComboBox.setPreferredSize(new Dimension(200, 35));

        // Add components to form panel
        gbc.gridx = 0; gbc.gridy = 0;
        formPanel.add(usernameLabel, gbc);
        gbc.gridx = 1;
        formPanel.add(usernameField, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        formPanel.add(passwordLabel, gbc);
        gbc.gridx = 1;
        formPanel.add(passwordField, gbc);

        gbc.gridx = 0; gbc.gridy = 2;
        formPanel.add(roleLabel, gbc);
        gbc.gridx = 1;
        formPanel.add(roleComboBox, gbc);

        // Login button with custom styling
        loginButton = new JButton("Login");
        loginButton.setPreferredSize(new Dimension(200, 40));
        loginButton.setBackground(new Color(41, 128, 185));
        loginButton.setForeground(Color.WHITE);
        loginButton.setFont(new Font("Arial", Font.BOLD, 14));
        loginButton.setFocusPainted(false);
        loginButton.setBorder(BorderFactory.createEmptyBorder(5, 15, 5, 15));
        loginButton.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // Add hover effect
        loginButton.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                loginButton.setBackground(new Color(52, 152, 219));
            }

            public void mouseExited(MouseEvent e) {
                loginButton.setBackground(new Color(41, 128, 185));
            }
        });

        loginButton.addActionListener(new LoginButtonListener());

        // Button panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(loginButton);

        // Add all panels to main panel
        mainPanel.add(titlePanel, BorderLayout.NORTH);
        mainPanel.add(subtitlePanel, BorderLayout.CENTER);
        mainPanel.add(formPanel, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        // Add tooltip text
        usernameField.setToolTipText("Enter your username");
        passwordField.setToolTipText("Enter your password");
        roleComboBox.setToolTipText("Select your role in the system");

        // Set background colors
        Color backgroundColor = new Color(245, 245, 245);
        mainPanel.setBackground(backgroundColor);
        titlePanel.setBackground(backgroundColor);
        subtitlePanel.setBackground(backgroundColor);
        formPanel.setBackground(backgroundColor);
        buttonPanel.setBackground(backgroundColor);

        add(mainPanel);
        setVisible(true);
    }

    private class LoginButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());
            String selectedRole = ((String) roleComboBox.getSelectedItem()).toLowerCase();

            // Show loading cursor
            setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));

            // Check login with selected role
            String role = LoginController.login(username, password);

            // Reset cursor
            setCursor(Cursor.getDefaultCursor());

            if (role != null && role.equals(selectedRole)) {
                dispose(); // Close login page
                switch (role) {
                    case "admin":
                        new AdminDashboard();
                        break;
                    case "receptionist":
                        new ReceptionistDashboard();
                        break;
                    case "doctor":
                        new DoctorDashboard();
                        break;
                }
            } else {
                JOptionPane.showMessageDialog(
                        null,
                        "Invalid credentials or role. Please try again.",
                        "Login Error",
                        JOptionPane.ERROR_MESSAGE
                );
                passwordField.setText(""); // Clear password field
            }
        }
    }
    public static void main(String[] args) {
        try {
            // Set system look and feel
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        SwingUtilities.invokeLater(LoginPage::new);
    }
}