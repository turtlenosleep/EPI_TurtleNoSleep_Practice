package epi.stack.kt

import epi.test_framework.EpiTest
import epi.test_framework.GenericTest
import java.util.*

object EvaluatePrefixNotation {
    // prefix notation
    // https://www.geeksforgeeks.org/convert-infix-prefix-notation/

    @JvmStatic
    @EpiTest(testDataFile = "evaluate_prefix_notation.tsv")
    fun eval(expression: String?): Int {
        if (expression.isNullOrEmpty()) return 0;
        val stack = LinkedList<Int>();

        val operations = expression.split(",");
        var num1 = 0;
        var num2 = 0;
        for (i in operations.size - 1 downTo 0) {
            val num = operations[i].toIntOrNull();
            if (num != null) {
                stack.push(num);
                continue;
            }

            num1 = stack.pop();
            num2 = stack.pop();

            when (operations[i]) {
                "+" -> {
                    stack.push(num1 + num2);
                }
                "-" -> {
                    stack.push(num1 - num2);
                }
                "*" -> {
                    stack.push(num1 * num2);
                }
                "/" -> {
                    stack.push(num1 / num2);
                }
            }
        }
        return stack.peek();
    }


    @JvmStatic
    fun main(args: Array<String>) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "EvaluateRpn.java",
                                object : Any() {}.javaClass.enclosingClass)
                        .ordinal)
    }
}