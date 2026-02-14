package com.java.HotelManagementSystem;

import jakarta.persistence.*;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Entity
@Table(name="booking_details")
public class Booking {
		
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int bookingId;
	
	@Column(nullable = false)
	String customerName;
	String roomType;
	String checkInDate;
	String checkOutDate;
	double totalAmount;
	
	
	public Booking() {
		super();
	}


    public Booking(String customerName, String roomType,
            String checkInDate, String checkOutDate) {
    	this.customerName = customerName;
    	this.roomType = roomType;
    	this.checkInDate = checkInDate;
    	this.checkOutDate = checkOutDate;
    	this.totalAmount = calculateTotalAmount();
    }

    private double calculateTotalAmount() {

    	double price = 0;

    	if (roomType.equalsIgnoreCase("Standard"))
    		price = 2000;
    	else if (roomType.equalsIgnoreCase("Deluxe"))
    		price = 3500;
    	else if (roomType.equalsIgnoreCase("Suite"))
    		price = 5000;
    	else
    		throw new IllegalArgumentException("Invalid Room Type");

    	LocalDate in = LocalDate.parse(checkInDate);
    	LocalDate out = LocalDate.parse(checkOutDate);

    	long days = ChronoUnit.DAYS.between(in, out);

    	return days * price;
}



	public int getBookingId() {
		return bookingId;
	}


	public void setBookingId(int bookingId) {
		this.bookingId = bookingId;
	}


	public String getCustomerName() {
		return customerName;
	}


	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}


	public String getRoomType() {
		return roomType;
	}


	public void setRoomType(String roomType) {
		this.roomType = roomType;
	}


	public String getCheckInDate() {
		return checkInDate;
	}


	public void setCheckInDate(String checkInDate) {
		this.checkInDate = checkInDate;
	}


	public String getCheckOutDate() {
		return checkOutDate;
	}


	public void setCheckOutDate(String checkOutDate) {
		this.checkOutDate = checkOutDate;
	}


	public double getTotalAmount() {
		return totalAmount;
	}


	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}


	
	
	
	
}
