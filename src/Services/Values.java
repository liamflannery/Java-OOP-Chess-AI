package Services;
import java.util.HashMap;
import java.util.Map;
import java.awt.Color;
public class Values {
    
    private static Map<String, Object> valuesDict;
    
    //store global visual values
    public static final Object valueOf(String input){
        valuesDict = new HashMap<String, Object>();
        valuesDict.put("Light Square", new Color(214, 248, 214));
        valuesDict.put("Dark Square", new Color(93, 115, 126));
        valuesDict.put("Highlight", new Color(127,198,164));
        valuesDict.put("Square Size", 100);
        valuesDict.put("Piece Size", 65);


        return valuesDict.get(input);
    }
    //return array with number of squares to the edge of the board 
    //in every direction, from every square 
    public static final int[][] computeSquares(){
        int[][] numSquaresToEdge = new int[64][];
        for(int squareIndex = 0; squareIndex < numSquaresToEdge.length; squareIndex++){
            int y = squareIndex / 8;
            int x = squareIndex - y * 8;

            int north = 7 - y;
            int south = y;
            int west = x;
            int east = 7 - x;
            numSquaresToEdge[squareIndex] = new int[8];
            numSquaresToEdge[squareIndex][0] = north;
            numSquaresToEdge[squareIndex][1] = south;
            numSquaresToEdge[squareIndex][2] = west;
            numSquaresToEdge[squareIndex][3] = east;
            numSquaresToEdge[squareIndex][4] = Math.min(north, west);
            numSquaresToEdge[squareIndex][5] = Math.min(south, east);
            numSquaresToEdge[squareIndex][6] = Math.min(north, east);
            numSquaresToEdge[squareIndex][7] = Math.min(south, west);
        }
        return numSquaresToEdge;
    }
    
}
