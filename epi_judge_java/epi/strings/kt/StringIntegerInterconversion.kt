package epi.strings.kt

import epi.test_framework.EpiTest
import epi.test_framework.GenericTest
import epi.test_framework.TestFailure
import kotlin.math.abs

object StringIntegerInterconversion {

    @JvmStatic
    fun intToString(x: Int): String {
        if(x ==0) return "0";
        val isMinus = x < 0;
        var value = x;
        val builder = StringBuilder();
        while (value != 0) {
            builder.append(abs(value % 10));
            value /= 10;

        }

        if (isMinus) builder.append("-");
        builder.reverse();
        return builder.toString();
    }

    @JvmStatic
    fun stringToInt(s: String?): Int {
        val isMinus = s!![0] == '-';
        var result = 0;
        for (i in (if (Character.isDigit(s!![0])) 0 else 1) until s.length) {
            result = result * 10 + (s!![i] - '0');
        }
        if (isMinus) result = -result;
        return result;
    }

    @JvmStatic
    @EpiTest(testDataFile = "string_integer_interconversion.tsv")
    @Throws(TestFailure::class)
    fun wrapper(x: Int, s: String?) {
        if (intToString(x).toInt() != x) {
            throw TestFailure("Int to string conversion failed")
        }
        if (stringToInt(s) != x) {
            throw TestFailure("String to int conversion failed")
        }
    }

    @JvmStatic
    fun main(args: Array<String>) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "StringIntegerInterconversion.java",
                                object : Any() {}.javaClass.enclosingClass)
                        .ordinal)
    }
}