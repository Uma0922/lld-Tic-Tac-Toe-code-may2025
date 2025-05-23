package com.example.parkinglot.controller;

import com.example.parkinglot.dto.ParkingLotInputDTO;
import com.example.parkinglot.dto.ParkingLotResponseDTO;
import com.example.parkinglot.service.iParkingLotService;

public class ParkingLotController {
    
    private iParkingLotService service;
    

    public ParkingLotController(iParkingLotService service) {
        this.service = service;
    }

    public void createParkingLot(ParkingLotInputDTO dto){
        // Validate for the Input.
        service.createParkingLot(dto);
    }

    public ParkingLotResponseDTO getParkingLotById(Long id){
        // Validate for the Id.
        ParkingLotResponseDTO response = service.getParkingLotById(id);
        return response;
    }


}
