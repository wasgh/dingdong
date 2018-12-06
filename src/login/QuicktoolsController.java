/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package login;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDecorator;
import helpers.Routes;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Border;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import schoolmusic.SchoolMusic;

/**
 * FXML Controller class
 *
 * @author ai-19
 */



public class QuicktoolsController implements Initializable{

    @FXML
    private JFXButton btnTuner;

    @FXML
    private JFXButton btnTodaysCalender;
    @FXML
    private JFXButton btnBack;
    @FXML
    public MediaPlayer player;

    @FXML
    void TodaysCalenderFn(ActionEvent event) {
        
    }

    @FXML
    void TunerFn(ActionEvent event) {

    }
    
    @FXML
    void BackToLoginPage(ActionEvent event) {
        try {
                
                 Stage stage = new Stage();
                Parent root = FXMLLoader.load(getClass().getResource(Routes.LOGINVIEW));
             
                
                JFXDecorator decorator=new JFXDecorator(stage, root, false, false, true);
                decorator.setCustomMaximize(false);
                decorator.setBorder(Border.EMPTY);
                Scene scene = new Scene(decorator);
                scene.getStylesheets().add(SchoolMusic.class.getResource("/styles/styles.css").toExternalForm());
                stage.initStyle(StageStyle.UNDECORATED);
                stage.setScene(scene);

                stage.setIconified(false);
                stage.show();
                //Hide login window
                btnBack.getScene().getWindow().hide();
            } catch (IOException ex) {
                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
            
    }
    }
          @FXML
    void PlayMusic(MouseEvent event) {
        
    String uriString;
    uriString = new File("D:\\WorkSpace\\NetBeansProjects\\MusicSchool\\src\\sounds\\longa_riad.mp3").toURI().toString();
    player = new MediaPlayer( new Media(uriString));
    player.play();
    
    }

    @FXML
    void StopMusic(MouseEvent event) {
        player.stop();
    }

   



    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
           // TODO
    }    
    

}