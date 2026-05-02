package org.example;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.animation.FadeTransition;
import javafx.util.Duration;

public class LoginPage {
    public void show(Stage stage) {
        Label title = new Label("Hotel Login");
        title.setFont(Font.font("Arial", FontWeight.BOLD,25));
        title.setStyle("-fx-font-size: 32px;" + "-fx-font-weight: bold;" + "-fx-text-fill: #C2185B;");
        TextField usernameField = new TextField();
        usernameField.setPromptText("Username");
        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Password");
        Button loginButton = new Button("Login");
        Label message = new Label();
        loginButton.setStyle("-fx-background-color: hotpink;" + "-fx-text-fill: white;" + "-fx-font-size: 18px;"
        );
        VBox root = new VBox(15);
        FadeTransition fade = new FadeTransition(Duration.seconds(2),root);
        fade.setFromValue(0);
        fade.setToValue(1);
        fade.play();
        root.setAlignment(Pos.CENTER);
        root.setStyle( "-fx-background-color: mistyrose;" +  "-fx-padding: 30px;"
        );
        root.getChildren().addAll(
                title,
                usernameField,
                passwordField,
                loginButton,
                message
        );
        loginButton.setOnAction(e -> {
            String user = usernameField.getText();
            String pass = passwordField.getText();
            if(user.equals("Admin") && pass.equals("0000")) {
                Dashboard dashboard = new Dashboard();
                dashboard.show(stage);
            } else {
                message.setText("Wrong username or password");
            }
        });
        Scene scene = new Scene(root, 900, 800);
        stage.setTitle("Login");
        stage.setScene(scene);
        stage.show();
    }
}