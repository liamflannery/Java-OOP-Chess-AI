package Board;

import java.util.List;
import Pieces.Piece;
import java.awt.Point;
public class Competitor {
    List<Piece> myPieces;
    Board board;
    boolean isPlayer;
    public Competitor(List<Piece> myPieces, Board board){
        this.myPieces = myPieces;
        this.board = board;
    }
    public void mousePressed(int x, int y){

    }
    public void mouseReleased(int x, int y){
        
    }
    public boolean findMove(){
        return true;

    }
    public boolean isPlayer(){
        return isPlayer;
    }
}
