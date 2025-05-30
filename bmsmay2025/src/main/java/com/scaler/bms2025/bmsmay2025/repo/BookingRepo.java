package com.scaler.bms2025.bmsmay2025.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.scaler.bms2025.bmsmay2025.model.Booking;

@Repository
public interface BookingRepo extends JpaRepository<Booking, Long> {
 
    List<Booking> getAllBookings();
    Booking save(Booking booking);
    
}
