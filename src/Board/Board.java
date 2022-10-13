package Board;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.Collections;
import Moves.*;
import Pieces.*;
import Services.Printer;
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
        //set up board
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
        boardArray = new int[]{
            -2,-3,0,0,-6,0,-3,-2,
            -1,-1,-1,0,-5,-1,0,-1,
            0,0,0,0,0,0,-1,0,
            0,0,-4,0,0,-4,0,0,
            0,0,0,-1,0,1,0,0,
            0,0,0,1,0,0,0,0,
            1,1,1,0,5,0,1,1,
            2,3,4,0,6,4,3,2
        };
        moveHandler = new MoveHandler(boardArray, this);
        createPieces();
        createSquares();
    }

    //create squares
    private void createSquares() {
        for(int i = 0; i < 64; i++){
            squares[i] = new Square(i);
        }
    }
    //create pieces
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
    }
   
    //paint pieces and squares
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
    

    //when mouse pressed, pick up piece under mouse, and find the pieces potential moves
    //^assuming there is a piece under the mouse, and the mouse isn't already holding a piece
    int[] potentialSquares;
    public void mousePressed(int x, int y) {
        Point mousePosition = new Point(x,y);
        if(selectedPiece == null){
            for(Piece piece : pieces){
                if(piece.pointAtPiece(mousePosition)){
                    selectedPiece = piece;
                    potentialSquares = moveHandler.findPieceMoves(selectedPiece.getBoardPos(), boardArray);
                //    System.out.println("potential squares:"); Printer.printArray(potentialSquares);
                    CheckFinder.findMoves(potentialSquares, boardArray, selectedPiece.getBoardPos());
                //    System.out.println("after checkfind squares:"); Printer.printArray(potentialSquares);
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

   //when mouse released, place piece if it is over a square it can move to 
    public void mouseReleased(int x, int y) {
        if(selectedPiece != null){
            for(int i = 0; i < squares.length; i++){
                if(squares[i].contains(new Point(x,y))){
                    if(potentialSquares[i] != 0){
                        move(selectedPiece.getBoardPos(), i);
                       // Printer.printArray(boardArray);
                    }
                }
            }
            selectedPiece.dropPiece();
            selectedPiece = null;
            potentialSquares = new int[64];
            paintSquares();
        }
    }
    //move piece objects to reflect array 
    public void updatePieces(int origin, int destination) {
        removePiece(destination);
        for(Piece piece : pieces){
            if(piece.getBoardPos() == origin){
                piece.updatePos(destination);
                break;
            }
        }
    }
    //delete piece at destination (handle captures)
    private void removePiece(int destination) {
        for(Piece piece: pieces){
            if(piece.getBoardPos() == destination){
                pieces.remove(piece);
                break;
            }
        }
    }
    //visually display potential moves
    private void paintSquares() {
        for(int i = 0; i < squares.length; i++){
            if(potentialSquares[i] != 0){
                squares[i].highlight();
            }
            else{
                squares[i].unHighlight();
            }
        }
    }
    //move piece from origin square to destination square, update visuals to reflect
    public void move(int origin, int destination){
        int piece = boardArray[origin];
        boardArray[destination] = piece;
        boardArray[origin] = 0;
        updatePieces(origin, destination);
    }
    
}
