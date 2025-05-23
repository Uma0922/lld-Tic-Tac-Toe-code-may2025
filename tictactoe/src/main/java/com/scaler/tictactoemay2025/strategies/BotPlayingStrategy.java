package com.scaler.tictactoemay2025.strategies;

import com.scaler.tictactoemay2025.model.Board;
import com.scaler.tictactoemay2025.model.Move;
import com.scaler.tictactoemay2025.model.Player;

public interface BotPlayingStrategy {
    
    Move makeMove(Board p);
}
