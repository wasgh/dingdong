/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package schoolmusic;

import ContactUsers.ContactusersController;
import Database.TableEvent;
import Database.TableUsername;
import ProfilePage.ProfilepageController;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.controls.JFXPopup;
import com.jfoenix.controls.JFXToolbar;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
import helpers.Routes;
import java.awt.Color;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.FadeTransition;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.util.Duration;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import login.LoginController;
import models.User;

/**
 * FXML Controller class
 *
 * @author danml
 */
public class HomeViewController implements Initializable {

    public String stringUsername;
    public String stringPassword;

    public static void setStringPassword() {
    }
    // private final LoginController aThis;
    /*  public HomeViewController(LoginController aThis) {
       this. aThis=(LoginController)aThis;
    }*/

//          private StringProperty PasswordString = new SimpleStringProperty();
//    private StringProperty UsernameString = new SimpleStringProperty();
    @FXML
    private JFXHamburger hamburger;
    HamburgerBackArrowBasicTransition transition;
    @FXML
    private AnchorPane holderPane;
    @FXML
    private JFXDrawer drawer;
    @FXML
    private Label txtCurrentWindow;

    @FXML
    private JFXToolbar toolbar;
    EntityManagerFactory emf;
    EntityManager em;
    static private String StringDrawer;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    public void setStringUsername(String stringUsername) {
        stringUsername = stringUsername;

    }

    public String getStringUsername() {
        return stringUsername;
    }

    public void setStringPassword(String stringPassword) {
        stringPassword = stringPassword;
    }

    public String getStringPassword() {
        return stringPassword;
    }
    private String Drawer;
    private String fxmlDrawer;

