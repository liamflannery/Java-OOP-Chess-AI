package Moves;

import Board.BoardState;

public class BishopMoves extends PieceMoves{
    @Override
    public int[] find(BoardState boardState, int boardPos, int[] moves) {
        this.boardState = boardState;
        this.boardPos = boardPos;
        this.moves = moves;
        //diagonal sliding moves
        slidingMoves(4,8);
     
        
        return moves;
    }
}
