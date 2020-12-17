package epi.strings.kt

import epi.test_framework.EpiTest
import epi.test_framework.GenericTest

object IsStringPalindromic {
    @JvmStatic
    @EpiTest(testDataFile = "is_string_palindromic.tsv")
    fun isPalindromic(s: String?): Boolean {
        if (s == null) return false;
        var i1 = 0;
        var i2 = s.length - 1;

        while (i1 < i2) {

            while (!Character.isLetter(s[i1]) && i1 < i2) i1++;
            while (!Character.isLetter(s[i2]) && i1 < i2) i2--;

            if (Character.toLowerCase(s[i1].toLowerCase()) != Character.toLowerCase(s[i2])) return false;
        }
        return true
    }

    @JvmStatic
    fun main(args: Array<String>) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "IsStringPalindromic.java",
                                object : Any() {}.javaClass.enclosingClass)
                        .ordinal)
    }
}