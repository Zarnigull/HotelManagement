package org.example;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class AddRoomPage {
    private double getRoomPrice(String roomType) {
        switch (roomType) {
            case "Standard": return 50.0;
            case "Deluxe": return 80.0;
            case "Family Suite": return 120.0;
            case "VIP": return 200.0;
            case "Queen Suite": return 250.0;
            default: return 0;
        }
    }
    public void show(Stage stage) {
        Label title = new Label("Add New Room");
        Label info = new Label("100-199 Standard ($50) | " +
                "200-299 Deluxe ($80)| " +
                "300-399 Family Suite ($120) | " +
                "400-499 VIP ($200)| " +
                "500-599 Queen Suite ($250)"
        );
        TextField roomNumberField = new TextField();
        roomNumberField.setPromptText("Room Number");

        Button addButton = new Button("Add Room");
        Label message = new Label();
        addButton.setStyle("-fx-background-color: hotpink;" + "-fx-text-fill: white;"
        );
        addButton.setOnAction(e -> {
            try {
                int roomNumber = Integer.parseInt(roomNumberField.getText());
                String roomType;
                if (roomNumber >= 100 && roomNumber <= 199) {
                    roomType = "Standard";
                } else if (roomNumber >= 200 && roomNumber <= 299) {
                    roomType = "Deluxe";
                } else if (roomNumber >= 300 && roomNumber <= 399) {
                    roomType = "Family Suite";
                } else if (roomNumber >= 400 && roomNumber <= 499) {
                    roomType = "VIP";
                } else if (roomNumber >= 500 && roomNumber <= 599) {
                    roomType = "Queen Suite";
                } else {
                    message.setText("Invalid room number range");
                    return;
                }
                double price = getRoomPrice(roomType);
                Room room = new Room(roomNumber, roomType, price, true);
                DataStore.rooms.add(room);
                message.setText("Room Added Successfully");
                roomNumberField.clear();
            } catch (NumberFormatException ex) {
                message.setText("Please enter valid number");
            }
        });
        Button backButton = new Button("Back");
        backButton.setStyle( "-fx-background-color: deeppink;" + "-fx-text-fill: white;");
        backButton.setOnAction(e -> {Dashboard dashboard = new Dashboard();
            dashboard.show(stage);
        });
        VBox root = new VBox(15);
        root.setAlignment(Pos.CENTER);
        root.setStyle("-fx-background-color: mistyrose;" + "-fx-padding: 30px;");
        root.getChildren().addAll(
                title,
                info,
                roomNumberField,
                addButton,
                backButton,
                message
        );
        Scene scene = new Scene(root, 900, 800);
        stage.setTitle("Add Room");
        stage.setScene(scene);
        stage.show();
    }
}