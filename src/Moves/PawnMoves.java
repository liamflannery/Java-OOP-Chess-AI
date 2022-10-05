package Moves;

public class PawnMoves extends PieceMoves{

    
    @Override
    public int[] find(int[] boardArray, int boardPos, int[] moves) {
        this.boardArray = boardArray;
        this.boardPos = boardPos;
        this.moves = moves;
        
         pawnMoves();
     
        
        return moves;
    }
    private void pawnMoves(){
        int piece = boardArray[boardPos];
        int direction = 8 * piece;

        if(boardArray[boardPos - direction] == 0){
            moves[boardPos - direction] = 1;
        }

    }
    
}
