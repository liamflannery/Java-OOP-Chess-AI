package Moves;

import Board.BoardState;

public class QueenMoves extends PieceMoves{
    @Override
    public int[] find(BoardState boardState, int boardPos, int[] moves) {
        this.boardState = boardState;
        this.boardPos = boardPos;
        this.moves = moves;
        
         slidingMoves(0,8);
     
        
        return moves;
    }
}
