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
import pandaFX.controller.CourseRegisteration_DataBase;
import pandaFX.controller.Evaluation_DataBase;
import pandaFX.models.*;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

public class Teacher_GradeController {

    @FXML
    private Label currDate;

    @FXML
    private Label Message;

    @FXML
    private Button markSheetButton;

    @FXML
    private Button grade;

    @FXML
    private Button showButton;

    @FXML
    private ChoiceBox<String> selectCourse;

    @FXML
    private ChoiceBox<String> gradeList;

    @FXML
    private ChoiceBox<String> selectSemester;

    @FXML
    private ChoiceBox<String> selectSection;

    @FXML
    private AnchorPane ancPane;

    ArrayList<CourseRegistration> ACR;

    @FXML
    private ListView<String> stdList;

    @FXML
    private ListView<String> markSheet;

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

        ancPane.setOpacity(1.0);
        stdList.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

        ACR = GUI.getInstance().fetchAllRegistrations(selectSemester.getValue(), selectCourse.getValue(), selectSection.getValue());

        for (CourseRegistration cr : ACR) {
            stdList.getItems().add(cr.getStu().getRoll_number());
        }
        showButton.setDisable(true);

    }

    @FXML
    public void onEvaluation(ActionEvent event) {

        boolean success = false;

        ObservableList<String> students;
        students = stdList.getSelectionModel().getSelectedItems();

        String semester = selectSemester.getValue();
        String crs = selectCourse.getValue();
        String sec = selectSection.getValue();

        if (event.getSource() == markSheetButton) {

            markSheet.getItems().clear();
            int total_weight = 0;
            int obtained_weight = 0;

            for (CourseRegistration cr : ACR) {
                for (String std : students) {
                    if (cr.getStu().getRoll_number().equals(std)) {
                        for (Evaluations ev : cr.getCourseEval()) {
                            markSheet.getItems().add(ev.getTitle() + ":");
                            markSheet.getItems().add("  Weightage: " + ev.getWeight());
                            markSheet.getItems().add("  Marks: " + ev.getObtained_marks() + "/" + ev.getTotal_marks());

                            total_weight += ev.getWeight();
                            obtained_weight += ((ev.getObtained_marks() / ev.getTotal_marks()) * ev.getWeight());
                        }
                    }
                }
            }
            markSheet.getItems().add("------------------------------------------------");
            markSheet.getItems().add("Total: " + obtained_weight + "/" + total_weight);

        } else if (event.getSource() == grade) {
            String g = gradeList.getValue();

            for (String std : students) {
                success = GUI.getInstance().getPerson().updateGrade(semester, crs, sec, std, g);
//                CourseRegisteration_DataBase crdb = new CourseRegisteration_DataBase();
//                success = crdb.addGrade(std,semester,crs,sec,g);
            }
            if (success) {
                Message.setTextFill(Paint.valueOf("#8fbcbb"));
                Message.setText("Grade Added!");
            } else {
                Message.setTextFill(Paint.valueOf("#bf616a"));
                Message.setText("Something went wrong in Database!");
            }
        }
    }
}

