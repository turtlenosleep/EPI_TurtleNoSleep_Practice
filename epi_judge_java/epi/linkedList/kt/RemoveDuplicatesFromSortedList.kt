package epi.linkedList.kt

import epi.test_framework.EpiTest
import epi.ListNode
import kotlin.jvm.JvmStatic
import epi.test_framework.GenericTest

object RemoveDuplicatesFromSortedList {

    @JvmStatic
    @EpiTest(testDataFile = "remove_duplicates_from_sorted_list.tsv")
    fun removeDuplicates(L: ListNode<Int>?): ListNode<Int>? {
        var node = L;
        while (node != null) {

            var next = node.next;
            while (next != null && node.data == next.data) {
                next = next.next;
            }

            node.next = next;
            node = node.next;
        }

        return L;
    }

    @JvmStatic
    fun main(args: Array<String>) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "RemoveDuplicatesFromSortedList.java",
                                object : Any() {}.javaClass.enclosingClass)
                        .ordinal)
    }
}