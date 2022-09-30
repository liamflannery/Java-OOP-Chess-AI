package Pieces;
import java.awt.Graphics;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.awt.Point;
public class Piece {
    int x;
    int y;
    int column;
    int row;
    int boardPos;
    BufferedImage image;
    Boolean isWhite;
    String pieceName;
    int[] pieceService; 
    public Piece(int inBoardPos, boolean white, String inPieceName){
        boardPos = inBoardPos;
        isWhite = white;
        pieceName = inPieceName;
        pieceService = Services.IndexToPos.Calculate(boardPos);
        x = pieceService[2] + pieceService[4]/6;
        y = pieceService[3] + pieceService[4]/6;
    }
    
    public void paint(Graphics g) {
        if(isWhite){
            try{
                image = ImageIO.read(new File(".\\img\\white_" + pieceName + ".png"));
                }
            catch(IOException e){
                    System.out.println("Failed to load: .\\img\\white_" + pieceName + ".png");
                }
        }
        else{
            try{
                image = ImageIO.read(new File(".\\img\\black_" + pieceName + ".png"));
                }
            catch(IOException e){
                    System.out.println("Failed to load: .\\img\\black_" + pieceName + ".png");
                }  
        }
        draw(g);
       
    }
   
    public void draw(Graphics g){
        g.drawImage(image, x, y, pieceService[5], pieceService[5], null);
    }
    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return this.getClass().toString();
    }
}
