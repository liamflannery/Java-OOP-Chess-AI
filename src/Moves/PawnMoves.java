package Moves;

import Services.Values;

public class PawnMoves extends PieceMoves{

    int upDirection = 0;
    @Override
    public int[] find(int[] boardArray, int boardPos, int[] moves) {
        this.boardArray = boardArray;
        this.boardPos = boardPos;
        this.moves = moves;
        upDirection = Values.upDirection(boardArray[boardPos]);
        pawnMoves();
        specialPawnMoves();
     
        
        return moves;
    }
    private void pawnMoves(){
        // int piece = boardArray[boardPos];
        // int direction = 8 * piece;

        // if(boardArray[boardPos - direction] == 0){
        //     moves[boardPos - direction] = 1;
        // }
        if(upDirection > 0){
            singleMoves(0,1);
            singleMoves(4, 5);
            singleMoves(6, 7);
        }
        else{
            singleMoves(1,2);
            singleMoves(5, 6);
            singleMoves(7, 8);  
        }
        

    }
    public void specialPawnMoves(){
       //can move two spaces on start
        if(!(hasMoved())){
            move = boardPos - 16 * boardArray[boardPos];
            if(boardArray[move] == 0 && boardArray[boardPos - 8 * boardArray[boardPos]] == 0){
                vetMove(1);
            }
            
        }

    }
    private boolean hasMoved(){
        return (upDirection > 0 && boardPos > 15 || upDirection < 0 && boardPos < 48);
    }
    
}
