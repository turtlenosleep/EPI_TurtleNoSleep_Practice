package epi.strings;

import epi.test_framework.EpiTest;
import epi.test_framework.EpiTestComparator;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.BiPredicate;

public class PhoneNumberMnemonic {
    static String[] map = new String[]{"0", "1", "ABC", "DEF", "GHI", "JKL", "MNO", "PQRS", "TUV", "WXYZ"};


    @EpiTest(testDataFile = "phone_number_mnemonic.tsv")
    public static List<String> phoneMnemonic(String phoneNumber) {
        List<String> result = new ArrayList();
        if (phoneNumber == null || phoneNumber.isEmpty()) return result;

        findAll(phoneNumber, 0, new StringBuilder(), result);
        return result;
    }

    private static void findAll(String number, int index, StringBuilder builder, List<String> result) {
        if (builder.length() == number.length()) {
            result.add(builder.toString());
            return;
        }
        int num = number.charAt(index) - '0';
        String input = map[num];
        for (int i = 0; i < input.length(); i++) {
            builder.append(input.charAt(i));
            findAll(number, index + 1, builder, result);
            builder.setLength(builder.length() - 1);
        }
    }

    @EpiTestComparator
    public static boolean comp(List<String> expected, List<String> result) {
        if (result == null) {
            return false;
        }
        Collections.sort(expected);
        Collections.sort(result);
        return expected.equals(result);
    }

    public static void main(String[] args) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "PhoneNumberMnemonic.java",
                                new Object() {
                                }.getClass().getEnclosingClass())
                        .ordinal());
    }
}
