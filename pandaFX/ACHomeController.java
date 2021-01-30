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
import pandaFX.models.AC_Officer;
import pandaFX.models.GUI;
import pandaFX.models.Teacher;
import pandaFX.models.User;

import java.io.IOException;
import java.net.URL;

public class ACHomeController {

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

        setHome();

    }

    @FXML
    public void setHome() {
        User officer = new AC_Officer();
        officer = GUI.getInstance().getPerson();

        name.setText(officer.getName());
        dob.setText(officer.getDoB());
        mail.setText(officer.getEmail());
        gender.setText(String.valueOf(officer.getGender()));
        contact.setText(officer.getContact());
        campus.setText(officer.getCampus());
        dis.setText(officer.getDiscipline());
        salary.setText(String.valueOf(officer.getSalary()));


    }
}
