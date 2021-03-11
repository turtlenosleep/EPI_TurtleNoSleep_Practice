package epi.tree.kt

import epi.BinaryTreeNode
import epi.test_framework.EpiTest
import kotlin.jvm.JvmStatic
import epi.test_framework.GenericTest

object PathSum {

    @JvmStatic
    @EpiTest(testDataFile = "path_sum.tsv")
    fun hasPathSum(tree: BinaryTreeNode<Int>?,
                   remainingWeight: Int): Boolean {
        if (tree == null) return false;

        val newValue = remainingWeight - tree.data;
        if (newValue == 0 && tree.left == null && tree.right == null) return true;
        return hasPathSum(tree.left, remainingWeight - tree.data)
                || hasPathSum(tree.right, remainingWeight - tree.data)
    }

    @JvmStatic
    fun main(args: Array<String>) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "PathSum.java",
                                object : Any() {}.javaClass.enclosingClass)
                        .ordinal)
    }
}