package epi.linkedList.kt

import epi.ListNode
import epi.test_framework.EpiTest
import epi.test_framework.GenericTest
import epi.test_framework.TestFailure
import epi.test_framework.TimedExecutor
import java.util.*
import kotlin.Throws
import kotlin.jvm.JvmStatic

object PivotList {
    fun listPivoting(l: ListNode<Int>?, x: Int): ListNode<Int>? {
        val smallerHead = ListNode<Int>(0, null);
        val equalHead = ListNode<Int>(0, null);
        val greaterHead = ListNode<Int>(0, null);

        var smallerPtr = smallerHead;
        var equalPtr = equalHead;
        var greaterPtr = greaterHead;

        var node = l;
        while (node != null) {
            if (node.data < x) {
                smallerPtr.next = node;
                smallerPtr = smallerPtr.next;

            } else if (node.data > x) {
                greaterPtr.next = node;
                greaterPtr = greaterPtr.next;

            } else {
                equalPtr.next = node;
                equalPtr = equalPtr.next;
            }

            node = node.next;
        }

        greaterPtr.next = null;
        equalPtr.next = greaterHead.next;
        smallerPtr.next = equalHead.next
        return smallerHead.next;
    }

    fun linkedToList(input: ListNode<Int>?): List<Int> {
        var l = input
        val v: MutableList<Int> = ArrayList()
        while (l != null) {
            v.add(l.data)
            l = l.next
        }
        return v
    }


    @JvmStatic
    @EpiTest(testDataFile = "pivot_list.tsv")
    @Throws(Exception::class)
    fun listPivotingWrapper(executor: TimedExecutor,
                            input: ListNode<Int>?, x: Int) {
        var l = input
        val original = linkedToList(l)
        val finalL = l
        l = executor.run<ListNode<Int>?> { listPivoting(finalL, x) }
        val pivoted = linkedToList(l)
        var mode = -1
        for (i in pivoted) {
            when (mode) {
                -1 -> if (i == x) {
                    mode = 0
                } else if (i > x) {
                    mode = 1
                }
                0 -> if (i < x) {
                    throw TestFailure("List is not pivoted")
                } else if (i > x) {
                    mode = 1
                }
                1 -> if (i <= x) {
                    throw TestFailure("List is not pivoted")
                }
            }
        }
        Collections.sort(original)
        Collections.sort(pivoted)
        if (original != pivoted) throw TestFailure("Result list contains different values")
    }

    @JvmStatic
    fun main(args: Array<String>) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "PivotList.java",
                                object : Any() {}.javaClass.enclosingClass)
                        .ordinal)
    }
}