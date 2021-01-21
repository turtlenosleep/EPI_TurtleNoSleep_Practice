package epi.linkedList.kt

import epi.test_framework.EpiTest
import epi.ListNode
import kotlin.jvm.JvmStatic
import epi.test_framework.GenericTest

object EvenOddListMerge {
    @JvmStatic
    @EpiTest(testDataFile = "even_odd_list_merge.tsv")
    fun evenOddMerge(L: ListNode<Int>?): ListNode<Int>? {
        if(L == null) return null;
        var even = L;
        val oddHead = L.next;
        var odd = L.next;
        /*
        *
        *       0 1 2 3 4 5
        * even  0  2  4
        * odd   1  3  5        odd.next = null;
        *
        *       0 1 2 3 4 5 6
        * even  0 2 4 6
        * odd   1 3 5 x         odd = null
        *
        * */

        while(odd?.next != null){
            even?.next = odd.next;
            even = even?.next;
            odd.next = even?.next;
            odd = odd.next;
        }

        even?.next = oddHead;

        return L;
    }

    @JvmStatic
    fun main(args: Array<String>) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "EvenOddListMerge.java",
                                object : Any() {}.javaClass.enclosingClass)
                        .ordinal)
    }
}