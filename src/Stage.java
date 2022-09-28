import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
public class Stage {
   Board board;
    public Stage() {
        board = new Board();
    }
       
        
    

    public void paint(Graphics g, Point mousePos) {
        board.paint(g, mousePos);
        underMouse(g, mousePos);
        
        
        
    }
    public void underMouse(Graphics g, Point mouseLoc){
       
    }
    public void mouseClicked(int x, int y){
        
    }
    
    

}