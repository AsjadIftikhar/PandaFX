package pandaFX;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import pandaFX.models.*;

import java.io.IOException;
import java.util.ArrayList;

public class Stu_ControllerMarks {

    ObservableList<String> Semester_Id_List =  FXCollections.observableArrayList();
    ObservableList<Stu_TableMarks> Table_data = FXCollections.observableArrayList();

    private String Current_Sem_id ;
    @FXML
    private ComboBox Semester_Id;
    @FXML
    private Button b1 , b2 , b3 , b4  , b5 ;
    @FXML
    private TableView<Stu_TableMarks> Marks_view;
    @FXML
    private TableColumn<Stu_TableMarks,String> Type;
    @FXML
    private TableColumn<Stu_TableMarks,Float> Obtained_Marks;
    @FXML
    private TableColumn<Stu_TableMarks,Float> Total_Marks;
    @FXML
    private TableColumn<Stu_TableMarks,Float> Weightage;
    @FXML
    private Label Total_Mark;
    @FXML
    private Label Obtained_Mark;




    @FXML
    private void comboAction(ActionEvent event) {

        b1.setText(".");
        b2.setText(".");
        b3.setText(".");
        b4.setText(".");
        b5.setText(".");

        Current_Sem_id =(String) Semester_Id.getValue() ;

        Student s = (Student) GUI.getInstance().getPerson();

        for (Semester semester : s.getSems_Data()) {

            if(semester.getTitle()==Semester_Id.getValue())
            {
               ArrayList<Course> c = semester.getCourseArray();
               int index = 0;
               for(Course c1 : c)
               {
                   index++;
                   if(index==1){
                       b1.setText(c1.getCode());
                   }else if(index==2){
                       b2.setText(c1.getCode());
                   }else if(index==3){
                       b3.setText(c1.getCode());
                   }else if(index==4){
                       b4.setText(c1.getCode());
                   }else if(index==5){
                       b5.setText(c1.getCode());
                   }

               }
            }
        }


    }

    private void addTable(String Cc)
    {
        Total_Mark.setText("0");
        Obtained_Mark.setText("0");

        Marks_view.getItems().clear();

        Current_Sem_id =(String) Semester_Id.getValue() ;

        Student s = (Student) GUI.getInstance().getPerson();

        for (Semester semester : s.getSems_Data()) {

            if (semester.getTitle() == Semester_Id.getValue()) {
                ArrayList<Course> c = semester.getCourseArray();

                for (Course c1 : c) {
                    if(c1.getCode()==Cc)
                    {
                        float TM = 0,OBM = 0, Temp=0;
                        ArrayList<Evaluations> e = c1.getSecArray().get(0).getCR().get(0).getCourseEval();
                        for(int i=0;i<e.size();i++)
                        {
                            Evaluations e1 = e.get(i);
                            TM += e1.getWeight();
                            Temp = (e1.getObtained_marks()/e1.getTotal_marks()) * e1.getWeight();
                            OBM += Temp;
                            Stu_TableMarks m1 = new Stu_TableMarks(e1.getTitle(),e1.getObtained_marks(),e1.getTotal_marks(),e1.getWeight());
                            Table_data.add(m1);
                            Marks_view.setItems(Table_data);
                        }
                        Total_Mark.setText(String.valueOf(TM));
                        Obtained_Mark.setText(String.valueOf(OBM));
                        break;
                    }
                }
            }
        }
    }

    @FXML
    private void buttonAction1(ActionEvent event) {

        String s1 = b1.getText();
        if(s1!=".")
        {
            addTable(s1);
        }

    }
    @FXML
    private void buttonAction2(ActionEvent event) {

        String s1 = b2.getText();
        if(s1!=".")
        {
            addTable(s1);
        }


    }
    @FXML
    private void buttonAction3(ActionEvent event) {

        String s1 = b3.getText();
        if(s1!=".")
        {
            addTable(s1);
        }


    }
    @FXML
    private void buttonAction4(ActionEvent event) {

        String s1 = b4.getText();
        if(s1!=".")
        {
            addTable(s1);
        }


    }
    @FXML
    private void buttonAction5(ActionEvent event) {

        String s1 = b5.getText();
        if(s1!=".")
        {
            addTable(s1);
        }


    }



    @FXML
    public void initialize() {
        Student s = (Student) GUI.getInstance().getPerson();
        for (Semester S : s.getSems_Data())
        {
            Semester_Id_List.add(S.getTitle());
        }
        Semester_Id.setItems(Semester_Id_List);

        Type.setCellValueFactory(new PropertyValueFactory<Stu_TableMarks, String>("Type"));
        Obtained_Marks.setCellValueFactory(new PropertyValueFactory<Stu_TableMarks, Float>("Obtained_Marks"));
        Total_Marks.setCellValueFactory(new PropertyValueFactory<Stu_TableMarks,Float>("Total_Marks"));
        Weightage.setCellValueFactory(new PropertyValueFactory<Stu_TableMarks,Float>("Weightage"));


    }

    Stu_ControllerMainMenu m = new Stu_ControllerMainMenu();

    public void Go_CR(ActionEvent event) throws IOException {
        m.Go_CR(event);
    }
    public void Go_Main(ActionEvent event) throws IOException {
        m.Go_Main(event);
    }
    public void Go_Attendence(ActionEvent event) throws IOException {
        m.Go_Attendence(event);
    }
    public void Go_Transcript(ActionEvent event) throws IOException {
        m.Go_Transcript(event);
    }
    public void Go_Marks(ActionEvent event) throws IOException {
        m.Go_Marks(event);
    }
    public void Go_WD(ActionEvent event) throws IOException {
        m.Go_WD(event);
    }

}
