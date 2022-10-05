package Moves;

import Board.Board;

public class MoveHandler {
    int[] boardArray;
    Board board;
    public MoveHandler(int[] inBoardArray, Board inBoard){
        boardArray = inBoardArray;
        board = inBoard;
    }
    public int[] findPieceMoves(int boardPos){
        int[] moves = new int[64];
        moves[0] = 1;
        return moves;
    }
    public void move(int origin, int destination){
        int piece = boardArray[origin];
        boardArray[destination] = piece;
        boardArray[origin] = 0;
        board.updatePieces(origin, destination);
    }
}
