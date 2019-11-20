
package Controller;

import View.GerenciLar;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;


public class FormMainController implements Initializable {

    @FXML
    private Button btnUser;

    @FXML
    private Button btnBill;

    @FXML
    private Button btnActivity;

    @FXML
    private Button btnList;
    
    @FXML
    private Label lblSair;

    @FXML
    private Label lblLogoff;
    
    @FXML
    private Label txtUser;
   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    } 
    
    public void Exit(){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Sair do Sistema");
        alert.setHeaderText("Você deseja realmente sair?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK)
            GerenciLar.exit();
    }
    
    public void Logoff(){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Logoff do Sistema");
        alert.setHeaderText("Você deseja realmente sair?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK)
            GerenciLar.logoff();
    }
    
    public void UserPage(){
        GerenciLar.user();
    }
    
    public void BillPage(){
        GerenciLar.bill();
    }
    
    public void ActivityPage(){
        GerenciLar.activity();
    }
    
    public void ListPage(){
        GerenciLar.list();
    }
}
