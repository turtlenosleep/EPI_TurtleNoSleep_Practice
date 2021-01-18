package epi.linkedList.kt

import epi.test_framework.EpiTest
import epi.ListNode
import kotlin.jvm.JvmStatic
import epi.test_framework.GenericTest

object RemoveDuplicatesMoreThanMTimesFromSortedList {

    @JvmStatic
    @EpiTest(testDataFile = "remove_duplicates_from_sorted_list.tsv")
    fun removeDuplicates(L: ListNode<Int>?, m: Int): ListNode<Int>? {
        val dummy = ListNode<Int>(0, L)
        var node: ListNode<Int>? = dummy;

        while (node != null && node.next != null) {

            var next = node.next;
            var distinct = next.next;
            var count = 1;
            while (distinct != null && next.data == distinct.data) {
                distinct = distinct.next;
                count++;
            }

            if (count > m) {
                node.next = distinct;
            }
            while (node!!.next != distinct) node = node.next;
        }

        return dummy.next;
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