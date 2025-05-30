package com.scaler.bms2025.bmsmay2025.service;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.scaler.bms2025.bmsmay2025.exception.BookingCreationException;
import com.scaler.bms2025.bmsmay2025.model.Booking;
import com.scaler.bms2025.bmsmay2025.model.BookingStatus;
import com.scaler.bms2025.bmsmay2025.model.Payment;
import com.scaler.bms2025.bmsmay2025.model.Show;
import com.scaler.bms2025.bmsmay2025.model.ShowSeat;
import com.scaler.bms2025.bmsmay2025.model.ShowSeatStatus;
import com.scaler.bms2025.bmsmay2025.model.User;
import com.scaler.bms2025.bmsmay2025.repo.BookingRepo;
import com.scaler.bms2025.bmsmay2025.repo.ShowRepo;
import com.scaler.bms2025.bmsmay2025.repo.ShowSeatRepo;
import com.scaler.bms2025.bmsmay2025.repo.UserRepo;

@Service
public class BookingServiceImpl {
    private BookingRepo bookingRepo;
    private ShowSeatRepo showSeatRepo;
    private ShowRepo showRepo;
    private UserRepo userRepo;
    

    @Autowired
    public BookingServiceImpl(UserRepo userRepo, BookingRepo bookingRepo, ShowSeatRepo showSeatRepo, ShowRepo showRepo) {
        this.bookingRepo = bookingRepo;
        this.showSeatRepo = showSeatRepo;
        this.showRepo = showRepo;
        this.userRepo = userRepo;
    }



    @Transactional(isolation = Isolation.SERIALIZABLE)
    public Booking initiateBooking(List<Long> seatIds, long showId, long userId) throws BookingCreationException {
        Optional<User> userOptional =  userRepo.findById(userId);
        if(!userOptional.isPresent()){
            throw new BookingCreationException("UserId does not exist");
        }
        Optional<Show> showOptional =  showRepo.findById(showId);
        if(!showOptional.isPresent()){
            throw new BookingCreationException("ShowId does not exist");
        }

        User user = userOptional.get();
        Show show = showOptional.get();

        // How would you take the lock?
        Booking booking;
        {
            List<ShowSeat> showSeats =  showSeatRepo.findAllByShowAndSeat(showId, seatIds);
            if(showSeats.isEmpty()){
                throw new BookingCreationException("No show seats exist.");
            }
            
            for(ShowSeat seat : showSeats){
                if(!seat.getShowSeatStatus().equals(ShowSeatStatus.AVAILABLE)){
                    throw new BookingCreationException("Seats are not available.");
                }
            }

            // now: we have to block seats for the user.
            List<ShowSeat> savedSeats = new ArrayList<>();
            for(ShowSeat showSeat : showSeats){
                showSeat.setShowSeatStatus(ShowSeatStatus.BLOCKED);
                showSeat.setSeatBlockedAt(new Date());
                showSeatRepo.update(showSeat); // Updating the seat status. [1,2,3 == BLOCKED]
                savedSeats.add(showSeat);
            }

            booking = new Booking();
            booking.setBookingCreatedAt(new Date());
            booking.setBookingStatus(BookingStatus.IN_PROGRESS);
            booking.setPayment(new Payment()); // that should be fine. 
            booking.setTotalAmount(calculateTotalAmount(showSeats));
            booking.setUserCreatedBy(user);
            booking.setShowSeats(savedSeats);
            booking.setShow(show);

             booking = bookingRepo.save(booking); // FAILS
    }
        // release a lock once booking is created.
        return booking;
    }


    private Double calculateTotalAmount(List<ShowSeat> showSeats) {
        Double price = 0.0;
        for(ShowSeat ss : showSeats){
            price += ss.getPrice();
        }
        price = price*showSeats.size();
        return price;
    }
    
}
