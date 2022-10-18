package Board;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.awt.Point;
import Evaluation.BoardScore;
import Moves.CheckFinder;
import Pieces.Piece;
import Services.BoardCalculations;

public class Comp extends Competitor{

    boolean isWhite;
    int parentDepth;
    public Comp(List<Piece> myPieces, Board board, boolean isWhite, int depth) {
        super(myPieces, board);
        isPlayer = false;
        this.isWhite = isWhite;
        parentDepth = depth;
    }
    Piece selectedPiece;
    List<Piece> myPiecesToCheck;
    public boolean findMove(){
      Minimax(board.boardArray, parentDepth, isWhite, 0, 0);
      if(bestMove == null){
        return false;
      }
      board.move(bestMove.getOrigin(), bestMove.getDestination(), bestMove.getType(), board.boardArray);
      
      return true;          
    }

    
   
    int whiteEval;
    int blackEval;
    Move bestMove;
    int[] currentPieceMoves;
    int[] currentBoard;
    
    public int Minimax(int[] currentBoard, int depth, boolean white, int distFromRoot, int parentMoveType){
        if(depth == 0){
            return BoardScore.calculate(currentBoard);
        }
        if(white){
            int maxEval = (int) Double.NEGATIVE_INFINITY;
            List<Move> moves = findMoves(currentBoard, white);
            if(moves.size() <= 0 && parentMoveType == 2){
                
                return (int) Double.NEGATIVE_INFINITY;
            }
            for (Move move : moves) {

                int[] testBoard = currentBoard.clone();
                board.move(move.getOrigin(), move.getDestination(), move.getType(), testBoard);
                whiteEval = Minimax(testBoard, depth - 1, false, distFromRoot + 1, move.getType());
              
                if(whiteEval >= maxEval && distFromRoot == 0){
                    bestMove = move;
                }
                maxEval = Math.max(maxEval, whiteEval);     
            }
            return maxEval;
        }
        else{
            int minEval = (int) Double.POSITIVE_INFINITY;
            List<Move> moves = findMoves(currentBoard, white);
            if(moves.size() <= 0 && parentMoveType == 2){
                System.out.println("found checkmate");
                return (int) Double.POSITIVE_INFINITY;
            }
            for (Move move : moves) {
                int[] testBoard = currentBoard.clone();
                board.move(move.getOrigin(), move.getDestination(), move.getType(), testBoard);
                blackEval = Minimax(testBoard, depth - 1, true, distFromRoot + 1, move.getType());
                if(blackEval <= minEval && distFromRoot == 0){
                    bestMove = move;
                }
                minEval = Math.min(minEval, blackEval);     
            }
            return minEval;
        }
    }


    private List<Move> findMoves(int[] thisBoard, boolean white) {
        List<Move> moves = new ArrayList<Move>();
        int[] pieceMoves;
        for(int i = 0; i < thisBoard.length; i++){
            if(thisBoard[i] != 0){
                if((white && thisBoard[i] > 0) || (!white && thisBoard[i] < 0)){
                    pieceMoves = board.moveHandler.findPieceMoves(i, thisBoard);
                    CheckFinder.findMoves(pieceMoves, thisBoard, i);
                    for(int j = 0; j < pieceMoves.length; j++){
                        if(pieceMoves[j] > 0){
                            if(pieceMoves[j] != 1){
                                System.out.println(pieceMoves[j]);
                            }
                            
                            moves.add(new Move(i, j, pieceMoves[j]));
                        }
                    }
                }
            }
        }
        return moves;
    }


    private boolean hasMove(int[] squares) {
        for(int i = 0; i < squares.length; i++){
            if(squares[i] > 0){
                return true;
            }
        }
        return false;
    }
    
}


