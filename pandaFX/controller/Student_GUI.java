package pandaFX.controller;

import pandaFX.models.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Student_GUI {

    private Student stu ;

    public Student_GUI(Student s1) {

         stu = s1;

    }

    public void Add_Course(Semester sem, Course crs , Sections sec)
    {

        for (int i = 0; i < stu.getSems_Data().size() ; i++) {
            String s1 = stu.getSems_Data().get(i).getTitle();
            String s2 = sem.getTitle();
            if (s1.compareTo(s2) == 0) {
                Course Cr = new Course();
                Cr.setTitle(crs.getTitle());
                Cr.setCode(crs.getCode());
                Cr.setCredit_hours(crs.getCredit_hours());
                Cr.setCourseSem(sem);
                ArrayList<Sections> Sec = new ArrayList<Sections>();
                Sections temp = new Sections();
                temp.setTitle(sec.getTitle());
                temp.setSecCourse(Cr);
                ArrayList<CourseRegistration> Cr_reg = new ArrayList<>();
                CourseRegistration cr = new CourseRegistration("l",temp,stu,null,null);
                Cr_reg.add(cr);
                temp.setCR(Cr_reg);
                Sec.add(temp);
                Cr.setSecArray(Sec);
                stu.getSems_Data().get(i).getCourseArray().add(Cr);
                Student_DataBase sD = new Student_DataBase();
                sD.query_Set_Cr('-', stu.getRoll_number(), sec.getTitle(), crs.getCode(), sem.getTitle());
            }

        }

    }

    public void Del_Course(Semester sem, Course crs)
    {

        int index2=0, index3=0;
        for (int i = 0; i < stu.getSems_Data().size(); i++) {

            if (stu.getSems_Data().get(i).getTitle().equals(sem.getTitle())) {

                for (int j = 0; j < stu.getSems_Data().get(i).getCourseArray().size() ; j++) {

                    if (stu.getSems_Data().get(i).getCourseArray().get(j).getCode().equals(crs.getCode())) {

                        for (int k = 0; k < stu.getSems_Data().get(i).getCourseArray().get(j).getSecArray().size(); k++) {

                            stu.getSems_Data().get(i).getCourseArray().get(j).getSecArray().get(k).getCR().clear();
                            Student_DataBase sD = new Student_DataBase();
                            String sec =  stu.getSems_Data().get(i).getCourseArray().get(j).getSecArray().get(k).getTitle();
                            sD.query_Del_Cr(stu.getRoll_number(), sec, crs.getCode(), sem.getTitle());
                            index2=j;
                            index3=k;


                        }

                        stu.getSems_Data().get(i).getCourseArray().get(j).getSecArray().remove(index3);

                    }

                }

                stu.getSems_Data().get(i).getCourseArray().remove(index2);
            }

        }

    }

    public ArrayList<String> Get_Messages(String  r)
    {
        ArrayList<String> s = new ArrayList<>();

        Connection_DataBase conn = new Connection_DataBase();
        if(conn.open())
        {

            String query =   "Select Messages from Messages WHERE  rollnum = '"+ r+"'";

            try(Statement statement = conn.conn.createStatement();
                ResultSet results = statement.executeQuery(query.toString())) {

                while(results.next()) {
                    String Stu = results.getString("Messages");
                    s.add(Stu);
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

    public void Set_Message(String Message,Student s ,Semester sem, Course crs , Sections sec)
    {
        Connection_DataBase conn = new Connection_DataBase();
        if(conn.open())
        {

            String q = "INSERT INTO Messages (Messages,  rollnum, sectitle, CourseCode, semTitle) VALUES ('"+Message+"','"+s.getRoll_number()+"','"+sec.getTitle()+"','"+crs.getCode()+"','"+sem.getTitle()+"')";

            try(Statement statement = conn.conn.createStatement()) {

                statement.execute(q);

            } catch(SQLException e) {
                System.out.println("Query failed: " + e.getMessage());
            }
        }

    }

    public void Clear_Message()
    {
        Connection_DataBase conn = new Connection_DataBase();
        if(conn.open())
        {

            String q = "Delete from Messages where rollnum = '"+stu.getRoll_number()+"'";
            try(Statement statement = conn.conn.createStatement()) {

                statement.execute(q);

            } catch(SQLException e) {
                System.out.println("Query failed: " + e.getMessage());
            }
        }
    }


}
