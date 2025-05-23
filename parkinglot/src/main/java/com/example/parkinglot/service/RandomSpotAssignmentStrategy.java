package com.example.parkinglot.service;

import java.util.Optional;

import com.example.parkinglot.model.ParkingFloor;
import com.example.parkinglot.model.ParkingLot;
import com.example.parkinglot.model.ParkingSpot;
import com.example.parkinglot.model.ParkingSpotStatus;
import com.example.parkinglot.model.VehicleType;

public class RandomSpotAssignmentStrategy implements iSpotAssignmentStrategy {

    @Override
    public Optional<ParkingSpot> findParkingSpot(VehicleType vehicleType, ParkingLot parkingLot) {
        for(ParkingFloor floor : parkingLot.getParkingFloors()){
            for(ParkingSpot spot : floor.getParkingSpots()){
                if(spot.getParkingSpotStatus().equals(ParkingSpotStatus.EMPTY) 
                && spot.getSupportedVehicleTypes().contains(vehicleType)){
                    return Optional.of(spot);
                }
            }
        }
        // No Parking spot is available for that vehicle.
        return Optional.empty();
    }
    
}
