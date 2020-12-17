package epi.strings.kt

import epi.test_framework.EpiTest
import epi.test_framework.GenericTest

object SpreadSheetEncodingIII {
    @JvmStatic
    fun ssEncodeColID(code: Int?): String {
        if (code == null || code <= 0) return "";

        var value = code - 1;
        val builder = StringBuilder();
        while (value != 0) {
            builder.append(value % 26);
            value /= 26;
        }

        builder.reverse();
        return builder.toString();
    }

    @JvmStatic
    fun main(args: Array<String>) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "SpreadsheetEncoding.java",
                                object : Any() {}.javaClass.enclosingClass)
                        .ordinal)
    }
}