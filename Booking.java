package org.example;

import java.time.LocalDate;

public class Booking {

    private String customerName;
    private int roomNumber;
    private int days;
    private double totalBill;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;

    public Booking(String customerName, int roomNumber, int days, double totalBill, LocalDate checkInDate) {
        this.customerName = customerName;
        this.roomNumber = roomNumber;
        this.days = days;
        this.totalBill = totalBill;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkInDate.plusDays(days);
    }
    public String getCustomerName() {
        return customerName;
    }
    public int getRoomNumber() {
        return roomNumber;
    }
    public int getDays() {
        return days;
    }
    public double getTotalBill() {
        return totalBill;
    }
    public LocalDate getCheckInDate() {
        return checkInDate;
    }
    public LocalDate getCheckOutDate(){
        return checkOutDate;
    }
    @Override
    public String toString() {
        return customerName + " | Room " + roomNumber + " | " + days + " day(s)" + " | $" + totalBill + " | Check-In: " + checkInDate;
    }
}