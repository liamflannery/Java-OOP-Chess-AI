package Moves;

import Board.Board;
import Services.Printer;

public class MoveHandler {
    static int[] boardArray;
    Board board;
    static PieceMoves pawnMoves;
    static PieceMoves knightMoves;
    static PieceMoves rookMoves;
    static PieceMoves bishopMoves;
    static PieceMoves queenMoves;
    static PieceMoves kingMoves;
    

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
    public static final int[] findPieceMoves(int boardPos, int[] currentBoardArray){
        int[] moves = new int[64];
        int piece = currentBoardArray[boardPos];
        switch(Math.abs(piece)){
            case(0):
                break;
            case(1):
                moves = pawnMoves.find(currentBoardArray, boardPos, moves);
                break;
            case(2):
                moves = rookMoves.find(currentBoardArray, boardPos, moves);
                break;
            case(3):
                moves = knightMoves.find(currentBoardArray, boardPos, moves);
                break;
            case(4):
                moves = bishopMoves.find(currentBoardArray, boardPos, moves);
                break;
            case(5):
                moves = queenMoves.find(currentBoardArray, boardPos, moves);
                break;
            case(6):
                moves = kingMoves.find(currentBoardArray, boardPos, moves);
                break;
            default:
                break;

        }
        
        //Printer.printArray(moves);
        return moves;
    }

}
