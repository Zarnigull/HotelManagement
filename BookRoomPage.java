package org.example;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.time.LocalDate;

public class BookRoomPage {
    public void show(Stage stage) {
        Label title = new Label("Book Room");
        TextField customerField = new TextField();
        customerField.setPromptText("Customer Name");
        TextField roomField = new TextField();
        roomField.setPromptText("Room Number");
        TextField daysField = new TextField();
        daysField.setPromptText("Number of Days");
        DatePicker checkInPicker = new DatePicker();
        checkInPicker.setPromptText("Select Check-in Date");
        Button bookButton = new Button("Book");
        Label message = new Label();
        bookButton.setStyle("-fx-background-color: hotpink;" + "-fx-text-fill: white;");
        bookButton.setOnAction(e -> {
            try {
                String customer = customerField.getText();
                int roomNumber = Integer.parseInt(roomField.getText());
                int days = Integer.parseInt(daysField.getText());
                boolean found = false;
                for(Room room : DataStore.rooms) {
                    if(room.getRoomNumber() == roomNumber && room.isAvailable()) {
                        double totalBill = room.getPrice() * days;

                        LocalDate checkInDate = checkInPicker.getValue();
                        Booking booking = new Booking(customer, roomNumber, days, totalBill, checkInDate);
                        if(checkInDate == null) {
                            message.setText("Please select check-in date");
                            return;
                        }
                        DataStore.bookings.add(booking);
                        room.setAvailable(false);
                        message.setText("Room Booked!");
                        found = true;
                        break;
                    }
                }
                if(!found) {
                    message.setText("Room not available");
                }
            } catch (Exception ex) {
                message.setText("Enter valid data");
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
        root.setStyle("-fx-background-color: mistyrose;" + "-fx-padding: 30px;"
        );
        root.getChildren().addAll(
                title,
                customerField,
                roomField,
                daysField,
                checkInPicker,
                bookButton,
                backButton,
                message
        );
        Scene scene = new Scene(root, 900, 800);
        stage.setTitle("Book Room");
        stage.setScene(scene);
        stage.show();
    }
}