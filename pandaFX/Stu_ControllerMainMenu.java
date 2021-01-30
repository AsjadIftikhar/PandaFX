package pandaFX;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class Stu_ControllerMainMenu {

    @FXML
    public void Go_CR(ActionEvent event) throws IOException {

        String sceneFile = "FXML_StuCourseRegView.fxml";
        Parent root = null;
        URL url  = null;
        try {
            url  = getClass().getResource( sceneFile );
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(url);
            root = loader.load();
            System.out.println( "  fxmlResource = " + sceneFile );

            Scene CR_ViewScene = new Scene(root);

            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(CR_ViewScene);
            window.show();

        }
        catch ( Exception ex )
        {
            System.out.println( "Exception on FXMLLoader.load()" );
            System.out.println( "  * url: " + url );
            System.out.println( "  * " + ex );
            System.out.println( "    ----------------------------------------\n" );
            throw ex;
        }



    }

    @FXML
    public void Go_Attendence(ActionEvent event) throws IOException {

        String sceneFile = "FXML_StuShowAttendence.fxml";
        Parent root = null;
        URL url  = null;
        try {
            url  = getClass().getResource( sceneFile );
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(url);
            root = loader.load();
            System.out.println( "  fxmlResource = " + sceneFile );

            Scene CR_ViewScene = new Scene(root);

            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(CR_ViewScene);
            window.show();

        }
        catch ( Exception ex )
        {
            System.out.println( "Exception on FXMLLoader.load()" );
            System.out.println( "  * url: " + url );
            System.out.println( "  * " + ex );
            System.out.println( "    ----------------------------------------\n" );
            throw ex;
        }



    }

    @FXML
    public void Go_Marks(ActionEvent event) throws IOException {

        String sceneFile = "FXML_StuShowMarks.fxml";
        Parent root = null;
        URL url  = null;
        try {
            url  = getClass().getResource( sceneFile );
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(url);
            root = loader.load();
            System.out.println( "  fxmlResource = " + sceneFile );

            Scene CR_ViewScene = new Scene(root);

            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(CR_ViewScene);
            window.show();

        }
        catch ( Exception ex )
        {
            System.out.println( "Exception on FXMLLoader.load()" );
            System.out.println( "  * url: " + url );
            System.out.println( "  * " + ex );
            System.out.println( "    ----------------------------------------\n" );
            throw ex;
        }



    }

    @FXML
    public void Go_Main(ActionEvent event) throws IOException {

        String sceneFile = "FXML_StuShowMain.fxml";
        Parent root = null;
        URL url  = null;
        try {
            url  = getClass().getResource( sceneFile );
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(url);
            root = loader.load();
            System.out.println( "  fxmlResource = " + sceneFile );

            Scene CR_ViewScene = new Scene(root);

            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(CR_ViewScene);
            window.show();

        }
        catch ( Exception ex )
        {
            System.out.println( "Exception on FXMLLoader.load()" );
            System.out.println( "  * url: " + url );
            System.out.println( "  * " + ex );
            System.out.println( "    ----------------------------------------\n" );
            throw ex;
        }



    }

    @FXML
    public void Go_Transcript(ActionEvent event) throws IOException {

        String sceneFile = "FXML_StuShowTranscript.fxml";
        Parent root = null;
        URL url  = null;
        try {
            url  = getClass().getResource( sceneFile );
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(url);
            root = loader.load();
            System.out.println( "  fxmlResource = " + sceneFile );

            Scene CR_ViewScene = new Scene(root);

            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(CR_ViewScene);
            window.show();

        }
        catch ( Exception ex )
        {
            System.out.println( "Exception on FXMLLoader.load()" );
            System.out.println( "  * url: " + url );
            System.out.println( "  * " + ex );
            System.out.println( "    ----------------------------------------\n" );
            throw ex;
        }



    }

    @FXML
    public void Go_WD(ActionEvent event) throws IOException {

        String sceneFile = "FXML_StuWithDrawCourse.fxml";
        Parent root = null;
        URL url  = null;
        try {
            url  = getClass().getResource( sceneFile );
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(url);
            root = loader.load();
            System.out.println( "  fxmlResource = " + sceneFile );

            Scene CR_ViewScene = new Scene(root);

            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(CR_ViewScene);
            window.show();

        }
        catch ( Exception ex )
        {
            System.out.println( "Exception on FXMLLoader.load()" );
            System.out.println( "  * url: " + url );
            System.out.println( "  * " + ex );
            System.out.println( "    ----------------------------------------\n" );
            throw ex;
        }



    }

}
