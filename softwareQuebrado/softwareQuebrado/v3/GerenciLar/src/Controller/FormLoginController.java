/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.DbAcessUser;
import Model.User;
import View.GerenciLar;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class FormLoginController implements Initializable {
    
    @FXML
    private TextField txtEmail;
    @FXML
    private ImageView imgHeader;
    @FXML
    private TextField txtSenha;
    @FXML
    private Button btnLogin;
    
    private final DbAcessUser service = new DbAcessUser ();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
      
    public void Login(){
        String senha = txtSenha.getText();
        String login = txtEmail.getText();
        boolean resposta = service.autenticaLogin(login, senha);
        System.out.println();
        
        if(resposta == true){
            txtEmail.setText("");
            txtSenha.setText("");
            GerenciLar.changeScreen("Main");
        }
        
        else {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Usuário ou Senha Incorreta");

            alert.showAndWait();
            txtEmail.requestFocus();
        }
    }
    public void AboutPage(){
        GerenciLar.about();
    }
            
    public void LoginEnter(KeyEvent e) {
            if(e.getCode() == KeyCode.ENTER) {
                Login();
            }
    }
    
}
