package epi.strings.kt

import epi.test_framework.EpiTest
import epi.test_framework.GenericTest
import kotlin.system.exitProcess

object ConvertBase {
    @EpiTest(testDataFile = "convert_base.tsv")
    @JvmStatic
    fun convertBase(numAsString: String, b1: Int, b2: Int): String {
        var decimal = 0
        val isMinus = numAsString[0] == '-'
        for (i in (if (isMinus) 1 else 0) until numAsString.length) {
            val ch = numAsString[i]
            if (isNum(ch)) decimal = decimal * b1 + (ch - '0') else if (isAlphabet(ch)) decimal = decimal * b1 + (ch - 'A') + 10
        }
        if (decimal == 0) return "0"
        val builder = StringBuilder()
        while (decimal != 0) {
            val newVal = decimal % b2
            if (newVal < 10) builder.append(newVal) else builder.append(('A'.toInt() + (newVal - 10)).toChar())
            decimal /= b2
        }
        if (isMinus) builder.append('-')
        builder.reverse()
        return builder.toString()
    }

    @JvmStatic
    private fun isAlphabet(ch: Char): Boolean {
        return ch in 'A'..'Z'
    }

    @JvmStatic
    private fun isNum(ch: Char): Boolean {
        return ch in '0'..'9'
    }

    @JvmStatic
    fun main(args: Array<String>) {
        exitProcess(
                GenericTest
                        .runFromAnnotations(args, "ConvertBase.java",
                                object : Any() {}.javaClass.enclosingClass)
                        .ordinal)
    }
}