package epi.linkedList.kt

import epi.test_framework.EpiTest
import epi.ListNode
import kotlin.jvm.JvmStatic
import epi.test_framework.GenericTest

object SortedListsMerge {
    @JvmStatic
    @EpiTest(testDataFile = "sorted_lists_merge.tsv") //@include
    fun mergeTwoSortedLists(L1: ListNode<Int>?,
                            L2: ListNode<Int>?): ListNode<Int>? {
        if (L1 == null && L2 == null) return null;
        if (L1 == null) return L2;
        if (L2 == null) return L1;

        val sudo = ListNode<Int>(0, null);
        var current = sudo;
        var node1 = L1;
        var node2 = L2;

        while (node1 != null && node2 != null) {
            if (node1.data < node2.data) {
                current.next = node1;
                node1 = node1.next;

            } else {
                current.next = node2;
                node2 = node2.next;
            }

            current = current.next;
        }

        if (node1 != null) {
            current.next = node1;
        } else if (node2 != null) {
            current.next = node2;
        }

        return sudo.next;
    }

    @JvmStatic
    fun main(args: Array<String>) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "SortedListsMerge.java",
                                object : Any() {}.javaClass.enclosingClass)
                        .ordinal)
    }
}