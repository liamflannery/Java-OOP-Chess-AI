package Moves;

import Board.Board;
import Services.Printer;

public class MoveHandler {
    int[] boardArray;
    Board board;
    PieceMoves pawnMoves;
    PieceMoves knightMoves;
    PieceMoves rookMoves;
    PieceMoves bishopMoves;
    PieceMoves queenMoves;
    PieceMoves kingMoves;
    

    public MoveHandler(int[] inBoardArray, Board inBoard){
        boardArray = inBoardArray;
        board = inBoard;
        pawnMoves = new PawnMoves();
        knightMoves = new KnightMoves();
        bishopMoves = new BishopMoves();
        rookMoves = new RookMoves();
        queenMoves = new QueenMoves();
        kingMoves = new KingMoves();
        
    }
    //find all potential moves for piece at certain position on board
    public int[] findPieceMoves(int boardPos, boolean hasMoved){
        int[] moves = new int[64];
        int piece = boardArray[boardPos];
        switch(Math.abs(piece)){
            case(0):
                break;
            case(1):
                moves = pawnMoves.find(boardArray, boardPos, moves, hasMoved);
                break;
            case(2):
                moves = rookMoves.find(boardArray, boardPos, moves, hasMoved);
                break;
            case(3):
                moves = knightMoves.find(boardArray, boardPos, moves, hasMoved);
                break;
            case(4):
                moves = bishopMoves.find(boardArray, boardPos, moves, hasMoved);
                break;
            case(5):
                moves = queenMoves.find(boardArray, boardPos, moves, hasMoved);
                break;
            case(6):
                moves = kingMoves.find(boardArray, boardPos, moves, hasMoved);
                break;

        }
        Printer.printArray(moves);
        return moves;
    }
    //move piece from origin square to destination square, update visuals to reflect
    public void move(int origin, int destination){
        int piece = boardArray[origin];
        boardArray[destination] = piece;
        boardArray[origin] = 0;
        board.updatePieces(origin, destination);
    }
}
