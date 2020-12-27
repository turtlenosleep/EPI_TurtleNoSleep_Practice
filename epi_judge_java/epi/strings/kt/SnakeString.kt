package epi.strings.kt

import epi.test_framework.EpiTest
import kotlin.jvm.JvmStatic
import epi.test_framework.GenericTest

object SnakeString {
    @JvmStatic
    @EpiTest(testDataFile = "snake_string.tsv")
    fun snakeString(s: String?): String {
        if(s.isNullOrEmpty()) return "";

        val builder = StringBuilder();
        var index =1;
        while(index<s.length){
            builder.append(s[index]);
            index +=4;
        }

        index =0;
        while(index<s.length){
            builder.append(s[index]);
            index +=2;
        }

        index = 3;
        while(index<s.length){
            builder.append(s[index]);
            index += 4;
        }

        return builder.toString();
    }

    @JvmStatic
    fun main(args: Array<String>) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "SnakeString.java",
                                object : Any() {}.javaClass.enclosingClass)
                        .ordinal)
    }
}