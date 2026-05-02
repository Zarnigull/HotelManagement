package org.example;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class GuestRequestPage {
    public void show(Stage stage) {
        Label title = new Label("Guest Requests");
        TextField roomField = new TextField();
        roomField.setPromptText("Room Number");

        ComboBox<String> requestBox = new ComboBox<>();
        requestBox.getItems().addAll(
                "Breakfast Delivery",
                "Extra Towels",
                "Room Cleaning",
                "Late Checkout",
                "Extra Pillow",
                "Water Bottles",
                "Laundry Service",
                "Mini Bar Refill"
        );
        requestBox.setPromptText("Select Request");
        Button sendButton = new Button("Send Request");
        Label message = new Label();
        ListView<String> requestList = new ListView<>();
        requestList.setPrefHeight(200);
        sendButton.setStyle("-fx-background-color: hotpink;" + "-fx-text-fill: white;"
        );
        sendButton.setOnAction(e -> {
            try {
                int room = Integer.parseInt(roomField.getText());
                String request = requestBox.getValue();
                if(request == null) {
                    message.setText("Please select a request");
                    return;
                }
                String entry = "Room " + room + " requested: " + request;
                requestList.getItems().add(entry);
                message.setText("Request sent successfully");
                roomField.clear();
                requestBox.setValue(null);
            } catch (Exception ex) {
                message.setText("Enter valid room number");
            }
        });
        Button backButton = new Button("Back");
        backButton.setStyle("-fx-background-color: deeppink;" + "-fx-text-fill: white;"
        );
        backButton.setOnAction(e -> {
            Dashboard dashboard = new Dashboard();
            dashboard.show(stage);
        });
        VBox root = new VBox(15);
        root.setAlignment(Pos.CENTER);
        root.setStyle("-fx-background-color: lavenderblush;" + "-fx-padding: 25px;"
        );
        root.getChildren().addAll(
                title,
                roomField,
                requestBox,
                sendButton,
                requestList,
                backButton,
                message
        );
        Scene scene = new Scene(root, 900, 750);
        stage.setTitle("Guest Requests");
        stage.setScene(scene);
        stage.show();
    }
}