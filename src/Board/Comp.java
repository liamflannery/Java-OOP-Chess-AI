package Board;

import java.util.List;
import java.util.Random;

import Moves.CheckFinder;
import Pieces.Piece;

public class Comp extends Competitor{
    private Random random;
    public Comp(List<Piece> myPieces, Board board) {
        super(myPieces, board);
        random = new Random();
        //TODO Auto-generated constructor stub
    }
    Piece selectedPiece;
    
    public void mousePressed(int x, int y){
        selectedPiece = myPieces.get(random.nextInt(myPieces.size()));
        board.potentialSquares = board.moveHandler.findPieceMoves(selectedPiece.getBoardPos(), board.boardArray);
        CheckFinder.findMoves(board.potentialSquares, board.boardArray, selectedPiece.getBoardPos());
        for(int i = 0; i < board.potentialSquares.length; i++){
            if(board.potentialSquares[i] > 0){
                board.move(selectedPiece.getBoardPos(), i);
                break;
            }
            
        }

        selectedPiece = null;
        board.potentialSquares = new int[64];
                    
    }
    
}
