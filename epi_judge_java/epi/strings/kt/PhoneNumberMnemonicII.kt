package epi.strings.kt

import epi.test_framework.EpiTest
import epi.test_framework.EpiTestComparator
import epi.test_framework.GenericTest
import java.util.*

object PhoneNumberMnemonicII {

    val mapping = arrayOf("0", "1", "ABC", "DEF", "GHI", "JKL", "MNO", "PQRS", "TUV", "WXYZ");

    @EpiTest(testDataFile = "phone_number_mnemonic.tsv")
    @JvmStatic
    fun phoneMnemonic(phoneNumber: String?): List<String>? {
        val result = mutableListOf<String>();
        if (phoneNumber.isNullOrEmpty()) return result;

        val builder = StringBuilder();
        val indexArray = IntArray(phoneNumber.length);
        var index = 0;
        while (true) {
            if (index < 0) break;

            if (index == phoneNumber.length) {
                result.add(builder.toString());
                builder.setLength(builder.length - 1);
                index--;
                continue;
            }

            val num = phoneNumber[index] - '0';
            val letters = mapping[num];

            val letterIndex = indexArray[index];
            if (letterIndex >= letters.length) {
                if (builder.isNotEmpty()) builder.setLength(builder.length - 1);
                indexArray[index] = 0;
                index--;
                continue;
            }

            indexArray[index]++;
            index++;
            builder.append(letters[letterIndex]);
            continue;
        }

        return result
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