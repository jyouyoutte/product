package com.jonathan.exception;

public class RationalStart {
    public static void main(String[] args) {

        try{
            Rational r1 = new Rational(2, 1);
            System.out.println(r1);
            Rational r2 = new Rational(1, 0);
            System.out.println(r2);
        } catch (RationalException e){
            System.out.println("Caught RationalException: " + e.getMessage());
        }
    }
}

