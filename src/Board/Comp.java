package Board;

import java.util.ArrayList;
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
    }
    Piece selectedPiece;
    List<Piece> myPiecesToCheck;
    public boolean findMove(){
        myPiecesToCheck = new ArrayList<Piece>(myPieces);
        selectedPiece = myPiecesToCheck.get(random.nextInt(myPieces.size()));
        board.potentialSquares = board.moveHandler.findPieceMoves(selectedPiece.getBoardPos(), board.boardArray);
        CheckFinder.findMoves(board.potentialSquares, board.boardArray, selectedPiece.getBoardPos());
        while(!hasMove(board.potentialSquares) && myPiecesToCheck.size() > 0){
            selectedPiece = myPiecesToCheck.get(random.nextInt(myPiecesToCheck.size()));
            board.potentialSquares = board.moveHandler.findPieceMoves(selectedPiece.getBoardPos(), board.boardArray);
            CheckFinder.findMoves(board.potentialSquares, board.boardArray, selectedPiece.getBoardPos());
            myPiecesToCheck.remove(selectedPiece);
        }
        if(!hasMove(board.potentialSquares)){
            System.out.println("game over");
            return false;
        }
        else{
            int move = random.nextInt(board.potentialSquares.length);
            if(board.potentialSquares.length > 0){
                while(board.potentialSquares[move] < 1){
                    move = random.nextInt(board.potentialSquares.length);
                }
                board.move(selectedPiece.getBoardPos(), move, board.potentialSquares[move], board.boardArray);
                selectedPiece = null;
                board.potentialSquares = new int[64];
                return true;
            }
            else{
                System.out.println("done");
                return false;
            }
        }
        
        
        
               
            
        

        
                    
    }


    // public int score Minimax(Node currentNode, int depth, boolean white){
    //     if(depth == 0 || !currentNode.hasMoves()){
            
    //     }
    // }


    private boolean hasMove(int[] squares) {
        for(int i = 0; i < squares.length; i++){
            if(squares[i] > 0){
                return true;
            }
        }
        return false;
    }
    
}


