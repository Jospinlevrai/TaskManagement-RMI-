/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Gui;

import model.Employee;
import rmi.ServiceInt;
import java.time.LocalDateTime;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import rmi.ClientMain;



public class Registration extends Application {

    private String fieldStyle() {
        return "-fx-padding: 8 12 8 12;" +
               "-fx-background-radius: 8;" +
               "-fx-border-radius: 8;" +
               "-fx-border-color: #dddddd;" +
               "-fx-font-size: 13px;";
    }

    private String labelStyle() {
        return "-fx-font-size: 12px; -fx-text-fill: #555555; -fx-font-weight: bold;";
    }

    @Override
    public void start(Stage primaryStage)  {

         Label title = new Label();
         title.setText("Create Account");
         title.setStyle("-fx-font-size: 22px; -fx-font-weight: bold; -fx-text-fill: #222222;");

         Label subtitle = new Label("Join the Task Management System");
         subtitle.setStyle("-fx-font-size: 12px; -fx-text-fill: #888888;");

         Label namelabel = new Label("Enter Full name");
         namelabel.setStyle(labelStyle());
         TextField Name = new TextField();
         Name.setPromptText("Full name");
         Name.setMaxWidth(280);
         Name.setStyle(fieldStyle());

         Label emailLabel = new Label("Enter your email");
         emailLabel.setStyle(labelStyle());
         TextField tfemail  = new TextField();
         tfemail.setPromptText("Email");
         tfemail.setMaxWidth(280);
         tfemail.setStyle(fieldStyle());


        Label AgeLabel = new Label("Enter your Age(+18)");
        AgeLabel.setStyle(labelStyle());
        TextField tfAge = new TextField();
        tfAge.setPromptText("Age");
        tfAge.setMaxWidth(280);
        tfAge.setStyle(fieldStyle());

        Label passwordLabel = new Label("Enter  your password");
        passwordLabel.setStyle(labelStyle());
        PasswordField pfPassword = new PasswordField();
        pfPassword.setPromptText("Ënter your Password");
        pfPassword.setMaxWidth(280);
        pfPassword.setStyle(fieldStyle());

        Label roleLabel = new Label("Role");
        roleLabel.setStyle(labelStyle());
        ComboBox<String> Role = new ComboBox<>();
        Role.setMaxWidth(280);
        Role.setStyle(fieldStyle());



        Role.getItems().addAll("MANAGER","EMPLOYEE");
        Role.setPromptText("Select Role");


        TextField managercode = new TextField();
        managercode.setPromptText("MANAGER CODE");
        managercode.setMaxWidth(280);
        managercode.setStyle(fieldStyle());
        managercode.setVisible(false);


        Role.setOnAction(new EventHandler<ActionEvent>(){
             @Override
             public void handle(ActionEvent event) {
              if (Role.getValue() != null && Role.getValue().equals("MANAGER")){
                managercode.setVisible(true);
        }else {
        managercode.setVisible(false);
        }
             }


        });

        CheckBox isActive = new CheckBox("Active Account");
        isActive.setStyle("-fx-font-size: 12px; -fx-text-fill: #555555;");

        Label statusLabel = new Label();
        statusLabel.setStyle("-fx-text-fill: #c0392b; -fx-font-size: 12px;");
        statusLabel.setWrapText(true);
        statusLabel.setMaxWidth(280);


        Button Register = new Button("REGISTER");
        Register.setMaxWidth(280);
        Register.setStyle(
            "-fx-background-color: #3C3489;" +
            "-fx-text-fill: white;" +
            "-fx-font-weight: bold;" +
            "-fx-font-size: 13px;" +
            "-fx-padding: 10 0 10 0;" +
            "-fx-background-radius: 8;" +
            "-fx-cursor: hand;"
        );

        Register.setOnAction(new EventHandler<ActionEvent>(){

        @Override
        public void handle(ActionEvent event){

            //Condition for Register
            if (Name.getText().isEmpty() || tfemail.getText().isEmpty() ||
            tfAge.getText().isEmpty() || pfPassword.getText().isEmpty() ||
            Role.getValue() == null) {
            statusLabel.setText(" Please fill in all fields.");
            return;
            }
            else if (!tfemail.getText().contains("@")){
              statusLabel.setText("Invalid email address");
                 return;
              }
            else if(Role.getValue().equals("MANAGER") && !managercode.getText().equals("AD-243")){
            statusLabel.setText("Invalid manager code");
            return;
            }



          try{


          ServiceInt service = ClientMain.getService();

          Employee employee = new Employee(
            0,Name.getText(),tfemail.getText(),Integer.parseInt(tfAge.getText()),
            pfPassword.getText(),Role.getValue(),isActive.isSelected(),LocalDateTime.now());

            service.insertoperation(employee);
            Name.clear();

            tfemail.clear();

            tfAge.clear();

            pfPassword.clear();

            Role.setValue(null);

            isActive.setSelected(true);

            Login loginPage = new Login();
            Stage loginStage = new Stage();
            loginPage.start(loginStage);
            primaryStage.close();


          }catch(Exception ex) {

            System.out.println(
            ex.getMessage()
            );}
        }

        });

        VBox root = new VBox(10);
        root.getChildren().addAll(title,subtitle,namelabel,Name,emailLabel,tfemail,AgeLabel,tfAge,
                                  passwordLabel,pfPassword,roleLabel,Role,managercode,isActive,Register
                                  ,statusLabel);


        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(36, 45, 36, 45));
        root.setMaxWidth(380);
        root.setStyle(
            "-fx-background-color: white;" +
            "-fx-background-radius: 16;"
        );
        root.setEffect(new DropShadow(20, Color.rgb(0, 0, 0, 0.15)));

        StackPane outer = new StackPane(root);
        outer.setStyle("-fx-background-color: #f2f2f7;");
        outer.setPadding(new Insets(20));

        Scene scene = new Scene(outer,400,560);

        primaryStage.setTitle("REGISTRATION PAGE");

        primaryStage.setScene(scene);

        primaryStage.show();


  }
  public static void main(String[] args) {

        launch(args);
    }
}