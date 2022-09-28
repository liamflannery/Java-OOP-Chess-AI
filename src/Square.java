import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;

public class Square extends Rectangle{
    static int size = 87;
    Color color;
    int squareNumber;
    int column;
    int row;
    public Square(int pos){
        squareNumber = pos;
        
        setPosition();
        setColour();
    }

    void setPosition(){
        x = (squareNumber % 8) * size;
        y = (squareNumber / 8) * size;
        column = (squareNumber % 8) + 1;
        row = (squareNumber / 8) + 1;
    }
    void setColour(){
        if((row + column) % 2 == 0){
            color = new Color(33, 52, 95);
        }
        else{
            color = new Color(227, 212, 213);
        }
    }
    void paint(Graphics g, Point mousePos) {
        g.setColor(color);
        g.fillRect(x,y,size,size);
        g.setColor(Color.BLACK);
        g.drawRect(x,y,size,size);
    }
    public String toString(){
        return "Square: " + squareNumber + " col: " + column + " row: " + row;
        
    }
    
    
}