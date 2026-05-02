package org.example;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class CheckOutPage {
    public void show(Stage stage) {
        Label title = new Label("Check Out / Cancel Booking");
        TextField roomField = new TextField();
        roomField.setPromptText("Room Number");
        Button checkoutBtn = new Button("Check Out");
        Button cancelBtn = new Button("Cancel Booking");
        Label message = new Label();
        checkoutBtn.setStyle("-fx-background-color: hotpink;" + "-fx-text-fill: white;");
        cancelBtn.setStyle("-fx-background-color: crimson;" + "-fx-text-fill: white;");

        checkoutBtn.setOnAction(e -> {
            try {
                int roomNumber = Integer.parseInt(roomField.getText());
                boolean found = false;
                for (Room room : DataStore.rooms) {
                    if (room.getRoomNumber() == roomNumber) {
                        found = true;
                        if (room.isAvailable()) {
                            message.setText("Room already empty");
                            return;
                        }
                        room.setAvailable(true);
                        Booking target = null;
                        for (Booking b : DataStore.bookings) {
                            if (b.getRoomNumber() == roomNumber) {
                                target = b;
                                break;
                            }
                        }
                        if (target != null) {
                            DataStore.bookings.remove(target);
                        }
                        DataStore.checkoutHistory.add(
                                "Room " + roomNumber + " | Checked Out Successfully"
                        );
                        message.setText("Checked Out Successfully");
                        break;
                    }
                }
                if (!found) {
                    message.setText("Room not found");
                }
            } catch (Exception ex) {
                message.setText("Enter valid room number");
            }
        });


        cancelBtn.setOnAction(e -> {
            try {
                int roomNumber = Integer.parseInt(roomField.getText());
                boolean found = false;
                for (int i = 0; i < DataStore.bookings.size(); i++) {
                    Booking booking = DataStore.bookings.get(i);
                    if (booking.getRoomNumber() == roomNumber) {
                        found = true;
                        LocalDate today = LocalDate.now();
                        LocalDate checkInDate = booking.getCheckInDate();
                        long daysLeft = ChronoUnit.DAYS.between(today, checkInDate);
                        String refundMessage;
                        if (daysLeft > 1) {
                            refundMessage = "FULL REFUND APPROVED";
                        } else {
                            refundMessage = "NO REFUND (Less than 24h)";
                        }
                        DataStore.checkoutHistory.add( booking.getCustomerName() + " | Room " + roomNumber + " | Cancelled | " + refundMessage);
                        DataStore.bookings.remove(i);
                        for (Room room : DataStore.rooms) {
                            if (room.getRoomNumber() == roomNumber) {
                                room.setAvailable(true);
                                break;
                            }
                        }
                        message.setText("Booking Cancelled\n" + refundMessage);
                        break;
                    }
                }
                if (!found) {
                    message.setText("Booking not found");
                }
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
        root.setStyle("-fx-background-color: mistyrose;" + "-fx-padding: 30px;");
        root.getChildren().addAll(title,
                roomField,
                checkoutBtn,
                cancelBtn,
                backButton,
                message
        );
        Scene scene = new Scene(root, 900, 800);
        stage.setTitle("Check Out System");
        stage.setScene(scene);
        stage.show();
    }
}
