package epi.strings.kt

import epi.test_framework.EpiTest
import kotlin.jvm.JvmStatic
import epi.test_framework.GenericTest

object IntegerToRoman {

    val valArray = intArrayOf(1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1);
    val romanArray = arrayOf<String>("M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I");


    @JvmStatic
    @EpiTest(testDataFile = "roman_to_integer.tsv")
    fun intToRoman(num: Int): String {
        var input = num;
        val builder = StringBuilder();
        var index = 0;

        while (input > 0) {

            while (input >= valArray[index]) {
                input -= valArray[index];
                builder.append(romanArray[index]);
            }
            index++;
        }

        return builder.toString();
    }

    @JvmStatic
    fun main(args: Array<String>) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "RomanToInteger.java",
                                object : Any() {}.javaClass.enclosingClass)
                        .ordinal)
    }
}