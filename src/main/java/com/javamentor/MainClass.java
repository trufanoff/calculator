package com.javamentor;

import java.util.Scanner;

public class MainClass {
    static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
        String str = sc.nextLine();
        Calculator calc = new Calculator(str);
        calc.getResult();
    }

}
