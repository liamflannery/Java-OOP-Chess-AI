package Moves;

import Board.Board;

public class MoveHandler {
    int[] boardArray;
    Board board;
    PieceMoves pawnMoves;
    PieceMoves rookMoves;
    public MoveHandler(int[] inBoardArray, Board inBoard){
        boardArray = inBoardArray;
        board = inBoard;
        pawnMoves = new PawnMoves();
        rookMoves = new RookMoves();
    }
    public int[] findPieceMoves(int boardPos){
        int[] moves = new int[64];
        int piece = boardArray[boardPos];
        switch(Math.abs(piece)){
            case(0):
                break;
            case(1):
                moves = pawnMoves.find(boardArray, boardPos, moves);
                break;
            case(2):
                moves = rookMoves.find(boardArray, boardPos, moves);
                


        }
        return moves;
    }
    public void move(int origin, int destination){
        int piece = boardArray[origin];
        boardArray[destination] = piece;
        boardArray[origin] = 0;
        board.updatePieces(origin, destination);
    }
}
