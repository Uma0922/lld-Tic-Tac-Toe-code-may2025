package com.scaler.bms2025.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor // 
public class CreateBookingRequestDTO {
    private long userId;
    private List<Long> seatIds;
    private long showId; 
}
