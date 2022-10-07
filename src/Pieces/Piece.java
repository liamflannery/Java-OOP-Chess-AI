package Pieces;
import java.awt.Graphics;
import javax.imageio.ImageIO;
import javax.swing.JFrame;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.awt.Point;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
public class Piece{
    int x;
    int y;
    int column;
    int row;
    int boardPos;
    BufferedImage image;
    Boolean isWhite;
    String pieceName;
    int[] pieceService; 
    Point imgPoint;
    Rectangle bounds;
    boolean drag = false; 
    String URL = "img/";
    boolean moved = false;
    public Piece(int inBoardPos, boolean white, String inPieceName){
        boardPos = inBoardPos;
        isWhite = white;
        pieceName = inPieceName;
        placePieceAtSquare();
        
        
    }
    
    
    public void paint(Graphics g, Point mousePos) {
        if(isWhite){
            try{
                image = ImageIO.read(new File(URL+"white_" + pieceName + ".png"));
                }
            catch(IOException e){
                    System.out.println("Failed to load: " + URL+ "white_" + pieceName + ".png");
                }
        }
        else{
            try{
                image = ImageIO.read(new File(URL+"black_" + pieceName + ".png"));
                }
            catch(IOException e){
                    System.out.println("Failed to load: " + URL+ "black_" + pieceName + ".png");
                }  
        }
        draw(g, mousePos);
       
    }
   
                       
    public void draw(Graphics g, Point mousePos){
        if(drag){
            x = mousePos.x - 40;
            y = mousePos.y - 40;
        }
        g.drawImage(image, x, y, pieceService[5], pieceService[5], null);
      
        
    }
    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return this.getClass().getName();
    }
    public Point getPos(){
        return new Point(x,y);
    }


    public void dragPiece() {
        drag = true;
    }


    public void dropPiece() {
        drag = false;
        placePieceAtSquare();

    }

    private void placePieceAtSquare(){
        pieceService = Services.IndexToPos.Calculate(boardPos);
        x = pieceService[2] + pieceService[4]/6;
        y = pieceService[3] + pieceService[4]/6;
        imgPoint = new Point(x,y);
        bounds = new Rectangle(imgPoint, new Dimension(pieceService[5], pieceService[5]));
    }
    
    public boolean pointAtPiece(Point mousePos){
        if(mousePos != null){
            if(bounds.contains(mousePos)) {
                return true;
            }
        }
        return false;
    }
 

    public int getBoardPos() {
        return boardPos;
    }


    public void updatePos(int destination) {
        boardPos = destination;
        moved = true;
        placePieceAtSquare();
    }


    public boolean hasMoved() {
        return moved;
    }
}
