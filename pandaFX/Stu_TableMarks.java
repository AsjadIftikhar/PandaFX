package pandaFX;

import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Stu_TableMarks {
    private  SimpleStringProperty Type;
    private SimpleFloatProperty Obtained_Marks;
    private SimpleFloatProperty  Total_Marks;
    private SimpleFloatProperty  Weightage;

    public Stu_TableMarks(String type, float obtained_Marks, float total_Marks, float w) {
        Type =  new SimpleStringProperty(type);
        Obtained_Marks = new SimpleFloatProperty(obtained_Marks);
        Total_Marks = new SimpleFloatProperty(total_Marks) ;
        Weightage = new SimpleFloatProperty(w) ;

    }

    public float getWeightage() {
        return Weightage.get();
    }

    public SimpleFloatProperty weightageProperty() {
        return Weightage;
    }

    public void setWeightage(float weightage) {
        this.Weightage.set(weightage);
    }

    public String getType() {
        return Type.get();
    }

    public SimpleStringProperty typeProperty() {
        return Type;
    }

    public void setType(String type) {
        this.Type.set(type);
    }

    public float getObtained_Marks() {
        return Obtained_Marks.get();
    }

    public SimpleFloatProperty obtained_MarksProperty() {
        return Obtained_Marks;
    }

    public void setObtained_Marks(float obtained_Marks) {
        this.Obtained_Marks.set(obtained_Marks);
    }

    public float getTotal_Marks() {
        return Total_Marks.get();
    }

    public SimpleFloatProperty total_MarksProperty() {
        return Total_Marks;
    }

    public void setTotal_Marks(float total_Marks) {
        this.Total_Marks.set(total_Marks);
    }
}
