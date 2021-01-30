package pandaFX;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import pandaFX.controller.Attendence_DataBase;
import pandaFX.models.*;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;

public class ACDatesController {

    @FXML
    private Label currDate;

    @FXML
    private Label Message;

    @FXML
    private ChoiceBox<String> selectSemester;

    @FXML
    private DatePicker Open;

    @FXML
    private DatePicker Close;

    @FXML
    private DatePicker withdraw;


    //Menu Buttons
    @FXML
    private Button homeButton;

    @FXML
    private Button dateButton;


    @FXML
    private Button logoutButton;

    @FXML
    public void onClickMenu(ActionEvent e) throws IOException {

        if (e.getSource() == homeButton) {
            String sceneFile = "FXML_ACHome.fxml";
            Parent root = null;
            URL url = null;
            try {
                url = getClass().getResource(sceneFile);
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(url);

                root = loader.load();
                Scene teacherScene = new Scene(root);

                Stage window = (Stage) ((Node) e.getSource()).getScene().getWindow();
                window.setScene(teacherScene);
                window.show();

            } catch (Exception ex) {
                System.out.println("Exception on FXMLLoader.load()" + ex);
                throw ex;
            }
        }
        else if (e.getSource() == dateButton) {

            String sceneFile = "FXML_ACDates.fxml";
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
        else if (e.getSource() == logoutButton) {
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
    public void initialize() {

        currDate.setText("   " + java.time.LocalDate.now().toString());
        for (Semester s : GUI.getInstance().getAllSemesterData()) {
            selectSemester.getItems().add(s.getTitle());
        }
    }

    @FXML
    public void onSet(ActionEvent event) {


        if (Open.getValue() == null || Close.getValue() == null || withdraw.getValue() == null) {
            Message.setText("Please Select all Dates first!");
        } else {
            LocalDate openDate = Open.getValue();
            LocalDate closeDate = Close.getValue();
            LocalDate withDrawDate = withdraw.getValue();

            boolean success = GUI.getInstance().getPerson().setDates(selectSemester.getValue(), openDate, closeDate, withDrawDate);
            if (success){
                Message.setTextFill(Paint.valueOf("#8fbcbb"));
                Message.setText("Dates were set successfully!");
            }
            else {
                Message.setTextFill(Paint.valueOf("#bf616a"));
                Message.setText("Something Went Wrong in Database!");
            }
        }
    }
}
