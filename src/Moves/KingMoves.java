package Moves;

import java.util.Arrays;

import Board.BoardState;
import Services.Values;

public class KingMoves extends PieceMoves{
    @Override
    public int[] find(BoardState boardState, int boardPos, int[] moves) {
        this.boardState = boardState;
        this.boardPos = boardPos;
        this.moves = moves;
        this.boardArray = boardState.getBoardArray();
        this.castleInfo = boardState.getCastlingArray();
        //single moves in all direcitons
        singleMoves(0,8);
        castle();
        
        return moves;
    }
    int[] bqCastle = {-2,0,0,0,-6};
    int[] wqCastle = {2,0,0,0,6};
    int[] bkCastle = {-6,0,0,-2};
    int[] wkCastle = {6,0,0,2};

    public void castle(){
        int piece = boardArray[boardPos];
        if(Values.upDirection(piece) > 0 && boardPos == 4){
            if(piece < 0){
                if(Arrays.equals(bkCastle, Arrays.copyOfRange(boardArray, boardPos, 8)) && castleInfo[2]){
                    move = boardPos + 2;
                    vetMove(4);
                }
                if(Arrays.equals(Arrays.copyOfRange(boardArray, 0, boardPos + 1), bqCastle) && castleInfo[3]){
                    move = boardPos - 2;
                    vetMove(5);
                }
            }
            if(piece > 0){
                if(Arrays.equals(wkCastle, Arrays.copyOfRange(boardArray, boardPos, 8)) && castleInfo[0]){
                    move = boardPos + 2;
                    vetMove(4);
                }
                if(Arrays.equals(Arrays.copyOfRange(boardArray, 0, boardPos + 1), wqCastle) && castleInfo[1]){
                    move = boardPos - 2;
                    vetMove(5);
                }
            }
        }
        else if(Values.upDirection(piece) < 0 && boardPos == 60){
            if(piece < 0){
                if(Arrays.equals(bkCastle, Arrays.copyOfRange(boardArray, boardPos, 64)) && castleInfo[2]){
                    move = boardPos + 2;
                    vetMove(4);
                }
                if(Arrays.equals(Arrays.copyOfRange(boardArray, 56, boardPos + 1), bqCastle) && castleInfo[3]){
                    move = boardPos - 2;
                    vetMove(5);
                }
            }
            if(piece > 0){
                if(Arrays.equals(wkCastle, Arrays.copyOfRange(boardArray, boardPos, 64)) && castleInfo[0]){
                    move = boardPos + 2;
                    vetMove(4);
                }
                if(Arrays.equals(Arrays.copyOfRange(boardArray, 56, boardPos + 1), wqCastle) && castleInfo[1]){
                    move = boardPos - 2;
                    vetMove(5);
                }
            }

        }

        // if(piece < 0 && castleInfo[0] || piece > 0 && castleInfo[2]){
        //     move = boardPos + 2;
        //     vetMove(4);
        // }
        // if(piece < 0 && castleInfo[1] || piece > 0 && castleInfo[3]){
        //     if(boardArray[boardPos - 3] == 0){
        //         move = boardPos - 2;
        //         vetMove(5);
        //     }
            
            
        // }

        
    }
}
