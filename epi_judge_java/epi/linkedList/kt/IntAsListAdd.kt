package epi.linkedList.kt

import epi.test_framework.EpiTest
import epi.ListNode
import kotlin.jvm.JvmStatic
import epi.test_framework.GenericTest

object IntAsListAdd {

    @JvmStatic
    @EpiTest(testDataFile = "int_as_list_add.tsv")
    fun addTwoNumbers(L1: ListNode<Int>?, L2: ListNode<Int>?): ListNode<Int>? {
        if (L1 == null && L2 == null) return null;
        if (L1 == null) return L2;
        if (L2 == null) return L1;

        var carry = 0;
        val dummy = ListNode<Int>(0, null);
        var current = dummy;
        var n1 = L1;
        var n2 = L2
        while (carry != 0 || n1 != null || n2 != null) {
            var sum = carry;

            if (n1 != null) {
                sum += n1.data;
                n1 = n1.next;
            }

            if (n2 != null) {
                sum += n2.data;
                n2 = n2.next;
            }

            carry = sum / 10;
            sum %= 10;

            current.next = ListNode<Int>(sum, null);
            current = current.next;
        }

        return dummy.next;
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