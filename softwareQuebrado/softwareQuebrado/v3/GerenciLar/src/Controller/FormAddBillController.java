package Controller;

import DAO.DbAcessBill;
import Model.Bill;
import View.GerenciLar;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class FormAddBillController implements Initializable {

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
    private TextField txtValue;

    @FXML
    private Button btnConcluir;
        
    @FXML
    private DatePicker dpExpDate;
    
    private DbAcessBill service = new DbAcessBill();
    
    int idBill = 0;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    public void salvar(){
        Bill bill  = new Bill();
        
        try{
            getData(bill);
            //if(idBill == 0)
                //service.salvar(bill);
            //else
                //service.atualizar(bill);
            clear();
            successMsg();
        }
        catch(Exception ex){
            failMsg();
        }

    } 
         
    private void getData(Bill bill){
        bill.setName(txtName.getText());
        bill.setValue(Float.parseFloat(txtValue.getText()));
        bill.setExpDate(converte(dpExpDate)); 
        bill.setId(idBill);
    }
    
    private Date converte(DatePicker dp){         
        LocalDateTime ld = dp.getValue().atStartOfDay();
        return Date.from(ld.atZone(ZoneId.systemDefault()).toInstant());
    }
         
    //Limpa os campos
    private void clear(){
        txtName.setText("");
        txtValue.setText("");
        dpExpDate.setValue(null);
    }
    
    //Msg Sucesso
    private void successMsg(){
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Cadastro de Conta");
 
        // Header Text: null
        alert.setHeaderText(null);
        alert.setContentText("Conta cadastrada com sucesso!");
 
        alert.showAndWait();
    }
    
    //Msg Falha
    private void failMsg(){
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Error Message");
        alert.setHeaderText(null);
        alert.setContentText("Não foi possível cadastrar Conta. Verifique os campos digitados");

        alert.showAndWait();
    }    
      
    public void setText (String name, Float value, Date expDate, int id){
        this.txtName.setText(name);
        this.txtValue.setText((value).toString());
        this.dpExpDate.setValue(LOCAL_DATE(expDate));
        idBill = id;
    }
    
    public static final LocalDate LOCAL_DATE (Date dateString){
        String dtstr;
        dtstr = dateString.toString();
        LocalDate localDate = LocalDate.parse(dtstr);
        return localDate;
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
