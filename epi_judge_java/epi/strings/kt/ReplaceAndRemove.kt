package epi.strings.kt

import epi.test_framework.EpiTest
import epi.test_framework.GenericTest
import epi.test_framework.TimedExecutor
import java.util.*

object ReplaceAndRemove {
    @JvmStatic
    fun replaceAndRemove(size: Int, s: CharArray?): Int {
        var index =0;
        var aCount=0;

        if(s == null){
            return 0;
        }

        for(i in 0 until size){
            if(s[i] == 'a') aCount++;
            if(s[i]!='b') s[index++] = s[i];
        }

        val realSize = index + aCount;
        var lastIndex = realSize -1;

        for(i in index-1 downTo 0){
            if(s[i] == 'a'){
                s[lastIndex--] ='d';
                s[lastIndex--] ='d';

            }else{
                s[lastIndex--] = s[i];
            }
        }

        return realSize;
    }

    @JvmStatic
    @EpiTest(testDataFile = "replace_and_remove.tsv")
    @Throws(Exception::class)
    fun replaceAndRemoveWrapper(executor: TimedExecutor, size: Int, s: List<String>): List<String> {
        val sCopy = CharArray(s.size)
        for (i in 0 until size) {
            if (!s[i].isEmpty()) {
                sCopy[i] = s[i][0]
            }
        }
        val resSize = executor.run<Int> { replaceAndRemove(size, sCopy) }
        val result: MutableList<String> = ArrayList()
        for (i in 0 until resSize) {
            result.add(Character.toString(sCopy[i]))
        }
        return result
    }

    @JvmStatic
    fun main(args: Array<String>) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "ReplaceAndRemove.java",
                                object : Any() {}.javaClass.enclosingClass)
                        .ordinal)
    }
}