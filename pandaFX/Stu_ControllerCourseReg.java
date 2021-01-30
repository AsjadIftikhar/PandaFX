package pandaFX;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.effect.Light;
import pandaFX.controller.Sections_DataBase;
import pandaFX.controller.Student_GUI;
import pandaFX.models.*;
import java.io.IOException;
import java.time.LocalDate;
import java.util.*;

public class Stu_ControllerCourseReg {


    @FXML
    private ComboBox Semester_Id;
    @FXML
    private TableView<Stu_TableCr> Cr_view;
    @FXML
    private TableColumn<Stu_TableCr,String> CourseCode;
    @FXML
    private TableColumn<Stu_TableCr,String> CourseName;
    @FXML
    private TableColumn<Stu_TableCr,String> Status;
    @FXML
    private TableColumn<Stu_TableCr,String> Section;
    @FXML
    private TableColumn<Stu_TableCr, Button> Register;
    @FXML
    private TableColumn<Stu_TableCr,Integer> CrdHrs;
    @FXML
    private TableColumn<Stu_TableCr,Button> Drop;
    @FXML
    private TableColumn<Stu_TableCr,Button> Waiting_List;
    @FXML
    private Label Reg_Per;
    @FXML
    private Label Message_Label;
    @FXML
    private Button Subscribe;


    private String Current_Sem_id ;
    private static String C_s_id;

    ObservableList<String> Semester_Id_List =  FXCollections.observableArrayList();
    ObservableList<Stu_TableCr> Table_data = FXCollections.observableArrayList();


    public Light.Point Total_Student(String sec, String sems, String cr )
    {
        Light.Point a1 = new Light.Point();
        ArrayList<Semester> s = GUI.getInstance().getAllSemesterData();
        for(Semester S : s)
        {
            for(Course c : S.getCourseArray())
                if (c.getCode().equals(cr)) {
                    for (Sections Sec : c.getSecArray())
                        if (Sec.getTitle().equals(sec)) {
                            a1.setX(Sec.getCR().size());
                            a1.setY(Sec.getCapacity());
                        }
                }

        }

        return a1;
    }

    @FXML
    private void Add_Course_Action(ActionEvent event)
    {

        final Node source = (Node) event.getSource();
        String id = source.getId();

        for(int i=0;i<Table_data.size();i++)
        {
            if(Table_data.get(i).getRegister().getId().equals(id))
            {
                Semester sem = new Semester(); Course crs= new Course();  Sections sec= new Sections();
                sem.setTitle(Current_Sem_id);
                crs.setCode(Table_data.get(i).getCourseCode());
                crs.setTitle(Table_data.get(i).getCourseName());
                crs.setCredit_hours(Table_data.get(i).getCrdHrs());
                sec.setTitle((String)Table_data.get(i).getSection().getValue());
                Light.Point a = Total_Student(sec.getTitle(),sem.getTitle(),crs.getCode());
                if(a.getX() >= a.getY())
                {
                    Subscribe.setDisable(false);
                    String S_Id = sem.getTitle() + "," + crs.getCode() + "," + sec.getTitle();
                    Subscribe.setId(S_Id);
                    Message_Label.setText("Section is Full.. Subscribe Waiting List if interested");
                }
                else
                {
                    Student_GUI s = new Student_GUI((Student) GUI.getInstance().getPerson());
                    s.Add_Course(sem,crs,sec);
                    addTable();
                }

                break;
            }

        }

    }

    @FXML
    public void Subscribe_WList(ActionEvent event)
    {
        final Node source = (Node) event.getSource();
        String id = source.getId();

        StringTokenizer date = new StringTokenizer(id,",");
        String sem = date.nextToken();
        String crs = date.nextToken();
        String sec = date.nextToken();

        ArrayList<Semester> s = GUI.getInstance().getAllSemesterData();

        for(Semester s1 : s)
        {
            if(s1.getTitle().equals(sem))
            {
                for(Course c : s1.getCourseArray())
                {
                    if(c.getCode().equals(crs))
                    {
                        for(Sections se : c.getSecArray())
                        {
                            if(se.getTitle().equals(sec))
                            {
                                Queue<Student> Q = se.getWaitingList();
                                Boolean check = false;
                                Student St = (Student) GUI.getInstance().getPerson();
                                String St_rollno = St.getRoll_number();
                                for(Student Stu1 : Q)
                                {
                                    if(Stu1.getRoll_number().equals(St_rollno))
                                    {
                                        check = true;
                                        Message_Label.setText("You're already in this list :-)");
                                    }
                                }
                                if(check == false)
                                {
                                    Student Stu = new Student();
                                    Stu.setRoll_number(St_rollno);
                                    se.getWaitingList().add(Stu);
                                    int size = se.getWaitingList().size();
                                    Sections_DataBase Sd = new Sections_DataBase();
                                    Sd.setWaitingList(s1,c,se,Stu,size);

                                    Message_Label.setText("You're added to Waiting list...");
                                    Subscribe.setDisable(true);

                                }


                            }
                        }
                    }
                }
            }

        }


    }


