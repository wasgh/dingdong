/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dashboard;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;

/**
 * FXML Controller class
 *
 * @author virtualali
 */
public class DashboardController implements Initializable {
    
    
    private final ObservableList<PieChart.Data> Skillsdetails=FXCollections.observableArrayList();
    private final ObservableList<PieChart.Data> Ranksdetails=FXCollections.observableArrayList();
    private final ObservableList<PieChart.Data> Genderdetails=FXCollections.observableArrayList();
    
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
           
            Skillsdetails.addAll(new PieChart.Data("Violin",29),
                    new PieChart.Data("Cello",31),
                    new PieChart.Data("Piano",25),
                    new PieChart.Data("Oud",15));
            pcSkills.setData(Skillsdetails);
            
            
            int m=50;//No. Of Males
            int f=45;//No. Of Females
            Genderdetails.addAll(new PieChart.Data("Male",m),
                    new PieChart.Data("Female",f));
            pcGender.setData(Genderdetails);
            
            
            int administrator=3;
            int teacher=30;
            int student=60;
            Ranksdetails.addAll(new PieChart.Data("Administrators",administrator),
                    new PieChart.Data("Teachers",teacher),
                    new PieChart.Data("Students",student));
            pcRanks.setData(Ranksdetails);
        
        
        
        
        // TODO
    }    
    
    @FXML
    private PieChart pcSkills;

    @FXML
    private PieChart pcRanks;

    @FXML
    private PieChart pcGender;
}
