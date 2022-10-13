package Moves;

import java.util.Arrays;

import Services.Printer;

public final class CheckFinder {
    static int[] moves;
    static MoveHandler checkMoveHandler;
    static int[] testBoard;
    public static void findMoves(int[] potentialSquares, int[] boardArray, int boardPos) {
        testBoard = boardArray.clone();
        int currentPiece = boardArray[boardPos];
        
        for(int i = 0; i < potentialSquares.length; i++){
            if(potentialSquares[i] > 0){
                testBoard = boardArray.clone();
                testBoard[boardPos] = 0;
                testBoard[i] = currentPiece;
                
                for(int j = 0; j < testBoard.length; j++){
                    if(currentPiece * testBoard[j] < 0){
                        int[] checkBoard = checkMoveHandler.findPieceMoves(j, testBoard);
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
