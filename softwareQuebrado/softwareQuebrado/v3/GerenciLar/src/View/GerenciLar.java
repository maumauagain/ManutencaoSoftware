package View;


import Model.User;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class GerenciLar extends Application {
    
    private static Stage myStage;
    
    private static Scene loginScene;
    private static Scene mainScene;
    private static Scene aboutScene;
    private static Scene userScene;
    private static Scene billScene;
    private static Scene activityScene;
    private static Scene listScene;
    private static Scene addUserScene;
    private static Scene addBillScene;
    private static Scene addActivityScene;
    private static Scene addListScene;
    private static Scene payBillScene;
    private static Scene historyScene;

    @Override
    public void start(Stage stage) throws Exception {
        myStage = stage;
        
        Parent fxmlLogin = FXMLLoader.load(getClass().getResource("FormLogin.fxml"));
        loginScene = new Scene(fxmlLogin);
        
        Parent fxmlMain = FXMLLoader.load(getClass().getResource("FormMain.fxml"));
        mainScene = new Scene(fxmlMain);
        
        Parent fxmlAbout = FXMLLoader.load(getClass().getResource("FormAbout.fxml"));
        aboutScene = new Scene(fxmlAbout);

        Parent fxmlUser = FXMLLoader.load(getClass().getResource("FormUser.fxml"));
        userScene = new Scene(fxmlUser);
        
        Parent fxmlBill = FXMLLoader.load(getClass().getResource("FormBill.fxml"));
        billScene = new Scene(fxmlBill);
        
        Parent fxmlActivity = FXMLLoader.load(getClass().getResource("FormActivity.fxml"));
        activityScene = new Scene(fxmlActivity);
        
        Parent fxmlList = FXMLLoader.load(getClass().getResource("FormList.fxml"));
        listScene = new Scene(fxmlList);
        
        Parent fxmlAddUser = FXMLLoader.load(getClass().getResource("FormAddUser.fxml"));
        addUserScene = new Scene(fxmlAddUser);
        
        Parent fxmlAddBill = FXMLLoader.load(getClass().getResource("FormAddBill.fxml"));
        addBillScene = new Scene(fxmlAddBill);        
        
        Parent fxmlAddActivity = FXMLLoader.load(getClass().getResource("FormAddActivity.fxml"));
        addActivityScene = new Scene(fxmlAddActivity);
        
        Parent fxmlAddList = FXMLLoader.load(getClass().getResource("FormAddList.fxml"));
        addListScene = new Scene(fxmlAddList);
        
        Parent fxmlPayBill = FXMLLoader.load(getClass().getResource("FormPayBill.fxml"));
        payBillScene = new Scene(fxmlPayBill);
        
        Parent fxmlHistoryScene = FXMLLoader.load(getClass().getResource("FormHistorico.fxml"));
        historyScene = new Scene(fxmlHistoryScene);
        
        stage.setScene(loginScene);
        stage.setTitle("GerenciLar");
        stage.getIcons().add(
    new Image(
        GerenciLar.class.getResourceAsStream( "Image/icon.png" ))); 
        stage.setResizable(false);
        stage.show();        
    }
    
    public static void changeScreen(String scr){
        switch(scr){
            case "Login":
                myStage.setScene(loginScene);
                break;
            case "Main":
                myStage.setScene(mainScene);
                break;
            case "About":
                myStage.setScene(aboutScene);
                break;
            case "User":
                myStage.setScene(userScene);
                break;
            case "Bill":
                myStage.setScene(billScene);
                break;    
            case "Activity":
                myStage.setScene(activityScene);
                break;  
            case "List":
                myStage.setScene(listScene);
                break;
            case "AddUser":
                myStage.setScene(addUserScene);
                break;
            case "AddBill":
                myStage.setScene(addBillScene);
                break;
            case "AddActivity":
                myStage.setScene(addActivityScene);
                break;
            case "AddList":
                myStage.setScene(addListScene);
                break;
            case "PayBill":
                myStage.setScene(payBillScene);
                break;
            case "History":
                myStage.setScene(historyScene);
        }
    }
    
    public static void exit(){
        myStage.close();
    }
    
    public static void logoff(){
        changeScreen("Login");
    }
    
    public static void about(){
        changeScreen("About");
    }
    
    public static void user(){
        changeScreen("User");
    }
    
    public static void bill(){
        changeScreen("Bill");
    }
    
    public static void activity(){
        changeScreen("Activity");
    }
    
    public static void list(){
        changeScreen("List");
    }
    
    public static void addUser(){
        changeScreen("AddUser");
    }
    
    public static void addBill(){
        changeScreen("AddBill");
    }
    
    public static void addActivity(){
        changeScreen("AddActivity");
    }
    
    public static void addList(){
        changeScreen("AddList");
    }
    
    public static void payBill(){
        changeScreen("PayBill");
    }
    
    public static void historico(){
        changeScreen("History");
    }
    
    public static void main(String[] args) {
        launch(args);       
        
    }
    
    
    
    
}


