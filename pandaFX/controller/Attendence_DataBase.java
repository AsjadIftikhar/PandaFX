package pandaFX.controller;
import pandaFX.models.Attendance;
import pandaFX.models.CourseRegistration;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Attendence_DataBase {

    public ArrayList<Attendance> queryAttendance(CourseRegistration crg) {

        Connection_DataBase conn = new Connection_DataBase();
        if (conn.open()) {

            String query = "SELECT * FROM Attendance WHERE sectitle = '" + crg.getSecCR().getTitle() + "'" + "AND rollnum = '" + crg.getStu().getRoll_number() + "'";


            try (Statement statement = conn.conn.createStatement();
                 ResultSet results = statement.executeQuery(query.toString())) {

                ArrayList<Attendance> A = new ArrayList<>();
                while (results.next()) {
                    Attendance a = new Attendance();

                    //Get Date as String and Convert to Local Date:
                    String dbDate = results.getString("date");
                    LocalDate dt = LocalDate.parse(dbDate);
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
                    dt.format(formatter);

                    a.setDate(dt);
                    a.setStatus(results.getString("status"));
                    a.setCr(crg);

                    A.add(a);
                }

                return A;

            } catch (SQLException e) {
                System.out.println("Query failed: " + e.getMessage());
                return null;
            }
        } else {
            return null;
        }


    }

    public boolean addAttendance(String roll_number, String sec, String date, String Status, String sem, String crs) {

        Connection_DataBase conn = new Connection_DataBase();
        if (conn.open()) {

            String query = "INSERT INTO Attendance VALUES('" + date + "','" + Status + "','" + roll_number + "','" + sec + "','" + crs + "','" + sem + "')";


            try {
                Statement statement = conn.conn.createStatement();
                statement.execute(query);

                return true;

            } catch (SQLException e) {
                System.out.println("Query failed: " + e.getMessage());

                return false;
            }
        }


        return false;
    }
}
