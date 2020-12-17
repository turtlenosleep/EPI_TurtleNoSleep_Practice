package epi.strings.kt

import epi.test_framework.EpiTest
import kotlin.jvm.JvmStatic
import epi.test_framework.GenericTest

object RomanToInteger {

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
    @EpiTest(testDataFile = "roman_to_integer.tsv")
    fun romanToInteger(s: String?): Int {
        if (s == null) return 0;
        var prev = romanMap[s[s.length-1]]?:0;
        var value: Int? = null;
        var total = 0;

        for (i in s.length -2 downTo 0) {
            value = romanMap[s[i]]?:0;

            if(prev>value){
               total += prev - value;

            }else{
                total += prev;
            }
            prev = value;
        }

        return total;



//        var index = 0;
//
//        while (index < s.length) {
//            value = romanMap[s[index++]] ?: 0;
//
//            total += if (prev < value) {
//                (value - 2 * prev);
//
//            } else {
//                value;
//            }
//
//            prev = value;
//        }
//
//        return total
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