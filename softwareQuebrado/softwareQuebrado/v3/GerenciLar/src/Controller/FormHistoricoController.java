package Controller;

import DAO.DbAcessPayBill;
import Model.PayBill;
import View.GerenciLar;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;

public class FormHistoricoController implements Initializable {
    
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
    private ImageView img_user;

    @FXML
    private TableView<PayBill> tbvUser;

    @FXML
    private TableColumn<PayBill, String> colUser;

    @FXML
    private TableColumn<PayBill, String> colConta;

    @FXML
    private TableColumn<PayBill, String> colStatus;

    @FXML
    private Button btnAtualizar;
    
    private DbAcessPayBill service = new DbAcessPayBill();; 
    
    ObservableList<PayBill> oblist = FXCollections.observableArrayList();

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        configuraColumns();
        atualizaDadosTabela();
    }    
    
    public void configuraColumns() {
            colUser.setCellValueFactory(new PropertyValueFactory<>("nameUser"));
            colConta.setCellValueFactory(new PropertyValueFactory<>("nameBill"));
            colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
    }

    public void atualizaDadosTabela() {
	tbvUser.getItems().setAll(service.buscarTodas());
	tbvUser.getSelectionModel().select(null);
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
    
    public void AddUser(){
        GerenciLar.addUser();
    }
    
}
