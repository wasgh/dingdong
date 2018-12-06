/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package contactus;

import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

/**
 * FXML Controller class
 *
 * @author ai-19
 */
public class ContactUsController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private JFXButton btnBack;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    @FXML
    void CloseWindow(ActionEvent event) {
        btnBack.getScene().getWindow().hide();
}
    
}
