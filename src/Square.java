import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;

public class Square extends Rectangle{
  
    Color color;
    int squareNumber;
    int column;
    int row;
    int size;
    int[] indexService;
    Rectangle bounds;
    public Square(int pos){
    
        squareNumber = pos;
        indexService = Services.IndexToPos.Calculate(squareNumber);
        this.size = indexService[4];
        
        setPosition();
        setColour();
        
    }

    void setPosition(){
        column = indexService[0];
        row = indexService[1];
        this.x = indexService[2];
        this.y = indexService[3];
        bounds = new Rectangle(new Point(x,y), new Dimension(size, size));
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

    public boolean contains(Point p) {
        if (p != null) {
            if(bounds.contains(p)){
                return true;
            }
        } 
        return false;
    }

    public String toString(){
        return "Square: " + squareNumber + " col: " + column + " row: " + row;
        
    }
    
    
}