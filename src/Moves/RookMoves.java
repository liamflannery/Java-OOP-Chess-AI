package Moves;

import Board.BoardState;

public class RookMoves extends PieceMoves{

    
    @Override
    public int[] find(BoardState boardState, int boardPos, int[] moves) {
        this.boardState = boardState;
        this.boardPos = boardPos;
        this.moves = moves;
        this.boardArray = boardState.getBoardArray();
        
         slidingMoves(0,4);
     
        
        return moves;
    }

    
}
