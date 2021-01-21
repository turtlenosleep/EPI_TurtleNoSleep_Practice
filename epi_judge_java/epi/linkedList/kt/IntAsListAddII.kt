package epi.linkedList.kt

import epi.test_framework.EpiTest
import epi.ListNode
import kotlin.jvm.JvmStatic
import epi.test_framework.GenericTest

object IntAsListAddII {

    @JvmStatic
    @EpiTest(testDataFile = "int_as_list_add.tsv")

    // https://leetcode.com/problems/add-two-numbers-ii/
    fun addTwoNumbers(L1: ListNode<Int>?, L2: ListNode<Int>?): ListNode<Int>? {
        if (L1 == null && L2 == null) return null;
        if (L1 == null) return L2;
        if (L2 == null) return L1;

        var len1 = getLength(L1);
        var len2 = getLength(L2);

        var n1 = L1;
        var n2 = L2;
        var head: ListNode<Int>? = null;
        var prev: ListNode<Int>? = null;

        var sum = 0;
        while (len1 > 0 && len2 > 0) {
            when {
                len1 > len2 -> {
                    sum = n1!!.data;
                    n1 = n1.next;
                    len1--

                }
                len2 > len1 -> {
                    sum = n2!!.data;
                    n2 = n2.next;
                    len2--;

                }
                else -> {
                    sum = n1!!.data + n2!!.data;
                    n1 = n1.next;
                    n2 = n2.next;
                    len1--;
                    len2--;
                }
            }

            prev = ListNode<Int>(sum, head);
            head = prev;
        }

        var carry = 0;
        var current: ListNode<Int>? = head;
        head = null;
        prev = null;
        while (current != null || carry > 0) {
            sum = carry;
            if (current != null) {
                sum += current.data;
                current = current.next
            }
            carry = sum / 10;
            sum %= 10
            prev = ListNode<Int>(sum, head);
            head = prev;
        }

        return head;
    }

    private fun getLength(head: ListNode<Int>?): Int {
        var len = 0;
        var node = head;
        while (node != null) {
            len++
            node = node.next;
        }

        return len;
    }

    @JvmStatic
    fun main(args: Array<String>) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "IntAsListAdd.java",
                                object : Any() {}.javaClass.enclosingClass)
                        .ordinal)
    }
}