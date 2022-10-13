package Moves;

import Services.BoardCalculations;
import Services.Values;

public class PieceMoves {
    int[] boardArray;
    int boardPos;
    int[] moves;
    int move;
    int[] directionIndex = new int[]{8,-8,-1,1,7,-7,9,-9};
    int[][] numSquaresToEdge = BoardCalculations.computeSquares();
    
    public int[] find(int[] boardArray, int boardPos, int[] moves) {
        
        return moves;

    }
    //one space moves (king/pawn)
    //for pawn, handle diagonal captures 
    public void singleMoves(int direction, int directionEnd){
        for(int i = direction; i < directionEnd ; i++){
            move = boardPos + directionIndex[i];
            if(move < boardArray.length && move >= 0 && numSquaresToEdge[boardPos][i] > 0){
                if(!(Math.abs(boardArray[boardPos]) == 1 && boardArray[move] != 0 && i < 3)){
                    if(!(Math.abs(boardArray[boardPos]) == 1 && boardArray[move] == 0 && i > 3))
                        vetMove();
                }
            }
            
        }
    }
    //multi space moves (rook/bishop/queen)
    //8 directions, [0-4] are straight [4-8] are diagonal
    public void slidingMoves(int direction, int directionEnd){
        
        for(int i = direction; i < directionEnd ; i++){
            for(int j = 1; j <= numSquaresToEdge[boardPos][i]; j++){
                move = boardPos + directionIndex[i] * j;
                if(move < boardArray.length && move >= 0){
                    if(boardArray[move] != 0){
                        if(boardArray[move] * boardArray[boardPos] <= 0){
                            vetMove();  
                            break;
                        }
                        else{
                            break;
                        }
                    }
                    else{
                        vetMove();
                    }
                }
                                      
                
                else{
                    break;
                }
            }
            
        }
    }

    //check if move is in range of board
    //check if move will take opponent or will move to friendly square
    public void vetMove(){
        if(move >= 0 && move < boardArray.length){
            if(boardArray[move] != 0){
                if(boardArray[move] * boardArray[boardPos] < 0){
                    if(Math.abs(boardArray[move]) == 6){
                        moves[move] = 2;
                    }
                    else{
                        moves[move] = 1;
                    }
                   
                }
            }
            else{
                moves[move] = 1;
            }
    }
    }
    
   
}
