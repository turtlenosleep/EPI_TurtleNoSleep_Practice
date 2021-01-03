package epi.linkedList.kt

import epi.test_framework.EpiTest
import epi.ListNode
import kotlin.jvm.JvmStatic
import epi.test_framework.GenericTest

object ReverseNodesInKGroup {

    @JvmStatic
    @EpiTest(testDataFile = "reverse_sublist.tsv")
    fun reverseKGroup(head: ListNode<Int>?, k: Int): ListNode<Int>? {
        if (head == null) return null;
        var size = 0;
        var pointer = head;

        while (pointer != null) {
            pointer = pointer.next;
            size++;
        }

        val dummy = ListNode(0, head);
        var subHead: ListNode<Int>? = dummy;
        var index = 1;
        pointer = head;

        while (size >= k) {

            while (index < k) {
                val temp = pointer!!.next;
                pointer.next = temp.next;
                temp.next = subHead!!.next;
                subHead.next = temp;
                index++
            }

            index = 1;
            subHead = pointer;
            pointer = pointer!!.next;
            size -= k;
        }

        return dummy.next;
    }

    @JvmStatic
    fun main(args: Array<String>) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "ReverseSublist.java",
                                object : Any() {}.javaClass.enclosingClass)
                        .ordinal)
    }
}