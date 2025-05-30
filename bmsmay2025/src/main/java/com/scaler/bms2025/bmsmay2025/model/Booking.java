package com.scaler.bms2025.bmsmay2025.model;



import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.databind.JsonSerializable.Base;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class Booking extends BaseModel {
    
    @Enumerated(EnumType.ORDINAL)
    private BookingStatus bookingStatus; // Integer value of enum.
    
    @ManyToOne
    private User userCreatedBy;
    @ManyToOne
    private Show show;
    @OneToMany
    private List<ShowSeat>  showSeats;
    @OneToOne
    private Payment payment;

    private Date bookingCreatedAt;
    private Double totalAmount;
}
