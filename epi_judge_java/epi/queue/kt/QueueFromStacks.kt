package epi.queue.kt

import epi.test_framework.EpiTest
import kotlin.Throws
import epi.test_framework.TestFailure
import kotlin.jvm.JvmStatic
import epi.test_framework.GenericTest
import epi.test_framework.EpiUserType
import java.util.*

object QueueFromStacks {

    @JvmStatic
    @EpiTest(testDataFile = "queue_from_stacks.tsv")
    @Throws(TestFailure::class)
    fun queueTester(ops: List<QueueOp>) {
        try {
            var q = Queue()
            for (op in ops) {
                when (op.op) {
                    "QueueWithMax" -> q = Queue()
                    "enqueue" -> q.enqueue(op.arg)
                    "dequeue" -> {
                        val result = q.dequeue()
                        if (result != op.arg) {
                            throw TestFailure("Dequeue: expected " + op.arg.toString() + ", got " + result.toString())
                        }
                    }
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
                        .runFromAnnotations(args, "QueueFromStacks.java",
                                object : Any() {}.javaClass.enclosingClass)
                        .ordinal)
    }

    class Queue {
        private val addQueue = LinkedList<Int>();
        private val removeQueue = LinkedList<Int>();

        fun enqueue(x: Int) {
            addQueue.addFirst(x)
        }

        fun dequeue(): Int {
            if (removeQueue.isEmpty()) {
                while (addQueue.isNotEmpty()) {
                    removeQueue.addFirst(addQueue.removeFirst());
                }
            }

            return removeQueue.removeFirst();
        }
    }

    @EpiUserType(ctorParams = [String::class, Int::class])
    class QueueOp(var op: String, var arg: Int)
}