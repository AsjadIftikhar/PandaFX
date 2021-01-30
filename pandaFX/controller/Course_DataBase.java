package pandaFX.controller;


import pandaFX.models.Course;
import pandaFX.models.Semester;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Course_DataBase {


    public ArrayList<Course> queryCourse(Semester sem) {

        Connection_DataBase conn = new Connection_DataBase();
        if(conn.open())
        {

            String query = "SELECT * FROM Course WHERE semTitle = '" + sem.getTitle() + "'";


            try(Statement statement = conn.conn.createStatement();
                ResultSet results = statement.executeQuery(query.toString())) {

                ArrayList<Course> S = new ArrayList<>();
                while(results.next()) {
                    Course c = new Course();
                    c.setTitle(results.getString("title"));
                    c.setCode(results.getString("code"));
                    c.setCredit_hours(results.getInt("credit_hrs"));

                    c.setCourseSem(sem);

                    S.add(c);
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


}
