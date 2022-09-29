package Pieces;
public class Piece {
    int x;
    int y;
    int column;
    int row;
    int boardPos;
    public Piece(int inBoardPos){
        boardPos = inBoardPos;
    }
    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return this.getClass().toString();
    }
}
