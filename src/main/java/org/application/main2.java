package org.application;

import java.util.Scanner;

public class main2 {
    public static void main(String[] args) {
        while(true) {

            Scanner s = new Scanner(System.in);
            System.out.println("enter a  valid phone number");
            String el = s.nextLine().trim();
            checkNumber(el);

        }
    }

    public static void checkNumber(String el){
        if (el.startsWith("+")) {
            for (int i = 1; i < el.length(); i++) {
                if (!Character.isDigit(el.charAt(i))) {
                    System.out.printf("There is a letter, the char %c at position %d is not valid%n",
                            el.charAt(i),
                            el.indexOf(el.charAt(i)));

                } else {
                    System.out.println(el + " is a valid number");
                    break;
                }
            }
        } else {
            System.out.println(el + " need a prefix!");
        }
    }
}
