package pandaFX.controller;


import pandaFX.models.Course;
import pandaFX.models.Sections;
import pandaFX.models.Semester;
import pandaFX.models.Student;

import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Sections_DataBase {

    public ArrayList<Sections> querySection(Course crs) {

        Connection_DataBase conn = new Connection_DataBase();
        if(conn.open())
        {

            String query = "SELECT * FROM Section WHERE courseCode = '" + crs.getCode() + "'";


            try(Statement statement = conn.conn.createStatement();
                ResultSet results = statement.executeQuery(query.toString())) {

                ArrayList<Sections> Sec = new ArrayList<>();
                while(results.next()) {
                    Sections sec = new Sections();
                    sec.setTitle(results.getString("title"));
                    sec.setCapacity(results.getInt("capacity"));
                    sec.setSecCourse(crs);


                    Sec.add(sec);
                }

                return Sec;

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
    public Queue<Student> queryWaitingList(Semester sem ,Course crs,Sections secs) {
        Queue<Student> s = new LinkedList<>();

        Connection_DataBase conn = new Connection_DataBase();
        if(conn.open())
        {

            String query =   "Select rollnum from Waiting_List_Queue WHERE  Waiting_List_Queue.semTitle = '"+sem.getTitle()+"'"+" and Waiting_List_Queue.CourseCode= '"+ crs.getCode()+"'"+" and Waiting_List_Queue.sectitle= '"+ secs.getTitle()+"'"+"order by Waiting_List_Queue.Id";

            try(Statement statement = conn.conn.createStatement();
                ResultSet results = statement.executeQuery(query.toString())) {

                while(results.next()) {
                   Student stu = new Student();
                   stu.setRoll_number(results.getString("rollnum"));
                   s.add(stu);
                }

                return s;

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
    public void setWaitingList(Semester sem ,Course crs,Sections secs,Student stu,int id)
    {

        Connection_DataBase conn = new Connection_DataBase();
        if(conn.open())
        {

            String q = "INSERT INTO Waiting_List_Queue (Id,  rollnum, sectitle, CourseCode, semTitle) VALUES ("+id+",'"+stu.getRoll_number()+"','"+secs.getTitle()+"','"+crs.getCode()+"','"+sem.getTitle()+"')";

            try(Statement statement = conn.conn.createStatement()) {

                statement.execute(q);

            } catch(SQLException e) {
                System.out.println("Query failed: " + e.getMessage());
            }
        }
    }


}
