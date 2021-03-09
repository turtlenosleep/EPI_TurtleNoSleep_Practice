package epi.tree.kt

import epi.BinaryTreeNode
import epi.test_framework.EpiTest
import kotlin.jvm.JvmStatic
import epi.test_framework.GenericTest
import kotlin.math.abs
import kotlin.math.max

object IsTreeNotKBalanced {

    class NodeWrapper(val node: BinaryTreeNode<Int>?, val childCount: Int) {

    }

    @JvmStatic
    @EpiTest(testDataFile = "is_tree_balanced.tsv")
    fun nodeNotKBalanced(tree: BinaryTreeNode<Int>?, k: Int): BinaryTreeNode<Int>? {
        if (tree == null) return tree;

        return getNotKBalanced(tree, k).node;
    }

    private fun getNotKBalanced(node: BinaryTreeNode<Int>?, k: Int): NodeWrapper {
        if (node == null) return NodeWrapper(node, 0);

        val left = getNotKBalanced(node.left, k);
        if (left.childCount > k) return left;

        val right = getNotKBalanced(node.right, k);
        if (right.childCount > k) return right;

        val count = left.childCount + right.childCount

        return NodeWrapper(node, count);
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