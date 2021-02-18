package epi.queue.kt

import epi.BinaryTreeNode
import epi.test_framework.EpiTest
import kotlin.jvm.JvmStatic
import epi.test_framework.GenericTest
import java.util.*

object ZigTreeLevelOrder {

    @JvmStatic
    @EpiTest(testDataFile = "tree_level_order.tsv")
    fun binaryTreeDepthOrder(tree: BinaryTreeNode<Int>?): List<List<Int>>? {
        val result = mutableListOf<MutableList<Int>>();
        if (tree == null) return result;

        val queue = LinkedList<BinaryTreeNode<Int>>();
        queue.offer(tree);

        var backward = false;
        while (!queue.isEmpty()) {
            val size = queue.size;
            val levelList = mutableListOf<Int>();
            for (i in 0 until size) {
                val node = queue.poll();
                levelList.add(node.data);
                node.left?.let { queue.offer(it) };
                node.right?.let { queue.offer(it) };

                if (backward) {
                    levelList.reverse();
                }
            }
            if (levelList.isNotEmpty()) result.add(levelList);
            backward = !backward;
        }

        return result;
    }

    @JvmStatic
    fun main(args: Array<String>) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "TreeLevelOrder.java",
                                object : Any() {}.javaClass.enclosingClass)
                        .ordinal)
    }
}