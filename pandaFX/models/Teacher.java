package pandaFX.models;

import pandaFX.controller.Attendence_DataBase;
import pandaFX.controller.Teacher_DataBase;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

public class Teacher implements TeacherInterface {

  // Fields
  private String name;
  private String DoB;
  private String discipline;
  private String campus;
  private String contact;
  private String gender;
  private double salary;
  private String email;

  private ArrayList<Semester> teacherSemester;

  @Override
  public String toString() {
    return "Teacher";
  }

  public Teacher() {
    teacherSemester = new ArrayList<Semester>();
  }

  public Teacher(String name, String doB, String discipline, String campus, String contact, String gender, double salary, String email, ArrayList<Semester> teacherSemester) {
    this.name = name;
    DoB = doB;
    this.discipline = discipline;
    this.campus = campus;
    this.contact = contact;
    this.gender = gender;
    this.salary = salary;
    this.email = email;
    this.teacherSemester = teacherSemester;
  }

  @Override
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @Override
  public String getDoB() {
    return DoB;
  }

  public void setDoB(String doB) {
    DoB = doB;
  }

  @Override
  public String getDiscipline() {
    return discipline;
  }

  public void setDiscipline(String discipline) {
    this.discipline = discipline;
  }

  @Override
  public String getCampus() {
    return campus;
  }

  public void setCampus(String campus) {
    this.campus = campus;
  }

  @Override
  public String getContact() {
    return contact;
  }

  public void setContact(String contact) {
    this.contact = contact;
  }

  @Override
  public String getGender() {
    return gender;
  }

  public void setGender(String gender) {
    this.gender = gender;
  }

  @Override
  public double getSalary() {
    return salary;
  }

  public void setSalary(double salary) {
    this.salary = salary;
  }

  @Override
  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  @Override
  public ArrayList<Semester> getTeacherSemester() {
    return teacherSemester;
  }

  public void setTeacherSemester(ArrayList<Semester> teacherSemester) {
    this.teacherSemester = teacherSemester;
  }

  @Override
  public void loadUser(String email, String pass) {
    Teacher_DataBase tdb = new Teacher_DataBase();
    tdb.queryTeacherObject(this,email,pass);
    this.teacherSemester = tdb.queryTeacher(this);

  }

  @Override
  public boolean setDates(String SemesterTitle, LocalDate openDate, LocalDate closeDate, LocalDate withDrawDate) {
    throw new UnsupportedOperationException("You Don't have access to this method!");
  }

  @Override
  public boolean addAttendance(String std, String status, String date, String semester, String course, String section) {
    Attendence_DataBase attDb = new Attendence_DataBase();
    return attDb.addAttendance(std, section, date,status, semester, course);
  }

  @Override
  public boolean createEvaluation(String title, Integer tmarks, Float weight,String std ,String semester, String crs, String sec) {
    return GUI.getInstance().createEvaluation(title, tmarks, weight, std ,semester, crs, sec);
  }

  @Override
  public boolean updateEvaluation(String title, Integer obmarks, String std, String semester, String crs, String sec) {
    return GUI.getInstance().updateEvaluation(title, obmarks,std ,semester, crs, sec);
  }

  @Override
  public boolean updateGrade(String semester, String crs, String sec, String std, String g) {
    return GUI.getInstance().updateGrade(semester,crs,sec,std,g);
  }
}
