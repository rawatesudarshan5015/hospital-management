// src/controllers/AppointmentController.java

package controllers;

import db.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public class AppointmentController {

    public static boolean scheduleAppointment(String patientId, String doctorId, String status, String notes) {
        Connection conn = DatabaseConnection.getConnection();
        // Updated SQL statement without appointment_id
        String sql = "INSERT INTO appointments (patient_id, doctor_id, appointment_date, appointment_time, status, notes) " +
                "VALUES (?, ?, ?, ?, ?, ?)";

        // Get the current date and time as a Timestamp for the appointment
        LocalDateTime now = LocalDateTime.now();
        Timestamp date = Timestamp.valueOf(now.toLocalDate().atStartOfDay()); // Set date without time part
        Timestamp time = Timestamp.valueOf(now); // Set full timestamp including time

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, patientId);
            stmt.setString(2, doctorId);
            stmt.setTimestamp(3, date);  // Appointment date without time
            stmt.setTimestamp(4, time);  // Appointment time with time component
            stmt.setString(5, status);
            stmt.setString(6, notes);

            int rowsInserted = stmt.executeUpdate();
            return rowsInserted > 0;  // Return true if rows were inserted successfully
        } catch (SQLException e) {
            System.err.println("Error scheduling appointment: " + e.getMessage());
            e.printStackTrace();  // Log the error for debugging
            return false;  // Return false if an exception occurs
        }
    }
}
