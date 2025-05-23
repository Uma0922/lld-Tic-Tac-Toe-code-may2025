package com.example.parkinglot.controller;

import com.example.parkinglot.dto.TicketRequestDTO;
import com.example.parkinglot.exception.ParkingLotException;
import com.example.parkinglot.model.Ticket;
import com.example.parkinglot.service.iTicketService;

public class TicketController {
    private iTicketService ticketService;

    public TicketController(iTicketService ticketService) {
        this.ticketService = ticketService;
    }
     
    
    // This method will be creating a ticket, and assign a spot.
    public Ticket generateTicket(TicketRequestDTO dto){
        // Validat the input.
        Ticket ticket = null;
        try {
            ticket = ticketService.generateTicket(dto.getVehicle(), 
            dto.getGateId(), dto.getParkingLotId());
        } catch (ParkingLotException e) {
            System.out.println("Error while creating the ticket: " + e.getCause());
        }
        return ticket;
    }



}
