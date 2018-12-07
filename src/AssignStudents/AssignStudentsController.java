/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AssignStudents;

import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author virtualali
 */
public class AssignStudentsController implements Initializable {

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    @FXML
    private TableView<?> vatvStudentTeacher;

    @FXML
    private TableColumn<?, ?> vatcStudent;

    @FXML
    private TableColumn<?, ?> vatcStudentFirstName;

    @FXML
    private TableColumn<?, ?> vatcStudentLastName;

    @FXML
    private TableColumn<?, ?> vatcSkill;

    @FXML
    private TableColumn<?, ?> vatcTeacher;

    @FXML
    private TableColumn<?, ?> vatcTeacherFirstName;

    @FXML
    private PieChart vapcSkills;

    @FXML
    private PieChart vapcGender;

    @FXML
    private PieChart vapcRanks;

    @FXML
    private TableView<?> aatvStudent;

    @FXML
    private TableColumn<?, ?> aatcStudent;

    @FXML
    private TableColumn<?, ?> aatcStudentFirstName;

    @FXML
    private TableColumn<?, ?> aatcStudentLastName;

    @FXML
    private TableColumn<?, ?> aatcStudentSkill;

    @FXML
    private TableColumn<?, ?> aatcStudentRank;

    @FXML
    private TableView<?> aatvTeacher;

    @FXML
    private TableColumn<?, ?> aatcTeacher;

    @FXML
    private TableColumn<?, ?> aatcTeacherFirstName;

    @FXML
    private TableColumn<?, ?> aatcTeacherLastName;

    @FXML
    private TableColumn<?, ?> aatcTeacherSkill;

    @FXML
    private JFXButton btnAssign;

    @FXML
    void displaySelected(MouseEvent event) {

    }
    
}
