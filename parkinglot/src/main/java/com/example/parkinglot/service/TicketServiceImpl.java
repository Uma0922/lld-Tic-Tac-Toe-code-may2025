package com.example.parkinglot.service;

import java.util.Optional;

import com.example.parkinglot.exception.ParkingLotException;
import com.example.parkinglot.model.Gate;
import com.example.parkinglot.model.ParkingLot;
import com.example.parkinglot.model.ParkingSpot;
import com.example.parkinglot.model.ParkingSpotStatus;
import com.example.parkinglot.model.Ticket;
import com.example.parkinglot.model.Vehicle;
import com.example.parkinglot.repository.GateRepo;
import com.example.parkinglot.repository.ParkingLotRepo;
import com.example.parkinglot.repository.TicketRepo;

public class TicketServiceImpl implements iTicketService {
    private TicketRepo ticketRepo;
    private ParkingLotRepo parkingLotRepo;
    private GateRepo gateRepo;
    private iSpotAssignmentStrategy iSpotAssignmentStrategy;



    public TicketServiceImpl(TicketRepo ticketRepo, ParkingLotRepo parkingLotRepo, GateRepo gateRepo,
    iSpotAssignmentStrategy iSpotAssignmentStrategy) {
        this.ticketRepo = ticketRepo;
        this.parkingLotRepo = parkingLotRepo;
        this.gateRepo = gateRepo;
        this.iSpotAssignmentStrategy = iSpotAssignmentStrategy;
    }




    @Override
    public Ticket generateTicket(Vehicle vehicle, Long gateId, Long parkinLotId) throws ParkingLotException {
        /**
         * D S1. check whether ParkingLot exists or Not from repo
         * D S2. Find the gate from gateId, and get the operator from the gate.
         * S3. Now you will have to check for the parking spot. -- Here you will have to strategy.
         * S4. Generate Ticket
         * S5. Save it in DB.
         */
        
        Optional<ParkingLot> parkingLotOptional = parkingLotRepo.getById(parkinLotId);
         // Optional
        if(parkingLotOptional.isEmpty()){
            throw new ParkingLotException("Parking lot not found!");
        }
        

        Optional<Gate> gateOptional = gateRepo.getById(gateId);
        if(gateOptional.isEmpty()){
            throw new ParkingLotException("Gate does not exist!");
        }

        ParkingLot parkingLot = parkingLotOptional.get();
        Gate gate = gateOptional.get();
        
        // Find the parking spot.
        Optional<ParkingSpot> availParkingSpotOptional = iSpotAssignmentStrategy.
        findParkingSpot(vehicle.getVehicleType(), parkingLot);
        
        if(availParkingSpotOptional.isEmpty()){
            throw new ParkingLotException("No Parking Spot available!");
        }
        
        availParkingSpotOptional.get().setParkingSpotStatus(ParkingSpotStatus.FILLED);
        // Update this spot in the Repo;

        Ticket ticket = new Ticket(vehicle, gate, 
        gate.getCurrentOperator(), availParkingSpotOptional.get());

        Optional<Ticket> generateTicket = ticketRepo.createTicket(ticket);
        if(generateTicket.isEmpty()){
            throw new ParkingLotException("Error while generating ticket!");
        }


        return generateTicket.get();
    }
    
}
