package com.scaler.bms2025.bmsmay2025.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.scaler.bms2025.bmsmay2025.service.BookingServiceImpl;
import com.scaler.bms2025.dto.BookingResponseDTO;
import com.scaler.bms2025.dto.CreateBookingRequestDTO;
import com.scaler.bms2025.dto.UpdateBookingRequestDTO;

@RestController
public class BookingController {
    private BookingServiceImpl bookingService;

    @Autowired
    public BookingController(BookingServiceImpl bookingService) {
        this.bookingService = bookingService;
    }

    @GetMapping("/bookings")
    public BookingResponseDTO getAllBookings(){
        return null;
    }

    @PostMapping("/bookings")
    public void createBooking(@RequestBody CreateBookingRequestDTO dto){
        /**
         * S1. Validate the Input.
         * S2. Pass the values to Service Layer.
         * S3. Fetch the Booking created by Service
         * S4. Return response.
         */
    }

    @PutMapping("/booking")
    public void updateBooking(@RequestBody UpdateBookingRequestDTO updateBookingRequestDTO){

    }
    
}
