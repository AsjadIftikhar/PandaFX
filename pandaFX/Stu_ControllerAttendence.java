package pandaFX;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import pandaFX.models.*;
import java.io.IOException;
import java.util.ArrayList;

public class Stu_ControllerAttendence {


    ObservableList<String> Semester_Id_List =  FXCollections.observableArrayList();
    ObservableList<Stu_TableAttendence> Table_data = FXCollections.observableArrayList();
    private String Current_Sem_id ;
    @FXML
    private ComboBox Semester_Id;
    @FXML
    private Button b1 , b2 , b3 , b4  , b5 ;
    @FXML
    private TableView<Stu_TableAttendence> Attendence_View;
    @FXML
    private TableColumn<Stu_TableAttendence,Integer> Lecture_No;
    @FXML
    private TableColumn<Stu_TableAttendence,String> Date;
    @FXML
    private TableColumn<Stu_TableAttendence,String> Status;




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

            if(semester.getTitle().equals(Semester_Id.getValue()))
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
        Attendence_View.getItems().clear();

        Current_Sem_id =(String) Semester_Id.getValue() ;

        Student s = (Student) GUI.getInstance().getPerson();

        for (Semester semester : s.getSems_Data()) {

            if (semester.getTitle().equals(Semester_Id.getValue())) {
                ArrayList<Course> c = semester.getCourseArray();

                for (Course c1 : c) {
                    if(c1.getCode()==Cc)
                    {
                        ArrayList<Attendance> e = c1.getSecArray().get(0).getCR().get(0).getCourseAtt();
                        for(int i=0;i<e.size();i++)
                        {
                            Attendance e1 = e.get(i);
                            String q1,q2;
                            q1 = e1.getDate().toString();
                            q2 = e1.getStatus();
                            Stu_TableAttendence m1 = new Stu_TableAttendence(i+1,q1,q2);
                            Table_data.add(m1);
                            Attendence_View.setItems(Table_data);
                        }
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

        Lecture_No.setCellValueFactory(new PropertyValueFactory<Stu_TableAttendence, Integer>("Lecture_No"));
        Date.setCellValueFactory(new PropertyValueFactory<Stu_TableAttendence, String>("Date"));
        Status.setCellValueFactory(new PropertyValueFactory<Stu_TableAttendence,String>("Status"));


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
