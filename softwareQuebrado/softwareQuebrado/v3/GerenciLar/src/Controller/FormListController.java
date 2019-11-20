package Controller;

import View.GerenciLar;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;

public class FormListController implements Initializable {


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
    
    public void AddList(){
        GerenciLar.addList();
    }
    
}
