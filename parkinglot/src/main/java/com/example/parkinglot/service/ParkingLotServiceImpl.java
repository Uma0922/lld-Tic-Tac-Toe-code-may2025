package com.example.parkinglot.service;

import java.util.Optional;

import com.example.parkinglot.dto.ParkingLotInputDTO;
import com.example.parkinglot.dto.ParkingLotResponseDTO;
import com.example.parkinglot.model.ParkingLot;
import com.example.parkinglot.repository.ParkingLotRepo;

public class ParkingLotServiceImpl implements iParkingLotService {

    private ParkingLotRepo repo;
    

    public ParkingLotServiceImpl(ParkingLotRepo repo) {
        this.repo = repo;
    }

    @Override
    public void createParkingLot(ParkingLotInputDTO dto) {

    }

    @Override
    public ParkingLotResponseDTO getParkingLotById(Long id) {
        Optional<ParkingLot> parkingLot = repo.getById(id);
        return convertToDTO(parkingLot);
    }

    private ParkingLotResponseDTO convertToDTO(Optional<ParkingLot> parkingLot) {
        return new ParkingLotResponseDTO();
    }
    
}
