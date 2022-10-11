package Services;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.awt.Color;
public class Printer {
    
   
    
    //prints board to the terminal
    public static final void printArray(int[] array) {
        System.out.println(" / / / / / / / / \n");
        int counter = 0;
        List<String> rows = new ArrayList<String>();
        String row = ""; 
        for(int i = 0; i < array.length; i++){
            if(counter < 8){
                if(array[i] < 0){
                    row += array[i] + ",";
                }
                else{
                    row += " " + array[i] + ",";
                }
                counter++;
            }
            else{
                rows.add(row);
                if(array[i] < 0){
                    row =  array[i] + ",";
                }
                else{
                    row =  " " + array[i] + ",";
                }
                
                counter = 1;
            }
            
        }
        if(!rows.contains(row)){
            rows.add(row);
        }
       for(String outRow : rows){
        System.out.println(outRow);
       }
    }
    
    
}
