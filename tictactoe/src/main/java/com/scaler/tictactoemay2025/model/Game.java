package com.scaler.tictactoemay2025.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.scaler.tictactoemay2025.exception.DuplicateSymbolException;
import com.scaler.tictactoemay2025.exception.PlayerValidationException;
import com.scaler.tictactoemay2025.strategies.WinningStrategy;

public class Game {
    private List<Player> players; // Y
    private Board board; // Y
    private List<Move> moves; // N
    private Player winner; // N
    private GameState gameState; // N
    private int nextMovePlayerIndex; // N
    private List<WinningStrategy> winningStrategies; // Y
    
    private Game(int dimension, List<Player> players, List<WinningStrategy> winningStrategies){
        this.board = new Board(dimension);
        this.players = players;
        this.winningStrategies = winningStrategies;
        this.moves = new ArrayList<>();
        this.winner = null;
        this.gameState = GameState.IN_PROGRESS;
    }

    public static Builder getBuilder(){
        return new Builder();
    }

    public static class Builder{
        private List<Player> players;
        private int dimension;
        private List<WinningStrategy> winningStrategies;

        private Builder(){
            this.dimension = 0;
            this.players = new ArrayList<>();
            this.winningStrategies = new ArrayList<>();
        }

        public Builder setPlayers(List<Player> players) {
            this.players = players;
            return this;
        }
        public Builder setDimension(int dimension) {
            this.dimension = dimension;
            return this;
        }
        public Builder setWinningStrategies(List<WinningStrategy> winningStrategies) {
            this.winningStrategies = winningStrategies;
            return this;
        }
       
        public Game build() throws DuplicateSymbolException, PlayerValidationException{
            runValidations();
            // This above method will throw exception if any validation fails.
            return new Game(dimension,players,winningStrategies);
        }

        private void runValidations() throws DuplicateSymbolException,PlayerValidationException  {
            validateForBotCount();
            validateSymbolForPlayer();
            // S3. Validate dimention -- (<3)
            // S4. Validate for number of Bots. (get player and get the type.)
        }

        private void validateForBotCount() throws PlayerValidationException {
            int botCount =0;
            for(Player p: players){
                if(p.getPlayerType().equals(PlayerType.BOT)){
                    botCount++;
                }
            }

            if(players.size() == botCount){
                throw new PlayerValidationException("All players are bots.");
            }
        }

        public void validateSymbolForPlayer() throws DuplicateSymbolException{
            Set<String> symbol = new HashSet<>();
            for(Player p: players){
                if(symbol.contains(p.getSymbol())) throw new DuplicateSymbolException("No duplicate symbols allowed.");

                // else add the symbol to set.
                symbol.add(p.getSymbol());
            }
        }



    }
    
    public List<Player> getPlayers() {
        return players;
    }
    public Board getBoard() {
        return board;
    }
    public List<Move> getMoves() {
        return moves;
    }
    public Player getWinner() {
        return winner;
    }
    public GameState getGameState() {
        return gameState;
    }
    public int getNextMovePlayerIndex() {
        return nextMovePlayerIndex;
    }
    public void setPlayers(List<Player> players) {
        this.players = players;
    }
    public void setBoard(Board board) {
        this.board = board;
    }
    public void setMoves(List<Move> moves) {
        this.moves = moves;
    }
    public void setWinner(Player winner) {
        this.winner = winner;
    }
    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }
    public void setNextMovePlayerIndex(int nextMovePlayerIndex) {
        this.nextMovePlayerIndex = nextMovePlayerIndex;
    }
    public List<WinningStrategy> getWinningStrategies() {
        return winningStrategies;
    }
    public void setWinningStrategies(List<WinningStrategy> winningStrategies) {
        this.winningStrategies = winningStrategies;
    }



    
    // method which is responsible for UNDO.
    public void undo() {
        if (moves.size() == 0) {
            System.out.println("No move to undo");
            return;
        }

        // WE ARE GOING AHEAD WITH WAY-1 FOR UNDO. EASY FOR THIS PROBLEM.
        /**
         * S1. Remove the last element from the moves
         * S2. Update the cell to empty state
         * S3. Update the winning strategy. 
         */
        Move lastMove = moves.get(moves.size() - 1);

        moves.remove(lastMove);

        Cell cell = lastMove.getCell();
        cell.setPlayer(null);
        cell.setCellState(CellState.EMPTY);

        for (WinningStrategy winningStrategy: winningStrategies) {
            winningStrategy.handleUndo(board, lastMove);
        }

        nextMovePlayerIndex -= 1;
        nextMovePlayerIndex = (nextMovePlayerIndex + players.size()) % players.size();
    }

    public void makeMove() {
        /**
         * STEPS:
         * 1. Get the Player. DONE
         * 2. Then you will take input from the player,. DONE
         * 3. Update symbol in particular cell.
         * 4. Update the cell state. D
         * 5. Store to the moves DONE
         * 6. Calculate next player
         * 7. Check winner.
         */

         Player currentPlayer  = players.get(nextMovePlayerIndex);
         System.out.println("It is Player: "+currentPlayer.getName() +" move right now.");
         Move currentMove = currentPlayer.makeMove(board);

         if(invalidMove(currentMove)){
            System.out.println("Invalid move by the player!");
            return;
         }
        
         int currentRow = currentMove.getCell().getRow();
         int currCol = currentMove.getCell().getCol();
         System.out.println("move is made in --> Row: " + currentRow + "col: " + currCol);
         
        Cell currCell =  board.getBoard().get(currentRow).get(currCol);
        currCell.setCellState(CellState.FILLED);
        currCell.setPlayer(currentPlayer);
        
        // Store in moves.
        Move newMove = new Move(currCell, currentPlayer);
        moves.add(newMove); // 
        
        // Calculate next player Index:
        nextMovePlayerIndex +=1;
        nextMovePlayerIndex %= players.size(); // (5/4=1)


        if(checkWinner(board, newMove)){
            gameState = GameState.WIN;
            winner = currentPlayer;
        }else if(moves.size() == this.getBoard().getSize()* this.getBoard().getSize()){
            gameState = GameState.DRAW;
        }
    }

    private boolean checkWinner(Board board, Move newMove) {
        for(WinningStrategy wStrategy : winningStrategies){
            if(wStrategy.checkWinner(board, newMove)){
                System.out.println("Check winner returns true : ");
                return true;
            }
        }
        return false;
    }

    private boolean invalidMove(Move currentMove) {
        /**
         *  But you guys will have to handle the validation cases.
         * 1. Row>=0 , Col>=0 && row<n && col <n
         * 2. If the current cell state is NOT EMPTY -- then return true.
         */
        return false;
    }

    public void printBoard() {
        board.printBoard();
    }

    
}
