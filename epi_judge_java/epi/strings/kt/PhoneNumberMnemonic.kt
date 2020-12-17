package epi.strings.kt

import epi.test_framework.EpiTest
import epi.test_framework.EpiTestComparator
import epi.test_framework.GenericTest
import java.util.*

object PhoneNumberMnemonic {

    val mapping = arrayOf("0", "1", "ABC", "DEF", "GHI", "JKL", "MNO", "PQRS", "TUV", "WXYZ");

    @EpiTest(testDataFile = "phone_number_mnemonic.tsv")
    @JvmStatic
    fun phoneMnemonic(phoneNumber: String?): List<String>? {
        val result = mutableListOf<String>();
        if(phoneNumber.isNullOrEmpty()) return result;

        findAll(phoneNumber,0,StringBuilder(),result);
        return result
    }

    private fun findAll(number: String, index: Int, builder: StringBuilder, result: MutableList<String>) {
        if (builder.length == number.length) {
            result.add(builder.toString());
            return;
        }

        val num = number[index]-'0';
        val input = mapping[num];

        for (i in input.indices) {
            builder.append(input[i]);
            findAll(number, index + 1, builder, result);
            builder.setLength(builder.length - 1);
        }
    }

    @EpiTestComparator
    @JvmStatic
    fun comp(expected: List<String>, result: List<String>?): Boolean {
        if (result == null) {
            return false
        }
        Collections.sort(expected)
        Collections.sort(result)
        return expected == result
    }

    @JvmStatic
    fun main(args: Array<String>) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "PhoneNumberMnemonic.java",
                                object : Any() {}.javaClass.enclosingClass)
                        .ordinal)
    }
}