package Controller;

import DAO.DbAcessBill;
import DAO.DbAcessPayBill;
import DAO.DbAcessUser;
import Model.Bill;
import Model.User;
import Model.PayBill;
import View.GerenciLar;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;

public class FormPayBillController implements Initializable {
    
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
    private Button btnAtualizar;
    @FXML
    private Button btnConcluir;    
    @FXML
    private ComboBox<Bill> cbConta;
    
    @FXML
    private ComboBox<User> cbMembro;
    
    @FXML
    private CheckBox cbPaga;
    
    ObservableList<User> oblist = FXCollections.observableArrayList();
    ObservableList<Bill> oblist2 = FXCollections.observableArrayList();
    
    private DbAcessBill service = new DbAcessBill();
    
    private DbAcessUser service2 = new DbAcessUser();
    
    private DbAcessPayBill service3 = new DbAcessPayBill();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       fillComboUser();
       fillComboBill();
    } 
    
    public void salvar (){
        PayBill pb = new PayBill();
        
        try {
            getData(pb);
            service3.salvar(pb);
            successMsg();            
        }
        catch(Exception ex){
            failMsg();
        }
    }
    
    public void getData(PayBill payBill){
        User usuario = cbMembro.getSelectionModel().getSelectedItem();
        payBill.setIdUser(usuario.getId());
        Bill conta = cbConta.getSelectionModel().getSelectedItem();
        payBill.setIdBill(conta.getId());
        payBill.setStatus(cbPaga.isSelected() ? 1 : 0);
        
    }
    
    public void AtualizaUserAndBill(){
        fillComboUser();
        fillComboBill();
    }
       
    private void fillComboUser(){
        List<User> membros = new ArrayList<User>(); 
        membros = service2.buscarTodas();
        oblist = FXCollections.observableArrayList(membros);
        cbMembro.setItems(oblist);
    }
    
     private void fillComboBill(){
        List<Bill> contas = new ArrayList<Bill>(); 
        contas = service.buscarTodas();
        oblist2 = FXCollections.observableArrayList(contas);
        cbConta.setItems(oblist2);
    }
     
    private void successMsg(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Cadastro de Pagamento de Conta");
 
        // Header Text: null
        alert.setHeaderText(null);
        alert.setContentText("Conta paga cadastrada com sucesso!");
 
        alert.showAndWait();
    }
     
    private void failMsg(){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error Message");
        alert.setHeaderText(null);
        alert.setContentText("Não foi possível cadastrar Pagamento de Conta. Verifique os campos digitados");

        alert.showAndWait();
    }  
    
    
    // Métodos públicos trocar de página.
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