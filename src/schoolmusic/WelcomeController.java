/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package schoolmusic;

import ContactUsers.ContactusersController;
import Database.TableEvent;
import Database.TableMessage;
import Database.TableUsername;
import com.jfoenix.controls.JFXButton;
import helpers.Routes;
import java.io.File;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import models.User;

/**
 * FXML Controller class
 *
 * @author danml
 */
public class WelcomeController implements Initializable {

    static TableUsername tableUsernameSigenIn;
    @FXML
    private TitledPane instrumentsForSaleTitledPane;

    @FXML
    private TitledPane eventsFestivalsTitledPane;

    @FXML
    private TitledPane announcementsTitledPane;

    @FXML
    private Label labelAdministratorMessage;

    @FXML
    private Label labelAnnouncement;

    @FXML
    private JFXButton btnLogOut;
    @FXML
    private TextField txtLoggedUser;
    @FXML
    private MediaPlayer player;
    private String password;
    private String username;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        password=Routes.pw;
        username=Routes.un;

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("schoolMusicFxPU");
        EntityManager entityEvent = emf.createEntityManager();
        TypedQuery<TableEvent> query = entityEvent.createNamedQuery("TableEvent.findAll", TableEvent.class);
        List<TableEvent> listEvent = query.getResultList();

        for (TableEvent row : listEvent) {
            labelAnnouncement.setText(row.getText());
        }
        
        
        EntityManagerFactory emfManager = Persistence.createEntityManagerFactory("schoolMusicFxPU");
        EntityManager entityManager = emfManager.createEntityManager();
        Query queryManager = entityManager.createNativeQuery("SELECT TABLE_MESSAGE.MESSAGE FROM HR.TABLE_MESSAGE WHERE  Hr.TABLE_MESSAGE.TO_USER_ID='"+currentUsernameID(username,password)+"'");
        List<String> listManager = queryManager.getResultList();
 StringBuffer stringBuffer = new StringBuffer();
           
        for (String row : listManager) {
            stringBuffer.append(row+"\n");
        }
        labelAdministratorMessage.setText(stringBuffer.toString());
}

    @FXML
    private void logOut(ActionEvent event) {
    }

    @FXML
    void PlayNote(MouseEvent event) {
        JFXButton note;
        note = (JFXButton) event.getSource();

        String uriString;
        uriString = new File("C:\\Users\\virtualali\\Desktop\\NetBeansProjects\\MusicSchool\\src\\sounds\\piano\\" + note.getAccessibleText() + ".mp3").toURI().toString();

        player = new MediaPlayer(new Media(uriString));
        player.play();

    }

    @FXML
    void StopNote(MouseEvent event) {
        ;
    }

    public static void setUsername(TableUsername tableUsernameSigenIn) {
        WelcomeController.tableUsernameSigenIn = tableUsernameSigenIn;
    }

    public static TableUsername getTableUsername() {
        return tableUsernameSigenIn;
    }

    public void setUser(TableUsername tableUsernameSigenIn) {
        this.tableUsernameSigenIn = tableUsernameSigenIn;
    }

    public TableUsername getTableUser() {
        return tableUsernameSigenIn;
    }
public String currentUsernameID(String User,String pass){

 EntityManagerFactory emf = Persistence.createEntityManagerFactory("schoolMusicFxPU");
        EntityManager entityWelcome = emf.createEntityManager();
        TableUsername tableUsername = (TableUsername) entityWelcome.createNativeQuery("Select * FROM table_username Where table_username.username=" + "'" + this.username + "'" + " and table_username.password=" + "'" + this.password + "'", TableUsername.class).getSingleResult();


    return tableUsername.getUsernameId().toString();
}

}
