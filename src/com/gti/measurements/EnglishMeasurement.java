package com.gti.measurements;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.lang.Integer.parseInt;

/**
 * Created by xach on 7/30/16.
 */
public class EnglishMeasurement {
    private Fraction inchFraction = new Fraction("0/1");
    static final int FOOT = 12;


    public EnglishMeasurement(String measurement) throws IOException {
        processMeasurementString(measurement);
    }
    public EnglishMeasurement(int foot, int inch, Fraction inchFraction) {
        int inches = (foot * 12) + inch;
        this.inchFraction = makeFraction(inches, inchFraction);
    }

    public EnglishMeasurement(int inch, Fraction inchFraction) {
        this.inchFraction = makeFraction(inch, inchFraction);
    }
    //
    private void processMeasurementString(String measurement) throws IOException {
       // System.out.println(measurement);
        // Validate all [0-9]'\s[0-9]"\s[0-9]\/[0-9]
        //Validate foot and inch [0-9]'\s[0-9]"
        //Validate inch and fraction [0-9]"\s[0-9]\/[0-9]
        //Validate foot [0-9]'
        //Validate inch [0-9]"
        //Validate Fraction [0-9]\/[0-9]


        Pattern p = Pattern.compile("[0-9]+'");
        Matcher m = p.matcher(measurement);
        System.out.println(measurement);
        int inch = 0;
        if(m.find()) {
          //  System.out.println("feet "+m.group(0));
            String foot = m.group(0).replace("'", "").trim();
            
            inch = parseInt(foot) * FOOT;
        }
        

        p = Pattern.compile("[0-9]+\"");
        m = p.matcher(measurement);
        if(m.find()) {
          //  System.out.println("inches "+m.group(0));
            String inches = m.group(0).replace("\"", "").trim();
            inch += parseInt(inches);
            
        }
        
        p = Pattern.compile("[0-9]+/[0-9]+");
        m = p.matcher(measurement);
        if(m.find()) {
            String fraction = m.group(0).trim();
          //  System.out.println("fraction "+fraction);
            this.inchFraction = makeFraction(inch, new Fraction(fraction));
        }else {
            this.inchFraction = makeFraction(inch, new Fraction("0/1"));
        }


    }
    public EnglishMeasurement(Fraction fraction) {
        inchFraction = fraction;
    }
    private void setToNoValue() {
        inchFraction = new Fraction(0, 1);
    }
    public EnglishMeasurement add(EnglishMeasurement measurement) {
        Fraction fraction = this.inchFraction().add(measurement.inchFraction());
        
        return new EnglishMeasurement(fraction);
    }
    
    public EnglishMeasurement subtract(EnglishMeasurement measurement) {
        Fraction fraction = this.inchFraction().subtract(measurement.inchFraction());
        
        return new EnglishMeasurement(fraction);
    }

    public EnglishMeasurement area(EnglishMeasurement measurement) {
        Fraction currentFraction = convertToFraction();
        Fraction measurementFraction = measurement.convertToFraction();
        
       // System.out.println(currentFraction);
        //System.out.println(measurementFraction);
        Fraction areaFraction = currentFraction.multiply(measurementFraction);
       // System.out.println(areaFraction);
        int inches = areaFraction.integer() / 12;
        Fraction fraction = new Fraction(areaFraction.numerator() % areaFraction.denominator(), areaFraction.denominator());
        return new EnglishMeasurement(inches, fraction);
    }
    
    public EnglishMeasurement multiply(EnglishMeasurement measurement) {
        Fraction currentFraction = convertToFraction();
        Fraction measurementFraction = measurement.convertToFraction();
        
        System.out.println(currentFraction);
        System.out.println(measurementFraction);
        Fraction multiplied = currentFraction.multiply(measurementFraction);
      //  System.out.println(multiplied);
        int inches = multiplied.integer();
        Fraction fraction = new Fraction(multiplied.numerator() % multiplied.denominator(), multiplied.denominator());
        System.out.println("multiplied "+inches+" "+fraction );
        return new EnglishMeasurement(inches, fraction);
    }
    
    public EnglishMeasurement divide(EnglishMeasurement measurement) {
        Fraction currentFraction = convertToFraction();
        Fraction measurementFraction = measurement.convertToFraction();

      //  System.out.println(currentFraction);
       // System.out.println(measurementFraction);
        Fraction divided = currentFraction.divide(measurementFraction);
     //   System.out.println(divided);
        int inches = divided.integer();

        Fraction fraction = new Fraction(divided.numerator() % divided.denominator(), divided.denominator());

        return new EnglishMeasurement(inches, fraction);
    }


    public Fraction convertToFraction() {
        //System.out.println(inch()*inchFraction().denominator() + inchFraction().numerator());
        return inchFraction();
    }
    
    private Fraction makeFraction(int inches, Fraction fraction) {
        Fraction newFraction = new Fraction(inches*fraction.denominator() + fraction.numerator(), fraction.denominator());
       // System.out.println(inches);
       // System.out.println("new fraction: "+newFraction);
        return newFraction;
    }
    public int inch() {
       // System.out.println(inchFraction().integer());
        return inchFraction().integer();
    }

    public Fraction inchFraction() {
        return inchFraction;
    }

    public String toString() {
        int foot = inch() / FOOT;
        int inches = inch() % FOOT;
        String fraction = "";
        if(inchFraction().proper().numerator() == 0) {
            fraction = "";
        }else{
            fraction = this.inchFraction().proper().toString();
        }
        return foot+"' "+inches+"\" "+fraction;
    }

    private boolean isInteger(String s, int radix) {
        if(s.isEmpty()) return false;
        for(int i = 0; i < s.length(); i++) {
            if(i == 0 && s.charAt(i) == '-') {
                if(s.length() == 1) return false;
                else continue;
            }
            if(Character.digit(s.charAt(i),radix) < 0) return false;
        }
        return true;
    }

    private boolean isInteger(String s) {
        return isInteger(s, 10);
    }
}
