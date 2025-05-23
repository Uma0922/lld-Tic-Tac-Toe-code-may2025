package com.example.parkinglot.service;

import com.example.parkinglot.dto.ParkingLotInputDTO;
import com.example.parkinglot.dto.ParkingLotResponseDTO;

public interface iParkingLotService {
    
    void createParkingLot(ParkingLotInputDTO dto);

    ParkingLotResponseDTO getParkingLotById(Long id);
}
