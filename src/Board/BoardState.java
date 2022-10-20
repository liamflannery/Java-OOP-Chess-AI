package Board;

import Moves.MoveHandler;

public class BoardState {
    int[] boardArray;
    // white kingside castle, white queenside castle, black kingside castle, black queenside castle
    boolean[] castlingArray;
    Move previousMove;
    MoveHandler moveHandler;
    public BoardState(int[] boardArray, boolean[] castlingArray, Move previousMove){
        this.boardArray = boardArray;
        this.castlingArray = castlingArray;
        this.previousMove = previousMove;
    }
    public BoardState(BoardState boardToClone){
        this.boardArray = boardToClone.getBoardArray().clone();
        this.castlingArray = boardToClone.getCastlingArray().clone();
        this.previousMove = boardToClone.getPreviousMove();
    }

    public int[] getBoardArray(){
        return boardArray;
    }
    public boolean[] getCastlingArray(){
        return castlingArray;
    }
    public Move getPreviousMove(){
        return previousMove;
    }
    
}
