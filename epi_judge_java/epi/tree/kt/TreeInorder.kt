package epi.tree.kt

import epi.BinaryTreeNode
import epi.test_framework.EpiTest
import kotlin.jvm.JvmStatic
import epi.test_framework.GenericTest
import java.util.*

object TreeInorder {

    @JvmStatic
    @EpiTest(testDataFile = "tree_inorder.tsv")
    fun inorderTraversal(tree: BinaryTreeNode<Int>?): List<Int>? {
        val result = mutableListOf<Int>();
        if(tree == null) return result;

        val stack = LinkedList<BinaryTreeNode<Int>>();
        var node:BinaryTreeNode<Int>? = tree;

        while(stack.isNotEmpty() || node!=null){
            while(node!=null){
                stack.push(node);
                node = node.left;
            }

            node = stack.pop();
            result.add(node.data);
            node = node.right;
        }

        return result;
    }

    @JvmStatic
    fun main(args: Array<String>) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "TreeInorder.java",
                                object : Any() {}.javaClass.enclosingClass)
                        .ordinal)
    }

    private class NodeAndState(var node: BinaryTreeNode<Int>,
                               var leftSubtreeTraversed: Boolean)
}