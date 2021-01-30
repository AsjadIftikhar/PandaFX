package pandaFX;

import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Stu_Trans_Table {

    private SimpleStringProperty CourseCode;
    private SimpleStringProperty CourseName;
    private SimpleStringProperty Section;
    private SimpleIntegerProperty CrdHrs;
    private SimpleStringProperty Grade;
    private SimpleFloatProperty Points;


    public Stu_Trans_Table(String courseCode, String courseName, String section, int crdHrs, String grade, float points) {
        CourseCode = new SimpleStringProperty(courseCode);
        CourseName = new SimpleStringProperty(courseName);
        Section = new SimpleStringProperty(section);
        CrdHrs = new SimpleIntegerProperty(crdHrs);
        Grade = new SimpleStringProperty(grade);
        Points = new SimpleFloatProperty(points);
    }

    public String getCourseCode() {
        return CourseCode.get();
    }

    public SimpleStringProperty courseCodeProperty() {
        return CourseCode;
    }

    public void setCourseCode(String courseCode) {
        this.CourseCode.set(courseCode);
    }

    public String getCourseName() {
        return CourseName.get();
    }

    public SimpleStringProperty courseNameProperty() {
        return CourseName;
    }

    public void setCourseName(String courseName) {
        this.CourseName.set(courseName);
    }

    public String getSection() {
        return Section.get();
    }

    public SimpleStringProperty sectionProperty() {
        return Section;
    }

    public void setSection(String section) {
        this.Section.set(section);
    }

    public int getCrdHrs() {
        return CrdHrs.get();
    }

    public SimpleIntegerProperty crdHrsProperty() {
        return CrdHrs;
    }

    public void setCrdHrs(int crdHrs) {
        this.CrdHrs.set(crdHrs);
    }

    public String getGrade() {
        return Grade.get();
    }

    public SimpleStringProperty gradeProperty() {
        return Grade;
    }

    public void setGrade(String grade) {
        this.Grade.set(grade);
    }

    public float getPoints() {
        return Points.get();
    }

    public SimpleFloatProperty pointsProperty() {
        return Points;
    }

    public void setPoints(float points) {
        this.Points.set(points);
    }
}