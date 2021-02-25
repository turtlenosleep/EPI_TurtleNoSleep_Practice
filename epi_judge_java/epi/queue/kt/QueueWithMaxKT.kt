package epi.queue.kt

import epi.test_framework.EpiUserType
import epi.test_framework.EpiTest
import kotlin.Throws
import epi.test_framework.TestFailure
import kotlin.jvm.JvmStatic
import epi.test_framework.GenericTest
import java.util.*

class QueueWithMaxKT {

    private val entries: Queue<Int> = LinkedList();
    private val maxQueue: Deque<Int> = LinkedList();

    fun enqueue(x: Int) {
        while (maxQueue.isNotEmpty()) {
            if (maxQueue.peekLast() < x) {
                maxQueue.removeLast();
            } else {
                break;
            }
        }

        maxQueue.addLast(x);
        entries.offer(x)
    }

    fun dequeue(): Int {
        if (entries.peek() == maxQueue.peekFirst()) {
            maxQueue.removeFirst();
        }

        return entries.poll();
    }

    fun max(): Int {
        return maxQueue.peekFirst();
    }

    @EpiUserType(ctorParams = [String::class, Int::class])
    class QueueOp(var op: String, var arg: Int)
    companion object {

        @JvmStatic
        @EpiTest(testDataFile = "queue_with_max.tsv")
        @Throws(TestFailure::class)
        fun queueTester(ops: List<QueueOp>) {
            try {
                var q = QueueWithMaxKT()
                for (op in ops) {
                    when (op.op) {
                        "QueueWithMax" -> q = QueueWithMaxKT()
                        "enqueue" -> q.enqueue(op.arg)
                        "dequeue" -> {
                            val result = q.dequeue()
                            if (result != op.arg) {
                                throw TestFailure("Dequeue: expected " + op.arg.toString() + ", got " + result.toString())
                            }
                        }
                        "max" -> {
                            val s = q.max()
                            if (s != op.arg) {
                                throw TestFailure("Max: expected " + op.arg.toString() +
                                        ", got " + s.toString())
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
                            .runFromAnnotations(args, "QueueWithMax.java",
                                    object : Any() {}.javaClass.enclosingClass)
                            .ordinal)
        }
    }
}