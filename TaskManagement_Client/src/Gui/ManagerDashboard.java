package Gui;

import model.Employee;
import model.Task;
import rmi.ServiceInt;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import rmi.ClientMain;


public class ManagerDashboard extends Application {
    private Employee LogEmp;
    public ManagerDashboard(Employee logEmp){
    this.LogEmp = logEmp;
    }
    public ManagerDashboard() {}

    @Override
    public void start(Stage Manstage) {
        int totalTasks = 0, inProgress = 0, empCount = 0;
    try {
         ServiceInt service = ClientMain.getService();
        totalTasks = service.countAllTasks();
        inProgress = service.countByStatus("PENDING");
        empCount = service.countAllEmployees();
    } catch (Exception ex) {
    System.out.println("Failed to load stats: " + ex.getMessage());
}
        String displayName;

        if (LogEmp != null) {
        displayName = LogEmp.getFullName();
}       else {
        displayName = "Manager";
}

        Label apptitle = new Label();
        apptitle.setText("Task Management System");
        apptitle.setStyle("-fx-font-size: 13px; -fx-text-fill: #7F77DD; -fx-font-weight: bold;");

        Label username = new Label(displayName);
        username.setStyle("-fx-font-weight: bold; -fx-font-size: 14px; -fx-text-fill: #222222;");

        Label userrole = new Label("Manager");
        userrole.setStyle(
            "-fx-background-color: #EEEDFE;" +
            "-fx-text-fill: #3C3489;" +
            "-fx-font-size: 11px;" +
            "-fx-font-weight: bold;" +
            "-fx-padding: 3 10 3 10;" +
            "-fx-background-radius: 20;"
        );

        Button Exit = new Button("Logout");
        Exit.setStyle(
            "-fx-background-color: transparent;" +
            "-fx-border-color: #cccccc;" +
            "-fx-border-radius: 8;" +
            "-fx-padding: 6 14 6 14;" +
            "-fx-font-size: 12px;" +
            "-fx-cursor: hand;"
        );

        Exit.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {

                Login Loginpage = new Login();
                Stage LoginStage = new Stage();
                Loginpage.start(LoginStage);
                Manstage.close();

            }

        });

        HBox leftHeader = new HBox(10);
        leftHeader.setAlignment(Pos.CENTER_LEFT);
        leftHeader.getChildren().addAll(apptitle,username,userrole);

        HBox rightHeader = new HBox(10);
        rightHeader.setAlignment(Pos.CENTER_RIGHT);
        rightHeader.getChildren().add(Exit);

        HBox header = new HBox(10);
        header.getChildren().addAll(leftHeader,rightHeader);
        header.setPadding(new Insets(14, 20, 14, 20));
        header.setStyle(
            "-fx-background-color: white;" +
            "-fx-border-color: #e0e0e0;" +
            "-fx-border-width: 0 0 1 0;"
        );

        Label val1 = new Label(String.valueOf(totalTasks));
        val1.setStyle("-fx-font-size: 24px; -fx-font-weight: bold; -fx-text-fill: #222222;");


        Label total = new Label("Total Task");
        total.setStyle("-fx-font-size: 12px; -fx-text-fill: #888;");

        VBox stat1 = new VBox(4);
        stat1.getChildren().addAll(val1,total);
        stat1.setPadding(new Insets(14,16,14,16));
        stat1.setStyle("-fx-background-color: white; -fx-background-radius: 10; -fx-border-color: #eeeeee; -fx-border-radius: 10;");

        HBox.setHgrow(stat1, Priority.ALWAYS);

        Label val2 = new Label(String.valueOf(inProgress));
        val2.setStyle("-fx-font-size: 24px; -fx-font-weight: bold; -fx-text-fill: #0C447C;");


        Label progress = new Label("In Progress");
        progress.setStyle("-fx-font-size: 12px; -fx-text-fill: #888;");

        VBox stat2 = new VBox(4);
        stat2.getChildren().addAll(val2,progress);
        stat2.setPadding(new Insets(14,16,14,16));
        stat2.setStyle("-fx-background-color: white; -fx-background-radius: 10; -fx-border-color: #eeeeee; -fx-border-radius: 10;");

        HBox.setHgrow(stat2, Priority.ALWAYS);

        Label val3 = new Label(String.valueOf(empCount));
        val3.setStyle("-fx-font-size: 24px; -fx-font-weight: bold; -fx-text-fill: #3C3489;");

        Label Employee = new Label("Employees");
        Employee.setStyle("-fx-font-size: 12px; -fx-text-fill: #888;");


        VBox stat3 = new VBox(4);
        stat3.getChildren().addAll(val3,Employee);
        stat3.setPadding(new Insets(14,16,14,16));
        stat3.setStyle("-fx-background-color: white; -fx-background-radius: 10; -fx-border-color: #eeeeee; -fx-border-radius: 10;");
        HBox.setHgrow(stat3, Priority.ALWAYS);

        HBox metrics = new HBox(14);
        metrics.getChildren().addAll(stat1,stat2,stat3);
        HBox.setHgrow(stat3, Priority.ALWAYS);
        metrics.setPadding(new Insets(18,20,0,20));

        Label actionsTitle = new Label("Choose an Action");
        actionsTitle.setStyle("-fx-font-size: 13px; -fx-text-fill: #555555; -fx-font-weight: bold;");

        Button assignBtn = new Button("Assign Task");
        assignBtn.setStyle(
            "-fx-background-color: #EEEDFE;" +
            "-fx-border-color: #AFA9EC;" +
            "-fx-text-fill: #3C3489;" +
            "-fx-border-radius: 8;" +
            "-fx-background-radius: 8;" +
            "-fx-padding: 8 16 8 16;" +
            "-fx-font-size: 12px;" +
            "-fx-font-weight: bold;" +
            "-fx-cursor: hand;"
        );

        Button cancelBtn = new Button("Cancel Task");
        cancelBtn.setStyle(
            "-fx-background-color: #FCEAEA;" +
            "-fx-border-color: #F09595;" +
            "-fx-text-fill: #A32D2D;" +
            "-fx-border-radius: 8;" +
            "-fx-background-radius: 8;" +
            "-fx-padding: 8 16 8 16;" +
            "-fx-font-size: 12px;" +
            "-fx-font-weight: bold;" +
            "-fx-cursor: hand;"
        );

        HBox actions = new HBox(10);
        actions.getChildren().addAll(assignBtn,cancelBtn);

        VBox actionsSection = new VBox(10);
        actionsSection.getChildren().addAll(actionsTitle,actions);
        actionsSection.setPadding(new Insets(18,20,0,20));

        Label taskTitle = new Label("All tasks");
        taskTitle.setStyle("-fx-font-size: 13px; -fx-text-fill: #555555; -fx-font-weight: bold;");

        VBox List = new VBox(10);

        List<Task> tasks = new java.util.ArrayList<>();
        try {
            tasks = ClientMain.getService().getAllTasks();
        } catch (Exception ex) {
            System.out.println("Failed to load tasks: " + ex.getMessage());
}

        if (tasks.isEmpty()){
        Label noTasks = new Label("No tasks found or assigned");
        noTasks.setStyle("-fx-text-fill: #888; -fx-font-size: 13px;");
        List.getChildren().add(noTasks);


        } else {
          for (Task t : tasks){

              String Badge;
              String Color;

              if (t.getStatus().equals("In progress")){
                  Badge = "#E6F1FB";
                  Color = "#0C447C";
              } else if (t.getStatus().equals("Done")){
                  Badge = "#EAF3DE";
                  Color = "#27500A";

              } else if (t.getStatus().equals("Cancelled")) {
                  Badge = "#F5F5F5";
                  Color = "#888888";
              }else {
                Badge    = "#FAEEDA"; // Pending default
                Color = "#633806";
              }

            Label taskName = new Label(t.getTitle());
            taskName.setStyle("-fx-font-size: 13px; -fx-font-weight: bold; -fx-text-fill: #222222;");

            Label Assign = new Label("Assigned to: "+t.getAssignedTo()+"  |  "+t.getDueDate());
            Assign.setStyle("-fx-font-size: 11px; -fx-text-fill: #888;");

            VBox taskleft = new VBox(2);
            taskleft.getChildren().addAll(taskName,Assign);


            Label taskStatus = new Label(""+t.getStatus());
            taskStatus.setStyle(
                    "-fx-background-color: " +Badge+ ";" +
                    "-fx-text-fill: "         +Color + ";" +
                    "-fx-padding: 2 10 2 10;"                       +
                    "-fx-background-radius: 20;"                   +
                    "-fx-font-size: 11px;" +
                    "-fx-font-weight: bold;"
                );
            HBox taskRow = new HBox();
            HBox.setHgrow(taskleft, Priority.ALWAYS);
            taskRow.getChildren().addAll(taskleft,taskStatus);
            taskRow.setAlignment(Pos.CENTER_LEFT);
            taskRow.setPadding(new Insets(10, 14, 10, 14));
            taskRow.setStyle(
                    "-fx-background-color: white;" +
                    "-fx-background-radius: 10;" +
                    "-fx-border-color: #eeeeee;" +
                    "-fx-border-radius: 10;"
                );
            taskRow.setEffect(new DropShadow(6, Color2()));
            List.getChildren().add(taskRow);

          }
        }
        VBox tasksSection = new VBox(10);
        tasksSection.getChildren().addAll(taskTitle, List);
        tasksSection.setPadding(new Insets(18, 20, 20, 20));


        Label formName = new Label("New Task");
        formName.setStyle("-fx-font-weight: bold; -fx-font-size: 14px; -fx-text-fill: #222222;");


        Label tasktitle= new Label("Task Title");
        tasktitle.setStyle("-fx-font-size: 12px; -fx-text-fill: #555555; -fx-font-weight: bold;");
        TextField tasktf = new TextField();
        tasktf.setPromptText("Give a task name");
        tasktf.setMaxWidth(Double.MAX_VALUE);
        tasktf.setStyle("-fx-padding: 8 12 8 12; -fx-background-radius: 8; -fx-border-radius: 8; -fx-border-color: #dddddd; -fx-font-size: 13px;");

        Label description = new Label("Description");
        description.setStyle("-fx-font-size: 12px; -fx-text-fill: #555555; -fx-font-weight: bold;");
        TextField descriptiontf = new TextField();
        descriptiontf.setPromptText("Enter a description");
        descriptiontf.setMaxWidth(Double.MAX_VALUE);
        descriptiontf.setStyle("-fx-padding: 8 12 8 12; -fx-background-radius: 8; -fx-border-radius: 8; -fx-border-color: #dddddd; -fx-font-size: 13px;");

        Label assign = new Label("Assign To");
        assign.setStyle("-fx-font-size: 12px; -fx-text-fill: #555555; -fx-font-weight: bold;");
        ComboBox<String> assignToField = new ComboBox<>();
        java.util.Map<String, Integer> employeeData = new java.util.HashMap<>();

        assignToField.setPromptText("Select Employee");
        assignToField.setMaxWidth(Double.MAX_VALUE);
        assignToField.setStyle("-fx-background-radius: 8; -fx-border-radius: 8; -fx-border-color: #dddddd;");

        List<Employee> employees = new java.util.ArrayList<>();
        try {
            employees = ClientMain.getService().getAllEmployees();
        } catch (Exception ex) {
            System.out.println("Failed to load employees: " + ex.getMessage());
}

        for (Employee emp : employees) {
        assignToField.getItems().add(emp.getFullName()); //
        employeeData.put(emp.getFullName(), emp.getEmployeeId()); //
}

        Label duedatelabel = new Label("Due Date(YYYY-MM-DD)");
        duedatelabel.setStyle("-fx-font-size: 12px; -fx-text-fill: #555555; -fx-font-weight: bold;");
        TextField dueDatetf = new TextField();
        dueDatetf.setPromptText("e.g. 2025-06-01");
        dueDatetf.setMaxWidth(Double.MAX_VALUE);
        dueDatetf.setStyle("-fx-padding: 8 12 8 12; -fx-background-radius: 8; -fx-border-radius: 8; -fx-border-color: #dddddd; -fx-font-size: 13px;");

        Label formStatus = new Label();
        formStatus.setStyle("-fx-font-size: 12px;");
        Button submitTask = new Button("ASSIGN TASK");
        submitTask.setStyle(
            "-fx-background-color: #3C3489;" +
            "-fx-text-fill: white;" +
            "-fx-border-radius: 8;" +
            "-fx-background-radius: 8;" +
            "-fx-padding: 9 16 9 16;" +
            "-fx-font-size: 12px;" +
            "-fx-font-weight: bold;" +
            "-fx-cursor: hand;"
        );


        submitTask.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
            if(tasktf.getText().isEmpty() || descriptiontf.getText().isEmpty() ||
               assignToField.getValue() == null || dueDatetf.getText().isEmpty()){

                formStatus.setText("Please fill in all fields.");
                formStatus.setStyle("-fx-text-fill: red;");
                return;
            }
            try{
                int selectedId = employeeData.get(assignToField.getValue());
                Task task = new Task(0,tasktf.getText(),descriptiontf.getText(),
                                     selectedId,"PENDING",LocalDate.parse(dueDatetf.getText()),
                                     LocalDateTime.now(),LocalDateTime.now());
                ServiceInt taskService = ClientMain.getService();
                taskService.inserttask(task);
                tasktf.clear();
                descriptiontf.clear();
                assignToField.setValue(null);
                dueDatetf.clear();
            formStatus.setText("Task assigned successfully");
            formStatus.setStyle("-fx-text-fill: green;");

          }catch (Exception ex) {
                    formStatus.setText("Failed: " + ex.getMessage());
                    formStatus.setStyle("-fx-text-fill: red;");
                    System.out.println(ex.getMessage());
        }
        }

        });

        VBox form = new VBox(10);
        form.getChildren().addAll(formName,tasktitle,tasktf,description,descriptiontf,
                                    assign,assignToField,duedatelabel,dueDatetf,submitTask,formStatus);
        form.setPadding(new Insets(18));
        form.setStyle(
            "-fx-background-color: white;" +
            "-fx-border-color: #e0e0e0;"     +
            "-fx-border-radius: 12;"          +
            "-fx-background-radius: 12;"
        );
        form.setEffect(new DropShadow(8, Color2()));
        form.setVisible(false);
        form.setManaged(false);

        assignBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                form.setVisible(true);
                form.setManaged(true);
            }
        });


        Label cancelSectionTitle = new Label("Cancel a task");
        cancelSectionTitle.setStyle("-fx-font-size: 13px; -fx-text-fill: #555555; -fx-font-weight: bold;");

        Label cancelLabel = new Label("Task ID");
        cancelLabel.setStyle("-fx-font-size: 12px; -fx-text-fill: #555555; -fx-font-weight: bold;");
        TextField cancelIdField = new TextField();
        cancelIdField.setPromptText("Enter task ID to cancel");
        cancelIdField.setMaxWidth(250);
        cancelIdField.setStyle("-fx-padding: 8 12 8 12; -fx-background-radius: 8; -fx-border-radius: 8; -fx-border-color: #dddddd; -fx-font-size: 13px;");

        Label cancelStatus = new Label();
        cancelStatus.setStyle("-fx-font-size: 12px;");

         cancelBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (cancelIdField.getText().isEmpty()) {
                    cancelStatus.setText("Please enter a task ID.");
                     cancelStatus.setStyle("-fx-text-fill: red;");

                    return;
                }
                try{
                    ServiceInt cancelService = ClientMain.getService();
cancelService.updatepro(Integer.parseInt(cancelIdField.getText()), "Cancelled");


                }catch (Exception ex) {
                    cancelStatus.setText("Failed: " + ex.getMessage());
                    System.out.println(ex.getMessage());

            }
            }
         });
         VBox cancelSection = new VBox(10);
        cancelSection.getChildren().addAll(cancelSectionTitle, cancelLabel, cancelIdField, cancelStatus);
        cancelSection.setPadding(new Insets(0, 20, 20, 20));

        VBox allcontent = new VBox(0);
        allcontent.setStyle("-fx-background-color: #f5f6f8;");
        allcontent.getChildren().addAll(header,metrics,actionsSection, new Separator(),
        tasksSection,form, new Separator(),cancelSection);


       ScrollPane scroll = new ScrollPane(allcontent);
       scroll.setFitToWidth(true);
       scroll.setStyle("-fx-background-color: #f5f6f8;");

       Scene Man = new Scene(scroll, 520, 600);
       Manstage.setTitle("Manager Dashboard");
       Manstage.setScene(Man);
       Manstage.show();



    }

    private static Color Color2() {
        return Color.rgb(0, 0, 0, 0.06);
    }

    public static void main (String[ ] args){
    launch(args);
    }
}