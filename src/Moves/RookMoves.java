package Moves;

public class RookMoves extends PieceMoves{

    
    @Override
    public int[] find(int[] boardArray, int boardPos, int[] moves) {
        this.boardArray = boardArray;
        this.boardPos = boardPos;
        this.moves = moves;
        
         slidingMoves(0,4);
     
        
        return moves;
    }

    
}
