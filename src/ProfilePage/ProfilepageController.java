/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProfilePage;


import Database.TableUsername;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import helpers.Routes;
import java.io.File;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import schoolmusic.WelcomeController;

/**
 * FXML Controller class
 *
 * @author ai-19
 */
public class ProfilepageController implements Initializable {
    static TableUsername tableUsernameSigenIn;
    @FXML
    private Label labelName;

    @FXML
    private HBox labelAge1;

    @FXML
    private Label labelUsername;

    @FXML
    private Label labelRank;

    @FXML
    private Label labelSkill;

    @FXML
    private Label labelLevel;

    @FXML
    private Label labelAge;

    @FXML
    private JFXTextField tfEmail;

    @FXML
    private JFXTextField tfPhoneNumber;

    @FXML
    private JFXTextField tfAdditionalInfo;

    @FXML
    private JFXButton btnSaveChanges;

    @FXML
    private JFXButton btnViolin;
    @FXML
    public MediaPlayer player;
    public String Un,Pw;
    TableUsername tableUsername;
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        updateInfo();

    }    

     @FXML
    void PlayViolin (MouseEvent event) {
        
    String uriString;
    uriString = new File("C:\\Users\\virtualali\\Desktop\\NetBeansProjects\\MusicSchool\\src\\sounds\\springvivaldi.mp3").toURI().toString();
    player = new MediaPlayer( new Media(uriString));
    player.play();
    
    }

    @FXML
    void StopViolin(MouseEvent event) {
        player.stop();
    }

   
  public static void setUsername(TableUsername tableUsernameSigenIn) {
ProfilepageController.tableUsernameSigenIn=tableUsernameSigenIn;    }

    public static TableUsername getTableUsername() {
        return tableUsernameSigenIn;
    }
    
     public   void setUser(TableUsername tableUsernameSigenIn) {
         this.tableUsernameSigenIn=tableUsernameSigenIn; 
        
   }

        public   TableUsername getTableUser() {
        return tableUsernameSigenIn;
    }

    public void updateInfo()
           {
               Un=Routes.un;
    Pw=Routes.pw;
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("schoolMusicFxPU");
        EntityManager entityWelcome = emf.createEntityManager();
        System.out.println("" + this.Un + this.Pw);
        tableUsername = (TableUsername) entityWelcome.createNativeQuery("Select * FROM table_username Where table_username.username=" + "'" + this.Un + "'" + " and table_username.password=" + "'" + this.Pw + "'", TableUsername.class).getSingleResult();

    labelName.setText(tableUsername.getFirstName()+tableUsername.getLastName());      
    labelUsername.setText(tableUsername.getUsername());
    labelSkill.setText(tableUsername.getUsername());
    labelRank.setText(tableUsername.getUsername());
   // labelLevel.setText(tableUsernameSigenIn.getLevel().toByteArray());
    tfPhoneNumber.setText(tableUsername.getPhoneNumber());
    tfAdditionalInfo.setText(tableUsername.getAdditionalInfo());
    tfEmail.setText(tableUsername.getEmailAddress());
        
    
           }
 }
    

