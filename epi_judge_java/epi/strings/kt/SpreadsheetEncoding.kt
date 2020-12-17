package epi.strings.kt

import epi.test_framework.EpiTest
import epi.test_framework.GenericTest

object SpreadsheetEncoding {
    @EpiTest(testDataFile = "spreadsheet_encoding.tsv")
    @JvmStatic
    fun ssDecodeColID(col: String?): Int {
        if(col == null) return 0;

        var result =0
        for(i in col.indices){
            result = result * 26 + (col[i] - 'A'+1)

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