package pandaFX.controller;

import pandaFX.models.AC_Officer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class AcOfficer_DataBase {
    public void queryACObject(AC_Officer t, String email, String pass) {

        Connection_DataBase conn = new Connection_DataBase();
        if (conn.open()) {
            String query = "SELECT * FROM acOfficer WHERE email = '" + email + "' AND password = '" + pass + "'";

            try (Statement statement = conn.conn.createStatement();
                 ResultSet results = statement.executeQuery(query.toString())) {

                while (results.next()) {

                    t.setName(results.getString("name"));
                    t.setDob(results.getString("dob"));
                    t.setContact(results.getString("contact"));
                    t.setCampus(results.getString("campus"));
                    t.setGender(results.getString("gender"));
                    t.setEmail(results.getString("email"));
                    t.setDiscipline(results.getString("discipline"));
                    t.setSalary(results.getDouble("salary"));
                }
            } catch (SQLException e) {
                System.out.println("Query failed: " + e.getMessage());

            }
        }
    }
}
