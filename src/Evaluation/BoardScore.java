package Evaluation;

import java.util.Map;
// https://www.chessprogramming.org/Simplified_Evaluation_Function#Piece-Square_Tables
public class BoardScore {
    static int score;
    private static Map<Integer, Integer> pieceValues = Map.of(
        //white
        1,100, //pawn
        2,500, // rook 
        3,320, // knight 
        4,330, //bishop 
        5,900, //queen 
        //black 
        -1,-100, //pawn
        -2,-500, // rook 
        -3,-320, // knight 
        -4,-330, //bishop 
        -5,-900 //queen 
        ); //queen = 5
  
    public static int calculate(int[] board){
        score = 0;
        for(int i = 0; i < board.length; i++){
            if(board[i] == 6){
                score += 20000;
                score += ScoreMap.getValue(i, board[i], board);
            }
            if(board[i] == -6){
                score += -20000;
                score += ScoreMap.getValue(i, board[i], board);
            }
            if(pieceValues.containsKey(board[i])){
                score += pieceValues.get(board[i]);
                score += ScoreMap.getValue(i, board[i], board);
            }
        }
        return score;
    }
    
}
