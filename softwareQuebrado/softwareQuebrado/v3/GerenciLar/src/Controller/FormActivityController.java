package Controller;

import DAO.DbAcessActivity;
import Model.Activity;
import View.GerenciLar;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.binding.BooleanBinding;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class FormActivityController implements Initializable {
    
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
    private Button btnEdit;

    @FXML
    private Button btnRemove;

    @FXML
    private Button btnAdd;
    
    @FXML
    private Button btnAtualizar;

    @FXML
    private TableView<Activity> tbvActivity;

    @FXML
    private TableColumn<Activity, String> colName;

    @FXML
    private TableColumn<Activity, String> colDescription;

    @FXML
    private TableColumn<Activity, String> colStartDate;

    @FXML
    private TableColumn<Activity, String> colEndDate;

    @FXML
    private TableColumn<Activity, String> colUser;

    @FXML
    private TableColumn<Activity, String> colStatus;
    
    @FXML
    private TableColumn<Activity, String> colId;
        
    private DbAcessActivity service = new DbAcessActivity();; 

    ObservableList<Activity> oblist = FXCollections.observableArrayList();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        configuraColumns();
        atualizaDadosTabela();
        configuraBindings();
        
//tbvActivity.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> atualizaDadosTabela());
    }    
    
     
    public void apagar(){
        Activity atividade = tbvActivity.getSelectionModel().getSelectedItem();
        service.apagar(atividade.getId());
        atualizaDadosTabela();
    }
    
     public void atualizaDadosTabela(){
        tbvActivity.getItems().setAll(service.buscarTodas());
	tbvActivity.getSelectionModel().select(null);
    }
     
    public void configuraColumns(){
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        colStartDate.setCellValueFactory(new PropertyValueFactory<>("startDate"));
        colEndDate.setCellValueFactory(new PropertyValueFactory<>("endDate"));
        colUser.setCellValueFactory(new PropertyValueFactory<>("userName"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));      
        colId.setVisible(false);
    }
    
    private void configuraBindings() {
        BooleanBinding algoSelecionado = tbvActivity.getSelectionModel().selectedItemProperty().isNull();
        btnRemove.disableProperty().bind(algoSelecionado);
        btnEdit.disableProperty().bind(algoSelecionado);
    }
    
    public void editar(){
        FXMLLoader Loader = new FXMLLoader();
        Loader.setLocation(getClass().getResource("/View/FormAddActivity.fxml"));
        
        try {
            Loader.load();
        }
        catch (IOException ex){
            System.out.println("erro ao trocar ctrl" + ex);
        }
        FormAddActivityController frmAdd = Loader.getController();
        Activity activity = tbvActivity.getSelectionModel().getSelectedItem();
        frmAdd.setText(activity.getName(), activity.getDescription(), activity.getStartDate(), activity.getEndDate(), activity.getStatus(), activity.getId());
        
        Parent p = Loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(p));
        stage.showAndWait();
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
    
    public void AddActivity(){
        GerenciLar.addActivity();
    }
    
    
 
    
}
