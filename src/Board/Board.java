package Board;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.Collections;
import Moves.*;
import Pieces.*;
public class Board {
    Square[] squares = new Square[64];
    List<Piece> pieces = new ArrayList<Piece>();
    int[] boardArray;
    MoveHandler moveHandler;
    Piece selectedPiece;
    Consumer<Square> paintSquares;
    Consumer<Piece> paintPiece;
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
        moveHandler = new MoveHandler(boardArray, this);
        createPieces();
        paintBoard();
        createSquares();
    }
    private void createSquares() {
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
                    addPiece = new Pawn(i, true, "pawn");
                    break;
                case(2):
                    addPiece = new Rook(i, true, "rook");
                    break;
                case(3):
                    addPiece = new Knight(i, true, "knight");
                    break;
                case(4):
                    addPiece = new Bishop(i, true, "bishop");
                    break;
                case(5):
                    addPiece = new Queen(i, true, "queen");
                    break;
                case(6):
                    addPiece = new King(i, true, "king");
                    break;
                case(-1):
                    addPiece = new Pawn(i, false, "pawn");
                    break;
                case(-2):
                    addPiece = new Rook(i, false, "rook");
                    break;
                case(-3):
                    addPiece = new Knight(i, false, "knight");
                    break;
                case(-4):
                    addPiece = new Bishop(i, false, "bishop");
                    break;
                case(-5):
                    addPiece = new Queen(i, false, "queen");
                    break;
                case(-6):
                    addPiece = new King(i, false, "king");
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
        paintSquares = square -> {
            square.paint(g, mousePos);
        };
        
        paintPiece = piece -> {
            piece.paint(g, mousePos);
        };
        
        doToEachSquare(paintSquares);
        doToEachPiece(paintPiece, mousePos);
    }
    public void doToEachPiece(Consumer<Piece> func, Point mousePos) {
        for(Piece piece: pieces){
            func.accept(piece);
        }
    }
    public void doToEachSquare(Consumer<Square> func) {
        for(int i=0; i < squares.length; i++) {
                func.accept(squares[i]);
        }
      }
    
    int[] potentialSquares;
    public void mousePressed(int x, int y) {
        Point mousePosition = new Point(x,y);
        if(selectedPiece == null){
            for(Piece piece : pieces){
                if(piece.pointAtPiece(mousePosition)){
                    selectedPiece = piece;
                    potentialSquares = moveHandler.findPieceMoves(selectedPiece.getBoardPos(), selectedPiece.hasMoved());
                    paintSquares();
                    break;
                }
            }
        }
       
               
        if(selectedPiece != null){
            Collections.swap(pieces, pieces.indexOf(selectedPiece), pieces.size() -1);
            selectedPiece.dragPiece();
        }
    }

   
    public void mouseReleased(int x, int y) {
        if(selectedPiece != null){
            for(int i = 0; i < squares.length; i++){
                if(squares[i].contains(new Point(x,y))){
                    if(potentialSquares[i] != 0){
                        moveHandler.move(selectedPiece.getBoardPos(), i);
                        printBoard();
                    }
                }
            }
            selectedPiece.dropPiece();
            selectedPiece = null;
            potentialSquares = new int[64];
            paintSquares();
        }
    }
    
    public void updatePieces(int origin, int destination) {
        removePiece(destination);
        for(Piece piece : pieces){
            if(piece.getBoardPos() == origin){
                piece.updatePos(destination);
                break;
            }
        }
    }
    private void removePiece(int destination) {
        for(Piece piece: pieces){
            if(piece.getBoardPos() == destination){
                pieces.remove(piece);
                break;
            }
        }
    }
    private void paintSquares() {
        for(int i = 0; i < squares.length; i++){
            if(potentialSquares[i] == 1){
                squares[i].highlight();
            }
            else{
                squares[i].unHighlight();
            }
        }
    }

    private void printBoard() {
        int counter = 0;
        List<String> rows = new ArrayList<String>();
        String row = "";
        for(int i = 0; i < boardArray.length; i++){
            if(counter < 8){
                if(boardArray[i] < 0){
                    row += boardArray[i];
                }
                else{
                    row += " " + boardArray[i];
                }
                counter++;
            }
            else{
                rows.add(row);
                if(boardArray[i] < 0){
                    row = "" + boardArray[i];
                }
                else{
                    row = " " + boardArray[i];
                }
                
                counter = 1;
            }
            
        }
       for(String outRow : rows){
        System.out.println(outRow);
       }
    }
}