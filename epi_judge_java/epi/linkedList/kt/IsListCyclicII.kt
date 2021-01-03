package epi.linkedList.kt

import epi.ListNode
import epi.test_framework.EpiTest
import kotlin.Throws
import epi.test_framework.TimedExecutor
import java.lang.RuntimeException
import epi.test_framework.TestFailure
import kotlin.jvm.JvmStatic
import epi.test_framework.GenericTest
import java.lang.Exception

object IsListCyclicII {


    fun hasCycle(head: ListNode<Int>?): ListNode<Int>? {
        if (head == null) return null;

        var fast = head;
        var slow = head;

        while (fast?.next != null) {
            slow = slow!!.next;
            fast = fast.next.next;
            if (slow == fast) {
                do {
                    fast = fast!!.next;
                } while (slow != fast);
                break;
            }
        }

        if (fast?.next == null) return null ?: return null;

       slow = head;
        while (slow != fast) {
            slow = slow!!.next;
            fast = fast!!.next;
        }

        return slow;
    }

    @JvmStatic
    @EpiTest(testDataFile = "is_list_cyclic.tsv")
    @Throws(Exception::class)
    fun HasCycleWrapper(executor: TimedExecutor,
                        head: ListNode<Int>?, cycleIdx: Int) {
        var cycleLength = 0
        if (cycleIdx != -1) {
            if (head == null) {
                throw RuntimeException("Can't cycle empty list")
            }
            var cycleStart: ListNode<Int>? = null
            var cursor: ListNode<Int> = head
            while (cursor.next != null) {
                if (cursor.data == cycleIdx) {
                    cycleStart = cursor
                }
                cursor = cursor.next
                if (cycleStart != null) {
                    cycleLength++
                }
            }
            if (cursor.data == cycleIdx) {
                cycleStart = cursor
            }
            if (cycleStart == null) {
                throw RuntimeException("Can't find a cycle start")
            }
            cursor.next = cycleStart
            cycleLength++
        }
        val result = executor.run<ListNode<Int>?> { hasCycle(head) }
        if (cycleIdx == -1) {
            if (result != null) {
                throw TestFailure("Found a non-existing cycle")
            }
        } else {
            if (result == null) {
                throw TestFailure("Existing cycle was not found")
            }
            var cursor = result
            do {
                cursor = cursor!!.next
                cycleLength--
                if (cursor == null || cycleLength < 0) {
                    throw TestFailure(
                            "Returned node does not belong to the cycle or is not the closest node to the head")
                }
            } while (cursor !== result)
            if (cycleLength != 0) {
                throw TestFailure(
                        "Returned node does not belong to the cycle or is not the closest node to the head")
            }
        }
    }

    @JvmStatic
    fun main(args: Array<String>) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "IsListCyclic.java",
                                object : Any() {}.javaClass.enclosingClass)
                        .ordinal)
    }
}