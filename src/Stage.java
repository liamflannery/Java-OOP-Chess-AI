import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.awt.Point;
import Board.Board;
import Board.Competitor;
public class Stage {
   Board board;
   public int turn = 1;
   Competitor white;
   Competitor black;
    public Stage() {
        board = new Board(white, black, turn);
    }
       
        
    

    public void paint(Graphics g, Point mousePos) {
        board.paint(g, mousePos);
        underMouse(g, mousePos);
        
        
        
    }
    public void underMouse(Graphics g, Point mouseLoc){
       
    }




    public void mousePressed(int x, int y) {
        board.mousePressed(x,y);
        
    }




    public void mouseReleased(int x, int y) {
        board.mouseReleased(x,y);
    }
    
    
    

}