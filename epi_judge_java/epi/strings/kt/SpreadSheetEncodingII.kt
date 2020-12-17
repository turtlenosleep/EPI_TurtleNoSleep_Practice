package epi.strings.kt

import epi.test_framework.EpiTest
import epi.test_framework.GenericTest

object SpreadSheetEncodingII {
    @EpiTest(testDataFile = "spreadsheet_encoding.tsv")
    @JvmStatic
    fun ssDecodeColID(col: String?): Int {
        var result =0;
        for(ch in col!!.toCharArray()){
            result = result*26 + (ch-'A');

        }
        return result;
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