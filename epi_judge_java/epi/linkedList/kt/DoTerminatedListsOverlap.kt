package epi.linkedList.kt

import epi.ListNode
import epi.test_framework.EpiTest
import kotlin.Throws
import epi.test_framework.TimedExecutor
import epi.test_framework.TestFailure
import kotlin.jvm.JvmStatic
import epi.test_framework.GenericTest
import java.lang.Exception

object DoTerminatedListsOverlap {
    fun overlappingNoCycleLists(l0: ListNode<Int?>?, l1: ListNode<Int?>?): ListNode<Int?>? {
        if (l0 == null || l1 == null) return null;

        var len1 = 1;
        var node1 = l0;
        while (node1 != null) {
            node1 = node1.next;
            len1++;
        }

        var len2 = 1;
        var node2 = l1;
        while (node2 != null) {
            node2 = node2.next;
            len2++;
        }

        if (node1 != node2) return null;

        var diff = 0;
        node1 = l0;
        node2 = l1;
        if (len1 > len2) {
            diff = len1 - len2;
            while (diff > 0) {
                node1 = node1?.next
                diff--;
            }

        } else {
            diff = len2 - len1;
            while (diff > 0) {
                node2 = node2?.next;
                diff--;
            }
        }

        while (node1 != null && node2 != null && node1 != node2) {
            node1 = node1.next;
            node2 = node2.next;
        }

        return node1;
    }

    @JvmStatic
    @EpiTest(testDataFile = "do_terminated_lists_overlap.tsv")
    @Throws(Exception::class)
    fun overlappingNoCycleListsWrapper(executor: TimedExecutor, l0: ListNode<Int?>?,
                                       l1: ListNode<Int?>?, common: ListNode<Int?>?) {
        var l0 = l0
        var l1 = l1
        if (common != null) {
            if (l0 != null) {
                var i: ListNode<Int?> = l0
                while (i.next != null) {
                    i = i.next
                }
                i.next = common
            } else {
                l0 = common
            }
            if (l1 != null) {
                var i: ListNode<Int?> = l1
                while (i.next != null) {
                    i = i.next
                }
                i.next = common
            } else {
                l1 = common
            }
        }
        val finalL0 = l0
        val finalL1 = l1
        val result = executor.run<ListNode<Int?>?> { overlappingNoCycleLists(finalL0, finalL1) }
        if (result !== common) {
            throw TestFailure("Invalid result")
        }
    }

    @JvmStatic
    fun main(args: Array<String>) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "DoTerminatedListsOverlap.java",
                                object : Any() {}.javaClass.enclosingClass)
                        .ordinal)
    }
}