package Services;
import java.awt.Point;
public class IndexToPos {
    /*
        [0] = column
        [1] = row
        [2] = x coord
        [3] = y coord
        [4] = squareSize
        [5] = pieceSize
    */
    static int squareSize = 100;
    static int pieceSize = 65;
    public final static int[] Calculate(int index){
        int column = (index % 8) + 1;
        int row = (index / 8) + 1;
        int x = (index % 8) * squareSize;
        int y = (index / 8) * squareSize;
        int[] returnValue = new int[] {column, row, x, y, squareSize, pieceSize};
        
        return returnValue;
    }
}
