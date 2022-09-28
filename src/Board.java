import java.awt.Graphics;
import java.awt.Point;
import java.util.function.Consumer;
public class Board {
    Square[] squares = new Square[64];
    public Board(){
        for(int i = 0; i < 64; i++){
            squares[i] = new Square(i);
        }
    }
    public void paint(Graphics g, Point mousePos) {
        Consumer<Square> paint = square -> {
            square.paint(g, mousePos);
        };
        doToEachSquare(paint);
    }
    public void doToEachSquare(Consumer<Square> func) {
        for(int i=0; i < squares.length; i++) {
                func.accept(squares[i]);
        }
      }
}
