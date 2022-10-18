package Board;

import org.ietf.jgss.Oid;

public class Move {
    int origin;
    int destination; 
    int type;
    public Move(int origin, int destination, int type){
        this.origin = origin;
        this.destination = destination;
        this.type = type;
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
        return("From: " + origin + " to " + destination + " type " + type);
    }
    
}
