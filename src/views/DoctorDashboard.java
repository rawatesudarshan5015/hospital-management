// src/views/DoctorDashboard.java

package views;

import javax.swing.*;

public class DoctorDashboard extends JFrame {
    public DoctorDashboard() {
        setTitle("Doctor Dashboard");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        JLabel label = new JLabel("Welcome, Doctor");
        panel.add(label);

        add(panel);
        setVisible(true);
    }
}
