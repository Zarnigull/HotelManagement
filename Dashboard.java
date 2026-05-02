package org.example;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Dashboard {
    public void show(Stage stage) {
        Label title = new Label("Barbie Hotel Dashboard");
        Button addRoomButton = new Button("Add Room");
        Button bookRoomButton = new Button("Book Room");
        Button cancelBookingButton = new Button("Cancel Booking");
        Button viewRoomsButton = new Button("View Rooms");
        Button housekeepingButton = new Button("Housekeeping");
        Button exitButton = new Button("Exit");
        Button viewBookingsButton = new Button("View Bookings");
        Button checkOutButton = new Button("Check Out");
        Button paymentButton = new Button("Payment");
        Button servicesButton = new Button("Services");
        Button requestButton = new Button("Guest Requests");

        addRoomButton.setStyle(buttonStyle());
        bookRoomButton.setStyle(buttonStyle());
        viewRoomsButton.setStyle(buttonStyle());
        housekeepingButton.setStyle(buttonStyle());
        exitButton.setStyle(buttonStyle());
        viewBookingsButton.setStyle(buttonStyle());
        checkOutButton.setStyle(buttonStyle());
        cancelBookingButton.setStyle(buttonStyle());
        paymentButton.setStyle(buttonStyle());
        servicesButton.setStyle(buttonStyle());
        requestButton.setStyle(buttonStyle());

        housekeepingButton.setOnAction(e-> { HousekeepingPage housekeepingPage = new HousekeepingPage();
        housekeepingPage.show(stage);
        });
        paymentButton.setOnAction(e -> { PaymentPage paymentPage = new PaymentPage();
            paymentPage.show(stage);
        });
        bookRoomButton.setOnAction(e -> { BookRoomPage bookRoomPage = new BookRoomPage();
            bookRoomPage.show(stage);
        });
        viewBookingsButton.setOnAction(e -> { ViewBookingsPage viewBookingsPage = new ViewBookingsPage();
            viewBookingsPage.show(stage);
        });
        checkOutButton.setOnAction(e -> { CheckOutPage checkOutPage = new CheckOutPage();
            checkOutPage.show(stage);
        });
        addRoomButton.setOnAction(e -> { AddRoomPage addRoomPage = new AddRoomPage();
            addRoomPage.show(stage);
        });
        viewRoomsButton.setOnAction(e -> { ViewRoomsPage viewRoomsPage = new ViewRoomsPage();
            viewRoomsPage.show(stage);
        });
        servicesButton.setOnAction(e -> {ServicesPage servicesPage = new ServicesPage();
            servicesPage.show(stage);
        });
        requestButton.setOnAction(e -> {GuestRequestPage page = new GuestRequestPage();
            page.show(stage);
        });
        exitButton.setOnAction(e -> {
            stage.close();
        });
        VBox root = new VBox(15);
        root.setAlignment(Pos.CENTER);
        root.setStyle( "-fx-background-color: lightpink;" + "-fx-padding: 30px;" );
        root.getChildren().addAll(
                title,
                addRoomButton,
                viewRoomsButton,
                bookRoomButton,
                viewBookingsButton,
                housekeepingButton,
                requestButton,
                servicesButton,
                paymentButton,
                checkOutButton,
                exitButton
                );
        Scene scene = new Scene(root, 900, 800);
        stage.setTitle("Dashboard");
        stage.setScene(scene);
        stage.show();
    }
    private String buttonStyle() {
        return "-fx-background-color: deeppink;" +
                "-fx-text-fill: white;" +
                "-fx-font-size: 14px;" +
                "-fx-pref-width: 200px;";
    }
}