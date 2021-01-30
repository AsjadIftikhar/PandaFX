package pandaFX.controller;


import pandaFX.models.Course;
import pandaFX.models.Sections;
import pandaFX.models.Semester;
import pandaFX.models.Teacher;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Teacher_DataBase {

    public ArrayList<Semester> queryTeacher(Teacher t) {

        Connection_DataBase conn = new Connection_DataBase();
        if (conn.open()) {
            String query = "SELECT semTitle, C.title AS T,C.code C ,C.credit_hrs CR, sec_title FROM Teacher_Section INNER JOIN Course C on course_code = code WHERE name = '" + t.getName() + "'";

            try (Statement statement = conn.conn.createStatement();
                 ResultSet results = statement.executeQuery(query.toString())) {

                ArrayList<Semester> S = new ArrayList<>();
                while (results.next()) {

                    Semester s = new Semester();
                    Course c = new Course();
                    Sections sec = new Sections();


                    s.setTitle(results.getString("semTitle"));

                    c.setTitle(results.getString("T"));
                    c.setCode(results.getString("C"));
                    c.setCredit_hours(results.getInt("CR"));

                    sec.setTitle(results.getString("sec_title"));

                    int i, j;
                    boolean semesterFound = false;
                    for (i = 0; i < S.size(); i++) {

                        if (S.get(i).getTitle() == s.getTitle()) {
                            semesterFound = true;
                            boolean courseFound = false;
                            for (j = 0; j < S.get(i).getCourseArray().size(); j++) {
                                if (S.get(i).getCourseArray().get(j).getTitle() == c.getTitle()){

                                    courseFound = true;
                                    S.get(i).getCourseArray().get(j).getSecArray().add(sec);
                                }
                            }
                            if (!courseFound){
                                c.getSecArray().add(sec);
                                S.get(i).getCourseArray().add(c);
                            }
                        }
                    }
                    if (!semesterFound){
                        c.getSecArray().add(sec);
                        s.getCourseArray().add(c);
                        S.add(s);
                    }
                }
                return S;

            } catch (SQLException e) {
                System.out.println("Query failed: " + e.getMessage());
                return null;
            }
        } else {
            return null;
        }


    }

    public void queryTeacherObject(Teacher t, String email, String pass) {

        Connection_DataBase conn = new Connection_DataBase();
        if (conn.open()) {
            String query = "SELECT * FROM Teacher WHERE email = '" + email + "' AND password = '" + pass + "'";

            try (Statement statement = conn.conn.createStatement();
                 ResultSet results = statement.executeQuery(query.toString())) {

                while (results.next()) {

                    t.setName(results.getString("name"));
                    t.setDoB(results.getString("dob"));
                    t.setDiscipline(results.getString("discipline"));
                    t.setContact(results.getString("contact"));
                    t.setCampus(results.getString("campus"));
                    t.setGender(results.getString("gender"));
                    t.setEmail(results.getString("email"));
                    t.setSalary(results.getDouble("salary"));
                }
            } catch (SQLException e) {
                System.out.println("Query failed: " + e.getMessage());

            }
        }
    }
}


