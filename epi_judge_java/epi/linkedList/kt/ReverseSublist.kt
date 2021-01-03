package epi.linkedList.kt

import epi.test_framework.EpiTest
import epi.ListNode
import kotlin.jvm.JvmStatic
import epi.test_framework.GenericTest

object ReverseSublist {

    @JvmStatic
    @EpiTest(testDataFile = "reverse_sublist.tsv")
    fun reverseSublist(L: ListNode<Int>?, start: Int,
                       finish: Int): ListNode<Int>? {
        if(L== null || start == finish) return L;

        val dummy = ListNode<Int>(0,L);
        var index =0;
        var node:ListNode<Int>?= dummy;
        while(index < start-1){
            node = node!!.next;
            index++;
        }


        val subHead = node;
        index++;
        node = node!!.next;

        var temp:ListNode<Int>? = null;

        while(index<finish){
            temp = node!!.next;
            node.next  = temp.next;
            temp.next = subHead!!.next;
            subHead!!.next = temp;
            index++;
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