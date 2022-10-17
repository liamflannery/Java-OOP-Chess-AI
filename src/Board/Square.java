package Board;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import Services.Values;

import java.awt.Color;
public class Square extends Rectangle{
  
    Color color;
    int squareNumber;
    int column;
    int row;
    int size;
    int[] indexService;
    Rectangle bounds;
    boolean lightSquare;
    private BufferedImage image;
    String URL = "img/";
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
            lightSquare = true;
        }
        else{
            lightSquare = false;
        }
        if(lightSquare){
            try{
                image = ImageIO.read(new File(URL+"light_square.png"));
                }
            catch(IOException e){
                    System.out.println("Failed to load: " + URL+ "light_square.png");
                }
        }
        else{
            try{
                image = ImageIO.read(new File(URL+"dark_square.png"));
                }
            catch(IOException e){
                    System.out.println("Failed to load: " + URL+ "dark_square.png");
                }
        }
    }
    public void paint(Graphics g, Point mousePos) {

   
        
        g.drawImage(image, x, y, size, size, null);
       
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

    public void highlight() {
        if(lightSquare){
            try{
                image = ImageIO.read(new File(URL+"light_square_highlight.png"));
                }
            catch(IOException e){
                    System.out.println("Failed to load: " + URL+ "light_square_highlight.png");
                }
        }
        else{
            try{
                image = ImageIO.read(new File(URL+"dark_square_highlight.png"));
                }
            catch(IOException e){
                    System.out.println("Failed to load: " + URL+ "dark_square_highlight.png");
                }
        }
    }

    public void unHighlight() {
        setColour();
    }
    
    
}