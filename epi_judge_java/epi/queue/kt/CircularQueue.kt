package epi.queue.kt

import epi.test_framework.EpiTest
import epi.test_framework.EpiUserType
import epi.test_framework.GenericTest
import epi.test_framework.TestFailure
import java.lang.StringBuilder
import java.util.*

object CircularQueue {

    @JvmStatic
    @EpiTest(testDataFile = "circular_queue.tsv")
    @Throws(TestFailure::class)
    fun queueTester(ops: List<QueueOp>) {
        var q = Queue(1)
        var opIdx = 0
        for (op in ops) {
            when (op.op) {
                "Queue" -> q = Queue(op.arg)
                "enqueue" -> q.enqueue(op.arg)
                "dequeue" -> {
                    val result = q.dequeue()
                    if (result != op.arg) {
                        throw TestFailure()
                                .withProperty(TestFailure.PropertyName.STATE, q)
                                .withProperty(TestFailure.PropertyName.COMMAND, op)
                                .withMismatchInfo(opIdx, op.arg, result)
                    }
                }
                "size" -> {
                    val s = q.size()
                    if (s != op.arg) {
                        throw TestFailure()
                                .withProperty(TestFailure.PropertyName.STATE, q)
                                .withProperty(TestFailure.PropertyName.COMMAND, op)
                                .withMismatchInfo(opIdx, op.arg, s)
                    }
                }
            }
            opIdx++
        }
    }

    @JvmStatic
    fun main(args: Array<String>) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "CircularQueue.java",
                                object : Any() {}.javaClass.enclosingClass)
                        .ordinal)
    }

    class Queue(capacity: Int) {
        val sizeFactor = 2;
        var values: IntArray = IntArray(capacity);
        var head = 0;
        var tail = 0;
        var size = 0;

        fun enqueue(x: Int) {
            if (size == values.size) {
                values = rotateLeft(values, head);
                head = 0;
                tail = size
                values = values.copyOf(values.size * sizeFactor);
            }

            values[tail] = x;
            tail = (tail + 1) % values.size;
            size++;
        }

        fun dequeue(): Int {
            size--;
            val value = values[head];
            head = (head + 1) % values.size;
            return value;
        }

        fun size(): Int {
            return size
        }

        override fun toString(): String {
            val builder = StringBuilder();
            for (value in values) {
                builder.append(value);
                builder.append(" ");
            }
            return builder.toString();
        }
    }

    private fun rotateLeft(values: IntArray, start: Int): IntArray {
        if (start % values.size == 0) return values;
        val newValues = IntArray(values.size);
        System.arraycopy(values, 0, newValues, values.size - start, start);
        System.arraycopy(values, start , newValues, 0, values.size - start);
        return newValues;
    }

    @EpiUserType(ctorParams = [String::class, Int::class])
    class QueueOp(var op: String, var arg: Int) {
        override fun toString(): String {
            return op
        }
    }
}