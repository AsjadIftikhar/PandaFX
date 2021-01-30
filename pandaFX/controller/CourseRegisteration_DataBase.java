package pandaFX.controller;


import pandaFX.models.Course;
import pandaFX.models.CourseRegistration;
import pandaFX.models.Sections;
import pandaFX.models.Student;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class CourseRegisteration_DataBase {


    public ArrayList<CourseRegistration> queryCourseReg(Sections secs) {

        Connection_DataBase conn = new Connection_DataBase();
        if(conn.open())
        {

            String query = "SELECT grade, rollnum, name FROM CourseRegistration INNER JOIN Student ON rollnum = rollNumber WHERE sectitle = '" + secs.getTitle() + "'" + "AND CourseCode = '" + secs.getSecCourse().getCode() + "'";


            try(Statement statement = conn.conn.createStatement();
                ResultSet results = statement.executeQuery(query.toString())) {

                ArrayList<CourseRegistration> Cr = new ArrayList<>();
                while(results.next()) {
                    CourseRegistration cr = new CourseRegistration();

                    Student std = new Student();
                    std.setRoll_number(results.getString("rollnum"));
                    std.setName(results.getString("name"));

                    cr.setStu(std);
                    cr.setGrade(results.getString("grade"));
                    cr.setSecCR(secs);



                    Cr.add(cr);
                }

                return Cr;

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

    public boolean addGrade(String roll_number,String sem, String crs, String sec, String g) {

        Connection_DataBase conn = new Connection_DataBase();
        if (conn.open()) {

            String query = "UPDATE CourseRegistration SET grade = '" + g + "' WHERE semTitle = '" + sem + "' AND CourseCode = '" + crs + "' AND sectitle = '" + sec + "' AND rollnum = '" + roll_number + "';";
            System.out.println(query);

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
