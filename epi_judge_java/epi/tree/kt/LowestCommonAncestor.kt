package epi.tree.kt

import epi.BinaryTreeNode
import epi.test_framework.EpiTest
import kotlin.Throws
import epi.test_framework.TimedExecutor
import epi.test_framework.BinaryTreeUtils
import epi.test_framework.TestFailure
import kotlin.jvm.JvmStatic
import epi.test_framework.GenericTest
import java.lang.Exception

object LowestCommonAncestor {

    private class NodeWrapper() {
        var node0: BinaryTreeNode<Int>? = null;
        var node1: BinaryTreeNode<Int>? = null;
        var lca: BinaryTreeNode<Int>? = null;
    }

    fun lca(tree: BinaryTreeNode<Int>?,
            node0: BinaryTreeNode<Int>?,
            node1: BinaryTreeNode<Int>?): BinaryTreeNode<Int>? {

        return findLCA(tree, node0, node1).lca;
    }

    private fun findLCA(node: BinaryTreeNode<Int>?, node0: BinaryTreeNode<Int>?, node1: BinaryTreeNode<Int>?): NodeWrapper {
        if (node == null) return NodeWrapper();

        val left = findLCA(node.left, node0, node1);
        if (left.lca != null) return left;

        val right = findLCA(node.right, node0, node1);
        if (right.lca != null) return right;

        val nodeWrapper = NodeWrapper();
        when {
            left.node0 != null -> nodeWrapper.node0 = left.node0
            right.node0 != null -> nodeWrapper.node0 = right.node0
            node == node0 -> nodeWrapper.node0 = node0
        };

        when {
            left.node1 != null -> nodeWrapper.node1 = left.node1
            right.node1 != null -> nodeWrapper.node1 = right.node1
            node == node1 -> nodeWrapper.node1 = node1
        };

        if (nodeWrapper.node0 != null && nodeWrapper.node1 != null) {
            nodeWrapper.lca = node;
        }

        return nodeWrapper;
    }

    @JvmStatic
    @EpiTest(testDataFile = "lowest_common_ancestor.tsv")
    @Throws(Exception::class)
    fun lcaWrapper(executor: TimedExecutor,
                   tree: BinaryTreeNode<Int>, key0: Int,
                   key1: Int): Int {
        val node0 = BinaryTreeUtils.mustFindNode(tree, key0)
        val node1 = BinaryTreeUtils.mustFindNode(tree, key1)
        val result = executor.run<BinaryTreeNode<Int>?> { lca(tree, node0, node1) }
                ?: throw TestFailure("Result can not be null")
        return result.data
    }

    @JvmStatic
    fun main(args: Array<String>) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "LowestCommonAncestor.java",
                                object : Any() {}.javaClass.enclosingClass)
                        .ordinal)
    }
}