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
    public void setCastlingArray(int piece, int pos) {
        if(piece == 6){
            castlingArray[0] = false;
            castlingArray[1] = false;
        }
        if(piece == -6){
            castlingArray[2] = false;
            castlingArray[3] = false;
        }
        if(piece == 2){
            if(pos + 8 % 8 == 0){
                castlingArray[0] = false;
            }
            if(pos + 8 % 7 == 0){
                castlingArray[1] = false;
            }
        }
        if(piece == -2){
            if(pos + 8 % 8 == 0){
                castlingArray[2] = false;
            }
            if(pos + 8 % 7 == 0){
                castlingArray[3] = false;
            }
        }
    }

    
    
}
