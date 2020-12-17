package epi.strings;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

public class IsStringPalindromic {
    @EpiTest(testDataFile = "is_string_palindromic.tsv")

    public static boolean isPalindromic(String s) {
        int i1 = 0, i2 = s.length() - 1;
        while (i1 < i2) {

            while (!Character.isLetter(s.charAt(i1)) && i1<i2) i1++;
            while (!Character.isLetter(s.charAt(i2)) && i1<i2) i2--;

            if (s.charAt(i1) != s.charAt(i2)) return false;
        }

        return true;
    }

    public static void main(String[] args) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "IsStringPalindromic.java",
                                new Object() {
                                }.getClass().getEnclosingClass())
                        .ordinal());
    }
}
