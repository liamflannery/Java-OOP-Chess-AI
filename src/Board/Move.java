package Board;

import org.ietf.jgss.Oid;

import Services.IndexToPos;

public class Move {
    int origin;
    int destination; 
    int type;
    int piece;
    public Move(int origin, int destination, int type, int piece){
        this.origin = origin;
        this.destination = destination;
        this.type = type;
        this.piece = piece;
    }
    public int getOrigin(){
        return origin;
    }
    public int getDestination(){
        return destination;
    }
    public int getType(){
        return type;
    }

    public String toString(){
       
        return(piece + " " + "From: " + IndexToPos.getCoord(origin) + " to " + IndexToPos.getCoord(destination) + " type " + type);
    }
    
}
