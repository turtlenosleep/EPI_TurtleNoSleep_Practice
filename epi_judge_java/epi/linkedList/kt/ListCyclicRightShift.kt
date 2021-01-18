package epi.linkedList.kt

import epi.test_framework.EpiTest
import epi.ListNode
import kotlin.jvm.JvmStatic
import epi.test_framework.GenericTest

object ListCyclicRightShift {

    @JvmStatic
    @EpiTest(testDataFile = "list_cyclic_right_shift.tsv")
    fun cyclicallyRightShiftList(L: ListNode<Int>?, k: Int): ListNode<Int>? {
        if(L== null) return null;

        var n = 1;
        var tail = L;
// O(n)
        while(tail?.next!=null){
            tail = tail?.next;
            n++;
        }

        var m = k%n;
        if(m ==0) return L;

        m = n -m;
        tail?.next = L;

        var preHead = tail;
//O(m)
        while(m>0){
            preHead = preHead?.next;
            m--;
        }

        val newHead = preHead?.next;
        preHead?.next = null;

        return newHead;

    }

    @JvmStatic
    fun main(args: Array<String>) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "ListCyclicRightShift.java",
                                object : Any() {}.javaClass.enclosingClass)
                        .ordinal)
    }
}