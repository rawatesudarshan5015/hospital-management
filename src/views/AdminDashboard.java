package views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class AdminDashboard extends JFrame {

    private JButton viewDoctorsButton;
    private JButton addDoctorButton;
    private JButton editDoctorButton;
    private JButton deleteDoctorButton;

    public AdminDashboard() {
        setTitle("Admin Dashboard");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Panel for layout
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(2, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));

        // Buttons for different actions
        viewDoctorsButton = new JButton("View Doctors");
        addDoctorButton = new JButton("Add Doctor");
        editDoctorButton = new JButton("Edit Doctor");
        deleteDoctorButton = new JButton("Delete Doctor");

        // Add buttons to panel
        panel.add(viewDoctorsButton);
        panel.add(addDoctorButton);
        panel.add(editDoctorButton);
        panel.add(deleteDoctorButton);

        // Action listeners for buttons
        viewDoctorsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Open View Doctors Page
//                new ViewDoctorsPage();
            }
        });

        addDoctorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Open Add Doctor Page
//                new AddDoctorPage();
            }
        });

        editDoctorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Open Edit Doctor Page (you can pass doctor ID for editing)
//                new EditDoctorPage();
            }
        });

        deleteDoctorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Open Delete Doctor Page
//                new DeleteDoctorPage();
            }
        });

        // Add panel to frame
        add(panel);
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new AdminDashboard());
    }
}
