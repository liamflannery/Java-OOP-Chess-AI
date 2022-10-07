package Moves;

import java.util.Arrays;

import Services.Printer;

public final class CheckFinder {
    static int[] moves;
    public final static boolean willCheck(int boardPos, int movePos, int[] boardArray){
        int[] testBoard = boardArray.clone();
        int currentPiece = boardArray[boardPos];
        testBoard[boardPos] = 0;
        testBoard[movePos] = currentPiece;
        Printer.printArray(testBoard);
        for(int i = 0; i < testBoard.length; i++){
            if(testBoard[i] * currentPiece < 0){
                
                //moves = MoveHandler.findPieceMoves(i, testBoard);
                // if(Arrays.asList(moves).contains(2)){
                //     return true;
                // }
            }
        }
        return false;
    }
}
