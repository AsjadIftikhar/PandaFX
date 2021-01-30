package pandaFX.models;

import pandaFX.controller.AcOfficer_DataBase;
import pandaFX.controller.Semester_DataBase;
import pandaFX.controller.Teacher_DataBase;

import java.time.LocalDate;
import java.util.ArrayList;

public class AC_Officer implements User {

  // Fields

  private double salary;
  private String email;
  private String name;
  private String DoB;
  private String campus;
  private String contact;
  private String Discipline;
  private String gender;

  //Constructor

  public AC_Officer() {
  }

  public AC_Officer(double salary, String email, String name, String dob, String campus, String contact, String gender) {

    this.salary = salary;
    this.email = email;
    this.name = name;
    DoB = dob;
    this.campus = campus;
    this.contact = contact;
    this.gender = gender;
  }

  @Override
  public ArrayList<Semester> getTeacherSemester() {

    return null;
  }

  @Override
  public void loadUser(String email, String pass) {
    AcOfficer_DataBase tdb = new AcOfficer_DataBase();
    tdb.queryACObject(this,email,pass);

  }

  @Override
  public boolean setDates(String SemesterTitle, LocalDate openDate, LocalDate closeDate, LocalDate withDrawDate) {

    Semester_DataBase sdb = new Semester_DataBase();
    return sdb.addDates(SemesterTitle,openDate.toString(),closeDate.toString(),withDrawDate.toString());
  }

  @Override
  public boolean addAttendance(String std, String status, String date, String semester, String course, String section) {
    throw new UnsupportedOperationException("You Don't have access to this method!");
  }

  @Override
  public boolean createEvaluation(String title, Integer tmarks, Float weight, String std, String semester, String crs, String sec) {
    throw new UnsupportedOperationException("You Don't have access to this method!");
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
  public String getName() {
    return name;
  }

  @Override
  public String getDoB() {
    return DoB;
  }

  @Override
  public String getDiscipline() {
    return Discipline;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setDob(String dob) {
    DoB = dob;
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

  public void setDiscipline(String discipline) {
    Discipline = discipline;
  }

  @Override
  public boolean updateEvaluation(String title, Integer obmarks, String std, String semester, String crs, String sec) {
    throw new UnsupportedOperationException("You Don't have access to this method!");
  }

  @Override
  public boolean updateGrade(String semester, String crs, String sec, String std, String g) {
    throw new UnsupportedOperationException("You Don't have access to this method!");
  }
}
