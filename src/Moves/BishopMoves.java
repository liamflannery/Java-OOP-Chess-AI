package Moves;

public class BishopMoves extends PieceMoves{
    @Override
    public int[] find(int[] boardArray, int boardPos, int[] moves) {
        this.boardArray = boardArray;
        this.boardPos = boardPos;
        this.moves = moves;
        //diagonal sliding moves
        slidingMoves(4,8);
     
        
        return moves;
    }
}
