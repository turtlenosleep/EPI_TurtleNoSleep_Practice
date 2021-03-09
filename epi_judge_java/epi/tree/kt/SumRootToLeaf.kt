package epi.tree.kt

import epi.BinaryTreeNode
import epi.test_framework.EpiTest
import kotlin.jvm.JvmStatic
import epi.test_framework.GenericTest

object SumRootToLeaf {

    @JvmStatic
    @EpiTest(testDataFile = "sum_root_to_leaf.tsv")
    fun sumRootToLeaf(tree: BinaryTreeNode<Int>?): Int {
        return sumRootToLeaf(tree, 0);  
    }

    private fun sumRootToLeaf(node: BinaryTreeNode<Int>?, value: Int): Int {
        if (node == null) return 0;

        val newValue = 2 * value + node.data;
        if (node.left == null && node.right == null) return newValue; // leaf

        return sumRootToLeaf(node.left, newValue) + sumRootToLeaf(node.right, newValue);
    }

    @JvmStatic
    fun main(args: Array<String>) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "SumRootToLeaf.java",
                                object : Any() {}.javaClass.enclosingClass)
                        .ordinal)
    }
}