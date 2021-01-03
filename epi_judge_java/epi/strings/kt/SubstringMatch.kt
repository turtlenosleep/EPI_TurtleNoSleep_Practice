package epi.strings.kt

import epi.test_framework.EpiTest
import kotlin.jvm.JvmStatic
import epi.test_framework.GenericTest
import kotlin.math.pow

object SubstringMatch {
    @JvmStatic
    @EpiTest(testDataFile = "substring_match.tsv") // Returns the index of the first character of the substring if found, -1
    // otherwise.
    fun rabinKarp(t: String?, s: String?): Int {
        if (t.isNullOrEmpty()) return -1;
        if (s.isNullOrEmpty()) return 0;
        if (t.length < s.length) return -1;

        val n = s.length;
        val base = 31;

        var hashT = 0L;
        var hashS = 0L;

        for (i in s.indices) {
            hashT = hashT * base + getValue(t[i]);
            hashS = hashS * base + getValue(s[i]);
        }


        val power = base.toDouble().pow((n - 1).toDouble()).toLong();

        for (i in n..t.length) {
            if (hashS == hashT && isEqual(s, t, i - n, i - 1)) {
                return i - n;
            }

            if (i < t.length)
                hashT = (hashT - getValue(t[i - n]) * power) * base + getValue(t[i]);
        }


        return -1;
    }

    private fun getValue(input: Char): Int {
        return input - 'a' + 1;
    }

    private fun isEqual(s: String, t: String, start: Int, end: Int): Boolean {
        for (i in start..end) {
            if (s[i - start] != t[i]) return false;
        }

        return true;
    }

    @JvmStatic
    fun main(args: Array<String>) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "SubstringMatch.java",
                                object : Any() {}.javaClass.enclosingClass)
                        .ordinal)
    }
}