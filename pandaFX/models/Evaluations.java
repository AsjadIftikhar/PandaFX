package pandaFX.models;


public class Evaluations {


  // Fields
  private String title;
  private float weight;
  private float total_marks;
  private float obtained_marks;
  private String date;

  private CourseRegistration CR;

  public Evaluations() {
  }

  public Evaluations(String title, float weight, float total_marks, CourseRegistration CR) {
    this.title = title;
    this.weight = weight;
    this.total_marks = total_marks;
    this.CR = CR;
  }


  public Evaluations(String title, float weight, float total_marks, float obtained_marks, String date, CourseRegistration CR) {
    this.title = title;
    this.weight = weight;
    this.total_marks = total_marks;
    this.obtained_marks = obtained_marks;
    this.date = date;
    this.CR = CR;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public float getWeight() {
    return weight;
  }

  public void setWeight(float weight) {
    this.weight = weight;
  }

  public float getTotal_marks() {
    return total_marks;
  }

  public void setTotal_marks(float total_marks) {
    this.total_marks = total_marks;
  }

  public float getObtained_marks() {
    return obtained_marks;
  }

  public void setObtained_marks(float obtained_marks) {
    this.obtained_marks = obtained_marks;
  }

  public String getDate() {
    return date;
  }

  public void setDate(String date) {
    this.date = date;
  }

  public CourseRegistration getCR() {
    return CR;
  }

  public void setCR(CourseRegistration CR) {
    this.CR = CR;
  }
}
