package pandaFX.controller;


import pandaFX.models.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Student_DataBase {

    public void query_Set_Cr(char g, String r, String se, String Cc, String smt) {

        Connection_DataBase conn = new Connection_DataBase();
        if (conn.open()) {

            try (Statement statement = conn.conn.createStatement();) {
                statement.execute("INSERT INTO CourseRegistration (grade, rollnum, sectitle, CourseCode, semTitle)" +
                        "VALUES('" + g + "', '" + r + "' , '" + se + "' , '" + Cc + "' , '" + smt + "')");

            } catch (SQLException e) {
                System.out.println("Query failed: " + e.getMessage());
                return;
            }
        }

    }

    public void query_Del_Cr(String r, String se, String Cc, String smt) {
        Connection_DataBase conn = new Connection_DataBase();
        if (conn.open()) {

            try (Statement statement = conn.conn.createStatement();) {
                statement.execute("Delete from  CourseRegistration  where CourseRegistration.rollnum = '" + r + "'" + "and CourseRegistration.semTitle= '" + smt + "'" + "and CourseRegistration.CourseCode= '" + Cc + "'" + "and CourseRegistration.sectitle= '" + se + "'");

            } catch (SQLException e) {
                System.out.println("Query failed: " + e.getMessage());
                return;
            }
        }
    }

    public void queryGet_Stu_Basic_Info(Student s, String email, String pass) {

        Connection_DataBase conn = new Connection_DataBase();
        if (conn.open()) {
            String query = "Select * from Student WHERE email = '" + email + "' AND password = '" + pass + "'";

            try (Statement statement = conn.conn.createStatement();
                 ResultSet results = statement.executeQuery(query.toString())) {


                String q = results.getString("gender");
                s.setGender(q);
                s.setContact(results.getString("contact"));
                s.setCampus(results.getString("campus"));
                s.setRoll_number(results.getString("rollNumber"));
                s.setDiscipline(results.getString("discipline"));
                s.setName(results.getString("name"));
                s.setDoB(results.getString("dob"));
                s.setBatch(results.getInt("batch"));
                s.setCgpa(results.getFloat("cgpa"));
                s.setEmail(results.getString("email"));


            } catch (SQLException e) {
                System.out.println("Query failed: " + e.getMessage());
            }
        }
    }

    public ArrayList<Semester> queryGet_Stu_Sem_List(Student stu) {

        Connection_DataBase conn = new Connection_DataBase();
        if (conn.open()) {
            String query = "Select * from Semester";

            try (Statement statement = conn.conn.createStatement();
                 ResultSet results = statement.executeQuery(query.toString())) {

                ArrayList<Semester> S = new ArrayList<>();
                while (results.next()) {
                    Semester s = new Semester();
                    s.setTitle(results.getString("title"));
                    String dbDate = results.getString("startDate");
                    if (dbDate != null) {
                        StringTokenizer date = new StringTokenizer(dbDate, "-");
                        String tokenVal = date.nextToken();
                        int day = Integer.parseInt(tokenVal);

                        tokenVal = date.nextToken();
                        int month = Integer.parseInt(tokenVal);

                        tokenVal = date.nextToken();
                        int year = Integer.parseInt(tokenVal);

                        LocalDate l = LocalDate.of(day, month, year);

                        s.setStartDate(l);

                        dbDate = results.getString("endDate");

                        if (dbDate != null) {
                            StringTokenizer date1 = new StringTokenizer(dbDate, "-");
                            tokenVal = date1.nextToken();
                            day = Integer.parseInt(tokenVal);

                            tokenVal = date1.nextToken();
                            month = Integer.parseInt(tokenVal);

                            tokenVal = date1.nextToken();
                            year = Integer.parseInt(tokenVal);

                            LocalDate l1 = LocalDate.of(day, month, year);
                            s.setEndDate(l1);

                        }

                        dbDate = results.getString("withDrawDate");

                        if (dbDate != null) {

                            StringTokenizer date2 = new StringTokenizer(dbDate, "-");
                            tokenVal = date2.nextToken();
                            day = Integer.parseInt(tokenVal);

                            tokenVal = date2.nextToken();
                            month = Integer.parseInt(tokenVal);

                            tokenVal = date2.nextToken();
                            year = Integer.parseInt(tokenVal);

                            LocalDate l3 = LocalDate.of(day, month, year);

                            s.setWithDrawDate(l3);
                        }


                    }

                    S.add(s);
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

    public ArrayList<Course> queryCourse(Semester sem, Student stu) {

        Connection_DataBase conn = new Connection_DataBase();
        if (conn.open()) {

            String query = "Select Course.code,Course.credit_hrs,Course.title from Course left join CourseRegistration on Course.code =CourseRegistration.CourseCode where CourseRegistration.rollnum = '" + stu.getRoll_number() + "'" + "and CourseRegistration.semTitle= '" + sem.getTitle() + "'";


            try (Statement statement = conn.conn.createStatement();
                 ResultSet results = statement.executeQuery(query.toString())) {

                ArrayList<Course> S = new ArrayList<>();
                while (results.next()) {
                    Course c = new Course();
                    c.setTitle(results.getString("title"));
                    c.setCode(results.getString("code"));
                    c.setCredit_hours(results.getInt("credit_hrs"));
                    c.setCourseSem(sem);
                    S.add(c);
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

    public ArrayList<Sections> querySection(Semester sem, Student stu, Course crs) {

        Connection_DataBase conn = new Connection_DataBase();
        if (conn.open()) {

            String query = "Select * from  CourseRegistration inner join Section on Section.title=CourseRegistration.sectitle where CourseRegistration.rollnum = '" + stu.getRoll_number() + "'" + "and CourseRegistration.semTitle= '" + sem.getTitle() + "'" + "and CourseRegistration.CourseCode= '" + crs.getCode() + "'" + "and Section.courseCode = '" + crs.getCode() + "'";

            try (Statement statement = conn.conn.createStatement();
                 ResultSet results = statement.executeQuery(query.toString())) {

                ArrayList<Sections> Sec = new ArrayList<>();
                while (results.next()) {

                    Sections sec = new Sections();
                    sec.setTitle(results.getString("title"));
                    sec.setCapacity(results.getInt("capacity"));
                    sec.setSecCourse(crs);


                    Sec.add(sec);
                }

                return Sec;

            } catch (SQLException e) {
                System.out.println("Query failed: " + e.getMessage());
                return null;
            }
        } else {
            return null;
        }


    }

    public ArrayList<CourseRegistration> queryCourseReg(Semester sem, Student stu, Course crs, Sections secs) {

        Connection_DataBase conn = new Connection_DataBase();
        if (conn.open()) {


            String query = "Select * from  CourseRegistration  where CourseRegistration.rollnum = '" + stu.getRoll_number() + "'" + "and CourseRegistration.semTitle= '" + sem.getTitle() + "'" + "and CourseRegistration.CourseCode= '" + crs.getCode() + "'" + "and CourseRegistration.sectitle= '" + secs.getTitle() + "'";

            try (Statement statement = conn.conn.createStatement();
                 ResultSet results = statement.executeQuery(query.toString())) {

                ArrayList<CourseRegistration> Cr = new ArrayList<>();
                while (results.next()) {
                    CourseRegistration cr = new CourseRegistration();

                    Student std = new Student();
                    std.setRoll_number(results.getString("rollnum"));
                    cr.setStu(std);

                    String s = results.getString("grade");
                    cr.setGrade(s);

                    cr.setSecCR(secs);


                    Cr.add(cr);
                }

                return Cr;

            } catch (SQLException e) {
                System.out.println("Query failed: " + e.getMessage());
                return null;
            }
        } else {
            return null;
        }


    }

    public ArrayList<Attendance> queryAttendance(Semester sem, Student stu, Course crs, Sections secs) {

        Connection_DataBase conn = new Connection_DataBase();
        if (conn.open()) {


            String query = "Select *  from  Attendance  where Attendance.rollnum = '" + stu.getRoll_number() + "'" + " and Attendance.semTitle = '" + sem.getTitle() + "'" + " and Attendance.CourseCode= '" + crs.getCode() + "'" + "and Attendance.sectitle= '" + secs.getTitle() + "'";


            try (Statement statement = conn.conn.createStatement();
                 ResultSet results = statement.executeQuery(query.toString())) {

                ArrayList<Attendance> A = new ArrayList<>();
                while (results.next()) {
                    Attendance a = new Attendance();

                    String dbDate = results.getString("date");

                    StringTokenizer date = new StringTokenizer(dbDate, "-");
                    String tokenVal = date.nextToken();
                    int day = Integer.parseInt(tokenVal);

                    tokenVal = date.nextToken();
                    int month = Integer.parseInt(tokenVal);

                    tokenVal = date.nextToken();
                    int year = Integer.parseInt(tokenVal);

                    LocalDate l = LocalDate.of(day, month, year);
                    a.setDate(l);

                    String s = results.getString("status");
                    a.setStatus(s);

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

    public ArrayList<Evaluations> queryEvaluations(Semester sem, Student stu, Course crs, Sections secs) {

        Connection_DataBase conn = new Connection_DataBase();
        if (conn.open()) {


            String query = "Select * from  Evaluations where Evaluations.rollnum = '" + stu.getRoll_number() + "'" + " and Evaluations.semTitle = '" + sem.getTitle() + "'" + " and Evaluations.CourseCode= '" + crs.getCode() + "'" + "and Evaluations.sectitle= '" + secs.getTitle() + "'";

            try (Statement statement = conn.conn.createStatement();
                 ResultSet results = statement.executeQuery(query.toString())) {

                ArrayList<Evaluations> E = new ArrayList<>();
                while (results.next()) {
                    Evaluations e = new Evaluations();

                    e.setTitle(results.getString("title"));
                    e.setWeight(results.getFloat("weight"));
                    e.setTotal_marks(results.getInt("totalMarks"));
                    e.setObtained_marks(results.getInt("obtainedMarks"));
                    E.add(e);
                }

                return E;

            } catch (SQLException e) {
                System.out.println("Query failed: " + e.getMessage());
                return null;
            }
        } else {
            return null;
        }


    }

    public void query_Get_Stu_Data(Student Stu, String e, String r) {

        this.queryGet_Stu_Basic_Info(Stu, e, r);

        if (Stu.getRoll_number() != null) {
            ArrayList<Semester> allSemesterData = queryGet_Stu_Sem_List(Stu);

            for (Semester semester : allSemesterData) {

                semester.setCourseArray(this.queryCourse(semester, Stu));

                for (Course course : semester.getCourseArray()) {

                    course.setSecArray(this.querySection(semester, Stu, course));

                    for (Sections sec : course.getSecArray()) {

                        sec.setCR(this.queryCourseReg(semester, Stu, course, sec));

                        for (CourseRegistration courseregistration : sec.getCR()) {

                            courseregistration.setCourseEval(this.queryEvaluations(semester, Stu, course, sec));
                            courseregistration.setCourseAtt(this.queryAttendance(semester, Stu, course, sec));

                        }


                    }

                }


            }

            Stu.setSems_Data(allSemesterData);
        }

    }

}
