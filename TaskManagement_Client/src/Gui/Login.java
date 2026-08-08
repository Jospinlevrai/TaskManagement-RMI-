package Gui;
import model.Employee;
import rmi.ServiceInt;
import rmi.ClientMain;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Login extends Application  {
    @Override
    public void start(Stage primaryStage)  {

        Label title = new Label();
        title.setText("Welcome Back");
        title.setStyle("-fx-font-size: 22px; -fx-font-weight: bold; -fx-text-fill: #222222;");

        Label subtitle = new Label("Sign in to Task Management System");
        subtitle.setStyle("-fx-font-size: 12px; -fx-text-fill: #888888;");

        Label userlabel = new Label();
        userlabel.setText("Email");
        userlabel.setStyle("-fx-font-size: 12px; -fx-text-fill: #555555; -fx-font-weight: bold;");

        TextField Email = new TextField();
        Email.setPromptText("Enter your username");
        Email.setMaxWidth(280);
        Email.setStyle(
            "-fx-padding: 8 12 8 12;" +
            "-fx-background-radius: 8;" +
            "-fx-border-radius: 8;" +
            "-fx-border-color: #dddddd;" +
            "-fx-font-size: 13px;"
        );

        Label passlabel = new Label();
        passlabel.setText("Password");
        passlabel.setStyle("-fx-font-size: 12px; -fx-text-fill: #555555; -fx-font-weight: bold;");

        PasswordField password = new PasswordField();
        password.setPromptText("Enter Password");
        password.setMaxWidth(280);
        password.setStyle(
            "-fx-padding: 8 12 8 12;" +
            "-fx-background-radius: 8;" +
            "-fx-border-radius: 8;" +
            "-fx-border-color: #dddddd;" +
            "-fx-font-size: 13px;"
        );

        Label Message = new Label();
        Message.setStyle("-fx-text-fill: #c0392b; -fx-font-size: 12px;");
        Message.setWrapText(true);
        Message.setMaxWidth(280);

        Button btn1 = new Button();
        btn1.setText("Login");
        btn1.setMaxWidth(280);
        btn1.setStyle(
            "-fx-background-color: #3C3489;" +
            "-fx-text-fill: white;" +
            "-fx-font-weight: bold;" +
            "-fx-font-size: 13px;" +
            "-fx-padding: 10 0 10 0;" +
            "-fx-background-radius: 8;" +
            "-fx-cursor: hand;"
        );
        btn1.setOnMouseEntered(e -> btn1.setStyle(
            "-fx-background-color: #2E2870;" +
            "-fx-text-fill: white;" +
            "-fx-font-weight: bold;" +
            "-fx-font-size: 13px;" +
            "-fx-padding: 10 0 10 0;" +
            "-fx-background-radius: 8;" +
            "-fx-cursor: hand;"
        ));
        btn1.setOnMouseExited(e -> btn1.setStyle(
            "-fx-background-color: #3C3489;" +
            "-fx-text-fill: white;" +
            "-fx-font-weight: bold;" +
            "-fx-font-size: 13px;" +
            "-fx-padding: 10 0 10 0;" +
            "-fx-background-radius: 8;" +
            "-fx-cursor: hand;"
        ));

        Label Regi = new Label();
        Regi.setText("Create Account");
        Regi.setStyle(
            "-fx-text-fill: #3C3489;" +
            "-fx-font-size: 12px;" +
            "-fx-font-weight: bold;" +
            "-fx-cursor: hand;" +
            "-fx-underline: true;"
        );

        Regi.setOnMouseClicked(new EventHandler<MouseEvent>(){

            @Override
            public void handle(MouseEvent event) {
            Registration loginPage = new Registration();
            Stage RegistrationStage = new Stage();
            loginPage.start(RegistrationStage);
            primaryStage.close();


            }


        });

        btn1.setOnAction( new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {

            String inputemail = Email.getText();
            String inpassword = password.getText();

            if(inputemail.isEmpty() || inpassword.isEmpty()){
            Message.setText("Please fill all the fields");

            return;
            }
        try{
            ServiceInt service = ClientMain.getService();
            Employee employee = service.checklogin(inputemail, inpassword);

            if(employee == null){
            Message.setText("Invalid Credentials");

            }else if(!employee.isActive()){
            Message.setText("Inactive Account.Contact your Manager");

            }else if(employee.getRole().equals("MANAGER")){

                ManagerDashboard Managerpage = new ManagerDashboard(employee);
                Stage Manstage = new Stage();
                Managerpage.start(Manstage);
                primaryStage.close();

            }else if(employee.getRole().equals("EMPLOYEE")){

                EmployeeDashboard Employeepage = new EmployeeDashboard(employee);
                Stage Employeestage = new Stage();
                Employeepage.start(Employeestage);
                primaryStage.close();
                }
            }catch (Exception ex) {
                    Message.setText("Connection error: " + ex.getMessage());
                    ex.printStackTrace();


            }
                }


        });

        VBox root = new VBox();

        root.getChildren().addAll(title,subtitle,userlabel,Email,passlabel,password,Message,btn1,Regi);

        root.setAlignment(Pos.CENTER);
        root.setSpacing(12);
        root.setPadding(new Insets(40, 45, 40, 45));
        root.setMaxWidth(360);
        root.setStyle(
            "-fx-background-color: white;" +
            "-fx-background-radius: 16;"
        );
        root.setEffect(new DropShadow(20, Color.rgb(0, 0, 0, 0.15)));

        StackPane outer = new StackPane(root);
        outer.setStyle("-fx-background-color: #f2f2f7;");
        outer.setPadding(new Insets(20));

        Scene Login = new Scene(outer,600,480);

        primaryStage.setTitle("LOGIN SYSTEM");
        primaryStage.setScene(Login);
        primaryStage.show();

    }
     public static void main(String[] args) {
        launch(args);
    }

}