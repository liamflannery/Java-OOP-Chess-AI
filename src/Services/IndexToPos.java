package Services;
import java.awt.Point;
import java.util.Map;
public class IndexToPos {
    /*
        input board position (0-63), return:
        [0] = column
        [1] = row
        [2] = x coord
        [3] = y coord
        [4] = squareSize
        [5] = pieceSize
    */
    static int squareSize = (int) Values.valueOf("Square Size");
    static int pieceSize = (int) Values.valueOf("Piece Size");
    private static Map<Integer, String> columnValues = Map.of(
            1, "A",
            2, "B",
            3, "C",
            4, "D",
            5, "E",
            6, "F",
            7, "G",
            8, "H"
        ); 
    public static final int[] Calculate(int index){
        int column = (index % 8) + 1;
        int row = (index / 8) + 1;
        int x = (index % 8) * squareSize;
        int y = (index / 8) * squareSize;
        
        int[] returnValue = new int[] {column, row, x, y, squareSize, pieceSize};
        
        return returnValue;
    }
    public static final String getCoord(int index){
        int column = (index % 8) + 1;
        int row = (index / 8) + 1;
        return columnValues.get(column) + "" + (Math.abs(8 - row) + 1); 
    }
}
