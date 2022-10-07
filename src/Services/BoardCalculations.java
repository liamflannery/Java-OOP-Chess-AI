package Services;

public class BoardCalculations {
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
