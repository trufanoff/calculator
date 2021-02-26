package com.javamentor;

public class Calculator {

    private String[] tokens;
    private int a;
    private int b;
    private String operation;
    private final String numRegex = "[0-9]+";
    private final String romRegex = "[IVXLCDM]+";
    private boolean romFlag;
    private int res;


    public Calculator(String expr) {
        this.tokens = expr.split(" ");
        setValues(tokens);
    }

    private void setValues(String[] tokens) {
        String tempA = tokens[0];
        String tempB = tokens[2];
        this.operation = tokens[1];
        if (tempA.matches(romRegex) && tempB.matches(romRegex)) {
            romFlag = true;
        }
        if (tempA.matches(numRegex) == tempB.matches(numRegex)) {
            a = getNumber(tempA);
            b = getNumber(tempB);
            if (a < 0 || a > 10 || b < 0 || b > 10) {
                throw new RuntimeException(String.format("The calculator accepts numbers only from 1 to 10 inclusive, but a = %d and b = %d", a, b));
            }
        } else {
            throw new RuntimeException(String.format("The variables entered in a different format: %s and %s", tempA, tempB));
        }

    }

    private int getNumber(String t) {
        try {
            return Integer.parseInt(t);
        } catch (NumberFormatException e) {
            try {
                return getNumberFromRomNum(t);
            } catch (NumberFormatException ex) {
                ex.printStackTrace();
            }
        }
        return -1;
    }

    private int getNumberFromRomNum(String t) {
        switch (t) {
            case "I":
                return 1;
            case "II":
                return 2;
            case "III":
                return 3;
            case "IV":
                return 4;
            case "V":
                return 5;
            case "VI":
                return 6;
            case "VII":
                return 7;
            case "VIII":
                return 8;
            case "IX":
                return 9;
            case "X":
                return 10;
            default:
                throw new IllegalArgumentException(String.format("There is no such digit like '%s'", t));
        }
    }

    public void getResult() {
        switch (this.operation) {
            case "*":
                res = a * b;
                break;
            case "/":
                res = a / b;
                break;
            case "+":
                res = a + b;
                break;
            case "-":
                res = a - b;
                break;
            default:
                throw new IllegalArgumentException(String.format("There is no such type of operation like '%s'", this.operation));
        }
        if (romFlag) {
            System.out.println(intToRoman(res));
        } else {
            System.out.println(res);
        }
    }

    public static String intToRoman(int num) {
        StringBuilder sb = new StringBuilder();
        int times = 0;
        String[] romans = new String[] { "I", "IV", "V", "IX", "X", "XL", "L",
                "XC", "C"};
        int[] ints = new int[] { 1, 4, 5, 9, 10, 40, 50, 90, 100};
        for (int i = ints.length - 1; i >= 0; i--) {
            times = num / ints[i];
            num %= ints[i];
            while (times > 0) {
                sb.append(romans[i]);
                times--;
            }
        }
        return sb.toString();
    }
}
