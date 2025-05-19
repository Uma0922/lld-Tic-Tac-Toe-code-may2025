package com.scaler.tictactoemay2025;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.scaler.tictactoemay2025.controller.GameController;
import com.scaler.tictactoemay2025.exception.DuplicateSymbolException;
import com.scaler.tictactoemay2025.model.Game;
import com.scaler.tictactoemay2025.model.GameState;
import com.scaler.tictactoemay2025.model.Player;
import com.scaler.tictactoemay2025.model.PlayerType;
import com.scaler.tictactoemay2025.strategies.ColWinningStrategy;
import com.scaler.tictactoemay2025.strategies.RowWinningStrategy;
import com.scaler.tictactoemay2025.strategies.WinningStrategy;

public class Main {
    public static void main(String[] args) throws DuplicateSymbolException {
        // S1. Set dimention
        // S2. Create Players.
        // S3. Add winningSt
        //S4. Create Game.

        try{
        int dimention = 3;
        List<Player> players = new ArrayList<>();
        players.add(new Player(1L, "Abhishek", "X", PlayerType.HUMAN));
        players.add(new Player(1L, "Naveen", "O", PlayerType.HUMAN));
        
        List<WinningStrategy> winningStrategies = new ArrayList<>();
        winningStrategies.add(new RowWinningStrategy());
        winningStrategies.add(new ColWinningStrategy());
        
        // Add code for adding winning strategies.


        GameController gameController = new GameController();
        // Now your game has been created.
        Game game = gameController.startGame(dimention, players, winningStrategies);
        
        System.out.println("Game is CREATED... " + game);
        
        Scanner scanner = new Scanner(System.in); // INPUT IN JAVA.
        
        while(gameController.getState(game).equals(GameState.IN_PROGRESS)){
            // UNTILL MY GAME IS RUNNING, I WILL HAVE TO TAKE INPUT.
            // S1. Print board and show to the user.
            gameController.printBoard(game);
            
            System.out.println("Does anyone wants UNDO? (y/n) ");
            String undoAns = scanner.next();
            
            if(undoAns.equals("y")){
                gameController.undo(game);
                continue;
            }
            // S2. 
            gameController.makeMove(game);
        }

        System.out.println("Game is finished...");
        GameState gameState = gameController.getState(game);

        if(gameState.equals(GameState.WIN)){
            System.out.println("Player : "+ game.getWinner() + " has won the game...");
        }else{
            System.out.println("it is a draw...");
        }
    }catch(Exception e){
        System.out.println("Exception : " + e);
    }
    }
}