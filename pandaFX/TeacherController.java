package pandaFX;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import pandaFX.models.GUI;
import pandaFX.models.Teacher;
import pandaFX.models.User;

import java.io.IOException;
import java.net.URL;

public class TeacherController {

    @FXML
    private Label name;

    @FXML
    private Label dob;

    @FXML
    private Label mail;

    @FXML
    private Label gender;

    @FXML
    private Label contact;

    @FXML
    private Label campus;

    @FXML
    private Label dis;

    @FXML
    private Label salary;

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

        setHome();

    }

    @FXML
    public void setHome() {
        User teacher = new Teacher();
        teacher = GUI.getInstance().getPerson();

        name.setText(teacher.getName());
        dob.setText(teacher.getDoB());
        mail.setText(teacher.getEmail());
        gender.setText(String.valueOf(teacher.getGender()));
        contact.setText(teacher.getContact());
        campus.setText(teacher.getCampus());
        dis.setText(teacher.getDiscipline());
        salary.setText(String.valueOf(teacher.getSalary()));


    }
}
