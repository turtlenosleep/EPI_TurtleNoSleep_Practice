package epi.tree.kt

import epi.BinaryTreeNode
import epi.test_framework.EpiTest
import kotlin.jvm.JvmStatic
import epi.test_framework.GenericTest

object IsTreeSymmetric {

    @JvmStatic
    @EpiTest(testDataFile = "is_tree_symmetric.tsv")
    fun isSymmetric(tree: BinaryTreeNode<Int>?): Boolean {
        if(tree == null) return true;
        return isSymmetric(tree.left,tree.right);
    }

    private fun isSymmetric(left:BinaryTreeNode<Int>? ,right:BinaryTreeNode<Int>?):Boolean{
        if(left== null && right == null) return true;
        if(left==null || right == null) return false;

        if(left.data != right.data) return false;
        return isSymmetric(left.left,right.right) && isSymmetric(left.right,right.left);
    }

    @JvmStatic
    fun main(args: Array<String>) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "IsTreeSymmetric.java",
                                object : Any() {}.javaClass.enclosingClass)
                        .ordinal)
    }
}