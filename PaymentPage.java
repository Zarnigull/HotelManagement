package org.example;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class PaymentPage {
    public void show(Stage stage) {
        Label title = new Label("Payment System");
        TextField customerField = new TextField();
        customerField.setPromptText("Customer Name");
        TextField roomField = new TextField();
        roomField.setPromptText("Room Number");
        Label amountLabel = new Label("Total: $0");
        Label message = new Label();

        ComboBox<String> paymentBox = new ComboBox<>();
        paymentBox.getItems().addAll("Cash", "Credit Card", "Check");
        paymentBox.setPromptText("Select Payment Method");
        ListView<String> paymentHistory = new ListView<>();
        paymentHistory.setPrefHeight(200);
        roomField.setOnAction(e -> {
            try {
                int roomNumber = Integer.parseInt(roomField.getText());
                double roomBill = 0;
                boolean found = false;
                for(Booking booking : DataStore.bookings) {
                    if(booking.getRoomNumber() == roomNumber) {
                        roomBill = booking.getTotalBill();
                        found = true;
                        break;
                    }
                }
                if(!found) {
                    message.setText("No booking found");
                    return;
                }
                int serviceBill = DataStore.serviceBills.getOrDefault(roomNumber, 0);
                double total = roomBill + serviceBill;
                amountLabel.setText("Total: $" + total);
            } catch (Exception ex) {
                message.setText("Enter valid room number");
            }
        });
        Button payButton = new Button("Pay Bill");
        payButton.setStyle("-fx-background-color: hotpink;" + "-fx-text-fill: white;"
        );
        payButton.setOnAction(e -> {
            try {
                String customer = customerField.getText();
                String paymentMethod = paymentBox.getValue();
                String totalText = amountLabel.getText().replace("Total: $", "");
                double amount = Double.parseDouble(totalText);
                if(customer.isEmpty()  paymentMethod == null  amount == 0) {
                    message.setText("Please fill all fields");
                    return;
                }
                String paymentInfo = customer + " paid $" + amount + " via " + paymentMethod;
                paymentHistory.getItems().add(paymentInfo);
                message.setText("Payment Successful");

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Receipt");
                alert.setHeaderText("Payment Completed");
                alert.setContentText("Customer: " + customer + "\nAmount Paid: $" + amount + "\nMethod: " + paymentMethod);
                alert.showAndWait();
                customerField.clear();
                roomField.clear();
                paymentBox.setValue(null);
                amountLabel.setText("Total: $0");
            } catch (Exception ex) {
                message.setText("Enter valid payment data");
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
                customerField,
                roomField,
                amountLabel,
                paymentBox,
                payButton,
                paymentHistory,
                backButton,
                message
        );
        Scene scene = new Scene(root, 900, 800);
        stage.setTitle("Payment Page");
        stage.setScene(scene);
        stage.show();
    }
}