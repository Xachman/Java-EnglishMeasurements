package tests;

import java.io.IOException;

import com.gti.measurements.EnglishMeasurement;
import com.gti.measurements.Fraction;


public class Divide {


    public void test() {
        try {
            EnglishMeasurement em = new EnglishMeasurement("12\"");
            EnglishMeasurement em2 = new EnglishMeasurement("1/2");
            
            System.out.println(em);
            System.out.println(em2);
            System.out.println(em.multiply(em2));
            Fraction fr1 = new Fraction("100000/1");
            Fraction fr2 = new Fraction("10000/1");

            //System.out.println(fr1.divide(fr2));
        } catch (IOException e) {

        }
    }


    //System.out.println(em.toString());
}
