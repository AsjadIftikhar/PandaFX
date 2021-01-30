package pandaFX.models;

import java.util.*;

public class Course {

  // Fields
  private String title;
  private int credit_hours;
  private String code;
  private ArrayList<Sections> SecArray;
  private Semester courseSem;


  public Course() {
    SecArray = new ArrayList<>();
  }

  public Course(String title, int credit_hours, String code, ArrayList<Sections> secArray) {
    this.title = title;
    this.credit_hours = credit_hours;
    this.code = code;
    SecArray = secArray;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public int getCredit_hours() {
    return credit_hours;
  }

  public void setCredit_hours(int credit_hours) {
    this.credit_hours = credit_hours;
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public ArrayList<Sections> getSecArray() {
    return SecArray;
  }

  public void setSecArray(ArrayList<Sections> secArray) {
    SecArray = secArray;
  }

  public Semester getCourseSem() {
    return courseSem;
  }

  public void setCourseSem(Semester courseSem) {
    this.courseSem = courseSem;
  }

  public ArrayList<CourseRegistration> fetchAllRegistrations(String sec) {
    for (Sections s : getSecArray()) {
      if (s.getTitle().equals(sec)) {
        return s.getCR();
      }
    }
    return null;
  }

  public boolean createEvaluation(String title, Integer tmarks, Float weight, String std, String semester, String crs, String sec) {
    for (Sections s : getSecArray()) {
      if (s.getTitle().equals(sec)) {
        return s.createEvaluation(title, tmarks, weight, std, semester, crs, sec);
      }
    }
    return false;
  }

  public boolean updateEvaluation(String title, Integer obmarks, String std, String semester, String crs, String sec) {

    for (Sections s : getSecArray()) {
      if (s.getTitle().equals(sec)) {
        return s.updateEvaluation(title, obmarks, std, semester, crs, sec);
      }
    }
    return false;
  }

  public boolean updateGrade(String semester, String crs, String sec, String std, String g) {
    for (Sections s : getSecArray()) {
      if (s.getTitle().equals(sec)) {
        return s.updateGrade(semester,crs,sec,std,g);
      }
    }
    return false;
  }
}
