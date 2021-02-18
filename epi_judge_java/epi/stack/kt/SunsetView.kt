package epi.stack.kt

import epi.test_framework.EpiTest
import kotlin.jvm.JvmStatic
import epi.test_framework.GenericTest
import java.util.*

object SunsetView {

    fun examineBuildingsWithSunset(sequence: Iterator<Int?>?): List<Int>? {
        if (sequence == null) return null;

        val buildingStack = LinkedList<Building>();
        var index = 0;
        while (sequence.hasNext()) {
            val height = sequence.next() ?: 0;

            while (!buildingStack.isEmpty() && height >= buildingStack.peekFirst().height) {
                buildingStack.removeFirst();
            }

            buildingStack.addFirst(Building(index++, height));
        }
        val result = mutableListOf<Int>();

        buildingStack.forEach {
            result.add(it.index);
        }
        return result;
    }

    class Building(var index: Int, var height: Int) {
    }

    @EpiTest(testDataFile = "sunset_view.tsv")
    @JvmStatic
    fun examineBuildingsWithSunsetWrapper(sequence: List<Int?>): List<Int>? {
        return examineBuildingsWithSunset(sequence.iterator())
    }

    @JvmStatic
    fun main(args: Array<String>) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "SunsetView.java",
                                object : Any() {}.javaClass.enclosingClass)
                        .ordinal)
    }
}