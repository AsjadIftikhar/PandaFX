package pandaFX;

import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;

public class Stu_TableCr {

    private SimpleStringProperty CourseCode;
    private SimpleStringProperty CourseName;
    private SimpleStringProperty Status;
    private SimpleIntegerProperty CrdHrs;
    private ComboBox Section;
    private Button Register;
    private Button Drop;
    private Button Waiting_List;


    public Stu_TableCr(String courseCode, String courseName, String status, int crdHrs, ObservableList data, Button d, Button rg) {

        CourseCode = new SimpleStringProperty(courseCode);
        CourseName = new SimpleStringProperty(courseName);
        Status = new SimpleStringProperty(status);
        CrdHrs = new SimpleIntegerProperty(crdHrs);
        Section = new ComboBox();
        Section.setItems(data);
        Register = rg;
        Drop = d;

        if(status.equals("Not Registered"))
        {
            Drop.setDisable(true);
        }
        else
        {
            Register.setDisable(true);
        }
        Register.setId(courseCode);
        Section.getSelectionModel().selectFirst();

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

    public String getStatus() {
        return Status.get();
    }

    public SimpleStringProperty statusProperty() {
        return Status;
    }

    public void setStatus(String status) {
        this.Status.set(status);
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

    public ComboBox getSection() {
        return Section;
    }

    public void setSection(ComboBox section) {
        Section = section;
    }

    public Button getRegister() {
        return Register;
    }

    public void setRegister(Button register) {
        Register = register;
    }

    public Button getDrop() {
        return Drop;
    }

    public void setDrop(Button drop) {
        Drop = drop;
    }
}