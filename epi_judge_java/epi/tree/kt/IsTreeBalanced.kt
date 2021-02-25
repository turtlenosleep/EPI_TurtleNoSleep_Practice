package epi.tree.kt

import epi.BinaryTreeNode
import epi.test_framework.EpiTest
import kotlin.jvm.JvmStatic
import epi.test_framework.GenericTest
import kotlin.math.abs
import kotlin.math.max

object IsTreeBalanced {

    @JvmStatic
    @EpiTest(testDataFile = "is_tree_balanced.tsv")
    fun isBalanced(tree: BinaryTreeNode<Int>?): Boolean {
        if (tree == null) return true;

        val left = getTreeHeight(tree.left);
        if (left == -1) return false;

        val right = getTreeHeight(tree.right);
        if (right == -1) return false;
        return abs(left - right) <= 1;
    }

    private fun getTreeHeight(node: BinaryTreeNode<Int>?): Int {
        if (node == null) return 0;

        val left = getTreeHeight(node.left);
        if (left == -1) return -1;

        val right = getTreeHeight(node.right);
        if (right == -1) return -1;

        if (abs(left - right) > 1) return -1

        return max(left, right) + 1
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