package Board;

import java.util.List;
import java.util.Random;

import Moves.CheckFinder;
import Pieces.Piece;

public class Comp extends Competitor{
    private Random random;
    public Comp(List<Piece> myPieces, Board board) {
        super(myPieces, board);
        isPlayer = false;
        random = new Random();
        //TODO Auto-generated constructor stub
    }
    Piece selectedPiece;
    
    public void findMove(){
        selectedPiece = myPieces.get(random.nextInt(myPieces.size()));
        board.potentialSquares = board.moveHandler.findPieceMoves(selectedPiece.getBoardPos(), board.boardArray);
        CheckFinder.findMoves(board.potentialSquares, board.boardArray, selectedPiece.getBoardPos());
        while(!hasMove(board.potentialSquares)){
            selectedPiece = myPieces.get(random.nextInt(myPieces.size()));
            board.potentialSquares = board.moveHandler.findPieceMoves(selectedPiece.getBoardPos(), board.boardArray);
            CheckFinder.findMoves(board.potentialSquares, board.boardArray, selectedPiece.getBoardPos());
        }
        int move = random.nextInt(board.potentialSquares.length);
        if(board.potentialSquares.length > 0){
            while(board.potentialSquares[move] != 1){
                move = random.nextInt(board.potentialSquares.length);
            }
            board.move(selectedPiece.getBoardPos(), move);
            selectedPiece = null;
            board.potentialSquares = new int[64];
        }
        else{
            System.out.println("done");
        }
        
        
               
            
        

        
                    
    }

    private boolean hasMove(int[] squares) {
        for(int i = 0; i < squares.length; i++){
            if(squares[i] == 1){
                return true;
            }
        }
        return false;
    }
    
}
