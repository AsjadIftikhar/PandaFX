package pandaFX;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import pandaFX.controller.Student_GUI;
import pandaFX.models.*;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;


public class Stu_ControllerMain {

    ObservableList<Stu_TableMessage> Table_data = FXCollections.observableArrayList();

    @FXML
    private TableView<Stu_TableMessage> Message_View;
    @FXML
    private TableColumn<Stu_TableMessage,String> Messages;


    @FXML
    private TextField Roll_No ;
    @FXML
    private TextField Degree ;
    @FXML
    private TextField Batch ;
    @FXML
    private TextField Section ;
    @FXML
    private TextField Campus ;
    @FXML
    private TextField Status ;
    @FXML
    private TextField Name ;
    @FXML
    private TextField DOB ;
    @FXML
    private TextField Gender ;
    @FXML
    private TextField Email ;
    @FXML
    private TextField Contact ;
    @FXML
    private TextField HomePhone ;
    @FXML
    private Button Cls_Msg;

    @FXML
    private Button logoutButton;

    @FXML
    public void onClickMenu(ActionEvent e) throws IOException {

        if (e.getSource() == logoutButton) {
            String sceneFile = "FXML_Login.fxml";
            Parent root2 = null;
            URL url = null;
            try {
                url = getClass().getResource(sceneFile);
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(url);

                root2 = loader.load();

                Scene attendanceScene = new Scene(root2);

                Stage window = (Stage) ((Node) e.getSource()).getScene().getWindow();
                window.setScene(attendanceScene);
                window.show();

            } catch (Exception ex) {
                System.out.println("Exception on FXMLLoader.load()" + ex);
                throw ex;
            }
        }


    }

    @FXML
    public void Cls_Msgs(ActionEvent event)
    {
        Student_GUI s = new Student_GUI((Student) GUI.getInstance().getPerson());
        s.Clear_Message();
        Message_View.getItems().clear();
    }


    @FXML
    public void initialize() {
        Student s = (Student) GUI.getInstance().getPerson();
        Roll_No.setText(s.getRoll_number());
        Degree.setText(s.getDiscipline());
        Batch.setText(String.valueOf(s.getBatch()));
        Section.setText("A");
        Campus.setText(s.getCampus());
        Status.setText("Current");
        Name.setText(s.getName());
        Gender.setText(s.getGender());
        Email.setText(s.getEmail());
        Contact.setText(s.getContact());
        HomePhone.setText(s.getContact());
        DOB.setText(s.getDoB());
        Messages.setCellValueFactory(new PropertyValueFactory<Stu_TableMessage, String>("Messages"));

        Message_View.getItems().clear();
        Student_GUI sg = new Student_GUI(s);
        ArrayList<String> M = sg.Get_Messages(s.getRoll_number());

        for(String Sm : M)
        {
            Stu_TableMessage m1 = new Stu_TableMessage(Sm);
            Table_data.add(m1);
            Message_View.setItems(Table_data);
        }


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
