package com.scaler.tictactoemay2025.model;

import java.util.Scanner;

public class Player {
    private String symbol;
    private String name;
    private Long id;
    private PlayerType playerType;
    private Scanner scanner; // WE WILL DISCUSS THIS. 

    public Player(Long id, String name, String symbol, PlayerType type) {
        this.id = id;
        this.playerType = type;
        this.name = name;
        this.symbol = symbol;
        this.scanner = new Scanner(System.in);
    }

    
    public String getSymbol() {
        return symbol;
    }
    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public PlayerType getPlayerType() {
        return playerType;
    }
    public void setPlayerType(PlayerType playerType) {
        this.playerType = playerType;
    }
    public Scanner getScanner() {
        return scanner;
    }
    public void setScanner(Scanner scanner) {
        this.scanner = scanner;
    }
    

    
}
