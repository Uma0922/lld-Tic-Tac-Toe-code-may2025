package com.scaler.tictactoemay2025.strategies;

import java.util.HashMap;
import java.util.Map;

import com.scaler.tictactoemay2025.model.Board;
import com.scaler.tictactoemay2025.model.Move;

public class ColWinningStrategy implements WinningStrategy {
    private Map<Integer, HashMap<String, Integer>> counts = new HashMap<>();
    // | 0 | -> {X -> 0; O -> 0}
    // | 1 | -> {X -> 0; O -> 0}
    // | 2 |
    // | 3 |

    @Override
    public boolean checkWinner(Board board, Move move) {
        int colNo = move.getCell().getCol();
        String symbol = move.getPlayer().getSymbol();

        if(!counts.containsKey(colNo)){
            counts.put(colNo, new HashMap<>());
        }

        Map<String,Integer> individualColMap = counts.get(colNo); // get from existing hashmap.

        if(!individualColMap.containsKey(symbol)){
            individualColMap.put(symbol, 0);
        }

        individualColMap.put(symbol, individualColMap.get(symbol)+1); // increamenting.

        // Win check. 
        if(individualColMap.get(symbol).equals(board.getSize())){
            return true;
        }
        return false;
    }

  
    @Override
    public void handleUndo(Board board, Move move) {
        int colNo = move.getCell().getCol();
        String symbol = move.getPlayer().getSymbol();

        Map<String,Integer> individualColMap = counts.get(colNo);
        individualColMap.put(symbol, individualColMap.get(symbol)-1);
    }
    
}
