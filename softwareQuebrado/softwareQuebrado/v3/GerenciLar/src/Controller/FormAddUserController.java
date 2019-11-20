package Controller;

import DAO.DbAcessUser;
import Model.User;
import View.GerenciLar;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

public class FormAddUserController implements Initializable {
    
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
    private TextField txtName;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtPassword;

    @FXML
    private TextField txtPhone;

    @FXML
    private TextField txtRg;

    @FXML
    private CheckBox cbAdm;

    @FXML
    private CheckBox cbMember;

    @FXML
    private Button btnConcluir;

    private DbAcessUser service = new DbAcessUser();
    
    private int userId = 0;
            
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    } 
    
    //metodos publicos onclick
    
    public void salvar(){
        User user  = new User();
        
        try{
            getData(user);
            if(userId == 0)
                service.salvar(user);
            else
                service.atualizar(user);
            clear();
            successMsg();
        }
        catch(Exception ex){
            failMsg();
        }

    }
    
    //metodos privados
    
    //Pega os valores nos campos e preenche o obj User   
    private void getData(User user){
        user.setName(txtName.getText());
        user.setPassword(txtPassword.getText());
        user.setRg(Integer.parseInt(txtRg.getText()));
        user.setEmail(txtEmail.getText());
        user.setPhone(txtPhone.getText()); 
        user.setPassword(txtPassword.getText());
        user.setId(userId);
        if(cbMember.isSelected())
            user.setUserType(1);
        else 
            user.setUserType(0);

    }
    
    public void cbMemberYes(){
        if(cbMember.isSelected())
            cbAdm.setSelected(false);
    }
    
    public void cbAdmYes(){
        if(cbAdm.isSelected())
            cbMember.setSelected(false);
    }
      
    //Limpa os campos
    private void clear(){
        txtName.setText("");
        txtPassword.setText("");
        txtRg.setText("");
        txtEmail.setText("");
        txtPhone.setText("");
    }
    
    //Msg Sucesso
    private void successMsg(){
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Cadastro de Usuário");
 
        // Header Text: null
        alert.setHeaderText(null);
        alert.setContentText("Usuário cadastrado com sucesso!");
 
        alert.showAndWait();
    }
    
    //Msg Falha
    private void failMsg(){
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Error Message");
        alert.setHeaderText(null);
        alert.setContentText("Não foi possível cadastrar Usuário. Verifique os campos digitados");

        alert.showAndWait();
    }   
    
    public void setText (String name, String email, String senha, String phone, int Rg, int id, int idType){
        this.txtName.setText(name);
        this.txtEmail.setText(email);
        this.txtPassword.setText(senha);
        this.txtPhone.setText(phone);
        this.txtRg.setText(Integer.toString(Rg)); 
        if(idType == 0)
            cbAdm.setSelected(true);
        else
            cbMember.setSelected(true);
        userId = id;
        
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
