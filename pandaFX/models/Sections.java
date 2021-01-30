package pandaFX.models;

import pandaFX.controller.Evaluation_DataBase;

import java.util.ArrayList;
import java.util.Queue;

public class Sections {

  // Fields
  private String title;
  private int capacity;
  private Queue<Student> waitingList;

  private Course secCourse;
  private ArrayList<CourseRegistration> CR;

  public Sections() {
    CR = new ArrayList<>();

  }

  public Sections(String title, int capacity, Queue<Student> waitingList, Course secCourse, ArrayList<CourseRegistration> CR) {
    this.title = title;
    this.capacity = capacity;
    this.waitingList = waitingList;
    this.secCourse = secCourse;
    this.CR = CR;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public int getCapacity() {
    return capacity;
  }

  public void setCapacity(int capacity) {
    this.capacity = capacity;
  }

  public Queue<Student> getWaitingList() {
    return waitingList;
  }

  public void setWaitingList(Queue<Student> waitingList) {
    this.waitingList = waitingList;
  }

  public Course getSecCourse() {
    return secCourse;
  }

  public void setSecCourse(Course secCourse) {
    this.secCourse = secCourse;
  }

  public ArrayList<CourseRegistration> getCR() {
    return CR;
  }

  public void setCR(ArrayList<CourseRegistration> CR) {
    this.CR = CR;
  }

  public boolean createEvaluation(String title, Integer tmarks, Float weight,String std , String semester, String crs, String sec) {

    for (CourseRegistration cr : getCR()) {
      if (cr.getStu().getRoll_number().equals(std)){
        return cr.createEvaluation(title, tmarks, weight, std, semester, crs, sec);
      }
    }
    return false;
  }

  public boolean updateEvaluation(String title, Integer obmarks, String std, String semester, String crs, String sec) {
    for (CourseRegistration cr : getCR()) {
      if (cr.getStu().getRoll_number().equals(std)){
        return cr.updateEvaluation(title, obmarks, std, semester, crs, sec);
      }
    }
    return false;
  }

  public boolean updateGrade(String semester, String crs, String sec, String std, String g) {
    for (CourseRegistration cr : getCR()) {
      if (cr.getStu().getRoll_number().equals(std)){
        return cr.updateGrade(semester,crs,sec,std,g);
      }
    }
    return false;
  }
}