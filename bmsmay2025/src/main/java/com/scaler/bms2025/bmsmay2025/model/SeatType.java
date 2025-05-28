package com.scaler.bms2025.bmsmay2025.model;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class SeatType extends BaseModel {
    private String seatTypeName; // SILVER, GOLD, BALCONY
}
