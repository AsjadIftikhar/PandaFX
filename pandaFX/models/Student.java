package pandaFX.models;


import pandaFX.controller.Student_DataBase;
import pandaFX.controller.Teacher_DataBase;

import java.time.LocalDate;
import java.util.ArrayList;

public class Student implements StudentInterface {

   private String roll_number;
   private int batch;
   private String name;
   private String DoB;
   private String discipline;
   private String campus;
   private String contact;
   private String gender;
   private float cgpa;
   private String email;
   private ArrayList<Semester> Sems_Data ;
   private double salary;


   public Student() {
   }

   public String getRoll_number() {
      return roll_number;
   }

   public void setRoll_number(String roll_number) {
      this.roll_number = roll_number;
   }

   public int getBatch() {
      return batch;
   }

   public void setBatch(int batch) {
      this.batch = batch;
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

   public float getCgpa() {
      return cgpa;
   }

   public void setCgpa(float cgpa) {
      this.cgpa = cgpa;
   }

   @Override
   public String getEmail() {
      return email;
   }

   @Override
   public ArrayList<Semester> getTeacherSemester() {
      return null;
   }

   @Override
   public void loadUser(String email, String pass) {
      Student_DataBase tdb = new Student_DataBase();
      tdb.query_Get_Stu_Data(this,email,pass);
   }

   @Override
   public boolean setDates(String SemesterTitle, LocalDate openDate, LocalDate closeDate, LocalDate withDrawDate) {
      return false;
   }

   @Override
   public boolean addAttendance(String std, String status, String date, String semester, String course, String section) {
      return false;
   }

   @Override
   public boolean createEvaluation(String title, Integer tmarks, Float weight, String std, String semester, String crs, String sec) {
      return false;
   }

   @Override
   public boolean updateEvaluation(String title, Integer obmarks, String std, String semester, String crs, String sec) {
      return false;
   }

   @Override
   public boolean updateGrade(String semester, String crs, String sec, String std, String g) {
      return false;
   }

   public void setEmail(String email) {
      this.email = email;
   }

   public ArrayList<Semester> getSems_Data() {
      return Sems_Data;
   }

   public void setSems_Data(ArrayList<Semester> sems_Data) {
      Sems_Data = sems_Data;
   }

   @Override
   public double getSalary() {
      return salary;
   }

   public void setSalary(double salary) {
      this.salary = salary;
   }

}
