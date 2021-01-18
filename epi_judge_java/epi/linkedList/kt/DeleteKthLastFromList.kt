package epi.linkedList.kt

import epi.test_framework.EpiTest
import epi.ListNode
import kotlin.jvm.JvmStatic
import epi.test_framework.GenericTest

object DeleteKthLastFromList {

    @JvmStatic
    @EpiTest(testDataFile = "delete_kth_last_from_list.tsv") // Assumes L has at least k nodes, deletes the k-th last node in L.
    fun removeKthLast(L: ListNode<Int?>?, k: Int): ListNode<Int>? {
        val dummy = ListNode<Int>(0,L);
        var fast:ListNode<Int>? = dummy
        var step =0;

        /*
        *  D 1 2 3 4 5 6
        *    3
        *
        *  fast 1 2 3 4 5 6
        *  slow     D 1 2 3
        *
        *
        * */

        while(step++ <k){
            fast = fast!!.next;
        }

        var slow = dummy;
        while(fast!!.next!=null){
            fast = fast.next;
            slow = slow.next;
        }
        slow.next = slow.next.next;
        return dummy.next;
    }

    @JvmStatic
    fun main(args: Array<String>) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "DeleteKthLastFromList.java",
                                object : Any() {}.javaClass.enclosingClass)
                        .ordinal)
    }
}