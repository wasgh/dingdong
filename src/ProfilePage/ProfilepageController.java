/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProfilePage;

import java.util.Date;
import Database.TableUsername;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import helpers.Routes;
import java.io.File;
import java.math.BigDecimal;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
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

    labelName.setText(tableUsername.getFirstName()+" "+tableUsername.getLastName());      
    labelUsername.setText(tableUsername.getUsername());
    labelSkill.setText(tableUsername.getSkill());
    labelRank.setText(tableUsername.getRank());
LocalDate birthday = tableUsername.getBirthdate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
// using period
Period period = Period.between(birthday, LocalDate.now());
    labelAge.setText(String.valueOf(period.getYears()));
    System.out.print(period.getYears());
    labelLevel.setText(tableUsername.getLevel().toString());
    tfPhoneNumber.setText(tableUsername.getPhoneNumber());
    tfAdditionalInfo.setText(tableUsername.getAdditionalInfo());
    tfEmail.setText(tableUsername.getEmailAddress());
    
    
           }
    
    
       @FXML
    void saveChanges(ActionEvent event) {
         EntityManagerFactory emf = Persistence.createEntityManagerFactory("schoolMusicFxPU");
        EntityManager entityWelcome = emf.createEntityManager();
        System.out.println("" + this.Un + this.Pw);
        tableUsername = (TableUsername) entityWelcome.createNativeQuery("Select * FROM table_username Where table_username.username=" + "'" + this.Un + "'" + " and table_username.password=" + "'" + this.Pw + "'", TableUsername.class).getSingleResult();
        if ( tfauValidateEamil() & tfauValidateMobileNo() ) {

        String additionalInfo = (tfAdditionalInfo.getText()!= null ? tfAdditionalInfo.getText() : "");
        String phoneNo = (tfPhoneNumber.getText() != null ? tfPhoneNumber.getText() : "");
        String Email = (tfEmail.getText() != null ? tfEmail.getText() : "");
       

        
        tableUsername.setAdditionalInfo(additionalInfo);
        tableUsername.setPhoneNumber(phoneNo);
        tableUsername.setEmailAddress(Email);

        if (updateUsername(tableUsername, tableUsername.getUsernameId())) {
            
            new Alert(Alert.AlertType.CONFIRMATION, "User has been updated", ButtonType.CLOSE).show();
            
            
        } else {
            new Alert(Alert.AlertType.CONFIRMATION, "updating User was failed", ButtonType.CLOSE).show();

        }}
        
        
    }
  private boolean tfauValidateMobileNo(){
        Pattern p = Pattern.compile("[0-9]+");
        Matcher m = p.matcher(tfPhoneNumber.getText());
        if(m.find() && m.group().equals(tfPhoneNumber.getText())){
            return true;
        }else{
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Validate Mobile Number");
                alert.setHeaderText(null);
                alert.setContentText("Please Enter Valid Mobile Number");
                alert.showAndWait();
            
            return false;            
        }
    }
      
    
    private boolean tfauValidateEamil() {

        Pattern p = Pattern.compile("[a-zA-Z0-9][a-zA-Z0-9._]*@[a-zA-Z0-9]+([.][a-zA-Z]+)");
        Matcher m = p.matcher(tfEmail.getText());
        if (m.find() && m.group().equals(tfEmail.getText())) {
            return true;
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Validate Eamil");
            alert.setHeaderText(null);
            alert.setContentText("Palease Enter Valid Email");
            alert.showAndWait();
            return false;
        }
    }

    
     public boolean updateUsername(TableUsername tableUsername, BigDecimal usernameID) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("schoolMusicFxPU");
        EntityManager entityUsername = emf.createEntityManager();
       
        try {
            entityUsername.getTransaction().begin();
            TableUsername un = entityUsername.find(TableUsername.class, usernameID);
            un.setAdditionalInfo(tableUsername.getAdditionalInfo());
            un.setEmailAddress(tableUsername.getEmailAddress());
            un.setPhoneNumber(tableUsername.getPhoneNumber());

            entityUsername.merge(tableUsername);
            entityUsername.getTransaction().commit();
           
            return true;
        } catch (Exception e) {
            return false;

        }

    }
    
    
    
    
    
    
    
    
 }
    

