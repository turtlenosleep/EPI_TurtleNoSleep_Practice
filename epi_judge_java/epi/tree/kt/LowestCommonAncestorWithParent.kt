package epi.tree.kt

import epi.BinaryTree
import epi.test_framework.EpiTest
import kotlin.Throws
import epi.test_framework.TimedExecutor
import epi.test_framework.BinaryTreeUtils
import epi.test_framework.TestFailure
import kotlin.jvm.JvmStatic
import epi.test_framework.GenericTest
import java.lang.Exception

object LowestCommonAncestorWithParent {
    fun lca(node0: BinaryTree<Int>,
            node1: BinaryTree<Int>): BinaryTree<Int> {
        var n0 = node0;
        var n1 = node1;

        var d0 = getDepth(node0);
        var d1 = getDepth(node1);

        while (d1 < d0) {
            n0 = n0.parent;
            d0--;
        }

        while (d0 < d1) {
            n1 = n1.parent;
            d1--;
        }

        while (n1 != n0) {
            n1 = n1.parent;
            n0 = n0.parent;
        }

        return n1;
    }

    private fun getDepth(node: BinaryTree<Int>): Int {
        var current: BinaryTree<Int>? = node;
        var depth = 0;
        while (current != null) {
            current = current.parent;
            depth++;
        }
        return depth;
    }

    @JvmStatic
    @EpiTest(testDataFile = "lowest_common_ancestor.tsv")
    @Throws(Exception::class)
    fun lcaWrapper(executor: TimedExecutor, tree: BinaryTree<Int>,
                   key0: Int, key1: Int): Int {
        val node0 = BinaryTreeUtils.mustFindNode(tree, key0)
        val node1 = BinaryTreeUtils.mustFindNode(tree, key1)
        val result = executor.run<BinaryTree<Int>?> { lca(node0, node1) }
                ?: throw TestFailure("Result can not be null")
        return result.data
    }

    @JvmStatic
    fun main(args: Array<String>) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "LowestCommonAncestorWithParent.java",
                                object : Any() {}.javaClass.enclosingClass)
                        .ordinal)
    }
}