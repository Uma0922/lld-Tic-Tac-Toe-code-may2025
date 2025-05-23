package com.example.parkinglot;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.parkinglot.controller.TicketController;
import com.example.parkinglot.dto.TicketRequestDTO;
import com.example.parkinglot.model.FeesCalculatorStrategyType;
import com.example.parkinglot.model.Gate;
import com.example.parkinglot.model.GateType;
import com.example.parkinglot.model.Operator;
import com.example.parkinglot.model.ParkingFloor;
import com.example.parkinglot.model.ParkingLot;
import com.example.parkinglot.model.ParkingLotStatus;
import com.example.parkinglot.model.ParkingSpot;
import com.example.parkinglot.model.SpotAssignmentStrategyType;
import com.example.parkinglot.model.Ticket;
import com.example.parkinglot.model.Vehicle;
import com.example.parkinglot.model.VehicleType;
import com.example.parkinglot.repository.GateRepo;
import com.example.parkinglot.repository.ParkingLotRepo;
import com.example.parkinglot.repository.TicketRepo;
import com.example.parkinglot.service.RandomSpotAssignmentStrategy;
import com.example.parkinglot.service.TicketServiceImpl;
import com.example.parkinglot.service.iSpotAssignmentStrategy;

public class Main {
    public static void main(String[] args) {
        Map<Long, ParkingLot> parkingLotMap = new HashMap<>();
        parkingLotMap.put(1L, createNewParkingLot());

        Map<Long, Gate> gateRepoMap = new HashMap();
        gateRepoMap.put(1L, createNewGate());

        TicketRepo ticketRepo = new TicketRepo(new HashMap<>());
        ParkingLotRepo parkingLotRepo = new ParkingLotRepo(parkingLotMap);
        
        GateRepo gateRepo = new GateRepo(gateRepoMap);



        iSpotAssignmentStrategy strategy = new RandomSpotAssignmentStrategy();


        TicketServiceImpl ticketService = new TicketServiceImpl(ticketRepo,
         parkingLotRepo, gateRepo, strategy);

        TicketController ticketController = new TicketController(ticketService);

        TicketRequestDTO ticketRequestDTO = generateTicket();
        Ticket response = ticketController.generateTicket(ticketRequestDTO);

        System.out.println("Ticket Response: " + response.getId() + " spot: "+ response.getAssignedSpot());
    }

    private static ParkingLot createNewParkingLot() {
        List<ParkingSpot> spots = Arrays.asList(new ParkingSpot(1));
        List<ParkingFloor> parkingFloors = Arrays.asList(new ParkingFloor(1, spots));
        List<Gate> gates = Arrays.asList(createNewGate());

        return new ParkingLot(parkingFloors, gates, 
        Arrays.asList(VehicleType.CAR), ParkingLotStatus.OPEN, SpotAssignmentStrategyType.NEAREST_SPOT, FeesCalculatorStrategyType.STRAIGHT_FARE);
    }

    private static Gate createNewGate() {
        return new Gate(GateType.ENTRY, 1, new Operator(100, " Yash"));
    }

    private static TicketRequestDTO generateTicket() {
        Vehicle vehicle  = new Vehicle("KA511234", VehicleType.CAR, "Yash");
        return new TicketRequestDTO(vehicle, 1L,1L);
    }
}