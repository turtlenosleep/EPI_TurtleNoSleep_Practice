package epi.queue.kt

import epi.BinaryTreeNode
import epi.test_framework.EpiTest
import kotlin.jvm.JvmStatic
import epi.test_framework.GenericTest
import java.util.*

object AverageTreeLevelOrder {

    @JvmStatic
    @EpiTest(testDataFile = "tree_level_order.tsv")
    fun binaryTreeDepthOrder(tree: BinaryTreeNode<Int>?): List<Double> {
        val result = mutableListOf<Double>();
        if (tree == null) return result;

        val queue = LinkedList<BinaryTreeNode<Int>>();
        queue.offer(tree);

        while (!queue.isEmpty()) {
            val size = queue.size;
            var average = 0.0;
            for (i in 0 until size) {
                val node = queue.poll();
                average += node.data;

                node.left?.let { queue.offer(it) };
                node.right?.let { queue.offer(it) };
            }

            average /= size;
            result.add(average);
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