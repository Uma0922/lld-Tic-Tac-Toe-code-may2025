package com.example.parkinglot.model;

import java.util.ArrayList;
import java.util.List;

public class ParkingFloor extends BaseModel {
    private List<ParkingSpot> parkingSpots;
    private int floorNumber;

    public ParkingFloor(int floorNo , List<ParkingSpot> spots){
        this.parkingSpots = spots;
        floorNumber = floorNo;
    }

    public List<ParkingSpot> getParkingSpots() {
        return parkingSpots;
    }

    public void setParkingSpots(List<ParkingSpot> parkingSpots) {
        this.parkingSpots = parkingSpots;
    }

    public int getFloorNumber() {
        return floorNumber;
    }

    public void setFloorNumber(int floorNumber) {
        this.floorNumber = floorNumber;
    }
}
