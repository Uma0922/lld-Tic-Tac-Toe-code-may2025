package com.example.parkinglot.service;

import com.example.parkinglot.exception.ParkingLotException;
import com.example.parkinglot.model.Ticket;
import com.example.parkinglot.model.Vehicle;

public interface iTicketService {

    Ticket generateTicket(Vehicle vehicle, Long gateId, Long parkinLotId) throws ParkingLotException;
    
}
