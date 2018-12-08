/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ContactUsers;

import Database.TableEvent;
import Database.TableMessage;
import Database.TableUsername;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import helpers.Routes;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

/**
 * FXML Controller class
 *
 * @author ai-19
 */
public class ContactusersController implements Initializable {

     TableUsername tableUsernameSigenIn;
    TableUsername usernameEdit;

    @FXML
    private TableColumn<TableUsername, String> firstNameColumn;

    @FXML
    private TableColumn<TableUsername, String> lastNameColumn;

    @FXML
    private TableView<TableUsername> contactTable;

    @FXML
    private TableColumn<TableUsername, String> nameCol;

    @FXML
    private TableColumn<TableUsername, String> usernameCol;

    @FXML
    private TableColumn<TableUsername, String> emailCol;

    @FXML
    private TableColumn<TableUsername, String> rankCol;

    @FXML
    private TableColumn<TableUsername, String> skillCol;

    @FXML
    private TableColumn<TableUsername, String> phoneNumberCol;

    @FXML
    private TableColumn<TableUsername, String> dataAddedCol;

    @FXML
    private JFXTextArea taMessage;

    @FXML
    private JFXButton btnSave;

    @FXML
    private JFXButton btnSaveAll;

    @FXML
    private JFXTextArea taAnnouncement;

    @FXML
    private JFXButton btnUpdateAnnouncement;
    ObservableList<TableUsername> dataUsernames;
    TableEvent tableEvent;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("schoolMusicFxPU");
        EntityManager entityEvent = emf.createEntityManager();
        EntityManager entityWelcome = emf.createEntityManager();
         tableUsernameSigenIn = (TableUsername) entityWelcome.createNativeQuery("Select * FROM table_username Where table_username.username=" + "'" + Routes.un + "'" + " and table_username.password=" + "'" + Routes.pw + "'", TableUsername.class).getSingleResult();

        /*@SequenceGenerator(name
        = "SequenceIdGeneratorEvent", sequenceName
        = "EVENT_ID_SEQUENCE", allocationSize = 1)*/

        entityEvent.getTransaction().begin();;
        tableEvent = entityEvent.find(TableEvent.class, new BigDecimal(1));
        taAnnouncement.setText(tableEvent.getText());
        entityEvent.getTransaction().commit();

        emf = Persistence.createEntityManagerFactory("schoolMusicFxPU");
        EntityManager entityManager = emf.createEntityManager();
        TypedQuery<TableUsername> query = entityManager.createNamedQuery("TableUsername.findAll", TableUsername.class);
        List<TableUsername> listUsernames = query.getResultList();

        dataUsernames = FXCollections.observableArrayList();
        for (TableUsername row : listUsernames) {
            dataUsernames.add(new TableUsername(row.getUsernameId(), row.getUsername(), row.getPassword(), row.getSex(), row.getAdditionalInfo(), row.getEmailAddress(), row.getSsn(), row.getDataadded(), row.getFirstName(), row.getLastName(), row.getBirthdate(), row.getPhoneNumber(), row.getRank(), row.getLevel(), row.getSkill()));
        }
        contactTable.setItems(dataUsernames);
        initColumns();

    }

    @FXML
    void btnSaveAllAction(ActionEvent event) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("schoolMusicFxPU");
        EntityManager entityMessage = emf.createEntityManager();
        TypedQuery<TableUsername> query = entityMessage.createNamedQuery("TableUsername.findAll", TableUsername.class);
        List<TableUsername> listUsernames = query.getResultList();
        EntityManager entityMessageemfFor = null;
        TableMessage tableMessage;
        for (TableUsername tableUsername : listUsernames) {
            EntityManagerFactory emfFor = Persistence.createEntityManagerFactory("schoolMusicFxPU");
            entityMessageemfFor = emfFor.createEntityManager();
            entityMessageemfFor.getTransaction().begin();

            tableMessage = new TableMessage();

            tableMessage.setMessage(taMessage.getText());
            tableMessage.setFromUserId(tableUsernameSigenIn);
            tableMessage.setToUserId(tableUsername);
            entityMessageemfFor.persist(tableMessage);
            entityMessageemfFor.getTransaction().commit();

            entityMessageemfFor.close();

        }

        this.taMessage.clear();
    }

    @FXML
    void btnUpdateAnnouncementAction(ActionEvent event) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("schoolMusicFxPU");
        EntityManager entityEvent = emf.createEntityManager();
        tableEvent.setText(taAnnouncement.getText());

        if (updateUsername(tableEvent, tableEvent.getIdEvent())) {
            new Alert(Alert.AlertType.CONFIRMATION, "updated", ButtonType.CLOSE).show();
        } else {
            new Alert(Alert.AlertType.CONFIRMATION, "updating  was failed", ButtonType.CLOSE).show();

        }

    }

    private void clearFields() {

        this.taAnnouncement.clear();

    }

    public void initColumns() {
        firstNameColumn.setCellValueFactory(new PropertyValueFactory<TableUsername, String>("firstName"));
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<TableUsername, String>("lastName"));
        usernameCol.setCellValueFactory(new PropertyValueFactory<TableUsername, String>("username"));
        emailCol.setCellValueFactory(new PropertyValueFactory<TableUsername, String>("emailAddress"));
        rankCol.setCellValueFactory(new PropertyValueFactory<TableUsername, String>("rank"));
        skillCol.setCellValueFactory(new PropertyValueFactory<TableUsername, String>("skill"));
        phoneNumberCol.setCellValueFactory(new PropertyValueFactory<TableUsername, String>("phoneNumber"));
        dataAddedCol.setCellValueFactory(new PropertyValueFactory<TableUsername, String>("dataadded"));

    }

    public boolean updateUsername(TableEvent tableEventNew, BigDecimal usernameID) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("schoolMusicFxPU");
        EntityManager entityEvent = emf.createEntityManager();
        try {
            entityEvent.getTransaction().begin();;
            TableEvent tableEventOld = entityEvent.find(TableEvent.class, usernameID);
            tableEventOld.setText(tableEventNew.getText());
            entityEvent.merge(tableEventNew);
            entityEvent.getTransaction().commit();
            return true;
        } catch (Exception e) {
            return false;

        }

    }

    @FXML
    void displaySelected(MouseEvent event) {
        int selectedIndex = contactTable.getSelectionModel().getSelectedIndex();

        if (selectedIndex >= 0) {
            if (usernameEdit != null) {
                usernameEdit = (TableUsername) contactTable.getItems().get(selectedIndex);

            }
        }
    }

    @FXML
    void btnSaveAction(ActionEvent event) {
        int selectedIndex = contactTable.getSelectionModel().getSelectedIndex();

        if (selectedIndex >= 0) {
            if (usernameEdit != null) {

                EntityManagerFactory emfFor = Persistence.createEntityManagerFactory("schoolMusicFxPU");
                EntityManager entityMessageemfFor = emfFor.createEntityManager();
                entityMessageemfFor.getTransaction().begin();

                TableMessage tableMessage = new TableMessage();

                tableMessage.setMessage(taMessage.getText());
                tableMessage.setFromUserId(tableUsernameSigenIn);
                tableMessage.setToUserId(usernameEdit);
                entityMessageemfFor.persist(tableMessage);
                entityMessageemfFor.getTransaction().commit();

                entityMessageemfFor.close();
                new Alert(Alert.AlertType.WARNING, "we sanededd the massage ").show();

            }
        } else {
            new Alert(Alert.AlertType.WARNING, "Paiese choose user to send massage!").show();
        }
        this.taMessage.clear();

    }

}
