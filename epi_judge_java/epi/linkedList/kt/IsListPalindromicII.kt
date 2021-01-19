package epi.linkedList.kt

import epi.DoublyListNode
import epi.test_framework.EpiTest
import epi.test_framework.GenericTest

object IsListPalindromicII {
    @JvmStatic
    @EpiTest(testDataFile = "is_list_palindromic.tsv")
    fun isLinkedListAPalindrome(L: DoublyListNode<Int>?): Boolean {
        if (L == null) return true;
        var tail = L;
        while (tail!!.next != null) {
            tail = tail.next;
        }
        var head = L;
        while (head != tail && head!!.next != tail) {
            if (head.data != tail!!.data) return false;
            head = head.next;
            tail = tail.prev;
        }
        return true;
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