package Moves;

import Board.BoardState;

public class KnightMoves extends PieceMoves{
    int[] knightDir;
    @Override
    public int[] find(BoardState boardState, int boardPos, int[] moves) {
        this.boardState = boardState;
        this.boardPos = boardPos;
        this.moves = moves;
        this.boardArray = boardState.getBoardArray();
        knightDir = new int[]{-17,-15,-10,-6,6,10,15,17};
        knightMoves();
     
        
        return moves;
    }

    public void knightMoves(){
        int inc = 0;
      //  -17,-15,-10,-6,6,10,15,17
        for(int i = 0; i < knightDir.length; i++){
            switch(knightDir[i]){
                case(-17):
                    if(numSquaresToEdge[boardPos][2] >= 1 && numSquaresToEdge[boardPos][1] >= 2){
                        inc = knightDir[i];
                    }
                    break;
                case(-15):
                    if(numSquaresToEdge[boardPos][1] >= 2 && numSquaresToEdge[boardPos][3] >= 1){
                        inc = knightDir[i];
                    }
                break;
                case(-10):
                    if(numSquaresToEdge[boardPos][1] >= 1 && numSquaresToEdge[boardPos][2] >= 2){
                        inc = knightDir[i];
                    }
                    break;
                case(-6):
                if(numSquaresToEdge[boardPos][3] >= 2 && numSquaresToEdge[boardPos][1] >= 1){
                    inc = knightDir[i];
                }
                break;
                case(17):
                    if(numSquaresToEdge[boardPos][3] >= 1 && numSquaresToEdge[boardPos][0] >= 2){
                        inc = knightDir[i];
                    }
                    break;
                case(15):
                    if(numSquaresToEdge[boardPos][0] >= 2 && numSquaresToEdge[boardPos][2] >= 1){
                        inc = knightDir[i];
                    }
                break;
                case(10):
                    if(numSquaresToEdge[boardPos][0] >= 1 && numSquaresToEdge[boardPos][3] >= 2){
                        inc = knightDir[i];
                    }
                    break;
                case(6):
                    if(numSquaresToEdge[boardPos][2] >= 2 && numSquaresToEdge[boardPos][0] >= 1){
                        inc = knightDir[i];
                    }
                    break;
                default:
                    inc = knightDir[i];
                    
            }
            move = boardPos + inc;
            vetMove(1);
        }
    }
}
