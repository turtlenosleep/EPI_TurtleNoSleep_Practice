package epi.linkedList.kt

import epi.test_framework.EpiTest
import epi.ListNode
import kotlin.jvm.JvmStatic
import epi.test_framework.GenericTest

object ReverseSublistII {

    @JvmStatic
    @EpiTest(testDataFile = "reverse_sublist.tsv")
    fun reverseSublist(L: ListNode<Int>?, start: Int, finish: Int): ListNode<Int>? {
        if (L == null || start == finish) return L;


        var preNode: ListNode<Int>? = null;
        var ptr = L;
        var index = 1;
        while (index < start) {
            preNode = ptr;
            ptr = ptr!!.next;
            index++;
        }

        val subHead = preNode;
        val tail = ptr;

        var nextNode: ListNode<Int>? = null

        while (index <= finish) {
            nextNode = ptr!!.next;
            ptr.next = preNode;
            preNode = ptr;
            ptr = nextNode;
            index++
        }

        subHead?.next = preNode;
        tail!!.next = nextNode;

        return if(subHead == null) preNode else L
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