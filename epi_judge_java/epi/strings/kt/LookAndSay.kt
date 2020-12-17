package epi.strings.kt

import epi.test_framework.EpiTest
import kotlin.jvm.JvmStatic
import epi.test_framework.GenericTest

object LookAndSay {
    @JvmStatic
    @EpiTest(testDataFile = "look_and_say.tsv")
    fun lookAndSay(n: Int): String {
        var result = "1";
        for(i in 1 until n){
            result = lookAndSay(result);
        }
        return result
    }

    private fun lookAndSay(input:String):String{
        val builder = StringBuilder();
        var count =1;
        for( i in 1 until input.length){
            if(input[i-1] != input[i]){
                builder.append(count);
                builder.append(input[i-1]);
                count =1;

            }else{
                count++;
            }
        }

        builder.append(count);
        builder.append(input[input.length-1]);

        return builder.toString();
    }

    @JvmStatic
    fun main(args: Array<String>) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "LookAndSay.java",
                                object : Any() {}.javaClass.enclosingClass)
                        .ordinal)
    }
}