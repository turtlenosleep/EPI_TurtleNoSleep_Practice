package epi.strings;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

public class ConvertBase {
    @EpiTest(testDataFile = "convert_base.tsv")

    public static String convertBase(String numAsString, int b1, int b2) {
        int decimal = 0;
        boolean isMinus = numAsString.charAt(0) == '-';
        for (int i = isMinus ? 1 : 0; i < numAsString.length(); i++) {
            char ch = numAsString.charAt(i);
            if (isNum(ch)) decimal = decimal * b1 + (ch - '0');
            else if (isAlphabet(ch)) decimal = decimal * b1 + (ch - 'A') + 10;
        }
        if(decimal ==0) return "0";

        StringBuilder builder = new StringBuilder();
        while (decimal != 0) {
            int newVal = decimal % b2;
            if (newVal < 10) builder.append(newVal);
            else builder.append((char) ('A' + (newVal - 10)));
            decimal /= b2;
        }
        if (isMinus) builder.append('-');
        builder.reverse();
        return builder.toString();
    }

    private static boolean isAlphabet(char ch) {
        return ch >= 'A' && ch <= 'Z';
    }

    private static boolean isNum(char ch) {
        return ch <= '9' && ch >= '0';
    }

    public static void main(String[] args) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "ConvertBase.java",
                                new Object() {
                                }.getClass().getEnclosingClass())
                        .ordinal());
    }
}
