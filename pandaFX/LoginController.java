package pandaFX;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import pandaFX.models.GUI;

import java.io.File;
import java.io.IOException;
import java.net.URL;


public class LoginController {

    @FXML
    private TextField userName;

    @FXML
    private TextField password;

    @FXML
    private Button loginButton;

    @FXML
    private Label error;

    @FXML
    public void initialize() {
        loginButton.setDisable(true);
        error.setDisable(true);
    }

    @FXML
    public void onButtonClicked(ActionEvent event) throws IOException {

        String ID = userName.getText();
        String Pass = password.getText();

        if (GUI.getInstance().findInDatabase(ID, Pass)) {

            if (GUI.getInstance().getPerson().getClass().toString().equals("class pandaFX.models.Teacher")){
                String sceneFile = "FXML_TeacherHome.fxml";
                Parent root = null;
                URL url = null;
                try {
                    url = getClass().getResource(sceneFile);
                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(url);

                    root = loader.load();
                    Scene scene = new Scene(root);

                    Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    window.setScene(scene);
                    window.show();

                } catch (Exception ex) {
                    System.out.println("Exception on FXMLLoader.load()" + ex);
                    throw ex;
                }
            }
            else if (GUI.getInstance().getPerson().getClass().toString().equals("class pandaFX.models.AC_Officer")) {
                String sceneFile = "FXML_ACHome.fxml";
                Parent root = null;
                URL url = null;
                try {
                    url = getClass().getResource(sceneFile);
                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(url);

                    root = loader.load();
                    Scene scene = new Scene(root);

                    Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    window.setScene(scene);
                    window.show();

                } catch (Exception ex) {
                    System.out.println("Exception on FXMLLoader.load()" + ex);
                    throw ex;
                }

            }
            else if (GUI.getInstance().getPerson().getClass().toString().equals("class pandaFX.models.Student")){

                String sceneFile = "FXML_StuShowMain.fxml";
                Parent root = null;
                URL url = null;
                try {
                    url = getClass().getResource(sceneFile);
                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(url);

                    root = loader.load();
                    Scene scene = new Scene(root);

                    Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    window.setScene(scene);
                    window.show();

                } catch (Exception ex) {
                    System.out.println("Exception on FXMLLoader.load()" + ex);
                    throw ex;
                }

            }



        } else {
            String err = "Invalid Email or Password";
            error.setDisable(false);
            error.setText(err);
        }


    }

    @FXML
    public void handleKeyReleased() {
        String UN = userName.getText();
        String pass = password.getText();

        boolean disableButtons = false;
        if (UN.isEmpty() || pass.isEmpty() || UN.trim().isEmpty() || pass.trim().isEmpty()) {
            disableButtons = true;
        } else if (UN.length() < 6 || pass.length() < 4) {
            disableButtons = true;
        }

        loginButton.setDisable(disableButtons);
    }

}
