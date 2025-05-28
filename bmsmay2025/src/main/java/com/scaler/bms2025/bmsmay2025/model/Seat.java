package com.scaler.bms2025.bmsmay2025.model;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Seat extends BaseModel {
    private String seatNo;
    @ManyToOne
    private SeatType type;
    private int rowVal;
    private int colVal;
}
