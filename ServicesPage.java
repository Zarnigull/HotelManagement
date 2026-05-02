package org.example;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ServicesPage {
    public void show(Stage stage) {
        Label title = new Label("Hotel Services");
        TextField roomField = new TextField();
        roomField.setPromptText("Room Number");

        CheckBox wifi = new CheckBox("WiFi ($10)");
        CheckBox food = new CheckBox("Room Service Food ($25)");
        CheckBox laundry = new CheckBox("Laundry ($15)");
        CheckBox spa = new CheckBox("Spa ($50)");
        CheckBox gym = new CheckBox("Gym Access ($20)");

        Button calculateButton = new Button("Calculate Bill");
        Label result = new Label();
        calculateButton.setStyle("-fx-background-color: hotpink;" + "-fx-text-fill: white;");

        calculateButton.setOnAction(e -> {
            String room = roomField.getText();
            if(room.isEmpty()) {result.setText("Enter room number");
                return;
            }
            int total = 0;

            if(wifi.isSelected()) total += 10;
            if(food.isSelected()) total += 25;
            if(laundry.isSelected()) total += 15;
            if(spa.isSelected()) total += 50;
            if(gym.isSelected()) total += 20;

            int roomNumber = Integer.parseInt(room);
            DataStore.serviceBills.put(roomNumber,total);

            result.setText("Room " + room + " Service Bill: $" + total);
        });
        Button backButton = new Button("Back");
        backButton.setStyle("-fx-background-color: deeppink;" + "-fx-text-fill: white;");
        backButton.setOnAction(e -> {Dashboard dashboard = new Dashboard();
            dashboard.show(stage);
        });
        VBox root = new VBox(12);
        root.setAlignment(Pos.CENTER);
        root.setStyle("-fx-background-color: lavenderblush;" + "-fx-padding: 25px;");
        root.getChildren().addAll(
                title,
                roomField,
                wifi,
                food,
                laundry,
                spa,
                gym,
                calculateButton,
                backButton,
                result
        );
        Scene scene = new Scene(root, 900, 850);
        stage.setTitle("Services");
        stage.setScene(scene);
        stage.show();
    }
}