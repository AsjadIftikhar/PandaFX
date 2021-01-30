package pandaFX.models;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

public class Semester {

  // Fields
  private String title;

  private ArrayList<Course> CourseArray;

  private LocalDate startDate;
  private LocalDate endDate;
  private LocalDate withDrawDate;

  public Semester() {

    CourseArray = new ArrayList<>();

  }

  public Semester(String title, ArrayList<Course> courseArray, LocalDate startDate, LocalDate endDate, LocalDate withDrawDate) {
    this.title = title;
    CourseArray = courseArray;
    this.startDate = startDate;
    this.endDate = endDate;
    this.withDrawDate = withDrawDate;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public ArrayList<Course> getCourseArray() {
    return CourseArray;
  }

  public void setCourseArray(ArrayList<Course> courseArray) {
    CourseArray = courseArray;
  }

  public LocalDate getStartDate() {
    return startDate;
  }

  public void setStartDate(LocalDate startDate) {
    this.startDate = startDate;
  }

  public LocalDate getEndDate() {
    return endDate;
  }

  public void setEndDate(LocalDate endDate) {
    this.endDate = endDate;
  }

  public LocalDate getWithDrawDate() {
    return withDrawDate;
  }

  public void setWithDrawDate(LocalDate withDrawDate) {
    this.withDrawDate = withDrawDate;
  }

  public ArrayList<CourseRegistration> fetchAllRegistrations(String crs, String sec) {
    for (Course c : getCourseArray()) {
      if (c.getCode().equals(crs)) {
        return c.fetchAllRegistrations(sec);
      }
    }
    return null;
  }

  public boolean createEvaluation(String title, Integer tmarks, Float weight, String std, String semester, String crs, String sec) {
    for (Course c : getCourseArray()) {
      if (c.getCode().equals(crs)) {
        return c.createEvaluation(title, tmarks, weight, std, semester, crs, sec);
      }
    }
    return false;
  }

  public boolean updateEvaluation(String title, Integer obmarks, String std, String semester, String crs, String sec) {

    for (Course c : getCourseArray()) {
      if (c.getCode().equals(crs)) {
        return c.updateEvaluation(title, obmarks, std, semester, crs, sec);
      }
    }
    return false;
  }

  public boolean updateGrade(String semester, String crs, String sec, String std, String g) {
    for (Course c : getCourseArray()) {
      if (c.getCode().equals(crs)) {
        return c.updateGrade(semester,crs,sec,std,g);
      }
    }
    return false;
  }
}
