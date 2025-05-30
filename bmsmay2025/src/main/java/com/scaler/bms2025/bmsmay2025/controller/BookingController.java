package com.scaler.bms2025.bmsmay2025.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.scaler.bms2025.bmsmay2025.model.Booking;
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
        validateRequestDTO(dto);
        Booking createdBooking =  bookingService.initiateBooking(dto.getSeatIds(), dto.getShowId(), dto.getUserId());
        // convert to dto and return. 
    }

    private void validateRequestDTO(CreateBookingRequestDTO dto) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'validateRequestDTO'");
    }

    @PutMapping("/booking")
    public void updateBooking(@RequestBody UpdateBookingRequestDTO updateBookingRequestDTO){

    }
    
}
