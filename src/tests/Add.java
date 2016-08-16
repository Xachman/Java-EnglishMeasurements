package tests;

import java.io.IOException;

import com.gti.measurements.EnglishMeasurement;

public class Add {
    
    
    public EnglishMeasurement getPullback(EnglishMeasurement tileHeight, EnglishMeasurement groutJoint, int tiles) throws IOException {
        EnglishMeasurement tilePullBackDistance = new EnglishMeasurement("0' 0\"");
        System.out.println("tileHight: "+ tileHeight);
        System.out.println("joint: "+groutJoint);
        
        
        System.out.println("tiles:"+ tiles);
        for(int i = 0; i < tiles; i++) {
            tilePullBackDistance = tilePullBackDistance.add(tileHeight).add(groutJoint);
            System.out.println("pullback "+tilePullBackDistance);
        }

        return tilePullBackDistance;
    }
    
    public void test()  throws IOException{
        EnglishMeasurement em = getPullback(new EnglishMeasurement("12\""), new EnglishMeasurement("1/4"), 2);
        System.out.println(em);
        
        EnglishMeasurement em2 = new EnglishMeasurement("12\" 1/2");
        System.out.println("em2 value: "+em2);
        EnglishMeasurement em3 = new EnglishMeasurement("12' 1/4");
        System.out.println("em3 Value: "+em3);        
        System.out.println(em2.add(em3));
        
    }
}
