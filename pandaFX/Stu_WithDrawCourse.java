package pandaFX;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import pandaFX.controller.Student_GUI;
import pandaFX.models.*;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;


public class Stu_WithDrawCourse {


    @FXML
    private ComboBox Semester_Id;
    @FXML
    private TableView<Stu_TableWithDraw> Cr_view;
    @FXML
    private TableColumn<Stu_TableWithDraw,String> CourseCode;
    @FXML
    private TableColumn<Stu_TableWithDraw,String> CourseName;
    @FXML
    private TableColumn<Stu_TableWithDraw, Button> Withdraw;
    @FXML
    private TableColumn<Stu_TableWithDraw,Integer> CrdHrs;
    @FXML
    private Label Reg_Per;


    private String Current_Sem_id ;
    private static String C_s_id;

    ObservableList<String> Semester_Id_List =  FXCollections.observableArrayList();
    ObservableList<Stu_TableWithDraw> Table_data = FXCollections.observableArrayList();


    // Intializer


    @FXML
    private void comboAction(ActionEvent event) {
        Cr_view.getItems().clear();
        Current_Sem_id =(String) Semester_Id.getValue() ;
        C_s_id =(String) Semester_Id.getValue();

        Student s = (Student) GUI.getInstance().getPerson();

        LocalDate datetoday = LocalDate.now();
        for (Semester semester : s.getSems_Data())
            if (semester.getTitle() != Semester_Id.getValue()) {
            } else {

                if (datetoday.isAfter(semester.getStartDate()) && datetoday.isBefore(semester.getWithDrawDate())) {
                    Reg_Per.setText("Withdraw Course Period is Active");
                    addTable();
                } else {
                    Reg_Per.setText("Withdraw Course has passed its deadline");
                }

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


        CourseCode.setCellValueFactory(new PropertyValueFactory<Stu_TableWithDraw, String>("CourseCode"));
        CourseName.setCellValueFactory(new PropertyValueFactory<Stu_TableWithDraw, String>("CourseName"));
        CrdHrs.setCellValueFactory(new PropertyValueFactory<Stu_TableWithDraw,Integer>("CrdHrs"));
        Withdraw.setCellValueFactory(new PropertyValueFactory<Stu_TableWithDraw, Button>("Withdraw"));


    }

    @FXML
    public void DropAction(ActionEvent event)
    {
        final Node source = (Node) event.getSource();
        String id = source.getId();
        Student_GUI sS = new Student_GUI((Student) GUI.getInstance().getPerson());
        Semester sem = new Semester(); Course crs= new Course(); Sections sec = new Sections();
        sem.setTitle(C_s_id);
        crs.setCode(id);

        //Get Course Section
        Student stu = (Student) GUI.getInstance().getPerson();

        for(Semester s1 : stu.getSems_Data())
        {
            if(s1.getTitle().equals(sem.getTitle()))
            {
                for(Course c : s1.getCourseArray())
                {
                    if(c.getCode().equals(crs.getCode()))
                    {
                        sec.setTitle(c.getSecArray().get(0).getTitle());
                    }
                }
            }

        }

        //To Delete course from Student Array
        sS.Del_Course(sem,crs);
        addTable();
    }

    private String check_reg(String Cc)
    {
        String Status = "Not Registered";
        Student s = (Student) GUI.getInstance().getPerson();

        for(Semester semester : s.getSems_Data())
        {
            if(semester.getTitle().equals(this.Current_Sem_id))
            {
                for(Course cc : semester.getCourseArray())
                {
                    String s1 = cc.getTitle();
                    if(s1.compareTo(Cc)==0)
                    {
                        Status = "Registered";
                    }
                }

            }

        }

        return Status;
    }
    private void addTable()
    {
        Cr_view.getItems().clear();

        Current_Sem_id =(String) Semester_Id.getValue() ;
        C_s_id =(String) Semester_Id.getValue();

        ArrayList<Semester> s = GUI.getInstance().getAllSemesterData();

        for (Semester semester : s) {

            ArrayList<Course> c = semester.getCourseArray();
            for (Course c1 : c) {

                String asd = check_reg(c1.getTitle());
                if(asd.equals("Registered"))
                {
                    ObservableList<String> Section_Id_List =  FXCollections.observableArrayList();
                    ArrayList<Sections> sec = c1.getSecArray();
                    for(Sections se1: sec)
                    {
                        Section_Id_List.add(se1.getTitle());
                    }
                    Button b = new Button();
                    b.setText("Withdraw");
                    b.setId(c1.getCode());
                    b.setOnAction(this::DropAction);

                    Stu_TableWithDraw m1 = new Stu_TableWithDraw(c1.getCode(),c1.getTitle(),c1.getCredit_hours(),b);
                    Table_data.add(m1);

                    Cr_view.setItems(Table_data);
                }

            }
            if (semester.getTitle() == Semester_Id.getValue()) {
                break;
            }
        }
    }


    ///Show Menu

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

