package epi.linkedList.kt

import epi.ListNode
import epi.test_framework.EpiTest
import kotlin.Throws
import epi.test_framework.TimedExecutor
import java.lang.RuntimeException
import java.util.HashSet
import epi.test_framework.TestFailure
import kotlin.jvm.JvmStatic
import epi.test_framework.GenericTest
import java.lang.Exception

object DoListsOverlap {
    fun overlappingLists(l0: ListNode<Int?>?, l1: ListNode<Int?>?): ListNode<Int?>? {
        if (l0 == null || l1 == null) return null;

        val node1 = findCircle(l0);
        val node2 = findCircle(l1);

        if (node1 == null && node2 == null) return findOverLappingNoCircle(l0, l1);
        if (node1 == null && node2 != null) return null;
        if (node2 == null && node1 != null) return null;

        var temp = node1?.next;
        while (temp != node1 && temp != node2) {
            temp = temp?.next
        }

        if (temp != node2) return null; // not same circle;

        val len1 = findLengthBeforeCircle(l0, node1);
        val len2 = findLengthBeforeCircle(l1, node2);

        var differ = 0;
        var head1 = l0;
        var head2 = l1;

        if (len2 > len1) {
            differ = len2 - len1;
            while (differ > 0) {
                head2 = head2?.next;
                differ--;
            }
            differ = len1;

        } else {
            differ = len1 - len2;
            while (differ > 0) {
                head1 = head1?.next
                differ--;
            }
            differ = len2;
        }

        while (differ > 0) {
            head1 = head1?.next;
            head2 = head2?.next;
            differ--;
        }

        return if (head1 == head2) head1 else node1;
    }

    private fun findOverLappingNoCircle(l0: ListNode<Int?>?, l1: ListNode<Int?>?): ListNode<Int?>? {
        val len1 = findLength(l0);
        val len2 = findLength(l1);
        var differ = 0;
        var node1 = l0;
        var node2 = l1;

        if (len2 > len1) {
            differ = len2 - len1;
            while (differ > 0) {
                node2 = node2?.next;
                differ--
            }
        } else {
            differ = len1 - len2;
            while (differ > 0) {
                node1 = node1?.next;
                differ--
            }
        }

        while (node1 != node2) {
            node1 = node1?.next;
            node2 = node2?.next;
        }

        return node1;
    }

    private fun findLength(head: ListNode<Int?>?): Int {
        var len = 0;
        var node = head;
        while (node != null) {
            node = node.next;
            len++
        }
        return len;
    }

    private fun findLengthBeforeCircle(head: ListNode<Int?>?, node: ListNode<Int?>?): Int {
        var temp1 = head;
        var len = 0;
        while (temp1 != node) {
            temp1 = temp1!!.next;
            len++;
        }
        return len;
    }

    private fun findCircle(head: ListNode<Int?>?): ListNode<Int?>? {
        var fast = head;
        var slow = head;

        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow!!.next;
            if (slow == fast) break;
        }

        if (slow != fast) return null; // no circle

        slow = head;
        while (slow != fast) {
            slow = slow!!.next;
            fast = fast!!.next;
        }

        return slow;
    }

    @JvmStatic
    @EpiTest(testDataFile = "do_lists_overlap.tsv")
    @Throws(Exception::class)
    fun overlappingListsWrapper(executor: TimedExecutor, l0: ListNode<Int?>?,
                                l1: ListNode<Int?>?, common: ListNode<Int?>?,
                                cycle0: Int, cycle1: Int) {
        var l0 = l0
        var l1 = l1
        var cycle0 = cycle0
        var cycle1 = cycle1
        if (common != null) {
            if (l0 == null) {
                l0 = common
            } else {
                var it: ListNode<Int?> = l0
                while (it.next != null) {
                    it = it.next
                }
                it.next = common
            }
            if (l1 == null) {
                l1 = common
            } else {
                var it: ListNode<Int?> = l1
                while (it.next != null) {
                    it = it.next
                }
                it.next = common
            }
        }
        if (cycle0 != -1 && l0 != null) {
            var last: ListNode<Int?> = l0
            while (last.next != null) {
                last = last.next
            }
            var it = l0
            while (cycle0-- > 0) {
                if (it == null) {
                    throw RuntimeException("Invalid input data")
                }
                it = it.next
            }
            last.next = it
        }
        if (cycle1 != -1 && l1 != null) {
            var last: ListNode<Int?> = l1
            while (last.next != null) {
                last = last.next
            }
            var it = l1
            while (cycle1-- > 0) {
                if (it == null) {
                    throw RuntimeException("Invalid input data")
                }
                it = it.next
            }
            last.next = it
        }
        val commonNodes: MutableSet<Int?> = HashSet()
        var it = common
        while (it != null && !commonNodes.contains(it.data)) {
            commonNodes.add(it.data)
            it = it.next
        }

        val finalL0 = l0
        val finalL1 = l1
        val result = executor.run<ListNode<Int?>?> { overlappingLists(finalL0, finalL1) }
        if (!(commonNodes.isEmpty() && result == null ||
                        result != null && commonNodes.contains(result.data))) {
            throw TestFailure("Invalid result")
        }
    }

    @JvmStatic
    fun main(args: Array<String>) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "DoListsOverlap.java",
                                object : Any() {}.javaClass.enclosingClass)
                        .ordinal)
    }
}