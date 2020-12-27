package epi.strings.kt

import epi.test_framework.EpiTest
import kotlin.Throws
import epi.test_framework.TestFailure
import kotlin.jvm.JvmStatic
import epi.test_framework.GenericTest

object RunLengthCompression {

    @JvmStatic
    fun decoding(s: String?): String {
        if (s.isNullOrEmpty()) return "";

        val builder = StringBuilder();
        var count = 0;

        for (i in s.indices) {
            if (isDigit(s[i])) {
                count = count * 10 + (s[i] - '0')

            } else {
                if (count > 0) {
                    while (count > 0) {
                        builder.append(s[i])
                        count--;
                    }

                } else {
                    builder.append(s[i]);
                }
            }
        }

        return builder.toString();
    }

    private fun isDigit(ch: Char): Boolean = ch in '0'..'9';

    @JvmStatic
    fun encoding(s: String?): String {
        if (s.isNullOrEmpty()) return "";
        val builder = StringBuilder();
        var count = 1;
        for (i in 1..s.length) {
            if (i == s.length || s[i] != s[i - 1]) {
                builder.append(count);
                builder.append(s[i - 1]);
                count = 1;

            } else {
                count++;
            }
        }

        return builder.toString();
    }

    @EpiTest(testDataFile = "run_length_compression.tsv")
    @JvmStatic
    @Throws(TestFailure::class)
    fun rleTester(encoded: String, decoded: String) {
        if (decoding(encoded) != decoded) {
            throw TestFailure("Decoding failed")
        }
        if (encoding(decoded) != encoded) {
            throw TestFailure("Encoding failed")
        }
    }

    @JvmStatic
    fun main(args: Array<String>) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "RunLengthCompression.java",
                                object : Any() {}.javaClass.enclosingClass)
                        .ordinal)
    }
}