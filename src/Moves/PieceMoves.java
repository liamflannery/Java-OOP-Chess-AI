package Moves;

import Services.Values;

public class PieceMoves {
    int[] boardArray;
    int boardPos;
    int[] moves;
    int[] directionIndex = new int[]{8,-8,-1,1,7,-7,9,-9};
    int[][] numSquaresToEdge = Values.computeSquares();
    public int[] find(int[] boardArray, int boardPos, int[] moves) {
        return moves;

    }
    public void slidingMoves(int direction, int directionEnd){
        int move;
        for(int i = direction; i < directionEnd ; i++){
            for(int j = 1; j <= numSquaresToEdge[boardPos][i]; j++){
                move = boardPos + directionIndex[i] * j;
                if(move < boardArray.length && move >= 0){
                    if(boardArray[move] != 0){
                        if(boardArray[move] * boardArray[boardPos] <= 0){
                            moves[move] = 1;  
                            break;
                        }
                        else{
                            break;
                        }
                    }
                    else{
                        moves[move] = 1;
                    }
                }
                                      
                
                else{
                    break;
                }
            }
            
        }
    }
   
}
