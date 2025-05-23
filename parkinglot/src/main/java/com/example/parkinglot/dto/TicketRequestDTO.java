package com.example.parkinglot.dto;

import com.example.parkinglot.model.Vehicle;

public class TicketRequestDTO {
    
    private Vehicle vehicle;
    private Long gateId;
    private Long parkingLotId;
    public TicketRequestDTO(Vehicle vehicle, Long gateId, Long parkingLotId) {
        this.vehicle = vehicle;
        this.gateId = gateId;
        this.parkingLotId = parkingLotId;
    }
    public Vehicle getVehicle() {
        return vehicle;
    }
    public Long getGateId() {
        return gateId;
    }
    public Long getParkingLotId() {
        return parkingLotId;
    }

    
    
}
