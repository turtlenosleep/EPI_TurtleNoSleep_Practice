package epi.tree.kt

import epi.BinaryTreeNode
import epi.test_framework.EpiTest
import epi.test_framework.GenericTest

object AllPathSumEqualsToK {
    // https://leetcode.com/problems/path-sum-ii/
    @JvmStatic
    @EpiTest(testDataFile = "path_sum.tsv")
    fun findPathSumEqualsKList(tree: BinaryTreeNode<Int>?, k: Int): List<List<BinaryTreeNode<Int>>> {
        val result = mutableListOf<List<BinaryTreeNode<Int>>>()

        createSumList(tree, k, mutableListOf(), result);

        return result;
    }

    private fun createSumList(node: BinaryTreeNode<Int>?, k: Int, tempList: MutableList<BinaryTreeNode<Int>>, result: MutableList<List<BinaryTreeNode<Int>>>) {
        if (node == null) return;

        val newK = k - node.data;

        tempList.add(node);
        if (newK == 0 && node.left == null && node.right == null) result.add(tempList.toList());
        else {
            createSumList(node.left, newK, tempList, result);
            createSumList(node.right, newK, tempList, result);
        }

        tempList.removeAt(tempList.lastIndex);
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