package Controller;

import DAO.DbAcessActivity;
import DAO.DbAcessUser;
import Model.Activity;
import Model.User;
import View.GerenciLar;
import java.net.URL;
import java.sql.Connection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;


public class FormAddActivityController implements Initializable {
    
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
    
    @FXML
    private DatePicker dpStartDate;

    @FXML
    private DatePicker dpEndDate;
    
    @FXML
    private CheckBox cbConc;
        
    @FXML
    private ComboBox<User> cbMember;
    
    
    ObservableList<User> oblist = FXCollections.observableArrayList();
    
    private DbAcessActivity service = new DbAcessActivity();
    
    private DbAcessUser service2 = new DbAcessUser();
    
    int idActivity = 0;
          
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        fillComboBox();
        
    }

    public void atualizaCombo(){
        fillComboBox();
    }
    
     public void salvar(){
        Activity activity  = new Activity();
        
        try{
            getData(activity);
            if(idActivity == 0)
                service.salvar(activity);
            else
                service.atualizar(activity);
            clear();
            successMsg();
        }
        catch(Exception ex){
            failMsg();
        }

    } 
         
    private void getData(Activity activity){
        activity.setName(txtName.getText());
        activity.setDescription(txtDescricao.getText());
        activity.setStartDate(converte(dpStartDate));
        activity.setEndDate(converte(dpEndDate)); 
        User usuario = cbMember.getSelectionModel().getSelectedItem();
        activity.setUserId(usuario.getId());
        
        if(cbConc.isSelected())
            activity.setStatus(1);
       else
            activity.setStatus(0);
        
        activity.setId(idActivity);
    }
    
    private Date converte(DatePicker dp){         
        LocalDateTime ld = dp.getValue().atStartOfDay();
        return Date.from(ld.atZone(ZoneId.systemDefault()).toInstant());
    }
         
    //Limpa os campos
    private void clear(){
        txtName.setText("");
        txtDescricao.setText("");
        dpStartDate.setValue(null);
        dpEndDate.setValue(null);
        if(cbConc.isSelected())
            cbConc.setSelected(false);
    }
    
    //Msg Sucesso
    private void successMsg(){
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Cadastro de Atividade");
 
        // Header Text: null
        alert.setHeaderText(null);
        alert.setContentText("Atividade cadastrada com sucesso!");
 
        alert.showAndWait();
    }
    
    //Msg Falha
    private void failMsg(){
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Error Message");
        alert.setHeaderText(null);
        alert.setContentText("Não foi possível cadastrar Atividade. Verifique os campos digitados");

        alert.showAndWait();
    }    
    
    public void setText (String name, String description, Date startDate, Date endDate, int status, int id){
        this.txtName.setText(name);
        this.txtDescricao.setText(description);
        this.dpStartDate.setValue(LOCAL_DATE(startDate));
        this.dpEndDate.setValue(LOCAL_DATE(endDate));
        this.cbMember.setItems(oblist); 
        this.cbConc.setSelected(status == 1 ? true : false);
        idActivity = id;
    }
    
    public static final LocalDate LOCAL_DATE (Date dateString){
        String dtstr;
        dtstr = dateString.toString();
        LocalDate localDate = LocalDate.parse(dtstr);
        return localDate;
    }
    
    private void fillComboBox(){
        List<User> membros = new ArrayList<User>(); 
        membros = service2.buscarTodas();
        oblist = FXCollections.observableArrayList(membros);
        cbMember.setItems(oblist);
       
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
