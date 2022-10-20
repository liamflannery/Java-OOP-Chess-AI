package Moves;

import Board.BoardState;

public class KingMoves extends PieceMoves{
    @Override
    public int[] find(BoardState boardState, int boardPos, int[] moves) {
        this.boardState = boardState;
        this.boardPos = boardPos;
        this.moves = moves;
        //single moves in all direcitons
        singleMoves(0,8);
        castle();
        
        return moves;
    }
    public void castle(){
        move = boardPos + 2;
        vetMove(4);
        move = boardPos - 2;
        vetMove(5);
        
    }
}
