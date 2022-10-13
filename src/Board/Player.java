package Board;

import java.util.Collections;
import java.util.List;

import Moves.CheckFinder;
import Pieces.Piece;
import java.awt.Point;
public class Player extends Competitor{

    public Player(List<Piece> myPieces, Board board) {
        super(myPieces, board);
        //TODO Auto-generated constructor stub
    }
    //when mouse pressed, pick up piece under mouse, and find the pieces potential moves
    //^assuming there is a piece under the mouse, and the mouse isn't already holding a piece
    int[] potentialSquares;
    Piece selectedPiece;
    @Override
    public void mousePressed(int x, int y) {
        System.out.println("mouse pressed");
        Point mousePosition = new Point(x,y);
        if(selectedPiece == null){
            for(Piece piece : myPieces){
                if(piece.pointAtPiece(mousePosition)){
                    selectedPiece = piece;
                    potentialSquares = board.moveHandler.findPieceMoves(selectedPiece.getBoardPos(), board.boardArray);
                    CheckFinder.findMoves(potentialSquares, board.boardArray, selectedPiece.getBoardPos());
                    board.paintSquares();
                    break;
                }
            }
        }
       
               
        if(selectedPiece != null){
            Collections.swap(board.allPieces, board.allPieces.indexOf(selectedPiece), board.allPieces.size() -1);
            selectedPiece.dragPiece();
        }
    }

   //when mouse released, place piece if it is over a square it can move to 
    public void mouseReleased(int x, int y) {
        if(selectedPiece != null){
            for(int i = 0; i < board.squares.length; i++){
                if(board.squares[i].contains(new Point(x,y))){
                    if(potentialSquares[i] != 0){
                        board.move(selectedPiece.getBoardPos(), i);
                        
                    }
                }
            }
            selectedPiece.dropPiece();
            selectedPiece = null;
            potentialSquares = new int[64];
            board.paintSquares();
        }
    }
    
}
