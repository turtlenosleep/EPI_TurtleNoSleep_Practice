package epi.tree.kt

import epi.BinaryTreeNode
import epi.test_framework.EpiTest
import kotlin.jvm.JvmStatic
import epi.test_framework.GenericTest

object LargestCompleteSubtree {

    var largestSubTree: NodeWrapper? = null;

    class NodeWrapper(var rootNode: BinaryTreeNode<Int>?, var depth: Int, var isComplete: Boolean, var isPerfect: Boolean) {

    }

    @JvmStatic
    @EpiTest(testDataFile = "is_tree_balanced.tsv")
    fun getLargestCompleteSubtree(tree: BinaryTreeNode<Int>?): BinaryTreeNode<Int>? {
        return getCompleteSubTreeNode(tree).rootNode;
    }

    private fun getCompleteSubTreeNode(node: BinaryTreeNode<Int>?): NodeWrapper {
        if (node == null) {
            return NodeWrapper(node, 0, isComplete = true, isPerfect = true);
        }

        val left = getCompleteSubTreeNode(node.left);
        val right = getCompleteSubTreeNode(node.right);

        if (left.depth == right.depth && left.isPerfect && right.isComplete) {
            return NodeWrapper(node, left.depth + 1, isComplete = true, right.isPerfect);
        }

        if (left.depth - right.depth == 1 && left.isComplete && right.isPerfect) {
            return NodeWrapper(node, left.depth + 1, isComplete = true, isPerfect = false);
        }

        val deeperNode = if (left.depth < right.depth) right else left;

        return NodeWrapper(deeperNode.rootNode, deeperNode.depth, isComplete = false, isPerfect = false)

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

