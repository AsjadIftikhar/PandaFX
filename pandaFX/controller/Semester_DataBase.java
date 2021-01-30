package pandaFX.controller;

import pandaFX.models.Semester;

import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;


public class Semester_DataBase {


    public static final String Semesters = "Semester";

    public ArrayList<Semester> querySemester() {

        Connection_DataBase conn = new Connection_DataBase();
        if(conn.open())
        {
            StringBuilder sb = new StringBuilder("SELECT * FROM ");
            sb.append(Semesters);

            try(Statement statement = conn.conn.createStatement();
                ResultSet results = statement.executeQuery(sb.toString())) {

                ArrayList<Semester> S = new ArrayList<>();
                while(results.next()) {
                    Semester s = new Semester();
                    s.setTitle(results.getString("title"));

                    String opDate = results.getString("startDate");
                    String clDate = results.getString("endDate");
                    String wiDate = results.getString("withDrawDate");

                    if (opDate != null && clDate != null && wiDate != null){
                        LocalDate dt1 = LocalDate.parse(opDate);
                        LocalDate dt2 = LocalDate.parse(clDate);
                        LocalDate dt3 = LocalDate.parse(wiDate);

                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
                        dt1.format(formatter);
                        dt2.format(formatter);
                        dt3.format(formatter);

                        s.setStartDate(dt1);
                        s.setEndDate(dt2);
                        s.setWithDrawDate(dt3);
                    }


                    S.add(s);
                }

                return S;

            } catch(SQLException e) {
                System.out.println("Query failed: " + e.getMessage());
                return null;
            }
        }
        else
        {
            return null;
        }
    }
    public boolean addDates(String sem, String openDate, String closeDate, String withDrawDate) {

        Connection_DataBase conn = new Connection_DataBase();
        if (conn.open()) {

            String query = "UPDATE Semester SET startDate = '"+ openDate + "' , endDate = '" + closeDate + "' , withDrawDate = '" + withDrawDate + "' WHERE title = '" + sem + "'";


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
