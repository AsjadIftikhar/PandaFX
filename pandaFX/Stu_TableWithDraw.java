package pandaFX;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;

public class Stu_TableWithDraw {


    private SimpleStringProperty CourseCode;
    private SimpleStringProperty CourseName;
    private SimpleIntegerProperty CrdHrs;
    private Button Withdraw;


    public Stu_TableWithDraw(String courseCode, String courseName, int crdHrs, Button rg) {

        CourseCode = new SimpleStringProperty(courseCode);
        CourseName = new SimpleStringProperty(courseName);
        CrdHrs = new SimpleIntegerProperty(crdHrs);
        Withdraw = rg;

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

    public int getCrdHrs() {
        return CrdHrs.get();
    }

    public SimpleIntegerProperty crdHrsProperty() {
        return CrdHrs;
    }

    public void setCrdHrs(int crdHrs) {
        this.CrdHrs.set(crdHrs);
    }

    public Button getWithdraw() {
        return Withdraw;
    }

    public void setWithdraw(Button withdraw) {
        Withdraw = withdraw;
    }
}
