package epi.strings.kt

import epi.test_framework.EpiTest
import epi.test_framework.GenericTest
import epi.test_framework.TimedExecutor

object ReverseWords {
    @JvmStatic
    fun reverseWords(input: CharArray?) {
        if (input == null || input.isEmpty()) return;
        reverse(input, 0, input.size - 1);
        var start = 0;
        var end = 0;
        while (end < input.size) {
            end = findBlank(input, start);
            reverse(input, start, end - 1);
            start = end + 1;
        }

        return
    }

    private fun findBlank(input: CharArray, start: Int): Int {
        for (i in start until input.size) {
            if (input[i] == ' ') {
                return i;
            }
        }
        return input.size;
    }

    private fun reverse(input: CharArray, start: Int, end: Int) {
        if (start >= end) return;

        val mid = start + (end - start) / 2;
        for (i in start..mid) {
            val shift = i - start;
            val temp = input[i];
            input[i] = input[end - shift];
            input[end - shift] = temp;
        }
    }

    @JvmStatic
    @EpiTest(testDataFile = "reverse_words.tsv")
    @Throws(Exception::class)
    fun reverseWordsWrapper(executor: TimedExecutor, s: String): String {
        val sCopy = s.toCharArray()
        executor.run { reverseWords(sCopy) }
        return String(sCopy)
    }

    @JvmStatic
    fun main(args: Array<String>) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "ReverseWords.java",
                                object : Any() {}.javaClass.enclosingClass)
                        .ordinal)
    }
}