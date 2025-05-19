package com.scaler.tictactoemay2025.strategies;

import java.util.HashMap;
import java.util.Map;

import com.scaler.tictactoemay2025.model.Board;
import com.scaler.tictactoemay2025.model.Move;

public class RowWinningStrategy implements WinningStrategy {
    // The HashMap to keep a Count.
    private Map<Integer, HashMap<String, Integer>> counts = new HashMap<>();
    // <RowNumber, Map<Symbol, Count>>
    // <0: <k,V> >
    // <1: {<X, 1>, <O:2>}>
    


    @Override
    public boolean checkWinner(Board board, Move move) {
        int rowNo = move.getCell().getRow();
        String symbol = move.getPlayer().getSymbol();

        if(!counts.containsKey(rowNo)){
            counts.put(rowNo, new HashMap<>());
        }

        Map<String,Integer> individualRowMap = counts.get(rowNo); // get from existing hashmap.

        if(!individualRowMap.containsKey(symbol)){
            individualRowMap.put(symbol, 0);
        }

        individualRowMap.put(symbol, individualRowMap.get(symbol)+1); // increamenting.

        // Win check. 
        if(individualRowMap.get(symbol).equals(board.getSize())){
            return true;
        }
        return false;
    }



    @Override
    public void handleUndo(Board board, Move move) {}
    
}
