package Evaluation;

import java.util.Map;

public class BoardScore {
    static int score;
    private static Map<Integer, Integer> pieceValues = Map.of(
        //white
        1,1, //pawn = 1
        2,5, // rook = 5
        3,3, // knight = 3
        4,3, //bishop = 4
        5,9, //queen = 5
        //black 
        -1,-1, //pawn = 1
        -2,-5, // rook = 5
        -3,-3, // knight = 3
        -4,-3, //bishop = 4
        -5,-9
        ); //queen = 5
  
    public static int calculate(int[] board){
        score = 0;
        for(int i = 0; i < board.length; i++){
            if(board[i] == 6){
                score += 90;
            }
            if(board[i] == -6){
                score += -90;
            }
            if(pieceValues.containsKey(board[i])){
                score += pieceValues.get(board[i]);
            }
        }
        return score;
    }
    
}
