package Controller;

import View.GerenciLar;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;


public class FormAddListController implements Initializable {
    
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
    private TextField txtName;

    @FXML
    private TextField txtDescricao;

    @FXML
    private Button btnConcluir;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    public void Exit(){
        GerenciLar.exit();
    }
    
    public void Logoff(){
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
