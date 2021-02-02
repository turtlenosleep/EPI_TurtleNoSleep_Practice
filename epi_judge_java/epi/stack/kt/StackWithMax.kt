package epi.stack.kt

import epi.test_framework.EpiTest
import kotlin.Throws
import epi.test_framework.TestFailure
import java.lang.RuntimeException
import kotlin.jvm.JvmStatic
import epi.test_framework.GenericTest
import epi.test_framework.EpiUserType
import java.util.*

object StackWithMax {

    @JvmStatic
    @EpiTest(testDataFile = "stack_with_max.tsv")
    @Throws(TestFailure::class)
    fun stackTester(ops: List<StackOp>) {
        try {
            var s = Stack()
            var result: Int
            for (op in ops) {
                when (op.op) {
                    "Stack" -> s = Stack()
                    "push" -> s.push(op.arg)
                    "pop" -> {
                        result = s.pop()
                        if (result != op.arg) {
                            throw TestFailure("Pop: expected " + op.arg.toString() +
                                    ", got " + result.toString())
                        }
                    }
                    "max" -> {
                        result = s.max()
                        if (result != op.arg) {
                            throw TestFailure("Max: expected " + op.arg.toString() +
                                    ", got " + result.toString())
                        }
                    }
                    "empty" -> {
                        result = if (s.empty()) 1 else 0
                        if (result != op.arg) {
                            throw TestFailure("Empty: expected " + op.arg.toString() +
                                    ", got " + s.toString())
                        }
                    }
                    else -> throw RuntimeException("Unsupported stack operation: " + op.op)
                }
            }
        } catch (e: NoSuchElementException) {
            throw TestFailure("Unexpected NoSuchElement exception")
        }
    }

    @JvmStatic
    fun main(args: Array<String>) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "StackWithMax.java",
                                object : Any() {}.javaClass.enclosingClass)
                        .ordinal)
    }

    class Stack {
        private val valueStack = LinkedList<Int>();
        private val maxStack = LinkedList<IntArray>();

        fun empty(): Boolean {
            return valueStack.isEmpty()
        }

        fun max(): Int {
            return maxStack.peek()[0];
        }

        fun pop(): Int {
            val max = maxStack.peek();
            if(max[1] ==1){
                maxStack.pop();

            }else{
                max[1]--;
            }
            return valueStack.pop();
        }

        fun push(x: Int) {
            if(maxStack.isEmpty() || x>maxStack.peek()[0]){
                maxStack.push(intArrayOf(x,1));

            }else{
                maxStack.peek()[1]++;
            }
            valueStack.push(x);
        }
    }

    @EpiUserType(ctorParams = [String::class, Int::class])
    class StackOp(var op: String, var arg: Int)
}