package epi.stack.kt

import epi.test_framework.EpiTest
import kotlin.jvm.JvmStatic
import epi.test_framework.GenericTest
import java.util.*

object IsValidParenthesization {

    @JvmStatic
    @EpiTest(testDataFile = "is_valid_parenthesization.tsv")
    fun isWellFormed(s: String?): Boolean {
        if (s == null) return true;
        val stack = LinkedList<Char>();
        for (ch in s) {
            when (ch) {
                '{', '[', '(' -> {
                    stack.push(ch);
                }

                '}' -> {
                    if (stack.isEmpty() || stack.pop() != '{') return false;
                }

                ']' -> {
                    if (stack.isEmpty() || stack.pop() != '[') return false;
                }

                ')' -> {
                    if (stack.isEmpty() || stack.pop() != '(') return false;
                }
            }
        }

        return stack.isEmpty();
    }

    @JvmStatic
    fun main(args: Array<String>) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "IsValidParenthesization.java",
                                object : Any() {}.javaClass.enclosingClass)
                        .ordinal)
    }
}