    @FXML
    private void comboAction(ActionEvent event) {
        Cr_view.getItems().clear();
        Current_Sem_id =(String) Semester_Id.getValue() ;
        C_s_id =(String) Semester_Id.getValue();

        Student s =(Student) GUI.getInstance().getPerson();

        LocalDate datetoday = LocalDate.now();
        for (Semester semester : s.getSems_Data())
            if (semester.getTitle() != Semester_Id.getValue()) {
            } else {

                if (datetoday.isAfter(semester.getStartDate()) && datetoday.isBefore(semester.getEndDate())) {
                    Reg_Per.setText("Registeration Period is Active");
                    addTable();
                } else {
                    Reg_Per.setText("Registeration Period is'nt Active");
                }

            }
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


        ArrayList<Semester> s = GUI.getInstance().getAllSemesterData();

        for(Semester s1 : s)
        {
            if(s1.getTitle().equals(sem.getTitle()))
            {
                for(Course c : s1.getCourseArray())
                {
                    if(c.getCode().equals(crs.getCode()))
                    {
                        for(Sections se : c.getSecArray())
                        {
                            if(se.getTitle().equals(sec.getTitle()))
                            {
                                Queue<Student> Q = se.getWaitingList();

                                if(Q.size() > 0)
                                {
                                    Student St_Notify = Q.peek();
                                    Student_GUI Set_Message = new Student_GUI((Student) GUI.getInstance().getPerson());
                                    String message = "Seat is available in Section :"+se.getTitle()+" of Course :"+ c.getTitle()+". Go and Register fast :-)";
                                    Set_Message.Set_Message(message,St_Notify,s1,c,se);
                                    Q.remove();
                                }

                            }
                        }
                    }
                }
            }

        }

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
        Message_Label.setText(".");


        Current_Sem_id =(String) Semester_Id.getValue() ;
        C_s_id =(String) Semester_Id.getValue();

        ArrayList<Semester> s = GUI.getInstance().getAllSemesterData();

        for (Semester semester : s) {

            ArrayList<Course> c = semester.getCourseArray();
            for (Course c1 : c) {
                ObservableList<String> Section_Id_List =  FXCollections.observableArrayList();
                ArrayList<Sections> sec = c1.getSecArray();
                for(Sections se1: sec)
                {
                    Section_Id_List.add(se1.getTitle());
                }
                Button b = new Button();
                b.setText("Drop");
                b.setId(c1.getCode());
                b.setOnAction(this::DropAction);

                Button cb = new Button();
                cb.setText("Register");
                cb.setId(c1.getCode());
                cb.setOnAction(this::Add_Course_Action);

                Stu_TableCr m1 = new Stu_TableCr(c1.getCode(),c1.getTitle(),check_reg(c1.getTitle()),c1.getCredit_hours(),Section_Id_List,b,cb);
                Table_data.add(m1);
                Cr_view.setItems(Table_data);
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


    // Intializer

    @FXML
    public void initialize() {

        Student s = (Student) GUI.getInstance().getPerson();

        for (Semester S : s.getSems_Data())
        {
            Semester_Id_List.add(S.getTitle());
        }
        Semester_Id.setItems(Semester_Id_List);


        CourseCode.setCellValueFactory(new PropertyValueFactory<Stu_TableCr, String>("CourseCode"));
        CourseName.setCellValueFactory(new PropertyValueFactory<Stu_TableCr, String>("CourseName"));
        Status.setCellValueFactory(new PropertyValueFactory<Stu_TableCr, String>("Status"));
        CrdHrs.setCellValueFactory(new PropertyValueFactory<Stu_TableCr,Integer>("CrdHrs"));
        Section.setCellValueFactory(new PropertyValueFactory<Stu_TableCr, String>("Section"));
        Register.setCellValueFactory(new PropertyValueFactory<Stu_TableCr,Button>("Register"));
        Drop.setCellValueFactory(new PropertyValueFactory<Stu_TableCr,Button>("Drop"));


    }


}
