package Controller;

import DAO.DbAcessUser;
import Model.User;
import View.GerenciLar;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.binding.BooleanBinding;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class FormUserController implements Initializable {
    
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
    private Button btnAdd;
    
     @FXML
    private Button btnRemove;

    @FXML
    private Button btnEdit;
    
    @FXML
    private Button btnAtualizar;

    @FXML
    private TableView<User> tbvUser;
    
    @FXML
    private TableColumn<User, String> colName;

    @FXML
    private TableColumn<User, String> colEmail;

    @FXML
    private TableColumn<User, String> colTell;
    
    @FXML
    private TableColumn<User, String> colRg;
    
    @FXML
    private TableColumn<User, String> colPassword;
    
    @FXML
    private TableColumn<User, String> colId;
    
    @FXML
    private TableColumn<User, String> colTypeId;
    
    @FXML
    private Button btnHistorico;
    
    private DbAcessUser service = new DbAcessUser();
       
    ObservableList<User> oblist = FXCollections.observableArrayList();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
            configuraColumns();
            atualizaDadosTabela();
            configuraBindings();                   
    }
    
    public void apagar(){
        User user = tbvUser.getSelectionModel().getSelectedItem();
        //service.apagar(user.getId());
        atualizaDadosTabela();
    }
    
    public void atualizaDadosTabela() {
	tbvUser.getItems().setAll(service.buscarTodas());
	tbvUser.getSelectionModel().select(null);
    }
    
    public void editar(){
        FXMLLoader Loader = new FXMLLoader();
        Loader.setLocation(getClass().getResource("/View/FormAddUser.fxml"));
        
        try {
            Loader.load();
        }
        catch (IOException ex){
            System.out.println("erro ao trocar ctrl" + ex);
        }
        FormAddUserController frmAdd = Loader.getController();
        User user = tbvUser.getSelectionModel().getSelectedItem();
        frmAdd.setText(user.getName(), user.getEmail(), user.getPassword(), user.getPhone(), user.getRg(), user.getId(), user.getUserType());
        
        Parent p = Loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(p));
        stage.showAndWait();
    }

    public void configuraColumns() {
            colName.setCellValueFactory(new PropertyValueFactory<>("name"));
            colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
            colTell.setCellValueFactory(new PropertyValueFactory<>("phone"));
            colRg.setCellValueFactory(new PropertyValueFactory<>("rg"));
            colPassword.setCellValueFactory(new PropertyValueFactory<>("Password"));
            colId.setCellValueFactory(new PropertyValueFactory<>("id"));
            colTypeId.setCellValueFactory(new PropertyValueFactory<>("userType"));

            colPassword.setVisible(false);
            colId.setVisible(false);
            colTypeId.setVisible(false);

        }
    
    private void configuraBindings() {
        BooleanBinding algoSelecionado = tbvUser.getSelectionModel().selectedItemProperty().isNull();
        btnRemove.disableProperty().bind(algoSelecionado);
        btnEdit.disableProperty().bind(algoSelecionado);
    }
    
    public void mostraHistorico(){
        GerenciLar.historico();
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
