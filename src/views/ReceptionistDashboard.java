// src/views/ReceptionistDashboard.java

package views;

import javax.swing.*;

public class ReceptionistDashboard extends JFrame {

    public ReceptionistDashboard() {
        setTitle("Receptionist Dashboard");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Tabbed pane for the receptionist functionalities
        JTabbedPane tabbedPane = new JTabbedPane();

        // Adding different panels to the tabs
        tabbedPane.addTab("Add New Patient", new AddPatientPanel());
        tabbedPane.addTab("Manage Appointments", new AppointmentPanel());
        tabbedPane.addTab("Track Bed Availability", new BedAvailabilityPanel());

        add(tabbedPane);
        setVisible(true);
    }
}
