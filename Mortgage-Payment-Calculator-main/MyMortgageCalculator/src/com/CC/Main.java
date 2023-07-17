package com.CC;

import java.text.NumberFormat;
import java.util.Scanner;

public class Main {
    final static byte PERCENT = 100;
    final static byte MONTHS_IN_YEAR = 12;

    public static void main(String[] args) {

        double principal = readNumber("Principal Amount: ", 1000, 1_000_000);
        float annualInterest = (float) readNumber("Annual Interest Rate: ", 1,30);
        byte years=(byte)readNumber("Period(Years): ",1,30);

        printMortgage(principal,annualInterest,years);
        printPaymentSchedule(principal,annualInterest,years);

    }

    public static double readNumber(String prompt, double min, double max){
        double value;
        Scanner scanner= new Scanner(System.in);
        while(true){
            System.out.print(prompt);
            value=scanner.nextDouble();
            if(value>=min && value<=max)
                break;
            System.out.println("Please!! Enter a data between "+min+" and "+ max+".");
        }
        return value;
    }

    private static void printMortgage(double principal,float annualInterest, byte years){
        double mortgage = calculateMortgage(principal,annualInterest,years);
        String mortgageFormatted =NumberFormat.getCurrencyInstance().format(mortgage);
        System.out.println("\nMORTGAGE\n________");
        System.out.println("Monthly Payments: "+mortgageFormatted);
    }

    private static double calculateMortgage(double principal,float annualInterest, byte years){
        float monthlyInterest = annualInterest/ PERCENT/MONTHS_IN_YEAR;
        float numberOfPayments = years *MONTHS_IN_YEAR;

        double mortgage = principal
                * (monthlyInterest * Math.pow(1 + monthlyInterest, numberOfPayments))
                / (Math.pow(1 + monthlyInterest, numberOfPayments) - 1);

        return mortgage;
    }

    public static void printPaymentSchedule(double principal,float annualInterest, byte years){
        System.out.println("\nPAYMENT SCHEDULE\n________________");
        for(short month = 1; month<= years*MONTHS_IN_YEAR; month++){
            double balance = calculateBalance(principal,annualInterest,years,month);
            System.out.println(NumberFormat.getCurrencyInstance().format(balance));
            }
    }

    public static double calculateBalance(double principal,float annualInterest, byte years,short month){
        float monthlyInterest = annualInterest / PERCENT / MONTHS_IN_YEAR;
        float numberOfPayments = years * MONTHS_IN_YEAR;

        double balance = principal
                * (Math.pow(1 + monthlyInterest, numberOfPayments) - Math.pow(1 + monthlyInterest, month))
                / (Math.pow(1 + monthlyInterest, numberOfPayments) - 1);

        return balance;
    }
}