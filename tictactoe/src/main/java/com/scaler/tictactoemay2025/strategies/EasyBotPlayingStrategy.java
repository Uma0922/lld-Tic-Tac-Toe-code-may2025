package com.scaler.tictactoemay2025.strategies;

import java.util.List;

import com.scaler.tictactoemay2025.model.Board;
import com.scaler.tictactoemay2025.model.Cell;
import com.scaler.tictactoemay2025.model.CellState;
import com.scaler.tictactoemay2025.model.Move;
import com.scaler.tictactoemay2025.model.Player;

public class EasyBotPlayingStrategy implements BotPlayingStrategy {

    @Override
    public Move makeMove(Board board) {
        Move botMove  =  null;
        for(List<Cell> row : board.getBoard()){
            for(Cell c: row){
                if(c.getCellState().equals(CellState.EMPTY)){
                    botMove = new Move(c, null);
                }
            }
        }
        return null;
    }
    
}
