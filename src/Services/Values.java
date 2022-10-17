package Services;
import java.util.HashMap;
import java.util.Map;
import java.awt.Color;
public class Values {
    
    private static Map<String, Object> valuesDict;
    static boolean whiteAtBottom = true;
    //store global visual values
    public static final Object valueOf(String input){
        valuesDict = new HashMap<String, Object>();
        valuesDict.put("Light Square", new Color(214, 248, 214));
        valuesDict.put("Dark Square", new Color(93, 115, 126));
        valuesDict.put("Highlight", new Color(127,198,164));
        valuesDict.put("Square Size", 100);
        valuesDict.put("Piece Size", 100);


        return valuesDict.get(input);
    }
    public static final int upDirection(int piece){
        if(piece < 0){
            if(whiteAtBottom){
                return 1;
            }
            else{
                return -1;
            }
        }
        else if(piece > 0){
            if(whiteAtBottom){
                return -1;
            }
            else{
                return 1;
            }

        }
        return 0;
    }

    
}
