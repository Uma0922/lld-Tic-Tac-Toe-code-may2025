package com.scaler.bms2025.bmsmay2025.model;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class ShowSeat extends BaseModel {
    @ManyToOne
    private Show show;
    
    @ManyToOne
    private Seat seat;
    
    @Enumerated(EnumType.ORDINAL)
    private ShowSeatStatus ShowSeatStatus;

    private double price;

    private Date seatBlockedAt;
}
