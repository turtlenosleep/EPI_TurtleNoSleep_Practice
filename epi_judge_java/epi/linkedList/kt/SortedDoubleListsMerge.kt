package epi.linkedList.kt

import epi.test_framework.EpiTest
import kotlin.jvm.JvmStatic
import epi.test_framework.GenericTest

object SortedDoubleListsMerge {
    @JvmStatic
    @EpiTest(testDataFile = "sorted_lists_merge.tsv") //@include
    fun mergeTwoSortedLists(L1: DListNode<Int>?,
                            L2: DListNode<Int>?): DListNode<Int>? {
        if (L1 == null && L2 == null) return null;
        if (L1 == null) return L2;
        if (L2 == null) return L1;

        val dummy = DListNode<Int>(0, null, null);
        var current = dummy;
        var node1 = L1;
        var node2 = L2;

        while (node1 != null && node2 != null) {
            if (node1.data < node2.data) {
                current.next = node1;
                node1.prev = current;
                node1 = node1.next;

            } else {
                current.next = node2;
                node2.prev = current;
                node2 = node2.next;
            }

            current = current!!.next!!;
        }

        if (node1 != null) {
            current.next = node1;
            node1.prev = current;

        } else if (node2 != null) {
            current.next = node2;
            node2.prev = current;
        }

        return dummy.next;
    }

    class DListNode<T>(var data: T, var next: DListNode<T>?, var prev: DListNode<T>?) {
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