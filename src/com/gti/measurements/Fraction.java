package com.gti.measurements;

import static java.lang.Integer.parseInt;

/**
 * Created by xach on 7/31/16.
 */
public class Fraction {
    private int numerator;
    private int denominator;

    public Fraction(String fraction) {
        String[] fractionArray = fraction.split("/");

        numerator = parseInt(fractionArray[0].trim());
        denominator = parseInt(fractionArray[1].trim());
    }
    public Fraction(int numerator, int denominator) {
        this.numerator = numerator;
        this.denominator = denominator;
    }
    public int numerator() {
        return numerator;
    }

    public int denominator() {
        return denominator;
    }

    private int GCD(int a, int b) {
        if (b==0) return a;
        return GCD(b,a%b);
    }


    public Fraction add(Fraction fraction) {
        //add fractions
        int commonDenominator  = this.denominator * fraction.denominator;
        int numeratorOne = this.numerator * fraction.denominator;
        int numeratorTwo = fraction.numerator * this.denominator;
        int addedNumerator = numeratorOne + numeratorTwo;
        return simplify(addedNumerator, commonDenominator);
    }

    public Fraction multiply(Fraction fraction) {
        Fraction fractionOne = this;
        Fraction fractionTwo = fraction;

        int denominator = fractionOne.denominator() * fractionTwo.denominator();
        int numerator = fractionOne.numerator() * fractionTwo.numerator();

        return simplify(numerator, denominator);
    }
    public Fraction divide(Fraction fraction) {
        Fraction fractionOne = this;
        Fraction fractionTwo = fraction;

        int denominator = fractionOne.denominator() * fractionTwo.numerator();
        int numerator = fractionOne.numerator() * fractionTwo.denominator();

        return simplify(numerator, denominator);
    }
    public Fraction subtract(Fraction fraction) {
        int commonDenominator  = this.denominator * fraction.denominator;
        int numeratorOne = this.numerator * fraction.denominator;
        int numeratorTwo = fraction.numerator * this.denominator;
        int addedNumerator = numeratorOne - numeratorTwo;
        return simplify(addedNumerator, commonDenominator);
    }
    private Fraction simplify(int numerator, int denominator) {
        int greatestCommonDivisor = GCD(numerator, denominator);
        return new Fraction(numerator / greatestCommonDivisor, denominator / greatestCommonDivisor);
    }
    private Fraction simplify(Fraction fraction) {
        return simplify(fraction.numerator(), fraction.denominator());
    }

    public int integer() {
        return numerator() / denominator();
    }
    
    public Fraction proper() {
        return simplify(numerator() % denominator(), denominator());
    }
    public String toString() {
        return  numerator+"/"+denominator;
    }
}
