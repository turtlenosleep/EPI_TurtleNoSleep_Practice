package epi.linkedList.kt

import epi.ListNode
import epi.test_framework.EpiTest
import kotlin.Throws
import epi.test_framework.TimedExecutor
import java.lang.RuntimeException
import java.lang.Runnable
import kotlin.jvm.JvmStatic
import epi.test_framework.GenericTest
import java.lang.Exception

object DeleteFromList {
    // Delete the node immediately following aNode. Assumes aNode is not a tail.
    fun deleteList(aNode: ListNode<Int?>?) {
        aNode?.next?.data = aNode?.next?.next?.data;
        aNode?.next?.next = aNode?.next?.next?.next;
    }

    @JvmStatic
    @EpiTest(testDataFile = "delete_from_list.tsv")
    @Throws(Exception::class)
    fun deleteListWrapper(executor: TimedExecutor, head: ListNode<Int?>?, nodeIdx: Int): ListNode<Int?>? {
        var nodeIdx = nodeIdx
        var nodeToDelete = head
        var prev: ListNode<Int?>? = null
        if (nodeToDelete == null) throw RuntimeException("List is empty")
        while (nodeIdx-- > 0) {
            if (nodeToDelete!!.next == null) throw RuntimeException("Can't delete last node")
            prev = nodeToDelete
            nodeToDelete = nodeToDelete.next
        }
        val finalPrev = prev
        executor.run { deleteList(finalPrev) }
        return head
    }

    @JvmStatic
    fun main(args: Array<String>) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "DeleteFromList.java",
                                object : Any() {}.javaClass.enclosingClass)
                        .ordinal)
    }
}