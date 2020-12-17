package epi.strings;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.TestFailure;

public class StringIntegerInterconversion {

    public static String intToString(int x) {
        if(x ==0) return "0";

        boolean isMinus = x < 0;
        StringBuilder builder = new StringBuilder();
        while (x != 0) {
            builder.append(Math.abs(x % 10));
            x /= 10;
        }

        if (isMinus) builder.append("-");
        builder.reverse();
        return builder.toString();
    }

    public static int stringToInt(String s) {
        int result = 0;
        boolean isMinus = s.charAt(0) == '-';
        for (int i = isNum(s.charAt(0)) ? 0 : 1; i < s.length(); i++) {
            char ch = s.charAt(i);
            result = result * 10 + (ch - '0');
        }
        if (isMinus) result = -result;
        return result;
    }

    private static boolean isNum(char ch) {
        return ch >= '0' && ch <= '9';
    }

    @EpiTest(testDataFile = "string_integer_interconversion.tsv")
    public static void wrapper(int x, String s) throws TestFailure {
        if (Integer.parseInt(intToString(x)) != x) {
            throw new TestFailure("Int to string conversion failed");
        }
        if (stringToInt(s) != x) {
            throw new TestFailure("String to int conversion failed");
        }
    }

    public static void main(String[] args) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "StringIntegerInterconversion.java",
                                new Object() {
                                }.getClass().getEnclosingClass())
                        .ordinal());
    }
}
