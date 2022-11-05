package Moves;

import Board.Board;
import Board.BoardState;
import Services.Printer;

public class MoveHandler {
    static int[] boardArray;
    static BoardState currentBoardState;
    Board board;
    static PieceMoves pawnMoves;
    static PieceMoves knightMoves;
    static PieceMoves rookMoves;
    static PieceMoves bishopMoves;
    static PieceMoves queenMoves;
    static PieceMoves kingMoves;
   
    public MoveHandler(){
        pawnMoves = new PawnMoves();
        knightMoves = new KnightMoves();
        rookMoves = new RookMoves();
        bishopMoves = new BishopMoves();
        queenMoves = new QueenMoves();
        kingMoves = new KingMoves();
    }

    //find all potential moves for piece at certain position on board
    public static final int[] findPieceMoves(int boardPos, BoardState boardState){
        int[] moves = new int[64];
        currentBoardState = boardState;
        int piece = currentBoardState.getBoardArray()[boardPos];
        switch(Math.abs(piece)){
            case(0):
                break;
            case(1):
                moves = pawnMoves.find(currentBoardState, boardPos, moves);
                break;
            case(2):
                moves = rookMoves.find(currentBoardState, boardPos, moves);
                break;
            case(3):
                moves = knightMoves.find(currentBoardState, boardPos, moves);
                break;
            case(4):
                moves = bishopMoves.find(currentBoardState, boardPos, moves);
                break;
            case(5):
                moves = queenMoves.find(currentBoardState, boardPos, moves);
                break;
            case(6):
                moves = kingMoves.find(currentBoardState, boardPos, moves);
                break;
            default:
                break;

        }
        
        //Printer.printArray(moves);
        return moves;
    }

}
