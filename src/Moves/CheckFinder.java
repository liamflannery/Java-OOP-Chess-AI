package Moves;

import java.util.Arrays;

import Board.BoardState;
import Services.Printer;

public final class CheckFinder {
    static int[] moves;
    static MoveHandler checkMoveHandler;
    static int[] testBoard;
    static BoardState testBoardState;
    public static void findMoves(int[] potentialSquares, BoardState boardState, int boardPos) {
        testBoardState = new BoardState(boardState);
        testBoard = testBoardState.getBoardArray();
        int currentPiece = testBoard[boardPos];
        
        for(int i = 0; i < potentialSquares.length; i++){
            if(potentialSquares[i] > 0){
                testBoardState = new BoardState(boardState);
                testBoard = testBoardState.getBoardArray();
                testBoard[boardPos] = 0;
                testBoard[i] = currentPiece;
                
                for(int j = 0; j < testBoard.length; j++){
                    if(currentPiece * testBoard[j] < 0){
                        int[] checkBoard = checkMoveHandler.findPieceMoves(j, testBoardState);
                        if(willCheck(checkBoard)){
                            potentialSquares[i] = 0;
                        }
                    }
                }
            }
        }

    }
    private static boolean willCheck(int[] checkBoard) {
        for(int i = 0; i < checkBoard.length; i++){
            if(checkBoard[i] == 2){
                return true;
            }
        }
        return false;
    }
}
