package Moves;

public class KingMoves extends PieceMoves{
    @Override
    public int[] find(int[] boardArray, int boardPos, int[] moves, boolean moved) {
        this.boardArray = boardArray;
        this.boardPos = boardPos;
        this.moves = moves;
        this.moved = moved;
        //single moves in all direcitons
        singleMoves(0,8);
     
        
        return moves;
    }
}
