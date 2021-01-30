package pandaFX;

import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Stu_TableAttendence {
    private SimpleIntegerProperty Lecture_No;
    private SimpleStringProperty Date;
    private final SimpleStringProperty  Status;

    public Stu_TableAttendence(int a , String b , String c) {
        Lecture_No =  new SimpleIntegerProperty(a);
        Date = new SimpleStringProperty(b);
        Status = new SimpleStringProperty(c) ;
    }

    public int getLecture_No() {
        return Lecture_No.get();
    }

    public SimpleIntegerProperty lecture_NoProperty() {
        return Lecture_No;
    }

    public void setLecture_No(int lecture_No) {
        this.Lecture_No.set(lecture_No);
    }

    public String getDate() {
        return Date.get();
    }

    public SimpleStringProperty dateProperty() {
        return Date;
    }

    public void setDate(String date) {
        this.Date.set(date);
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
}
