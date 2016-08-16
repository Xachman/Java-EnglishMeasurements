package tests;

import java.io.IOException;

import com.gti.measurements.EnglishMeasurement;

public class Subtract {
    
    public void test() {
       
        try {
            EnglishMeasurement em1 = new EnglishMeasurement("12' 6\" 1/2");
            EnglishMeasurement em2 = new EnglishMeasurement("12'");
            System.out.print(em1.subtract(em2));
        }catch(IOException e){
            e.printStackTrace();
        }

    }
}
