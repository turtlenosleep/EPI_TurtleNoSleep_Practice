package epi.linkedList.kt

import epi.test_framework.EpiTest
import epi.ListNode
import kotlin.jvm.JvmStatic
import epi.test_framework.GenericTest

object IsListPalindromic {
    @JvmStatic
    @EpiTest(testDataFile = "is_list_palindromic.tsv")
    fun isLinkedListAPalindrome(L: ListNode<Int>?): Boolean {
        if (L == null) return true;
        var slow = L;
        var fast = L;

        /*
        *    1 2 3 4 5 6
        *  S 1 2 3 4
        *  F 1 3 5 x
        *
        *  reverse second half
        * */

        while (fast != null && fast.next != null) {
            slow = slow!!.next;
            fast = fast.next.next;
        }

        var secondHalfHead: ListNode<Int>? = reverseSecondHalf(slow);
        var firstHalfHead = L;

        while (secondHalfHead != null) {
            if (firstHalfHead!!.data != secondHalfHead!!.data) return false;
            firstHalfHead = firstHalfHead.next;
            secondHalfHead = secondHalfHead.next;
        }

        return true
    }

    private fun reverseSecondHalf(head: ListNode<Int>?): ListNode<Int> {
        val dummy = ListNode<Int>(0, head);
        var node = head;

        while (node != null && node.next != null) {
            val temp = node.next;
            val next = temp.next;

            temp.next = dummy.next;
            dummy.next = temp;
            node.next = next;
        }

        return dummy.next;
    }

    @JvmStatic
    fun main(args: Array<String>) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "IsListPalindromic.java",
                                object : Any() {}.javaClass.enclosingClass)
                        .ordinal)
    }
}