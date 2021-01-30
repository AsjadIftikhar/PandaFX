package pandaFX.controller;


import pandaFX.models.CourseRegistration;
import pandaFX.models.Evaluations;
import pandaFX.models.Sections;
import pandaFX.models.Student;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Evaluation_DataBase {

    public ArrayList<Evaluations> queryEvaluations(CourseRegistration crg) {

        Connection_DataBase conn = new Connection_DataBase();
        if(conn.open())
        {

            String query = "SELECT * FROM Evaluations WHERE sectitle = '" + crg.getSecCR().getTitle() + "'" + "AND rollnum = '" + crg.getStu().getRoll_number() + "'";


            try(Statement statement = conn.conn.createStatement();
                ResultSet results = statement.executeQuery(query.toString())) {

                ArrayList<Evaluations> E = new ArrayList<>();
                while(results.next()) {
                    Evaluations e = new Evaluations();

                    e.setTitle(results.getString("title"));
                    e.setWeight(results.getFloat("weight"));
                    e.setTotal_marks(results.getInt("totalMarks"));
                    e.setObtained_marks(results.getInt("obtainedMarks"));

                    e.setCR(crg);
                    E.add(e);
                }

                return E;

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
    public boolean createEvaluation(String sem, String crs, String sec, String title, float weight, int totalmarks, String std) {

        Connection_DataBase conn = new Connection_DataBase();
        if (conn.open()) {

            String query = "INSERT INTO Evaluations (title,weight,totalMarks,rollnum,sectitle,CourseCode,semTitle) VALUES('" + title + "','" + weight + "','" + totalmarks + "','" + std + "','" + sec + "','" + crs + "','"  + sem + "')";


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

    public boolean updateEvaluation(String roll_number,String sem, String crs, String sec, String title, int obtainedmarks) {

        Connection_DataBase conn = new Connection_DataBase();
        if (conn.open()) {

            String query = "UPDATE Evaluations SET obtainedMarks = " + obtainedmarks + " WHERE rollnum = '" + roll_number + "' AND semTitle = '" + sem + "' AND CourseCode = '" + crs + "' AND sectitle = '" + sec + "' AND title = '" + title + "';";
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
