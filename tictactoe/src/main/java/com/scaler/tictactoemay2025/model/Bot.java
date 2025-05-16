package com.scaler.tictactoemay2025.model;

public class Bot extends Player {
    private DifficultyLevel botDifficultyLevel;
    
    public Bot(Long id, String name, String symbol, PlayerType type, DifficultyLevel difficultyLevel) {
        super(id, name, symbol, type);
        this.botDifficultyLevel = difficultyLevel;
    }

    

    public DifficultyLevel getBotDifficultyLevel() {
        return botDifficultyLevel;
    }
    public void setBotDifficultyLevel(DifficultyLevel botDifficultyLevel) {
        this.botDifficultyLevel = botDifficultyLevel;
    }
    
    
}
