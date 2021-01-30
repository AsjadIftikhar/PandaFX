package pandaFX;

import javafx.beans.Observable;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.ChoiceBoxListCell;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import pandaFX.controller.Attendence_DataBase;
import pandaFX.controller.CourseRegisteration_DataBase;
import pandaFX.controller.Evaluation_DataBase;
import pandaFX.controller.Teacher_DataBase;
import pandaFX.models.*;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Teacher_MarksController {

    private final HashMap<String, String> uniqueEvaluations = new HashMap<>();

    @FXML
    private Label currDate;

    @FXML
    private Label Message;

    @FXML
    private TextField evl;

    @FXML
    private TextField weightage;

    @FXML
    private TextField totalMarks;

    @FXML
    private TextField obtainedMarks;

    @FXML
    private Button evaluation;

    @FXML
    private Button addMarks;

    @FXML
    private ChoiceBox<String> evList;

    @FXML
    private Button showButton;

    @FXML
    private ChoiceBox<String> selectCourse;

    @FXML
    private ChoiceBox<String> selectSemester;

    @FXML
    private ChoiceBox<String> selectSection;

    @FXML
    private AnchorPane ancPane;

    @FXML
    private ListView<String> stdList;

    ArrayList<CourseRegistration> ACR;

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

        stdList.getItems().clear();
        evList.getItems().clear();

        ancPane.setOpacity(1.0);
        stdList.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        ACR = GUI.getInstance().fetchAllRegistrations(selectSemester.getValue(), selectCourse.getValue(), selectSection.getValue());

        for (CourseRegistration cr : ACR) {
            stdList.getItems().add(cr.getStu().getRoll_number());
        }

        updateOptions();

    }

    @FXML
    public void onEvaluation(ActionEvent event) {

        boolean success = false;

        ObservableList<String> students;
        students = stdList.getSelectionModel().getSelectedItems();

        String semester = selectSemester.getValue();
        String crs = selectCourse.getValue();
        String sec = selectSection.getValue();

        if (event.getSource() == evaluation) {

            String title = evl.getText();
            Float weight = Float.parseFloat(weightage.getText());
            Integer tmarks = Integer.parseInt(totalMarks.getText());

            if (title != null) {
                for (String std : stdList.getItems()) {
                    success = GUI.getInstance().getPerson().createEvaluation(title, tmarks, weight, std, semester, crs, sec);
                }
            }

            weightage.clear();
            totalMarks.clear();
            evl.clear();

            if (success) {
                Message.setTextFill(Paint.valueOf("#8fbcbb"));
                Message.setText("Evaluation was Created Successfully!");
            } else {
                Message.setTextFill(Paint.valueOf("#bf616a"));
                Message.setText("Something went wrong in Database!");
            }

        } else if (event.getSource() == addMarks) {

            String title = evList.getValue();
            Integer obmarks = Integer.parseInt(obtainedMarks.getText());

            for (String std : students) {
                success = GUI.getInstance().getPerson().updateEvaluation(title, obmarks, std, semester, crs, sec);
            }

            if (success) {
                Message.setTextFill(Paint.valueOf("#8fbcbb"));
                Message.setText("Marks Added!");
            } else {
                Message.setTextFill(Paint.valueOf("#bf616a"));
                Message.setText("Something went wrong in Database!");
            }

        }
    }

    public void updateOptions() {

        for (CourseRegistration cr : ACR) {
            for (Evaluations ev : cr.getCourseEval()) {
                uniqueEvaluations.put(ev.getTitle(), ev.getTitle());
            }
        }
        for (String key : uniqueEvaluations.keySet()) {
            evList.getItems().add(key);
        }
    }
}
