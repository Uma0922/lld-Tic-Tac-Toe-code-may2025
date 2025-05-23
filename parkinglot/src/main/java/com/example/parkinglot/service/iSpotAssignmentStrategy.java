package com.example.parkinglot.service;

import java.util.Optional;

import com.example.parkinglot.model.ParkingLot;
import com.example.parkinglot.model.ParkingSpot;
import com.example.parkinglot.model.VehicleType;

public interface iSpotAssignmentStrategy {
    Optional<ParkingSpot> findParkingSpot(VehicleType vehicleType, ParkingLot parkingLot);
}
