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
    boolean abp;
    public Comp(List<Piece> myPieces, Board board, boolean isWhite, int depth, boolean abp) {
        super(myPieces, board);
        isPlayer = false;
        this.isWhite = isWhite;
        parentDepth = depth;
        this.abp = abp;
    }
    int moveCount;
    Piece selectedPiece;
    List<Piece> myPiecesToCheck;
    public boolean findMove(){
      moveCount = 0;
      Minimax(board.boardState,parentDepth, (int) Double.NEGATIVE_INFINITY , (int) Double.POSITIVE_INFINITY, isWhite, 0, 0);
      System.out.println("Move Count: " + moveCount);
      if(bestMove == null){
        return false;
      }
      board.move(bestMove.getOrigin(), bestMove.getDestination(), bestMove.getType(), board.boardState);
      
      return true;          
    }

    
   
    int whiteEval;
    int blackEval;
    Move bestMove;
    int[] currentPieceMoves;
    int[] currentBoard;
    
    public int Minimax(BoardState boardState, int depth, int alpha, int beta, boolean white, int distFromRoot, int parentMoveType){
        currentBoard = boardState.getBoardArray();
        if(depth == 0){
            return BoardScore.calculate(currentBoard);
        }
        
        int maxEval = (int) Double.NEGATIVE_INFINITY;
        int minEval = (int) Double.POSITIVE_INFINITY;
        List<Move> moves = findMoves(boardState, white);
        if(moves.isEmpty()){
            List<Move> childMoves = findMoves(boardState, !white);
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
            BoardState testBoardState = new BoardState(boardState);
            int[] testBoard = testBoardState.getBoardArray();
            board.move(move.getOrigin(), move.getDestination(), move.getType(), testBoardState);
            
            int eval = Minimax(testBoardState, depth - 1, alpha, beta, !white, distFromRoot + 1, move.getType());
                   
            
            if(((white && eval >= maxEval) || (!white && eval <= minEval)) && distFromRoot == 0){
                bestMove = move;
            }
            if(white){
                maxEval = Math.max(maxEval, eval);
                // alpha = Math.max(alpha, eval);
                //  if(beta <= alpha && abp){
                //     break;
                // }
            }
            else{
                minEval = Math.min(minEval, eval);
                // beta = Math.min(beta, eval);
                // if(beta <= alpha && abp){
                //     break;
                // }
            }
                    
        }
        if(white){
            return maxEval;
        }
        else{
            return minEval;
        }
    }




    private List<Move> findMoves(BoardState thisBoardState, boolean white) {
        List<Move> moves = new ArrayList<Move>();
        int[] thisBoard = thisBoardState.getBoardArray();
        int[] pieceMoves;
        for(int i = 0; i < thisBoard.length; i++){
            if(thisBoard[i] != 0){
                if((white && thisBoard[i] > 0) || (!white && thisBoard[i] < 0)){
                    pieceMoves = board.moveHandler.findPieceMoves(i, thisBoardState);
                    CheckFinder.findMoves(pieceMoves, thisBoardState, i);
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