    public void GetData(String stringUsername, String stringPassword) {
        Routes.pw = stringPassword;
        Routes.un = stringUsername;
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("schoolMusicFxPU");
        EntityManager entityWelcome = emf.createEntityManager();
        System.out.println("" + stringUsername + stringPassword);
        final TableUsername tableUsername = (TableUsername) entityWelcome.createNativeQuery("Select * FROM table_username Where table_username.username=" + "'" + stringUsername + "'" + " and table_username.password=" + "'" + stringPassword + "'", TableUsername.class).getSingleResult();

        Connection c = null;
        Statement stmt = null;
        try {
            c = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "hr", "hr");
            c.setAutoCommit(false);

            System.out.println("Opened database successfully");
            stmt = c.createStatement();
            String sQueryDrawer = "Select TABLE_USERNAME.RANK FROM table_username Where table_username.username=" + "'" + stringUsername + "'" + " and table_username.password=" + "'" + stringPassword + "'";

            ResultSet rs = stmt.executeQuery(sQueryDrawer);
            rs.next();
            String sDrawer = rs.getString(1);
            //  System.out.println("MyTable has " + sDrawer + " row(s).");
            rs.close();
            stmt.close();
            c.close();
            switch (sDrawer) {
                case "Teacher":
                    fxmlDrawer = "DrawerTeacher";

                    break;
                case "Student":
                    fxmlDrawer = "DrawerStudent";

                    break;
                case "Administrator":
                    fxmlDrawer = "DrawerAdministrator";

                    break;

            }

        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());

        }
        try {

            System.out.println("MyTable has " + fxmlDrawer + " row(s).");

            VBox sidePane = FXMLLoader.load(getClass().getResource("/schoolmusic/" + fxmlDrawer + ".fxml"));

            HamburgerBackArrowBasicTransition transition = new HamburgerBackArrowBasicTransition(hamburger);
            transition.setRate(-1);
            hamburger.addEventHandler(MouseEvent.MOUSE_PRESSED, (MouseEvent me) -> {
                transition.setRate(transition.getRate() * -1);
                transition.play();

                if (drawer.isOpened()) {
                    drawer.close();
                } else {
                    drawer.open();
                }

            });
            try {

                
               
                
                AnchorPane contactus = FXMLLoader.load(getClass().getResource(Routes.CONTACTUS));  
                AnchorPane welcome = FXMLLoader.load(getClass().getResource(Routes.WELCOMEVIEW));
                
                setNode(welcome);
                drawer.setSidePane(sidePane);

                for (Node node : sidePane.getChildren()) {
                    if (node.getAccessibleText() != null) {
                        node.addEventHandler(MouseEvent.MOUSE_PRESSED, (MouseEvent ev) -> {
                            switch (node.getAccessibleText()) {
                                case "homeMenu":
                                    AnchorPane WelcomeView;
                                    try {
                                        WelcomeView = FXMLLoader.load(getClass().getResource(Routes.WELCOMEVIEW));
                                        drawer.close();
                                        setNode(WelcomeView);

                                        this.txtCurrentWindow.setText("Welcome View");
                                        transition.setRate(transition.getRate() * -1);
                                        transition.play();
                                    } catch (IOException ex) {
                                        Logger.getLogger(HomeViewController.class.getName()).log(Level.SEVERE, null, ex);
                                    }

                                    break;

                                case "profileMenu":
                                    AnchorPane Profileview;
                                    try {
                                        Profileview = FXMLLoader.load(getClass().getResource(Routes.PROFILEVIEW));
                                        drawer.close();
                                        setNode(Profileview);

                                        this.txtCurrentWindow.setText("Profile Page");
                                        transition.setRate(transition.getRate() * -1);
                                        transition.play();
                                    } catch (IOException ex) {
                                        Logger.getLogger(HomeViewController.class.getName()).log(Level.SEVERE, null, ex);
                                    }

                                    break;
                                    
                                    case "dashboardMEnu":
                                    AnchorPane Dashboard;
                                    try {
                                        Dashboard = FXMLLoader.load(getClass().getResource(Routes.DASHBOARD));
                                        drawer.close();
                                        setNode(Dashboard);

                                        this.txtCurrentWindow.setText("Dashboard");
                                        transition.setRate(transition.getRate() * -1);
                                        transition.play();
                                    } catch (IOException ex) {
                                        Logger.getLogger(HomeViewController.class.getName()).log(Level.SEVERE, null, ex);
                                    }

                                    break;

                                case "contactusMenu":
                                    drawer.close();
                                    setNode(contactus);
                                    transition.setRate(transition.getRate() * -1);
                                    transition.play();
                                    break;

                                case "contactusersMenu":
                                   AnchorPane ContactUsers;
                                    try {
                                        ContactUsers = FXMLLoader.load(getClass().getResource(Routes.CONTACTUSERS));
                                        drawer.close();
                                        setNode(ContactUsers);

                                        this.txtCurrentWindow.setText("Contact Users");
                                        transition.setRate(transition.getRate() * -1);
                                        transition.play();
                                    } catch (IOException ex) {
                                        Logger.getLogger(HomeViewController.class.getName()).log(Level.SEVERE, null, ex);
                                    }

                                    break;

                                case "manageusersMenu":
                                    AnchorPane ManageUsers;
                                    try {
                                        ManageUsers = FXMLLoader.load(getClass().getResource(Routes.MANAGEUSERS));
                                        drawer.close();
                                        setNode(ManageUsers);

                                        this.txtCurrentWindow.setText("Manage Users");
                                        transition.setRate(transition.getRate() * -1);
                                        transition.play();
                                    } catch (IOException ex) {
                                        Logger.getLogger(HomeViewController.class.getName()).log(Level.SEVERE, null, ex);
                                    }

                                    break;
                                case "assignstudentMenu":
                                    AnchorPane AssignStudents;
                                    try {
                                        AssignStudents = FXMLLoader.load(getClass().getResource(Routes.ASSIGNSTUDENTS));
                                        drawer.close();
                                        setNode(AssignStudents);

                                        this.txtCurrentWindow.setText("Assign Students");
                                        transition.setRate(transition.getRate() * -1);
                                        transition.play();
                                    } catch (IOException ex) {
                                        Logger.getLogger(HomeViewController.class.getName()).log(Level.SEVERE, null, ex);
                                    }

                                    break;

                            }
                        });
                    }

                }

            } catch (IOException ex) {
                Logger.getLogger(HomeViewController.class.getName()).log(Level.SEVERE, null, ex);
            }

        } catch (IOException ex) {
            Logger.getLogger(HomeViewController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        //       TableUsername tableUsername = entityEvent.find(TableUsername.class, usernameID);
    }

    private void setNode(Node node) {
        holderPane.getChildren().clear();
        holderPane.getChildren().add((Node) node);
    }

}
