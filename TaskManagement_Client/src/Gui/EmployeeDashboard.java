package Gui;

import model.Employee;
import model.Task;
import rmi.ServiceInt;
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

public class EmployeeDashboard extends Application {

    private static final Color CARD_SHADOW_COLOR = Color.rgb(0, 0, 0, 0.06);

    private Employee LogEmp;

    public EmployeeDashboard(Employee logEmp) {
        this.LogEmp = logEmp;
    }

    public EmployeeDashboard() {}

    @Override
    public void start(Stage Employeestage) {

        String displayName;
        if (LogEmp != null) {
            displayName = LogEmp.getFullName();
        } else {
            displayName = "Employee";
        }


        Label apptitle = new Label("Task Management System");
        apptitle.setStyle("-fx-font-size: 13px; -fx-text-fill: #1D9E75; -fx-font-weight: bold;");

        Label username = new Label(displayName);
        username.setStyle("-fx-font-weight: bold; -fx-font-size: 14px; -fx-text-fill: #222222;");

        Label userrole = new Label("Employee");
        userrole.setStyle(
            "-fx-background-color: #E1F5EE;" +
            "-fx-text-fill: #085041;" +
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
        Exit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Login Loginpage = new Login();
                Stage LoginStage = new Stage();
                Loginpage.start(LoginStage);
                Employeestage.close();
            }
        });

        HBox leftHeader = new HBox(10);
        leftHeader.setAlignment(Pos.CENTER_LEFT);
        leftHeader.getChildren().addAll(apptitle, username, userrole);

        HBox rightHeader = new HBox(10);
        rightHeader.setAlignment(Pos.CENTER_RIGHT);
        HBox.setHgrow(rightHeader, Priority.ALWAYS);
        rightHeader.getChildren().add(Exit);

        HBox header = new HBox(10);
        header.getChildren().addAll(leftHeader, rightHeader);
        header.setPadding(new Insets(14, 20, 14, 20));
        header.setStyle(
            "-fx-background-color: white;" +
            "-fx-border-color: #e0e0e0;" +
            "-fx-border-width: 0 0 1 0;"
        );



        int empId = (LogEmp != null) ? LogEmp.getEmployeeId() : 0;
        int myTasks = 0, myPending = 0, myDone = 0;
        List<Task> tasks = new java.util.ArrayList<>();
        try {
            ServiceInt service = ClientMain.getService();
            myTasks = service.countBytaskEmployee(empId);
            myPending = service.countBystat(empId, "PENDING");
            myDone = service.countBystat(empId, "Done");
            tasks = service.sortTasksByEmployee(empId);
        } catch (Exception ex) {
            System.out.println("Failed to load dashboard: " + ex.getMessage());
        }

        Label val1 = new Label(String.valueOf(myTasks));
        val1.setStyle("-fx-font-size: 24px; -fx-font-weight: bold; -fx-text-fill: #222222;");

        Label lbl1 = new Label("My tasks");

        lbl1.setStyle("-fx-font-size: 12px; -fx-text-fill: #888;");

        VBox stat1 = new VBox(4);

        stat1.getChildren().addAll(val1, lbl1);

        stat1.setPadding(new Insets(14, 16, 14, 16));

        stat1.setStyle("-fx-background-color: white; -fx-background-radius: 10; -fx-border-color: #eeeeee; -fx-border-radius: 10;");


        HBox.setHgrow(stat1, Priority.ALWAYS);

        Label val2 = new Label(String.valueOf(myPending));

        val2.setStyle("-fx-font-size: 24px; -fx-font-weight: bold; -fx-text-fill: #633806;");

        Label lbl2 = new Label("Pending");

        lbl2.setStyle("-fx-font-size: 12px; -fx-text-fill: #888;");


        VBox stat2 = new VBox(4);
        stat2.getChildren().addAll(val2, lbl2);
        stat2.setPadding(new Insets(14, 16, 14, 16));
        stat2.setStyle("-fx-background-color: white; -fx-background-radius: 10; -fx-border-color: #eeeeee; -fx-border-radius: 10;");

        HBox.setHgrow(stat2, Priority.ALWAYS);

        Label val3 = new Label(String.valueOf(myDone));

        val3.setStyle("-fx-font-size: 24px; -fx-font-weight: bold; -fx-text-fill: #27500A;");
        Label lbl3 = new Label("Completed");

        lbl3.setStyle("-fx-font-size: 12px; -fx-text-fill: #888;");

        VBox stat3 = new VBox(4);
        stat3.getChildren().addAll(val3, lbl3);

        stat3.setPadding(new Insets(14, 16, 14, 16));
        stat3.setStyle("-fx-background-color: white; -fx-background-radius: 10; -fx-border-color: #eeeeee; -fx-border-radius: 10;");

        HBox.setHgrow(stat3, Priority.ALWAYS);

        HBox metrics = new HBox(14);
        metrics.getChildren().addAll(stat1, stat2, stat3);
        metrics.setPadding(new Insets(18, 20, 0, 20));


        Label taskTitle = new Label("My Tasks");
        taskTitle.setStyle("-fx-font-size: 13px; -fx-text-fill: #555555; -fx-font-weight: bold;");

        VBox taskList = new VBox(10);



        if (tasks.isEmpty()) {
            Label noTasks = new Label("No tasks assigned yet.");
            noTasks.setStyle("-fx-text-fill: #888; -fx-font-size: 13px;");
            taskList.getChildren().add(noTasks);
        } else {
            for (Task t : tasks) {


                String Badge;
                String Color;
                if (t.getStatus().equals("Done")) {
                    Badge = "#EAF3DE";
                    Color = "#27500A";
                } else if (t.getStatus().equals("Cancelled")) {
                    Badge = "#F5F5F5";
                    Color = "#888888";
                } else {
                    Badge = "#FAEEDA";
                    Color = "#633806";
                }

                // ── Task details ──────────────────────────────
                Label taskName = new Label(t.getTitle());
                taskName.setStyle("-fx-font-size: 14px; -fx-font-weight: bold; -fx-text-fill: #222222;");

                Label taskDesc = new Label("Description: " + t.getDescription());
                taskDesc.setStyle("-fx-font-size: 12px; -fx-text-fill: #555;");

                Label taskDue = new Label("Due date: " + t.getDueDate());
                taskDue.setStyle("-fx-font-size: 12px; -fx-text-fill: #555;");

                Label taskId = new Label("Task ID: " + t.getTaskId());
                taskId.setStyle("-fx-font-size: 11px; -fx-text-fill: #aaa;");

                Label taskCreated = new Label("Assigned on: " + t.getCreatedAt().toLocalDate());
                taskCreated.setStyle("-fx-font-size: 11px; -fx-text-fill: #aaa;");

                Label taskStatus = new Label(t.getStatus());
                taskStatus.setStyle(
                    "-fx-background-color: " + Badge + ";" +
                    "-fx-text-fill: "         + Color + ";" +
                    "-fx-padding: 2 10 2 10;" +
                    "-fx-background-radius: 20;" +
                    "-fx-font-size: 11px;" +
                    "-fx-font-weight: bold;"
                );


                Button doneBtn = new Button("Mark as Done");
                doneBtn.setStyle(
                    "-fx-background-color: #E1F5EE;" +
                    "-fx-border-color: #5DCAA5;" +
                    "-fx-text-fill: #085041;" +
                    "-fx-border-radius: 8;" +
                    "-fx-background-radius: 8;" +
                    "-fx-padding: 5 12 5 12;" +
                    "-fx-font-size: 12px;" +
                    "-fx-cursor: hand;"
                );

                Label rowStatus = new Label();


                if (t.getStatus().equals("Done") || t.getStatus().equals("Cancelled")) {
                    doneBtn.setDisable(true);
                }

                doneBtn.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        try {
                            ServiceInt service = ClientMain.getService();
                            service.updatepro(t.getTaskId(), "Done");
                            doneBtn.setDisable(true);
                            taskStatus.setText("Done");
                            taskStatus.setStyle(
                                "-fx-background-color: #EAF3DE;" +
                                "-fx-text-fill: #27500A;" +
                                "-fx-padding: 2 10 2 10;" +
                                "-fx-background-radius: 20;" +
                                "-fx-font-size: 11px;" +
                                "-fx-font-weight: bold;"
                            );
                            rowStatus.setText("Marked as Done!");
                            rowStatus.setStyle("-fx-text-fill: green; -fx-font-size: 11px;");
                        } catch (Exception ex) {
                            rowStatus.setText("Failed: " + ex.getMessage());
                            rowStatus.setStyle("-fx-text-fill: red;");
                            System.out.println(ex.getMessage());
                        }
                    }
                });


                HBox topRow = new HBox(10);
                HBox.setHgrow(new VBox(), Priority.ALWAYS);
                topRow.setAlignment(Pos.CENTER_LEFT);
                topRow.getChildren().addAll(taskName, taskStatus);

                HBox bottomRow = new HBox(10);
                bottomRow.setAlignment(Pos.CENTER_LEFT);
                bottomRow.getChildren().addAll(doneBtn, rowStatus);

                VBox taskCard = new VBox(6);
                taskCard.getChildren().addAll(
                    topRow,
                    taskDesc,
                    taskDue,
                    taskId,
                    taskCreated,
                    bottomRow
                );
                taskCard.setPadding(new Insets(14));
                taskCard.setStyle(
                    "-fx-background-color: white;" +
                    "-fx-border-color: #e0e0e0;" +
                    "-fx-border-radius: 10;" +
                    "-fx-background-radius: 10;"
                );
                taskCard.setEffect(new DropShadow(6, CARD_SHADOW_COLOR));

                taskList.getChildren().add(taskCard);
            }
        }

        VBox tasksSection = new VBox(10);
        tasksSection.getChildren().addAll(taskTitle, taskList);
        tasksSection.setPadding(new Insets(18, 20, 20, 20));

        // ── MAIN LAYOUT ───────────────────────────────────────
        VBox allcontent = new VBox(0);
        allcontent.setStyle("-fx-background-color: #f5f6f8;");
        allcontent.getChildren().addAll(
            header,
            metrics,
            new Separator(),
            tasksSection
        );

        ScrollPane scroll = new ScrollPane(allcontent);
        scroll.setFitToWidth(true);
        scroll.setStyle("-fx-background-color: #f5f6f8;");

        Scene Emp = new Scene(scroll, 520, 600);
        Employeestage.setTitle("Employee Dashboard");
        Employeestage.setScene(Emp);
        Employeestage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}