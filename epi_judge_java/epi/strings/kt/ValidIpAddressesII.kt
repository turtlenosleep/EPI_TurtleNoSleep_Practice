package epi.strings.kt

import epi.test_framework.EpiTest
import epi.test_framework.EpiTestComparator
import kotlin.jvm.JvmStatic
import epi.test_framework.GenericTest
import java.util.*
import kotlin.Comparator

object ValidIpAddressesII {
    @JvmStatic
    @EpiTest(testDataFile = "valid_ip_addresses.tsv")
    fun getValidIpAddress(s: String?, k: Int): List<String>? {
        val result = mutableListOf<String>();
        if (s.isNullOrEmpty()) return result;

        findValidIP(0, 0, k + 1, s, StringBuilder(), result)
        return result;
    }

    private fun findValidIP(index: Int, set: Int, maxSet: Int, s: String, builder: StringBuilder, result: MutableList<String>) {
        if (index == s.length) {
            if (set == maxSet) {
                result.add(builder.substring(0, builder.length - 1));
            }
            return;
        }

        val tempBuilder = StringBuilder();
        var ip = -1;

        for (i in index until s.length) {
            if (ip == 0) return;

            tempBuilder.append(s[i]);
            ip = tempBuilder.toString().toInt();
            if (ip in 0..255) {
                builder.append(tempBuilder);
                builder.append(".");
                findValidIP(i + 1, set + 1, maxSet, s, builder, result);
                builder.setLength(builder.length - tempBuilder.length - 1);
            }else{
                return;
            }
        }
    }

    @JvmStatic
    @EpiTestComparator
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
                        .runFromAnnotations(args, "ValidIpAddresses.java",
                                object : Any() {}.javaClass.enclosingClass)
                        .ordinal)
    }
}