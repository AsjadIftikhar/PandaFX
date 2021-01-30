package pandaFX.models;

import pandaFX.controller.CourseRegisteration_DataBase;
import pandaFX.controller.Evaluation_DataBase;

import java.util.ArrayList;

public class CourseRegistration {

  // Fields
  private String grade;
  private Sections secCR;
  private Student stu;

  private ArrayList<Evaluations> CourseEval;
  private ArrayList<Attendance> CourseAtt;

  public CourseRegistration(String grade, Sections secCR, Student stu, ArrayList<Evaluations> courseEval, ArrayList<Attendance> courseAtt) {
    this.grade = grade;
    this.secCR = secCR;
    this.stu = stu;
    CourseEval = courseEval;
    CourseAtt = courseAtt;
  }

  public CourseRegistration() {
  }

  public String getGrade() {
    return grade;
  }

  public void setGrade(String grade) {
    this.grade = grade;
  }

  public Sections getSecCR() {
    return secCR;
  }

  public void setSecCR(Sections secCR) {
    this.secCR = secCR;
  }

  public Student getStu() {
    return stu;
  }

  public void setStu(Student stu) {
    this.stu = stu;
  }

  public ArrayList<Evaluations> getCourseEval() {
    return CourseEval;
  }

  public void setCourseEval(ArrayList<Evaluations> courseEval) {
    CourseEval = courseEval;
  }

  public ArrayList<Attendance> getCourseAtt() {
    return CourseAtt;
  }

  public void setCourseAtt(ArrayList<Attendance> courseAtt) {
    CourseAtt = courseAtt;
  }

  public boolean createEvaluation(String title, Integer tmarks, Float weight, String std, String semester, String crs, String sec) {

    boolean success = false;

    //ADD evaluation to the database for the entire section.
    Evaluation_DataBase evdb = new Evaluation_DataBase();
    success = evdb.createEvaluation(semester, crs, sec, title, weight, tmarks, std);

    //Add evaluation to the class
    Evaluations newEvaluation = new Evaluations(title, weight, tmarks, this);
    CourseEval.add(newEvaluation);

    return success;
  }

  public boolean updateEvaluation(String title, Integer obmarks, String std, String semester, String crs, String sec) {
    boolean success = false;

    //Update evaluation in the database for the entire section.
    Evaluation_DataBase evdb = new Evaluation_DataBase();
    success = evdb.updateEvaluation(std, semester, crs, sec, title, obmarks);

    //Update evaluation in the class
    for (Evaluations e : CourseEval) {
      if (e.getTitle().equals(title)) {
        e.setObtained_marks(obmarks);
      }
    }


    return success;
  }

  public boolean updateGrade(String semester, String crs, String sec, String std, String g) {
    boolean success = false;

    setGrade(g);

    //IN database:
    CourseRegisteration_DataBase crdb = new CourseRegisteration_DataBase();
    success = crdb.addGrade(std,semester,crs,sec,g);

    return success;
  }
}
