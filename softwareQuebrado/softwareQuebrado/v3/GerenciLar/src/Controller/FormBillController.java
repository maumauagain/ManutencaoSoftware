package Controller;

import DAO.DbAcessBill;
import Model.Bill;
import View.GerenciLar;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.binding.BooleanBinding;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

public class FormBillController implements Initializable {
   
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
    private TableView<Bill> tbvBill;

    @FXML
    private TableColumn<Bill, String> colName;

    @FXML
    private TableColumn<Bill, Float> colValue;

    @FXML
    private TableColumn<Bill, String> colExpDate;
    
    @FXML
    private TableColumn<Bill, String> colId;
    
    @FXML
    private Button btnEdit;

    @FXML
    private Button btnRemove;

    @FXML
    private Button btnAdd;
    
    @FXML
    private Button btnAtualizar;
    
    private DbAcessBill service = new DbAcessBill();; 

    ObservableList<Bill> oblist = FXCollections.observableArrayList();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        configuraColumns();
        atualizaDadosTabela();
        configuraBindings();
    }    
    
     
    public void apagar(){
        Bill bill = tbvBill.getSelectionModel().getSelectedItem();
        //service.apagar(bill.getId());
        atualizaDadosTabela();
    }
    
     public void atualizaDadosTabela(){
        tbvBill.getItems().setAll(service.buscarTodas());
	tbvBill.getSelectionModel().select(null);
    }
     
    public void editar(){
        FXMLLoader Loader = new FXMLLoader();
        Loader.setLocation(getClass().getResource("/View/FormAddBill.fxml"));
        
        try {
            Loader.load();
        }
        catch (IOException ex){
            System.out.println("erro ao trocar ctrl" + ex);
        }
        FormAddBillController frmAdd = Loader.getController();
        Bill bill = tbvBill.getSelectionModel().getSelectedItem();
        frmAdd.setText(bill.getName(), bill.getValue(), bill.getExpDate(), bill.getId());
        
        Parent p = Loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(p));
        stage.showAndWait();
    } 
     
    public void configuraColumns(){
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colValue.setCellValueFactory(new PropertyValueFactory<>("value"));
        colExpDate.setCellValueFactory(new PropertyValueFactory<>("expDate"));
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));      
        colId.setVisible(false);
    }
    
    private void configuraBindings() {
        BooleanBinding algoSelecionado = tbvBill.getSelectionModel().selectedItemProperty().isNull();
        btnRemove.disableProperty().bind(algoSelecionado);
        btnEdit.disableProperty().bind(algoSelecionado);
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
    
    public void AddBill(){
        GerenciLar.addBill();
    }
    
    public void PayBill(){
        GerenciLar.payBill();
    }
    
}
