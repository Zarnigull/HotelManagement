package org.example;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class HousekeepingPage {
    public void show(Stage stage) {
        Label title = new Label("Housekeeping Log");
        ComboBox<String> choresBox = new ComboBox<>();
        choresBox.getItems().addAll(
                "Room Cleaning",
                "Change Bed Sheets",
                "Replace Towels",
                "Bathroom Cleaning",
                "Vacuum Floor",
                "Window Cleaning",
                "Mini Bar Refill",
                "Air Conditioner Check",
                "Restock Toiletries",
                "Trash Removal"
        );
        choresBox.setPromptText("Select Housekeeping Task");
        TextField roomField = new TextField();
        roomField.setPromptText("Room Number");
        Button saveButton = new Button("Save Log");
        Label message = new Label();
        ListView<String> logList = new ListView<>();
        logList.setPrefHeight(200);
        saveButton.setStyle("-fx-background-color: hotpink;" + "-fx-text-fill: white;");
        saveButton.setOnAction(e -> {
            String room = roomField.getText();
            String log = choresBox.getValue();
            if(room.isEmpty() || log == null) {
                message.setText("Please Fill all fields");
            } else {
                String fullLog = "Room " + room + " → " + log;
                logList.getItems().add(fullLog);
                message.setText("Housekeeping task saved");
                roomField.clear();
                choresBox.setValue(null);
            }
        });
        Button backButton = new Button("Back");
        backButton.setStyle( "-fx-background-color: deeppink;" + "-fx-text-fill: white;");
        backButton.setOnAction(e -> {Dashboard dashboard = new Dashboard();
            dashboard.show(stage);
        });
        VBox root = new VBox(15);
        root.setAlignment(Pos.CENTER);
        root.setStyle( "-fx-background-color: lavenderblush;" + "-fx-padding: 25px;");
        root.getChildren().addAll(
                title,
                roomField,
                choresBox,
                saveButton,
                logList,
                backButton,
                message
        );
        Scene scene = new Scene(root, 900, 800);
        stage.setTitle("Housekeeping");
        stage.setScene(scene);
        stage.show();
    }
}