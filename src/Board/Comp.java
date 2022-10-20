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
    int moveCount;
    Piece selectedPiece;
    List<Piece> myPiecesToCheck;
    public boolean findMove(){
      moveCount = 0;
      Minimax(board.boardArray, parentDepth, isWhite, 0, 0);
      System.out.println(moveCount);
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
        
        int maxEval = (int) Double.NEGATIVE_INFINITY;
        int minEval = (int) Double.POSITIVE_INFINITY;
        List<Move> moves = findMoves(currentBoard, white);
        if(moves.isEmpty()){
            List<Move> childMoves = findMoves(currentBoard, !white);
            if(willCheck(childMoves)){
                if(white){
                    return (int) Double.NEGATIVE_INFINITY;
                }
                else{
                    return (int) Double.POSITIVE_INFINITY;
                }
            }
            else{
                return 0;
            }
            
            
        }
        for (Move move : moves) {
            // System.out.println(move + " node: " + distFromRoot);
            int[] testBoard = currentBoard.clone();
            board.move(move.getOrigin(), move.getDestination(), move.getType(), testBoard);
            int eval = Minimax(testBoard, depth - 1, !white, distFromRoot + 1, move.getType());
            if(((white && eval >= maxEval) || (!white && eval <= minEval)) && distFromRoot == 0){
                bestMove = move;
            }
            if(white){
                maxEval = Math.max(maxEval, eval);
            }
            else{
                minEval = Math.min(minEval, eval); 
            }
                    
        }
        if(white){
            return maxEval;
        }
        else{
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
                            moves.add(new Move(i, j, pieceMoves[j], thisBoard[i]));
                            moveCount++;
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

    private boolean willCheck(List<Move> childMoves) {
        for(Move move: childMoves){
            if(move.getType() == 2){
                return true;
            }
        }
        return false;
    }

    
}


