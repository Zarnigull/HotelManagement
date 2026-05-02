package org.example;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ViewRoomsPage {
    public void show(Stage stage) {
        Label title = new Label("All Rooms");
        ListView<String> roomList = new ListView<>();
        for(Room room : DataStore.rooms) {
            String status;
            if(room.isAvailable()) {
                status = "Available";
            } else {
                status = "Booked";
            }
            roomList.getItems().add(
                    "Room " + room.getRoomNumber() +
                            " | " + room.getRoomType() +
                            " | $" + room.getPrice() +
                            " | " + status
            );
        }
        Button refreshButton = new Button("Refresh");
        refreshButton.setStyle( "-fx-background-color: hotpink;" +  "-fx-text-fill: white;");
        refreshButton.setOnAction(e -> {
            roomList.getItems().clear();
            for(Room room : DataStore.rooms) {
                roomList.getItems().add(room.toString());
            }
        });
        Button backButton = new Button("Back");
        backButton.setStyle( "-fx-background-color: deeppink;" +  "-fx-text-fill: white;");
        backButton.setOnAction(e -> {
            Dashboard dashboard = new Dashboard();
            dashboard.show(stage);
        });
        VBox root = new VBox(15);
        root.setAlignment(Pos.CENTER);
        root.setStyle("-fx-background-color: lightpink;" + "-fx-padding: 20px;");
        roomList.setPrefHeight(300);
        root.getChildren().addAll(
                title,
                roomList,
                refreshButton,
                backButton
        );
        Scene scene = new Scene(root, 900, 800);
        stage.setTitle("View Rooms");
        stage.setScene(scene);
        stage.show();
    }
}