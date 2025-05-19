package com.scaler.tictactoemay2025.controller;

import java.util.List;

import com.scaler.tictactoemay2025.exception.DuplicateSymbolException;
import com.scaler.tictactoemay2025.exception.PlayerValidationException;
import com.scaler.tictactoemay2025.model.Game;
import com.scaler.tictactoemay2025.model.GameState;
import com.scaler.tictactoemay2025.model.Player;
import com.scaler.tictactoemay2025.strategies.WinningStrategy;

public class GameController {
    
    // This method is the entry point for creating a game. 
    public Game startGame(int dimention, List<Player> players, List<WinningStrategy> winningSt) throws DuplicateSymbolException, PlayerValidationException{
        return Game.getBuilder()
        .setDimension(dimention)
        .setPlayers(players)
        .setWinningStrategies(winningSt)
        .build();
    }

    public GameState getState(Game game){
        return game.getGameState();
    }

    public void makeMove(Game game){
        game.makeMove();
    }

    public void undo(Game game){
        game.undo();
    }

    public void printBoard(Game game){
        game.printBoard();
    }
}
