package sample;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import actors.*;
import java.util.Date;

public class Main extends Application {
    Stage window;
    Scene root, agentLogin, officeLogin, house;

    @Override
    public void start(Stage primaryStage) throws Exception{

        GridPane grid;
        boolean agent = ConfirmBox.display("Login Confirmation", "Are you an agent?");
        if(agent) {
            primaryStage.setTitle("Agent Login");
            grid = new GridPane();
            grid.setAlignment(Pos.CENTER);
            grid.setHgap(10);
            grid.setVgap(10);
            grid.setPadding(new Insets(25, 25, 25, 25));

            Text scene_title = new Text("Agent Login");
            scene_title.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
            grid.add(scene_title, 0, 0, 2, 1);

            Label userName = new Label("User Id:");
            grid.add(userName, 0, 1);

            TextField userTextField = new TextField();
            grid.add(userTextField, 1, 1);


            Label pw = new Label("Password:");
            grid.add(pw, 0, 2);

            PasswordField pwBox = new PasswordField();
            grid.add(pwBox, 1, 2);

            Button btn = new Button("Sign in");
            HBox hbBtn = new HBox(10);
            hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
            hbBtn.getChildren().add(btn);
            grid.add(hbBtn, 1, 4);

            btn.setOnAction(e -> {
                long agent_id = Integer.parseInt(userTextField.getText());
                        House_Agent h = new House_Agent(agent_id);
                        try {
                            h.display(agent_id);
                        } catch (Exception exception) {
                            exception.printStackTrace();
                        }
                    }
            );
        }
        else{
            primaryStage.setTitle("Office Login");
            grid = new GridPane();
            grid.setAlignment(Pos.CENTER);
            grid.setHgap(10);
            grid.setVgap(10);
            grid.setPadding(new Insets(25, 25, 25, 25));

            Text scene_title = new Text("Office Login");
            scene_title.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
            grid.add(scene_title, 0, 0, 2, 1);

            Label userName = new Label("User Id:");
            grid.add(userName, 0, 1);

            TextField userTextField = new TextField();
            grid.add(userTextField, 1, 1);

            Label pw = new Label("Password:");
            grid.add(pw, 0, 2);

            PasswordField pwBox = new PasswordField();
            grid.add(pwBox, 1, 2);

            Button btn = new Button("Sign in");
            HBox hbBtn = new HBox(10);
            hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
            hbBtn.getChildren().add(btn);
            grid.add(hbBtn, 1, 4);

            btn.setOnAction(e -> {
                        House h = new House();
                        h.display();
                    }
            );
        }
        Scene scene = new Scene(grid, 300, 275);
        primaryStage.setMaximized(true);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch (args);
    }
}
