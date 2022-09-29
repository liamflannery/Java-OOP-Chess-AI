import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import Pieces.*;
public class Board {
    Square[] squares = new Square[64];
    List<Piece> pieces = new ArrayList<Piece>();
    int[] boardArray;
    public Board(){
        /* 
            0 : empty
            1 : pawn
            2 : rook
            3 : knight
            4 : bishop
            5 : queen
            6 : king
            - : black
            + : white 
        */
        boardArray = new int[]{
            -2,-3,-4,-5,-6,-4,-3,-2,
            -1,-1,-1,-1,-1,-1,-1,-1,
             0, 0, 0, 0, 0, 0, 0, 0,
             0, 0, 0, 0, 0, 0, 0, 0,
             0, 0, 0, 0, 0, 0, 0, 0,
             0, 0, 0, 0, 0, 0, 0, 0,
             1, 1, 1, 1, 1, 1, 1, 1,
             2, 3, 4, 5, 6, 4, 3, 2
        };
        createPieces();
        paintBoard();
        for(int i = 0; i < 64; i++){
            squares[i] = new Square(i);
        }
    }
    void createPieces(){
        for(int i = 0; i < boardArray.length; i++){
            Piece addPiece = null;
            switch(boardArray[i]){
                case(0):
                    break;
                case(1):
                    addPiece = new Pawn(i);
                    break;
                default:
                    break;
            }
            if(addPiece != null){
                pieces.add(addPiece);
            }

        }
        System.out.println(pieces);
    }
    void paintBoard(){

    }
    public void paint(Graphics g, Point mousePos) {
        Consumer<Square> paint = square -> {
            square.paint(g, mousePos);
        };
        doToEachSquare(paint);
    }
    public void doToEachSquare(Consumer<Square> func) {
        for(int i=0; i < squares.length; i++) {
                func.accept(squares[i]);
        }
      }
}
