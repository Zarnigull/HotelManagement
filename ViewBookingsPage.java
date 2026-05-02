package org.example;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.time.LocalDate;
import javafx.scene.control.Alert;

public class ViewBookingsPage {
    public void show(Stage stage) {
        Label title = new Label("All Bookings");
        ListView<String> bookingList = new ListView<>();
        for(Booking booking : DataStore.bookings) {
            bookingList.getItems().add(booking.toString());
        }
        Button refreshButton = new Button("Refresh");
        refreshButton.setStyle("-fx-background-color: hotpink;" + "-fx-text-fill: white;");
        refreshButton.setOnAction(e -> {
            bookingList.getItems().clear();
            LocalDate today = LocalDate.now();
            for(Booking booking : DataStore.bookings) {
                bookingList.getItems().add(booking.toString());
                if(booking.getCheckInDate().minusDays(0).equals(today)) { //changed '1' to '0' for running
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Reminder");
                    alert.setHeaderText("Check-in Reminder");
                    alert.setContentText("Room " + booking.getRoomNumber() + " check-in is in 24 hours");
                    alert.show();
                }
                if(booking.getCheckOutDate().minusDays(1).equals(today)) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Reminder");
                    alert.setHeaderText("Check-out Reminder");
                    alert.setContentText("Room " + booking.getRoomNumber() + " check-out is in 24 hours"
                    );
                    alert.show();
                }
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
        root.setStyle("-fx-background-color: lightpink;" + "-fx-padding: 20px;"
        );
        bookingList.setPrefHeight(300);
        root.getChildren().addAll(
                title,
                bookingList,
                refreshButton,
                backButton
        );
        Scene scene = new Scene(root, 900, 800);
        stage.setTitle("Bookings");
        stage.setScene(scene);
        stage.show();
    }
}