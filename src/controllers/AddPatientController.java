// src/controllers/AddPatientController.java

package controllers;

import db.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AddPatientController {

    public static boolean addPatient(String name, int age, String gender, String contact, String address,
                                     String email, String dateOfBirth, String bloodGroup, String emergencyContact) {
        Connection conn = DatabaseConnection.getConnection();
        String sql = "INSERT INTO patients (name, age, gender, contact, address, email, date_of_birth, blood_group, emergency_contact) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, name);
            stmt.setInt(2, age);
            stmt.setString(3, gender);
            stmt.setString(4, contact);
            stmt.setString(5, address);
            stmt.setString(6, email);
            stmt.setString(7, dateOfBirth);
            stmt.setString(8, bloodGroup);
            stmt.setString(9, emergencyContact);

            int rowsInserted = stmt.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
