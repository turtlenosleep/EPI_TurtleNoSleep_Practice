package epi.tree.kt

import epi.BinaryTreeNode
import epi.test_framework.EpiTest
import kotlin.jvm.JvmStatic
import epi.test_framework.GenericTest
import kotlin.math.abs
import kotlin.math.max

object IsTreeNotKBalanced {

    class NodeWrapper(val node: BinaryTreeNode<Int>?, val depth: Int, var isBalanced: Boolean) {

    }

    @JvmStatic
    @EpiTest(testDataFile = "is_tree_balanced.tsv")
    fun nodeNotKBalanced(tree: BinaryTreeNode<Int>?, k: Int): BinaryTreeNode<Int>? {
        if (tree == null) return tree;

        return getNotKBalanced(tree, k).node;
    }

    private fun getNotKBalanced(node: BinaryTreeNode<Int>?, k: Int): NodeWrapper {
        if (node == null) return NodeWrapper(node, 0, false);

        val left = getNotKBalanced(node.left, k);
        if (!left.isBalanced) return left;

        val right = getNotKBalanced(node.right, k);
        if (!right.isBalanced) return right;

        val depth = max(left.depth, right.depth) + 1
        val isBalanced = abs(left.depth - right.depth) <= k

        return NodeWrapper(node, depth, isBalanced)
    }


    @JvmStatic
    fun main(args: Array<String>) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "IsTreeBalanced.java",
                                object : Any() {}.javaClass.enclosingClass)
                        .ordinal)
    }
}