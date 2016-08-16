import java.io.IOException;

import tests.Add;
import tests.Divide;
import tests.Subtract;
import tests.Subtract;

public class Main {

    public static void main(String[] args) {
	    // write your code here
	    System.out.println("Hello World!");
	    
	    Divide d = new Divide();
	    d.test();

        Add add = new Add();
        try {
            add.test();
        }catch(IOException e) {
            e.printStackTrace();
        }
        
        
        Subtract subtract = new Subtract();
        subtract.test();
    }
}
