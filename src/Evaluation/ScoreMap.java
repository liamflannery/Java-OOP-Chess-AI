package Evaluation;

import java.util.Arrays;

import Services.Values;

// https://www.chessprogramming.org/Simplified_Evaluation_Function#Piece-Square_Tables
public final class ScoreMap {
    static int[] pawnMap = new int[]{
        0,  0,  0,  0,  0,  0,  0,  0,
        50, 50, 50, 50, 50, 50, 50, 50,
        10, 10, 20, 30, 30, 20, 10, 10,
        5,  5, 10, 25, 25, 10,  5,  5,
        0,  0,  0, 20, 20,  0,  0,  0,
        5, -5,-10,  0,  0,-10, -5,  5,
        5, 10, 10,-20,-20, 10, 10,  5,
        0,  0,  0,  0,  0,  0,  0,  0
    };
    static int[] rookMap = new int[]{
        0,  0,  0,  0,  0,  0,  0,  0,
        5, 10, 10, 10, 10, 10, 10,  5,
       -5,  0,  0,  0,  0,  0,  0, -5,
       -5,  0,  0,  0,  0,  0,  0, -5,
       -5,  0,  0,  0,  0,  0,  0, -5,
       -5,  0,  0,  0,  0,  0,  0, -5,
       -5,  0,  0,  0,  0,  0,  0, -5,
        0,  0,  0,  5,  5,  0,  0,  0
    };
    static int[] knightMap = new int[]{
        -50,-40,-30,-30,-30,-30,-40,-50,
        -40,-20,  0,  0,  0,  0,-20,-40,
        -30,  0, 10, 15, 15, 10,  0,-30,
        -30,  5, 15, 20, 20, 15,  5,-30,
        -30,  0, 15, 20, 20, 15,  0,-30,
        -30,  5, 10, 15, 15, 10,  5,-30,
        -40,-20,  0,  5,  5,  0,-20,-40,
        -50,-40,-30,-30,-30,-30,-40,-50,
    };
    static int[] bishopMap = new int[]{
        -20,-10,-10,-10,-10,-10,-10,-20,
        -10,  0,  0,  0,  0,  0,  0,-10,
        -10,  0,  5, 10, 10,  5,  0,-10,
        -10,  5,  5, 10, 10,  5,  5,-10,
        -10,  0, 10, 10, 10, 10,  0,-10,
        -10, 10, 10, 10, 10, 10, 10,-10,
        -10,  5,  0,  0,  0,  0,  5,-10,
        -20,-10,-10,-10,-10,-10,-10,-20,

    };
    static int[] queenMap = new int[]{
        -20,-10,-10, -5, -5,-10,-10,-20,
        -10,  0,  0,  0,  0,  0,  0,-10,
        -10,  0,  5,  5,  5,  5,  0,-10,
        -5,  0,  5,  5,  5,  5,  0, -5,
        0,  0,  5,  5,  5,  5,  0, -5,
        -10,  5,  5,  5,  5,  5,  0,-10,
        -10,  0,  5,  0,  0,  0,  0,-10,
        -20,-10,-10, -5, -5,-10,-10,-20

    };
    static int[] kingMap = new int[]{
        -30,-40,-40,-50,-50,-40,-40,-30,
        -30,-40,-40,-50,-50,-40,-40,-30,
        -30,-40,-40,-50,-50,-40,-40,-30,
        -30,-40,-40,-50,-50,-40,-40,-30,
        -20,-30,-30,-40,-40,-30,-30,-20,
        -10,-20,-20,-20,-20,-20,-20,-10,
        20, 20,  0,  0,  0,  0, 20, 20,
        20, 30, 10,  0,  0, 10, 30, 20

    };
    static int[] kingEndGameMap = new int[]{
        -50,-40,-30,-20,-20,-30,-40,-50,
        -30,-20,-10,  0,  0,-10,-20,-30,
        -30,-10, 20, 30, 30, 20,-10,-30,
        -30,-10, 30, 40, 40, 30,-10,-30,
        -30,-10, 30, 40, 40, 30,-10,-30,
        -30,-10, 20, 30, 30, 20,-10,-30,
        -30,-30,  0,  0,  0,  0,-30,-30,
        -50,-30,-30,-30,-30,-30,-30,-50
    };
    

    public final static int getValue(int pos, int piece, int[] board){
        
        int lookUpVal = pos;
        int returnVal;
        if(Values.upDirection(piece) > 0){
            lookUpVal = 63 - lookUpVal;
        }
        
        switch(Math.abs(piece)){
            case(1):
                returnVal = pawnMap[lookUpVal];
                break;
            case(2):
                returnVal = rookMap[lookUpVal];
                break;
            case(3):
                returnVal = knightMap[lookUpVal];
                break;
            case(4):
                returnVal = bishopMap[lookUpVal];
                break;
            case(5):
                returnVal = queenMap[lookUpVal];
                break;
            case(6):
                if(endgame(board)){
                    returnVal = kingEndGameMap[lookUpVal];
                }
                else{
                    returnVal = kingMap[lookUpVal];
                }
                
                break;
            default:
                returnVal = 0;
                break;
        }
        if(piece > 0){
            return returnVal;
        }
        else{
            return -returnVal;
        }
        
    }


    private static boolean endgame(int[] board) {
      
        if(!(Arrays.asList(board).contains(6) || Arrays.asList(board).contains(-6))){
            return true;
        }
        int boardSum = 0;
        for(int i = 0; i < board.length; i++){
            if(Math.abs(board[i]) != 1 && Math.abs(board[i]) != 6 && Math.abs(board[i]) != 5){
                boardSum += Math.abs(board[i]);
            }
        }
        
        if(boardSum < 10){
            return true;
        }
        else{
            return false;
        }
    }
}
