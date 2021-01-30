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
import pandaFX.models.*;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

public class Teacher_ControllerAttendance {

    @FXML
    private Label currDate;

    @FXML
    private Label Message;

    @FXML
    private Button markPresent;

    @FXML
    private Button markAbsent;

    @FXML
    private Button showButton;

    @FXML
    private ChoiceBox<String> selectSemester;

    @FXML
    private ChoiceBox<String> selectCourse;

    @FXML
    private ChoiceBox<String> selectSection;

    @FXML
    private AnchorPane ancPane;

    @FXML
    private ListView<String> stdList;

    @FXML
    private ListView<String> stdList1;

    //Menu Buttons
    @FXML
    private Button homeButton;
    @FXML
    private Button attButton;
    @FXML
    private Button marksButton;
    @FXML
    private Button gradeButton;
    @FXML
    private Button logoutButton;

    @FXML
    public void onClickMenu(ActionEvent e) throws IOException {

        if (e.getSource() == homeButton) {
            String sceneFile = "FXML_TeacherHome.fxml";
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
        } else if (e.getSource() == attButton) {

            String sceneFile = "FXML_TeacherAttendance.fxml";
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
        } else if (e.getSource() == gradeButton) {
            String sceneFile = "FXML_TeacherGrades.fxml";
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
        } else if (e.getSource() == marksButton) {
            String sceneFile = "FXML_TeacherMarks.fxml";
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
        } else if (e.getSource() == logoutButton) {
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
        Teacher t = (Teacher) GUI.getInstance().getPerson();
        if (!GUI.getInstance().getPerson().getTeacherSemester().isEmpty()) {
            for (Semester semesterObj : GUI.getInstance().getPerson().getTeacherSemester()) {
                selectSemester.getItems().add(semesterObj.getTitle());
                for (Course courseObj : semesterObj.getCourseArray()) {
                    selectCourse.getItems().add(courseObj.getCode());
                    for (Sections secObj : courseObj.getSecArray()) {
                        selectSection.getItems().add(secObj.getTitle());
                    }
                }
            }
        }
    }

    @FXML
    public void onSectionSelect() {


        ancPane.setOpacity(1.0);
        stdList.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        stdList1.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

        ArrayList<CourseRegistration> ACR ;
        ACR = GUI.getInstance().fetchAllRegistrations(selectSemester.getValue(),selectCourse.getValue(),selectSection.getValue());

        int i = 0;
        for (CourseRegistration cr : ACR) {

            stdList.getItems().add(cr.getStu().getRoll_number());
            stdList1.getItems().add(cr.getStu().getName());
            i++;
        }
        showButton.setDisable(true);

    }

    @FXML
    public void onAttendance(ActionEvent event) {

        boolean success = false;

        String date = java.time.LocalDate.now().toString();
        ObservableList<String> students;
        students = stdList.getSelectionModel().getSelectedItems();
        String status = "";

        if (event.getSource() == markPresent) {
            status = "P";
        } else if (event.getSource() == markAbsent) {
            status = "A";
        }

        for (String std:students) {
            success = GUI.getInstance().getPerson().addAttendance(std,status,date,selectSemester.getValue(),selectCourse.getValue(),selectSection.getValue());
        }


        if (success) {
            Message.setTextFill(Paint.valueOf("#8fbcbb"));
            Message.setText("Marked Successfully!");
        } else {
            Message.setTextFill(Paint.valueOf("#bf616a"));
            Message.setText("Attendance was already Marked!");
        }
    }
}
