package Board;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import Evaluation.BoardScore;

import java.util.Collections;
import Moves.*;
import Pieces.*;

import Services.Printer;
public class Board {
    Square[] squares = new Square[64];
    // List<Piece> pieces = new ArrayList<Piece>();
    List<Piece> blackPieces = new ArrayList<Piece>();
    List<Piece> whitePieces = new ArrayList<Piece>();
    List<Piece> allPieces;
    List<Piece> currentPieces = whitePieces;
    
    int[] boardArray = new int[64];
    MoveHandler moveHandler;
    Piece selectedPiece;
    Consumer<Square> paintSquares;
    Consumer<Piece> paintPiece;
    Competitor black;
    Competitor white;
    int turn;
    int[] potentialSquares;
    BoardState boardState;
    public Board(int turn){
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
        
        //blank board
        // boardArray = new int[]{
        //     0, 0, 0, 0, 0, 0, 0, 0,
        //     0, 0, 0, 0, 0, 0, 0, 0,
        //     0, 0, 0, 0, 0, 0, 0, 0,
        //     0, 0, 0, 0, 0, 0, 0, 0,
        //     0, 0, 0, 0, 0, 0, 0, 0,
        //     0, 0, 0, 0, 0, 0, 0, 0,
        //     0, 0, 0, 0, 0, 0, 0, 0,
        //     0, 0, 0, 0, 0, 0, 0, 0
        // };
        // boardArray = new int[]{
        //     0, 0, 0, 0, 0, 0, 0, 0,
        //     0, 0, 0, 0,-6, 0, 0, 0,
        //     0, 0, 0, 0, 0, 0, 0, 0,
        //     0, 0, 0, 0, 0, 0, 0, 0,
        //     0, 0, 0, 0, 0, 0, 0, 0,
        //     0, 0, 0, 0, 6, 0, 0, 0,
        //     0, 0, 0, 0, 0, 0, 0, 0,
        //     0, 0, 0, 0, 0, 0, 0, 0
        // };

        //white at bottom start position
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

        //     boardArray = new int[]{
        //     0, 0, 0, 5, 0, 0, 0, 0,
        //     0, 0, 0, 0, 0, 0,-1,-6,
        //     0, 0, 0, 0, 0,-1, 3, 0,
        //     0, 0, 0, 0, 0, 4, 0,-1,
        //     0, 0, 0, 0, 6, 0, 0, 0,
        //     0, 0, 0, 0, 0, 0, 0, 0,
        //     0, 0, 0, 0, 0, 0, 0, 0,
        //     0, 0, 0, 0, 0, 0, 0, 0
        // };
        //black at bottom start position
        // boardArray = new int[]{
        //      2, 3, 4, 6, 5, 4, 3, 2,
        //      1, 1, 1, 1, 1, 1, 1, 1,
        //      0, 0, 0, 0, 0, 0, 0, 0,
        //      0, 0, 0, 0, 0, 0, 0, 0,
        //      0, 0, 0, 0, 0, 0, 0, 0,
        //      0, 0, 0, 0, 0, 0, 0, 0,
        //      -1,-1,-1,-1,-1,-1,-1,-1,
        //     -2,-3,-4,-6,-5,-4,-3,-2
        // };

        // boardArray = new int[]{
        //     -2, 0, 0, 0,-6, 0, 0,-2,
        //     -1,-1,-1,-1,-1,-1,-1,-1,
        //      0, 0, 0, 0, 0, 0, 0, 0,
        //      0, 0, 0, 0, 0, 0, 0, 0,
        //      0, 0, 0, 0, 0, 0, 0, 0,
        //      0, 0, 0, 0, 0, 0, 0, 0,
        //      1, 1, 1, 1, 1, 1, 1, 1,
        //      2, 0, 0, 0, 6, 0, 0, 2
        // };


        // boardArray = new int[]{
        //     0, 0, 0,-2, 0,-4, 0,-6,
        //     0,-1, 0, 0, 0, 2, 0, 0,
        //     0, 0, 0, 0, 0, 0, 0,-1,
        //     0, 0,-1, 0, 0, 0, 0, 3,
        //     -1, 0, 0, 0, 0, 1, 0, 0,
        //     0, 0, 6, 0, 0, 0, 2, 0,
        //     1, 1, 0, 0, 0, 0, 0, 0,
        //     0, 0, 0,-2, 0, 0, 0, 0
        // };

        // boardArray = new int[]{
        //     -2,-3,0,0,-6,0,-3,-2,
        //     -1,-1,-1,0,-5,-1,0,-1,
        //     0,0,0,0,0,0,-1,0,
        //     0,0,-4,0,0,-4,0,0,
        //     0,0,0,-1,0,1,0,0,
        //     0,0,0,1,0,0,0,0,
        //     1,1,1,0,5,0,1,1,
        //     2,3,4,0,6,4,3,2
        // };
        
        this.turn = turn;
        createPieces();
        createSquares();
        boardState = new BoardState(boardArray, new boolean[]{true,true,true,true}, null);
        white = new Comp(whitePieces, this, true, 5, true);
        black = new Player(blackPieces, this, false, 4);
        moveHandler = new MoveHandler();
        allPieces = Stream.concat(whitePieces.stream(), blackPieces.stream()).collect(Collectors.toList());;
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
            if(addPiece != null && boardArray[i] < 0){
                blackPieces.add(addPiece);
            }
            if(addPiece != null && boardArray[i] > 0){
                whitePieces.add(addPiece);
            }

        }
    }
   
    //paint pieces and squares
    boolean gameContinue = true;
    public void paint(Graphics g, Point mousePos) {
        paintSquares = square -> {
            square.paint(g, mousePos);
        };
        
        paintPiece = piece -> {
            piece.paint(g, mousePos);
        };
        
        // if(gameContinue){
        //     gameContinue = compMove();
        // }
        // else{
        //     white = new Player(whitePieces, this);
        //     black = new Player(blackPieces, this);
        // }


        doToEachSquare(paintSquares);
        doToEachPiece(paintPiece, mousePos);
    }
    private boolean compMove() {
        if(!white.isPlayer && turn > 0){
            return white.findMove();
        }
        else if(!black.isPlayer && turn < 0){
            return black.findMove();
        }
        return false;
    }

    public void doToEachPiece(Consumer<Piece> func, Point mousePos) {
        for(Piece piece: allPieces){
            func.accept(piece);
        }
    }
    public void doToEachSquare(Consumer<Square> func) {
        for(int i=0; i < squares.length; i++) {
                func.accept(squares[i]);
        }
      }
    

    
    private void changeTurn() {
        if(turn < 0){
            currentPieces = whitePieces;
        }
        else{
            currentPieces = blackPieces;
        }
        turn *= -1;
        
    }

    //move piece objects to reflect array 
    public void updatePieces(int origin, int destination, int type) {
        removePiece(destination);
        if(type == 3){
            removePiece(origin);
            Piece promotedQueen;
            if(turn < 0){
                promotedQueen = new Queen(destination, false, "queen");
                blackPieces.add(promotedQueen);
                allPieces.add(promotedQueen);
            }
            else{
                promotedQueen = new Queen(destination, true, "queen");
                whitePieces.add(promotedQueen);
                allPieces.add(promotedQueen);
            }
        }
        else{
            for(Piece piece : allPieces){
                if(piece.getBoardPos() == origin){
                    piece.updatePos(destination);
                    break;
                }
            }
        }
        
    }
    //delete piece at destination (handle captures)
    private void removePiece(int destination) {
        for(Piece piece: allPieces){
            if(piece.getBoardPos() == destination){
                allPieces.remove(piece);
                blackPieces.remove(piece);
                whitePieces.remove(piece);
                break;
            }
        }
    }
    //visually display potential moves
    public void paintSquares() {
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
    public void move(int origin, int destination, int moveType, BoardState inBoardState){
        int[] inBoard = inBoardState.getBoardArray();
        int piece = inBoard[origin];
        if(moveType == 3){
            piece = 5 * piece;
        }
        if(moveType == 4){
            int rookOrigin = origin + 3;
            int rookDestination = origin + 1;
            inBoard[rookDestination] = inBoard[rookOrigin];
            inBoard[rookOrigin] = 0;
            if(inBoardState == boardState){
                updatePieces(rookOrigin, rookDestination, moveType);
            }
            
        }
        if(moveType == 5){
            int rookOrigin = origin - 4;
            int rookDestination = origin - 1;
            inBoard[rookDestination] = inBoard[rookOrigin];
            inBoard[rookOrigin] = 0;
            if(inBoardState == boardState){
                updatePieces(rookOrigin, rookDestination, moveType);
            }
            
        }
        if(Math.abs(piece) == 6 || Math.abs(piece) == 2){
            castleUpdates(piece, origin, inBoardState);
        }
        inBoard[destination] = piece;
        inBoard[origin] = 0;

        if(inBoardState == boardState){
            updatePieces(origin, destination, moveType);
            changeTurn();
        }

    }

    private void castleUpdates(int piece, int pos, BoardState inBoardState) {
        inBoardState.setCastlingArray(piece, pos);
    }

    public void mousePressed(int x, int y) {
        if(turn > 0){
            white.mousePressed(x, y);
        }
        else{
            black.mousePressed(x, y);
        }
        compMove();
        
    }

    public void mouseReleased(int x, int y) {
        if(turn > 0){
            white.mouseReleased(x,y);
        }
        else{
            black.mouseReleased(x,y);
        }
    }
    
}
