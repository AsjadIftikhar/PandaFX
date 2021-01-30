package pandaFX;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import pandaFX.models.*;

import java.io.IOException;
import java.util.*;

public class Stu_ControllerTranscript {



    //For Calculating SGPA
    public final List<Double> credits = new ArrayList<>();
    public final List<Double> points = new ArrayList<>();
    private final Map<String, Double> gradeToScore = new HashMap<>();

    //Methods For Calculating SGPA
    public void GradeCalculator() {
        gradeToScore.put("A+", 4.33);
        gradeToScore.put("A", 4.00);
        gradeToScore.put("A-", 3.67);
        gradeToScore.put("B+", 3.33);
        gradeToScore.put("B", 3.00);
        gradeToScore.put("B-", 2.67);
        gradeToScore.put("C+", 2.33);
        gradeToScore.put("C", 2.00);
        gradeToScore.put("C-", 1.67);
        gradeToScore.put("D+", 1.33);
        gradeToScore.put("D", 1.00);
        gradeToScore.put("F", 0.0);
        gradeToScore.put("FX", 0.0);
    }
    private double getTotal(List<Double> doubles) {
        double total = 0;
        for(Double d:doubles) {
            total += d;
        }
        return total;
    }
    private double calculateGpa() {
        double totalCredits = getTotal(credits);
        double totPts = getTotal(points);
        double gpa = totPts / totalCredits;

        credits.clear();
        points.clear();

        return gpa;
    }
    private void loadCreditsAndPoints(double crdhrs,String G) {

        credits.add( crdhrs );
        double gradeValue = 0;
        gradeValue = gradeToScore.get(G);
        double classTotPts = gradeValue * crdhrs;
        points.add(classTotPts);
    }



    ObservableList<String> Semester_Id_List =  FXCollections.observableArrayList();
    ObservableList<Stu_Trans_Table> Table_data = FXCollections.observableArrayList();


    private String Current_Sem_id ;
    @FXML
    private ComboBox Semester_Id;
    @FXML
    private TableView<Stu_Trans_Table> Trans_view;
    @FXML
    private TableColumn<Stu_Trans_Table,String> CourseCode;
    @FXML
    private TableColumn<Stu_Trans_Table,String> CourseName;
    @FXML
    private TableColumn<Stu_Trans_Table,String> Section;
    @FXML
    private TableColumn<Stu_Trans_Table,String> Grade;
    @FXML
    private TableColumn<Stu_Trans_Table,Float> Points;
    @FXML
    private TableColumn<Stu_Trans_Table,Integer> CrdHrs;
    @FXML
    private Label CGPA_Set;
    @FXML
    private Label SGPA;

    @FXML
    private void comboAction(ActionEvent event) {
        Current_Sem_id =(String) Semester_Id.getValue() ;
        addTable();
    }


    private void addTable()
    {
        Trans_view.getItems().clear();
        SGPA.setText(".");

        Current_Sem_id =(String) Semester_Id.getValue() ;

        Student s = (Student) GUI.getInstance().getPerson();

        for (Semester semester : s.getSems_Data()) {

            if (semester.getTitle() == Semester_Id.getValue()) {
                ArrayList<Course> c = semester.getCourseArray();

                for (Course c1 : c) {

                    String grade  = c1.getSecArray().get(0).getCR().get(0).getGrade();
                    String Sec = c1.getSecArray().get(0).getCR().get(0).getSecCR().getTitle();
                    if(grade != "l")
                    {
                        double pt = gradeToScore.get(grade);
                        float pts = (float) pt;
                        Stu_Trans_Table m1 = new Stu_Trans_Table(c1.getCode(),c1.getTitle(),Sec,c1.getCredit_hours(),grade,pts);
                        Table_data.add(m1);
                        loadCreditsAndPoints(c1.getCredit_hours(),grade);
                    }
                    else
                    {
                        Stu_Trans_Table m1 = new Stu_Trans_Table(c1.getCode(),c1.getTitle(),Sec,c1.getCredit_hours(),grade,(float)0.00);
                        Table_data.add(m1);
                    }


                    Trans_view.setItems(Table_data);

                }
                SGPA.setText(Double.toString(calculateGpa()));
            }
        }
    }


    @FXML
    public void initialize() {

        GradeCalculator();
        Student s = (Student) GUI.getInstance().getPerson();
        CGPA_Set.setText(Float.toString(s.getCgpa()));
        for (Semester S : s.getSems_Data())
        {
            Semester_Id_List.add(S.getTitle());
        }
        Semester_Id.setItems(Semester_Id_List);


        CourseCode.setCellValueFactory(new PropertyValueFactory<Stu_Trans_Table, String>("CourseCode"));
        CourseName.setCellValueFactory(new PropertyValueFactory<Stu_Trans_Table, String>("CourseName"));
        Grade.setCellValueFactory(new PropertyValueFactory<Stu_Trans_Table, String>("Grade"));
        Section.setCellValueFactory(new PropertyValueFactory<Stu_Trans_Table, String>("Section"));
        Points.setCellValueFactory(new PropertyValueFactory<Stu_Trans_Table, Float>("Points"));
        CrdHrs.setCellValueFactory(new PropertyValueFactory<Stu_Trans_Table,Integer>("CrdHrs"));

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
