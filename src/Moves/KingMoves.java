package Moves;

import Board.BoardState;

public class KingMoves extends PieceMoves{
    @Override
    public int[] find(BoardState boardState, int boardPos, int[] moves) {
        this.boardState = boardState;
        this.boardPos = boardPos;
        this.moves = moves;
        this.boardArray = boardState.getBoardArray();
        this.castleInfo = boardState.getCastlingArray();
        //single moves in all direcitons
        singleMoves(0,8);
        castle();
        
        return moves;
    }
    public void castle(){
        if(boardArray[boardPos] < 0 && castleInfo[0] || boardArray[boardPos] > 0 && castleInfo[2]){
            move = boardPos + 2;
            vetMove(4);
        }
        if(boardArray[boardPos] < 0 && castleInfo[1] || boardArray[boardPos] > 0 && castleInfo[3]){
            move = boardPos - 2;
            vetMove(5);
        }

        
    }
}
