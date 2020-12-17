package epi.strings.kt

import epi.test_framework.EpiTest
import kotlin.jvm.JvmStatic
import epi.test_framework.GenericTest

object CheckRomanValid {

    val romanMap = mapOf<Char, Int>(
            'I' to 1,
            'V' to 5,
            'X' to 10,
            'L' to 50,
            'C' to 100,
            'D' to 500,
            'M' to 1000
    )

    @JvmStatic
    @EpiTest(testDataFile = "check_roman_valid.tsv")
    fun checkRomanValid(s: String?): Boolean {
        if (s.isNullOrEmpty()) return false;

        var pre = romanMap[s[s.length - 1]] ?: 0;
        var current = pre;
        var value: Int?;
        var isDecending = false;

        for (i in s.length - 2 downTo 0) {
            value = romanMap[s[i]] ?: 0;

            if (value < pre) {
                if (isDecending || !isValidSucceed(s[i], s[i + 1])) return false;

                isDecending = true;
                current -= value;

            } else {
                if (value < current) return false;

                isDecending = false;
                current = value;
            }

            pre = value;
        }

        return true;
    }

    private fun isValidSucceed(ch: Char, pre: Char): Boolean {
        if (pre == 'M' || pre == 'D') return ch == 'C';
        if (pre == 'C' || pre == 'L') return ch == 'X';
        if (pre == 'X' || pre == 'V') return ch == 'I';
        return false;
    }

    @JvmStatic
    fun main(args: Array<String>) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "CheckRomanValid.java",
                                object : Any() {}.javaClass.enclosingClass)
                        .ordinal)
    }
}