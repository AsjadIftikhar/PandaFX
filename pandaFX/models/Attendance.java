package pandaFX.models;

import java.time.LocalDate;

public class Attendance {


  // Fields
  private LocalDate date;
  private String status;
  private CourseRegistration cr;

  public LocalDate getDate() {
    return date;
  }

  public void setDate(LocalDate date) {
    this.date = date;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public CourseRegistration getCr() {
    return cr;
  }

  public void setCr(CourseRegistration cr) {
    this.cr = cr;
  }
}
